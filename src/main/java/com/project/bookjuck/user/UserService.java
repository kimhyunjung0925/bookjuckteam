package com.project.bookjuck.user;

import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.cscenter.model.ComplaintEntity;
import com.project.bookjuck.cscenter.model.FaqEntity;
import com.project.bookjuck.user.model.UserDto;
import com.project.bookjuck.user.model.UserEntity;
import com.project.bookjuck.user.model.UserVO;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    public UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationFacade authenticationFacade;


    //----------회원가입
    public int join(UserVO vo) {
        //유효성 검사
        //String hashedUpw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
        //이메일합치기하는중

        UserVO voresult = new UserVO();
        vo.setEmail(voresult.totalEmail(vo.getEmail1(), vo.getEmail2()));
        vo.setAddr(vo.totalAddr());

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

        if (!matcher.find()) {
            return 3;
        }

        return result == null ? 1 : 0;
    }

    //-------유저 정보 수정
    public int changeUserInfo(UserEntity entity) {
        entity.setIuser(authenticationFacade.getLoginUserPk());
        try {
            int result = mapper.updUser(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //-------비밀번호
    public int changePw(UserDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        UserEntity dbUser = mapper.selPw(dto);

        if (!passwordEncoder.matches(dto.getCurrentupw(), dbUser.getUpw())) {
            return 2; //현재비밀번호 다름
        }

        String hashedUpw = passwordEncoder.encode(dto.getUpw());
        dto.setUpw(hashedUpw);
        return mapper.updPw(dto);
    }

    //--------회원탈퇴
    public int leaveUser(UserDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        UserEntity dbUser = mapper.selUser(dto);

        if (!passwordEncoder.matches(dto.getCurrentupw(), dbUser.getUpw())) {
            return 2; //현재비밀번호 다름
        }
        String hashedUpw = passwordEncoder.encode(dto.getUpw());
        dto.setUpw(hashedUpw);
        return mapper.delUser(dto);
    }

    //----------내 문의 내역
    public List<ComplaintEntity> selComplain(ComplaintEntity entity){
        entity.setIuser(authenticationFacade.getLoginUserPk());
        return mapper.selComplain(entity);
    }

}
