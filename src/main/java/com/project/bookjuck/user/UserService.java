package com.project.bookjuck.user;

import com.project.bookjuck.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int join(UserEntity entity) {
        //유효성 검사

        //String hashedUpw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
        String hashedUpw = passwordEncoder.encode(entity.getUpw());
        entity.setUpw(hashedUpw);
        try {
            return mapper.insUser(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int idChk(String uid) {
        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        UserEntity result = mapper.selUser(entity);

        return result == null ? 1 : 0;
    }


}
