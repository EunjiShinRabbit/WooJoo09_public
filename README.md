# :sparkles:우주공구:sparkles:
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/Javascript-F7DF1E?style=flat-square&logo=Javascript&logoColor=white"/> <img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=React&logoColor=white"/> <img src="https://img.shields.io/badge/React Native-09D3AC?style=flat-square&logo=Create React App&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/Sass-CC6699?style=flat-square&logo=Sass&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=firebase&logoColor=white"/> 

> 우리 주변의 공동 구매, 공동 구매 중개 플랫폼  
> http://woojoo09.site/  
> https://play.google.com/store/apps/details?id=com.woojoo09  



## :stars: 목차

- [프로젝트 소개](#프로젝트-소개)
- [프로젝트 주요 기능](#프로젝트-주요-기능)
- [개발 기간](#개발-기간)
- [개발 환경](#개발-환경)
- [업데이트 내역](#업데이트-내역)

### :stars: 프로젝트 소개
우주공구는 우리 주변의 공동구매, 우주만큼 많은 공동구매라는 이름으로  
우리 동네(지역)의 공동구매를 쉽게 가능하게 하고,   
다른 지역간의 택배거래도 지원하여 많은 상품을 공동구매 할 수 있는   
공동구매 중개 플랫폼입니다   

![](../header.png)

### :stars: 프로젝트 주요 기능
- 사이트 자체 회원가입시 문자 및 이메일 인증(Naver Cloud Platform Sens api, Java Mail api)
- jwt(Java Web Token)과 Password Encoder(Spring Security)를 이용해 보안 성능 향상
- 모바일(최대 767px)과 태블릿(최대 1023px) 환경에서도 편하게 사용 가능하도록 미디어 쿼리 적용
- 사용자 편의를 위한 게시글 12개 단위 조회(Sping JPA Paging) 및 페이지네이션(직접 구현)
- 각 공동구매를 카테고리별, 동네별, 추천순, 최신순, 마감임박순 등으로 확인 가능
- 공동구매 글 작성시 이미지 업로드(firebase) 및 지도 확인 가능(카카오 지도 api)
- 실시간 채팅 및 채팅 읽음 여부 알림(WebSocket)
- Admin 페이지에서 편리하게 각 공동구매와 회원을 관리

### :stars: 팀원
- [:rabbit2:신은지](https://github.com/eunjishinrabbit)
- [:dolphin:이희주](https://github.com/brownsally2)
- [:hatched_chick:이한솔](https://github.com/solsol529)

### :stars: 개발 기간
- 기획 : 2022.11.21 - 2022.12.02
- 개발 : 2022.12.03 - 2022.12.23

### :stars: 개발 환경
- OS : Window 10
- IDE : VS Code, IntelliJ
- Language : Java, Javascript
- FrontEnd : HTML/CSS
- Library : ReactJS
- DB : mySql, Oracle
- Server : Tomcat

### :stars: 업데이트 내역
- 0.0.0 (2022.12.20)
  - 작업 진행 중
- 0.0.1 (2022.12.26)
  - 1차 완성 및 구글 플레이스토어 업로드
  
### :stars: 정보
- 사용 이미지 출처(https://www.flaticon.com/)

### :stars: 빌드 전 사전 작업
- application.properties 의 mysql 계정 정보 입력, java mail api용 메일 계정 정보 입력
- service/sens_sms sendSms 의 네이버 클라우드 플랫폼 api 인증 정보 입력
- woojoo09_front index.html 의 카카오 api 인증 정보 입력
- woojoo09_front api/firebase firebaseconfig 의 firebase api 인증 정보 입력

### :stars: 빌드

```sh
./gradlew build -x test
```
```sh
java -jar build/libs/WooJoo09-0.0.1-SNAPSHOT.jar
```
터미널에서 테스트 제외하고 gradle 빌드 실행한 후 위의 명령어로 파일 실행



