package com.project.bookjuck.cart;

import com.project.bookjuck.cart.model.CartDto;
import com.project.bookjuck.cart.model.CartInfo.CartEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

//    List<CartDto> sel(CartDto dto);

    // 장바구니에 저장
//    void save(CartDto itemId);

    // 해당 사용자의 장바구니 찾기
    List<CartEntity> selItemCart(int iuser);

    // 장바구니 내의 상품 찾기
//    CartDto findByUsernameAndPno(Integer iuser, Integer itemId);
//
//    // 장바구니 상품 개수 증가
//    void increase(CartDto itemId);
//
//    // 장바구니 상품 개수 감소
//    void decrease(CartDto itemId);
//
//    // 장바구니에서 삭제
//    void delete(List<Integer> itemId, Integer iuser);
}
