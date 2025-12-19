# 패키지 구조 설계 (Step 1)

## 1. 설계 원칙

- 도메인별 응집도를 높이기 위해 기능 단위로 패키지 분리
- Controller, Service, Repository 계층을 명확히 구분
- 공통 관심사는 global 패키지로 분리
- 변경 가능성이 높은 영역과 낮은 영역을 분리

## 2. 전체 패키지 구조
```commandline
com.cmc.board
├─ (1) auth (로그인/세션/권한)
│ ├─ controller
│ ├─ service
│ └─ domain
|
├─ (2) user (사용자 정보/권한/식별)
│ ├─ controller
│ ├─ service
│ ├─ domain
│ └─ repository
|
├─ (3) post (게시판 핵심 Domain 1)
│ ├─ controller
│ ├─ service
│ ├─ domain
│ └─ repository
|
├─ (4) comment (게시판 핵심 Domain 2)
│ ├─ controller
│ ├─ service
│ ├─ domain
│ └─ repository
|
├─ (5) bookmark (게시판 핵심 Domain 3)
│ ├─ service
│ ├─ domain
│ └─ repository
|
├─ (6) category (게시판 핵심 Domain 4)
│ ├─ service
│ ├─ domain
│ └─ repository
|
└─ global
├─ config
├─ error
└─ common
```

## 3. 패키지 분리 이유

- 도메인 단위 패키지는 기능 확장 시 변경 범위를 최소화
- Controller-Service-Repository 분리는 테스트 및 유지보수 용이
- global 패키지는 횡단 관심사를 모아 중복 방지
- bookmark와 category는 게시글의 부가 기능 성격으로 판단하여 별도의 Controller 없이 게시글 도메인을 통해 접근하도록 설계
