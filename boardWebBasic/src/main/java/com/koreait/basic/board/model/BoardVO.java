package com.koreait.basic.board.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder // 딱한번 값을 넣을 수 있다.
public class BoardVO { // vo는 Setter 가 빠져있는 것
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;

    private String writerNm;
    private int cnt;
}
