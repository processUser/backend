package com.koreait.basic.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor // 생성자로만 값을 넣을 수 있다.
public class LoginResult {
    private final int result;
    private final UserEntity loginUser;
}
