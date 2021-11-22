package com.koreait.board2.model;

public class BoardParamVO {
    private int recordCnt; // 리스트에 표시할 갯수
    private int sIdx; // 패이지별 리스트 갯수
    private int page;

    public int getRecordCnt() {
        return recordCnt;
    }

    public void setRecordCnt(int recordCnt) {
        this.recordCnt = recordCnt;
    }

    public int getsIdx() {
        return sIdx;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.sIdx = (page -1) * recordCnt;
    }
}
