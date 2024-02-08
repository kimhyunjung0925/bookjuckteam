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

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @Autowired
    private AuthenticationFacade auth;

//     장바구니 보기
//    @GetMapping("/cart")
//    public String cart() {
//        return "cart/cart";
//    }

    //장바구니 보기
    @GetMapping("/cart")
    public String cartList(Model model, CartEntity entity){
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

//    public class ResponseDto {
//        private boolean success;
//        private String message;
//
//        public ResponseDto() {}
//
//        public ResponseDto(boolean success, String message) {
//            this.success = success;
//            this.message = message;
//        }
//
//        public boolean isSuccess() {
//            return success;
//        }
//
//        public void setSuccess(boolean success) {
//            this.success = success;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

//    @PostMapping("/addCart")
//    @ResponseBody
//    public String addCart(@RequestBody CartDto dto, RedirectAttributes rtta){
////        logger.info("Request to add cart received: {}", dto);
//
//        int iuser = auth.getLoginUserPk();
//
//        if(iuser == 0) {
//
//            rtta.addFlashAttribute(Const.MSG, "로그인 필요.");
//
//            return "redirect:/user/login";
//
//        }
//
//        boolean result = service.addCart(dto.getItemId(), dto.getItemQty(), iuser);
//
//        if (result == true) {
//            return "redirect:/cart";
//        } else {
//            return "redirect:/main";
//        }
//    }

    @PostMapping("/addCart")
    @ResponseBody
    public ResponseEntity<?> addCart(@RequestBody CartEntity entity, Model model){
        int iuser = auth.getLoginUserPk();

        if(iuser == 0) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401 상태 코드 설정
                    .body(Collections.singletonMap("redirectUrl", "/user/login"));
        }

        boolean result = service.addCart(entity.getItemId(), entity.getItemQty(), iuser);

        if (result) {
            return ResponseEntity
                    .ok(Collections.singletonMap("redirectUrl", "/cart/cart"));
        } else {
            return ResponseEntity
                    .ok(Collections.singletonMap("redirectUrl", "/main"));
        }
    }

//    @PostMapping("/cart/addCart")
//    @ResponseBody
//    public ResponseDto addCart(@RequestBody CartDto dto, Authentication auth) {
//        int iuser = auth.getLoginUserPk(); // 예제 코드에서 사용자 인증 정보를 가져오는 방식은 애플리케이션에 따라 다를 수 있습니다.
//        boolean temp = service.addCart(dto.getItemId(), dto.getItemQty(), iuser);
//
//        if (temp) {
//            return new ResponseDto(true, "카트 담기 성공");
//        } else {
//            return new ResponseDto(false, "카트 담기 실패");
//        }
//    }
//    @PostMapping("/cart/addCart")
//    @ResponseBody
//    public boolean addCart(@RequestBody CartDto dto){
//        int iuser = auth.getLoginUserPk();
//        Boolean temp = service.addCart(dto.getItemId(), dto.getItemQty(), iuser);
//
////        result.put("result",temp);
//        return temp;
//    }

//  public String addCart(Integer itemId, Integer itemQty, RedirectAttributes rtta) {
//        int iuser = auth.getLoginUserPk();
//        if (iuser == 0) {
//            rtta.addFlashAttribute(Const.MSG, "로그인이 필요합니다.");
//            return "redirect:/user/login";
//        }
//
//        Boolean result = service.addCart(itemId, itemQty, iuser);
//
//        return "cart/cart";
//    }


//    // 장바구니 담기 - 이미 담긴 상품일 경우 개수를 증가시킨다.
//    @PostMapping("/cart/add")
//    public String add(Integer pno, Integer count, Principal principal) {
//        Boolean result = service.add(pno, count, principal.getName());
//        if (result == false)
//            return "redirect:/product/read?pno=" + pno;
//        return "redirect:/cart/list";
//    }
//
//    // 장바구니 물품 개수 증가
//    @PatchMapping("/cart/increase/{pno}")
//    public ResponseEntity<Integer> increase(@PathVariable Integer pno, Principal principal) {
//        Integer count = service.increase(pno, principal.getName());
//        if (count <= 0)
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(count);
//        return ResponseEntity.ok(count);
//    }
//
//    // 장바구니 물품 개수 감소
//    @PatchMapping("/cart/decrease/{pno}")
//    public ResponseEntity<Integer> decrease(@PathVariable Integer pno, Principal principal) {
//        Integer count = service.decrease(pno, principal.getName());
//        if (count <= 0)
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(count);
//        return ResponseEntity.ok(count);
//    }
//
//    // 장바구니에서 삭제
//    @PostMapping("/cart/delete")
//    public String delete(CartDeleteDto dto, Principal principal) {
//        service.delete(dto, principal.getName());
//        return "redirect:/cart/cart";
//    }
}
