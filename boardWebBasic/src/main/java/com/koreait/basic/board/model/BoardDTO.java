package com.koreait.basic.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int page; // 몇번째 패이지 인지
    private int startIdx;
    private int rowCnt; // rowCnt: 5,  16개 값이 있다면 4페이지가 나와야한다.

    private int searchType;
    private String searchText;

}
