acc : access : number(1)
id : id : varchar2(24) PK
pw : password : varchar2(24) not null
name : name : varchar2(24) not null
birth : birth(920803-1 ****** ) : varchar2(8) not null
email : email : varchar2(30) unique
adrs1 : address1 : varchar2(200)
adrs2 : address2 : varchar2(200)
phone : phone : varchar2(11) not null - 없이
signDate : signDate  : date default sysdate
checkSN : checkShopNumber : number(5)

--create문
create table sign(
acc number(1),
id varchar2(24) constraint sign_id_pk primary key,
pw varchar2(24) constraint sign_pw_nn not null,
name varchar(24) constraint sign_name_nn not null,
birth varchar2(8) constraint sign_birth_nn not null,
email varchar2(30) constraint sign_email_uniq unique,
adrs1 varchar2(200),
adrs2 varchar2(200),
phone varchar2(11) constraint sign_phone_nn not null,
signDate date default sysdate,
checkSN number(5)
);

--drop 테이블
--drop table sign

--확인용
select * from SIGN

--더미데이터
insert into sign values (1,'aaa','aaa','강기훈','920803-1','aaa@naver.com','창원시 대원동','호호테크','01057895800', null, null)

--board table 생성
create table tbl_board(
bno number primary key,
title varchar2(102) not null,
content varchar2(3000) not null,
writer varchar2(30) not null,
regidate date default sysdate,
updatedate date default sysdate,
viewcnt number(5) default 0
);

-- board 시퀀스 생성
create sequence seq_board;

--댓글테이블 생성
create table tbl_reply(
rno number primary key,
bno number,
replytext varchar2(3000) not null,
replyer varchar2(30) not null,
regidate date default sysdate,
updatedate date default sysdate,
constraint fk_reply foreign key(bno) references tbl_board(bno)
);

--reply 시퀀스 생성
create sequence seq_reply;

--select문
select * from tbl_board
select * from tbl_reply

--파일업로드용 테이블 생성
create table tbl_attach(
id number(8) primary key,
bno number references tbl_board(bno),
filename varchar2(150) not null,
regidate date default sysdate
);

--파일업로드용 시퀀스 생성
create sequence seq_attach;