package com.project.bookjuck.cscenter.model;

import com.project.bookjuck.user.model.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComplaintEntity extends UserEntity {
    private int icom;
    private String com_email;
    private int com_cate;
    private String com_ctnt;
    private int iuser;

    private String filename;
    private String filepath;
}
