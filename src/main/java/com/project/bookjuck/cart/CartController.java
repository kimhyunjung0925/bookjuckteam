package com.project.bookjuck.cart;

import com.project.bookjuck.Const;
import com.project.bookjuck.cart.model.CartDto;
import com.project.bookjuck.cart.model.CartInfo.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.bookjuck.AuthenticationFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @Autowired
    private AuthenticationFacade auth;

    //장바구니 보기
    @GetMapping("/cart")
    public String cartList(Model model, CartEntity entity) {
        //세션값
//        int sessionId = Integer.parseInt(session.getAttribute("sessionId").toString());
//        entity.setIuser(authenticationFacade.getLoginUserPk());
//        Member member = memberService.findMember(sessionId);
//        Subscribe subscribe = subscribeService.findSubscribe(sessionId);
//        List<Cart> cartList = CartService.myCartList (sessionId);

//        List<CartDto> itemList = new ArrayList<>();

        int iuser = auth.getLoginUserPk();
        List<CartEntity> list = service.selItemCart(iuser);

        model.addAttribute("list", list);
////        model.addAttribute("item", service.selItemCart(cartDto));
        return "cart/cart";
    }

    //장바구니 추가
    @PostMapping("/addCart")
    @ResponseBody
    public  ResponseEntity<?> addCart(@RequestBody CartEntity entity, Model model) throws Exception {
        //현재 로그인한 사람 정보 저장
        int iuser = auth.getLoginUserPk();

        //장바구니 추가 클릭시 해당 아이템 해당 유저에게 있는지 확인.
        int findResult = service.findItemCart(entity.getItemId(), iuser);

        //있을경우(true)
        if (findResult != 0){
            //updateCart 처리
            boolean result = service.updCart(entity.getItemId(), entity.getItemQty(), iuser);
            if (result) {
                return ResponseEntity.ok(Collections.singletonMap("state", "update"));
            }
            // 실패 시 메시지 전달
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Failed to add to cart"));
        //없을경우(false)
        } else {
            //addCart 처리
            boolean result = service.addCart(entity.getItemId(), entity.getItemQty(), iuser);

            if (result) {
                return ResponseEntity.ok(Collections.singletonMap("state", "insert"));
//                return ResponseEntity.ok(Collections.singletonMap("redirectUrl", "/cart/cart"));
            }
            // 실패 시 메시지 전달
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Failed to add to cart"));
        }
    }


    //장바구니 수량변경
    @PostMapping("/updCart")
    @ResponseBody
    public  ResponseEntity<?> updCart(@RequestBody CartEntity entity, Model model) throws Exception {
        //현재 로그인한 사람 정보 저장
        int iuser = auth.getLoginUserPk();

        boolean result = service.updCart(entity.getItemId(), entity.getItemQty(), iuser);
        if (result) {
            return ResponseEntity.ok(Collections.singletonMap("redirectUrl", "/cart/cart"));
        }
        // 실패 시 메시지 전달
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Failed to add to cart"));

    }

    //장바구니 삭제
    @PostMapping("/delCart")
    @ResponseBody
    public  ResponseEntity<?> delCart(@RequestBody CartDto dto, Model model) throws Exception {
        //현재 로그인한 사람 정보 저장
        int iuser = auth.getLoginUserPk();

        // 서비스 메소드 호출
        List<Integer> deletedItemIds = service.delCart(dto.getItemIds(), iuser);

        if (!deletedItemIds.isEmpty()) {
            return ResponseEntity.ok(Collections.singletonMap("redirectUrl", "/cart/cart"));
        }
        // 실패 시 메시지 전달
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Failed to add to cart"));

    }

}

