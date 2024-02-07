package com.project.bookjuck.cart;

import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.book.model.BookDto;
import com.project.bookjuck.cart.model.CartDto;
import com.project.bookjuck.cart.model.CartInfo.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartMapper mapper;

    @Autowired
    private AuthenticationFacade auth;

    public List<CartEntity> selItemCart(int iuser) {
        List<CartEntity> CartEntityList = mapper.selItemCart(iuser);

//        for (CartEntity list:CartEntityList) {
//
//            String title = list.getTitle();
//            Integer priceStandard = list.getPriceStandard();
//            Integer itemQty = list.getItemQty();
//
//            list.setTitle(title);
//            list.setPriceStandard(priceStandard);
//            list.setItemQty(itemQty);
//        }
////        return mapper.selItemCart(iuser);
//        return  CartEntityList;
        return mapper.selItemCart(iuser);
    }

    // 장바구니 상품 추가
    public Boolean addCart(Integer itemId, Integer itemQty, int iuser) {
//    public Boolean add(Integer pno, Integer count, String loginId) {
//        CartItem cartItem = cartDao.findByUsernameAndPno(loginId, pno);
//        if (cartItem != null) {
//            Product product = productDao.findById(pno);
//            if ((cartItem.getCount() + count) > product.getStock())
//                return false;
//            cartDao.increase(new CartItem(loginId, pno, count));
//        } else {
//            cartDao.save(new CartItem(loginId, pno, count));
//        }
        return true;
    }

    // 장바구니 읽기
//    public List<CartDto> read(String loginId) {
//        List<CartItem> cartItems = cartDao.findByUsername(loginId);
//        List<CartDto> cartDtos = new ArrayList<>();
//        for (CartItem cartItem : cartItems) {
//            Product p = productDao.findById(cartItem.getPno());
//            CartDto cartDto = new CartDto(p.getPno(), p.getVendor(), p.getName(), imagePath+p.getImagename(), p.getPrice(), cartItem.getCount());
//            cartDtos.add(cartDto);
//        }
//        return cartDtos;
//    }
//

//
//    // 장바구니 상품 개수 증가
//    public Integer increase(Integer pno, String loginId) {
//        Product product = productDao.findById(pno);
//        CartItem cartItem = cartDao.findByUsernameAndPno(loginId, pno);
//        if ((cartItem.getCount()+1) >= product.getStock()) {
//            return -1;
//        }
//        cartDao.increase(new CartItem(loginId, pno, 1));
//        return cartItem.getCount() + 1;
//    }
//
//    // 장바구니 상품 개수 감소
//    public Integer decrease(Integer pno, String loginId) {
//        Product product = productDao.findById(pno);
//        CartItem cartItem = cartDao.findByUsernameAndPno(loginId, pno);
//        if (cartItem.getCount() <= 1)
//            return -1;
//        cartDao.decrease(new CartItem(loginId, pno, 1));
//        return cartItem.getCount() - 1;
//    }
//
//    // 장바구니에서 상품 삭제
//    public void delete(CartDeleteDto dto, String loginId) {
//        cartDao.delete(dto.getPnos(), loginId);
//    }

}
