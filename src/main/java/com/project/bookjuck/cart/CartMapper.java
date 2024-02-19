package com.project.bookjuck.cart;

import com.project.bookjuck.cart.model.CartDto;
import com.project.bookjuck.cart.model.CartInfo.CartEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    // 해당 사용자의 장바구니 내역
    List<CartEntity> selItemCart(int iuser);
    // 장바구니 추가시 장바구니 내의 해당유저의 해당 아이템 존재유무
    int findItemCart(int itemId, int iuser);
    //장바구니 새로 추가
    void insItemCart(int itemId, int iuser, int itemQty);
    //장바구니 수량 추가
    void updItemCart(int itemId, int iuser, int itemQty);

    //장바구니 삭제
    int delItemCart(@Param("itemIds") List<Integer> itemIds, @Param("iuser") int iuser);

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
