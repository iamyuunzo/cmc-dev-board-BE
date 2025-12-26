# CMC BUG 1st Dev – 게시판 서비스 Frontend & E2E

CMC 1기 개발 파트 2주차 개인 과제로  
Spring Boot + Thymeleaf 기반 게시판 프론트엔드 화면을 구현하고,  
Playwright를 활용한 E2E 테스트를 통해 사용자 흐름을 검증한 프로젝트입니다.

---

## 🧭 프로젝트 목표

- SSR 기반 웹 페이지 개발 흐름(HTML → CSS → 테스트) 경험
- Thymeleaf를 활용한 서버 사이드 렌더링 구조 이해
- Bootstrap CSS 프레임워크를 활용한 기본 UI 구성
- Playwright E2E 테스트를 통해 사용자 관점의 동작 검증
- AI 도구를 활용하되, 작성한 코드와 테스트를 직접 이해하는 것에 집중

---

## 🛠 기술 스택

- **Language**: Java 17  
- **Framework**: Spring Boot, Thymeleaf  
- **CSS Framework**: Bootstrap  
- **E2E Test**: Playwright  
- **Build Tool**: Gradle / npm  

---

## 🖥 구현한 화면 목록 (SSR 기반)

> 본 프로젝트의 프론트엔드는  
> CSR(fetch/axios API 호출) 방식이 아닌 **SSR(Server Side Rendering)** 방식으로 구현되었습니다.

### 📄 게시글 목록
- URL: `GET /posts`
- 기능
  - 게시글 목록 렌더링
  - 게시글 클릭 시 상세 페이지 이동
  - 게시글 작성 페이지 이동 버튼 제공

### 📄 게시글 상세
- URL: `GET /posts/{id}`
- 기능
  - 게시글 제목 / 내용 출력
  - 목록으로 이동 버튼 제공

### 📄 게시글 작성
- URL: `GET /posts/new`
- 폼 제출: `POST /posts`
- 기능
  - 제목 / 내용 입력 폼 제공
  - 제출 시 게시글 목록 페이지로 redirect
  - 화면 흐름 구현을 우선하여 실제 저장 로직은 제외

---

## 🎨 CSS 프레임워크 적용 (Bootstrap)

Bootstrap CSS 프레임워크를 활용하여  
기본 UI 컴포넌트를 빠르게 구성했습니다.

### 사용한 주요 컴포넌트
- 버튼: `btn`, `btn-primary`, `btn-secondary`, `btn-outline-secondary`
- 폼: `form-control`, `textarea`
- 레이아웃: `container`, `py-4`, `mb-3`, `d-flex`
- 테이블: `table`, `table-hover`, `table-light`
- 카드: `card`, `card-body`, `shadow-sm`
- 알림: `alert`, `alert-info`

---

## 🧩 API / View Controller 구조 분리

- **View (Thymeleaf)**: `/posts/**`
- **API (JSON)**: `/api/posts/**`

### 분리 배경
초기 구현 시 `/posts/{id}` 경로에서  
API Controller와 View Controller 간 매핑 충돌이 발생했습니다.

이를 해결하기 위해:
- API 엔드포인트에 `/api` prefix를 적용
- View 전용 Controller와 명확히 분리

→ URL 충돌 문제 해결 및 역할 분리 명확화

---

## 🧪 E2E 테스트 (Playwright)

### 테스트 목적
- 단순 기능 확인이 아닌  
  **사용자가 실제로 수행하는 핵심 화면 흐름 검증**

### 테스트 시나리오
1. 게시글 목록 페이지 접속 (`/posts`)
2. 게시글 클릭
3. 게시글 상세 페이지(`/posts/{id}`) 이동 확인
4. 상세 화면 정상 렌더링 확인

### 테스트 코드 위치
```text
e2e/post.spec.js
```
실행 결과

- 테스트 1건 실행
- 실패 없이 통과 (1 passed)

## ▶ 실행 방법
### 서버 실행
```text
./gradlew bootRun
```
- 게시글 목록: http://localhost:8080/posts
- 게시글 상세: http://localhost:8080/posts/1
- 게시글 작성: http://localhost:8080/posts/new

## ▶ E2E 테스트 실행 방법
서버 실행 상태에서 테스트를 수행합니다.
```text
npm install -D @playwright/test
npx playwright install
npx playwright test
```

## 🤖 AI 도구 활용 방식
- Thymeleaf SSR 구조 이해
- Bootstrap 컴포넌트 적용 방향 정리
- Playwright 테스트 코드의 의미를 한 줄씩 확인

## 🏁 개발 흐름 요약

1. Thymeleaf 기반 HTML 화면 구현
2. Bootstrap CSS 프레임워크 적용
3. API / View Controller 구조 분리
4. Playwright 기반 E2E 테스트 작성
5. 테스트 실행 및 사용자 흐름 검증
