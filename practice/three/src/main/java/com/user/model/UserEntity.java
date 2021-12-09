package com.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity { // DB와 1:1 대응
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private int gender;
    private String rdt;
}
