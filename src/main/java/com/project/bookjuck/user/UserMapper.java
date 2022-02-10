package com.project.bookjuck.user;

import com.project.bookjuck.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity entity);
    UserEntity selUser(UserEntity entity);
}
