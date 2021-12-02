package com.koreait.basic.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardEntity { // db와 1:1 대응 됨
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;
}
