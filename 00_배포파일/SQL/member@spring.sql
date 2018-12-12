-------------------------------------------------------------------------
--회원테이블생성
-------------------------------------------------------------------------
CREATE TABLE MEMBER(
  USERID VARCHAR2(15) PRIMARY KEY
 ,PASSWORD VARCHAR2(300) NOT NULL
 ,USERNAME  VARCHAR2(20) NOT NULL
 ,GENDER CHAR(1) CHECK (GENDER IN ('M','F'))
 ,AGE NUMBER
 ,EMAIL VARCHAR2(30)
 ,PHONE CHAR(11)  NOT NULL
 ,ADDRESS VARCHAR2(100)
 ,HOBBY VARCHAR2(50)
 ,ENROLLDATE DATE DEFAULT SYSDATE
);

INSERT INTO SPRING.MEMBER VALUES ('admin','admin','관리자','M',25,'admin@test.com','01012345678','서울시 강남구','운동,등산,독서',DEFAULT);
INSERT INTO SPRING.MEMBER VALUES ('user01','pass01','신사임당','F',30,'user01@test.com','01098765432','서울시 서초구','운동,등산',DEFAULT);
INSERT INTO SPRING.MEMBER VALUES ('user02','pass02','홍길동','M',33,'user02@test.com','01012345678','서울시 강남구','독서',DEFAULT);
COMMIT;



