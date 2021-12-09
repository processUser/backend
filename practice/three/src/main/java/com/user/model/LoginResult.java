package com.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 생성자로 값을 넣는 것.
public class LoginResult {
    private int result;
    private UserEntity loginUser;
}
