Spring MVC 기반 게시판 프로젝트
==========================

# 1.프로젝트 소개

### 1-1 프로젝트를 한 이유?
개인 프로젝트를 한다면 web에서 기본적으로 사용되는 crud를 할 줄 알아야한다고 생각하여 게시판 프로젝트를 해보았다.  
처음부터 끝까지 혼자서 기획, db설계, erd설계, 프로젝트 진행까지 하게 되어 뜻깊은 시간이 된 프로젝트이다.

### 1-2 주요 기능
- 게시판에서는 게시글 및 댓글 작성, 조회, 수정, 삭제 crud기능 구현
- 사용자를 식별하기 위해 회원가입 및 로그인시 유효성 검사 및 중복 검사
- 게시글을 10개씩 조회 되도록 페이징 구현
- 원하는 게시글을 제목으로 검색해 볼 수 있도록 구현



# 2.사용기술

### 백엔드
- Java 17
- Spring boot 3.1.7
- Spring Web MVC
- Spring Data Jpa

### DB
- Mysql 8.0.36

### 프론트엔드
- HTML/CSS
- Bootstrap
- Javascript, Ajax
- Thymleaf

### Build tool
- Gragle 8.5

### 기타
- Lombok
- slf4j
