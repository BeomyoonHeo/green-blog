# MyBatis DB연결 세팅

### 테이블 chracter set => utf-8로 통일하기
- alter table 테이블명 convert to character set utf8;

### 페이징 개수 변경법
- boards.xml에 paging 부분에 ceil(count(*)/5) totalPage
- boards.xml에 findAll 부분에 FETCH NEXT 5 ROWS ONLY
- boardsService에 게시글 목록보기() 메서드에 int startNum = page * 5;

### 설정방법
- MyBatisConfig 파일 필요
- resources/mapper/*.xml 파일 필요
- Users 엔티티 필요
- UsersDao 인터페이스 생성 필요

### MariaDB 사용자 생성 및 권한 주기
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```

### 테이블 생성
```sql
USE greendb;

create table users(
    id int primary KEY auto_increment,
    username varchar(20) UNIQUE,
    password varchar(20),
    email varchar(50),
    createdAt TIMESTAMP
);

create table boards(
    id int primary KEY auto_increment,
    title varchar(150),
    content longtext,
    usersId int,
    createdAt TIMESTAMP
);

create table loves(
    id int primary KEY auto_increment,
    usersId int,
    boardsId INT,
    createdAt TIMESTAMP,
    UNIQUE uk_loves (usersId,boardsId)
);
```

### 더미데이터 추가
```sql
insert into users(username, password, email, createdAt) values('ssar', '1234', 'ssar@nate.com', NOW());
insert into users(username, password, email, createdAt) values('cos', '1234', 'cos@nate.com', NOW());
insert into users(username, password, email, createdAt) values('hong', '1234', 'hong@nate.com', NOW());
COMMIT;

SELECT * FROM users;
```

### 페이징 쿼리
```sql
SELECT COUNT(*) totalCount,
ceil(COUNT(*)/3) totalPage,
1 currentPage,
case 
WHEN  #{page}=0
then '1' ELSE '0' END first,
case 
WHEN  #{page}=ceil(COUNT(*)/3)-1
then '1' ELSE '0' END last
FROM boards;
```