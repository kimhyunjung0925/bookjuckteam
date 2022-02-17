package com.project.bookjuck.user;

import com.project.bookjuck.user.model.UserEntity;
import com.project.bookjuck.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    public UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int join(UserVO vo) {
        //유효성 검사
        //String hashedUpw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
        //이메일합치기하는중

        UserVO voresult = new UserVO();
        vo.setEmail(voresult.totalEmail(vo.getEmail1(), vo.getEmail2()));

        String hashedUpw = passwordEncoder.encode(vo.getUpw());
        vo.setUpw(hashedUpw);
        try {
            return mapper.insUser(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //-------- 아이디 중복 체크
    public int idChk(String uid) {
        UserEntity entity = new UserEntity();
        entity.setUid(uid);
        UserEntity result = mapper.selUser(entity);

        Pattern pattern = Pattern.compile("^[a-z]+[a-z0-9]{4,9}$");
        Matcher matcher = pattern.matcher(uid);

        if(!matcher.find()){
            return 3;
        }

        return result == null ? 1 : 0;
    }



}
