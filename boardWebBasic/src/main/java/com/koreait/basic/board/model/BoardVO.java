package com.koreait.basic.board.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder // 딱한번 값을 넣을 수 있다.
public class BoardVO { // vo는 Setter 가 빠져있는 것  // select 후 값을 뿌릴때 이용한다.
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;

    private int cnt;

    private String writerNm;
    private String profileImg;
}
