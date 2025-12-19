# CMC BUG 1st Dev – 게시판 서비스 Backend

CMC 1기 개발 파트 1주차 개인 과제로  
Spring Boot 기반 게시판 서비스 백엔드를 구현하고,  
테스트 및 AI 코드리뷰를 통해 코드 품질을 개선한 프로젝트입니다.

---

## 🧭 프로젝트 목표

- 단순 기능 구현이 아닌 **설계 → 구현 → 테스트 → 개선**의 개발 흐름 경험
- ERD 기반 도메인 설계 역량 강화
- JUnit + JaCoCo를 활용한 테스트 코드 작성
- AI 코드리뷰를 통한 설계/아키텍처 개선 경험

---

## 🛠 기술 스택

- **Language**: Java 17  
- **Framework**: Spring Boot  
- **Build Tool**: Gradle  
- **Database**: H2 (In-memory)  
- **ORM**: Spring Data JPA  
- **Test**: JUnit 5  
- **Coverage**: JaCoCo  

---

## 📁 패키지 구조

```text
com.cmc.board
 ├─ auth
 ├─ user
 │   ├─ controller
 │   ├─ service
 │   ├─ domain
 │   └─ repository
 ├─ post
 │   ├─ controller
 │   ├─ controller.dto
 │   ├─ service
 │   ├─ domain
 │   └─ repository
 ├─ comment
 ├─ bookmark
 ├─ category
 └─ global
     ├─ config
     ├─ error
     └─ common
```
- 도메인 단위로 패키지를 분리하여 응집도를 높이고 Controller / Service / Repository 역할을 명확히 분리했습니다.

## 🗂 ERD 설계

- DBML 기반으로 ERD 설계
- 게시글 / 댓글 / 대댓글 / 북마크 / 카테고리 요구사항 반영

### 📄 관련 문서

- docs/erd.dbml
- docs/erd.md

## ✨ 주요 기능 (Step 3)

- 게시글 생성 API
- JPA 연관관계 기반 Entity 매핑
- Service → Repository 계층 분리
- Postman을 통한 API 동작 검증

### 게시글 생성 API 예시
```http
POST /posts
Content-Type: application/json
```
```json
{
 "title": "첫 번째 게시글",
  "content": "게시글 생성 테스트"
}
```

## 🧪 테스트 (Step 4)

### 단위 테스트
- PostService 대상 유닛 테스트 작성
- Given / When / Then BDD 스타일 적용

- 테스트 실행
./gradlew test

- 테스트 커버리지
JaCoCo를 통해 커버리지 리포트 생성

- 📍 리포트 위치
build/reports/jacoco/test/html/index.html

테스트 실행 결과: 성공률 100%
<img width="1072" height="530" alt="image" src="https://github.com/user-attachments/assets/79951dfd-fb2c-4544-abc6-7707fb9b66b3" />


## 🔍 AI 코드리뷰 & 리팩토링 (Step 5)

AI 코드리뷰를 통해 다음 관점에서 개선을 진행했습니다.

- SOLID 원칙
- 이펙티브 자바
- 레이어드 아키텍처

### 주요 개선 내용
- Service의 책임을 "게시글 생성"으로 한정 (SRP 준수)
- Controller에서 도메인 객체 생성 책임 축소
- 도메인 엔티티가 생성 시간 책임을 직접 가지도록 개선
- DTO 분리를 통한 역할 명확화

📄 코드리뷰 정리 문서
docs/review.md

## 🏁 개발 프로세스 요약
1. 요구사항 분석 및 패키지 구조 설계
2. ERD 설계 (DBML)
3. Spring Boot API 구현
4. JUnit 기반 유닛 테스트 작성
5. JaCoCo 테스트 커버리지 확인
6. AI 코드리뷰 및 리팩토링

## 📌 실행 방법
./gradlew bootRun


- 서버 주소: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
