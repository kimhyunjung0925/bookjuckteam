package com.project.bookjuck.cart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.book.model.ApiSearchDto;
import com.project.bookjuck.book.model.BookDto;
import com.project.bookjuck.book.model.bookinfo.Authors;
import com.project.bookjuck.book.model.bookinfo.BookEntity;
import com.project.bookjuck.cart.model.CartDto;
import com.project.bookjuck.cart.model.CartInfo.CartEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartMapper mapper;

    @Autowired
    private AuthenticationFacade auth;

    public List<CartEntity> selItemCart(int iuser) {
        List<CartEntity> CartEntityList = mapper.selItemCart(iuser);

        return mapper.selItemCart(iuser);
    }

    // 장바구니 추가시 해당아이템이 해당 유저에게 있는지 확인
    public int findItemCart(Integer itemId, int iuser) {
        int result = mapper.findItemCart(itemId, iuser);
        return result;
    }

    // 장바구니 상품 새로 추가
    public boolean addCart(Integer itemId, Integer itemQty, int iuser) {
        try {
            mapper.insItemCart(itemId, iuser, itemQty );
            return true; // 성공적으로 처리되었을 경우
        } catch (Exception e) {
            System.out.println(e);
            return false; // 처리 중 예외 발생 시
        }
    }

    // 장바구니 상품 수량 추가
    public boolean updCart(Integer itemId, Integer itemQty, int iuser) {
        try {
            mapper.updItemCart(itemId, iuser, itemQty );
            return true; // 성공적으로 처리되었을 경우
        } catch (Exception e) {
            System.out.println(e);
            return false; // 처리 중 예외 발생 시
        }
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
