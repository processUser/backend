CREATE TABLE t_user (
    iuser INT unsigned PRIMARY KEY AUTO_INCREMENT,
    uid VARCHAR(20) unique NOT NULL,
    upw VARCHAR(50) NOT NULL,
    nm VARCHAR(5) NOT NULL,
    gender INT UNSIGNED CHECK ( gender IN (0, 1) ),
    rdt DATETIME DEFAULT NOW()
);


CREATE TABLE t_board(
    iboard INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    ctnt VARCHAR(2000) NOT NULL,
    writer INT UNSIGNED NOT NULL COMMENT '작성자 iuser',
    FOREIGN KEY (writer) REFERENCES t_user(iuser),
    rdt DATETIME DEFAULT NOW(),
    mdt DATETIME DEFAULT NOW()
);