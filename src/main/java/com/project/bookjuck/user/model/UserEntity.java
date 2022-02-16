package com.project.bookjuck.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private String addr;
    private String email;
    private String birth;
    private String ph;
    private int gender;


}
