package com.project.bookjuck.cart;

import com.project.bookjuck.Const;
import com.project.bookjuck.book.model.BookDto;
import com.project.bookjuck.cart.model.CartDto;
import com.project.bookjuck.cart.model.CartInfo.CartEntity;
import com.project.bookjuck.cscenter.model.NoticeEntity;
import com.project.bookjuck.user.model.UserDto;
import com.project.bookjuck.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.bookjuck.AuthenticationFacade;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/addCart")
    public String addCart(Integer itemId, Integer itemQty, RedirectAttributes rtta) {
        int iuser = auth.getLoginUserPk();
        if (iuser == 0) {
            rtta.addFlashAttribute(Const.MSG, "로그인이 필요합니다.");
            return "redirect:/user/login";
        }

        Boolean result = service.addCart(itemId, itemQty, iuser);

        return "cart/cart";
    }
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
