package com.project.bookjuck.user;

import com.project.bookjuck.user.model.UserEntity;
import com.project.bookjuck.user.model.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity entity);
    UserEntity selUser(UserEntity entity);
    UserEntity selPw(UserEntity entity);
    int updPw(UserEntity entity);

    //주소를 나눠받기위한 개지랄
    UserVO selUser2(UserEntity entity);

    //유저정보 업데이트
    int updUser(UserEntity entity);
}
