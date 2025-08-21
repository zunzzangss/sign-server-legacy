# sign-server

문서 서명 솔루션 백앤드 프로젝트 입니다.

서식 문서를 업로드하고, 서명자를 지정하여 서명 프로세스를 진행할 수 있습니다.

---

## 기술 스택
- Language : Java 8
- Framework : Spring Framework 4.3.30.RELEASE
  - Spring Web Services, Spring Data JPA, Spring REST Docs 
- Build : Gradle
- DB : H2

---

## 아키텍처 & 설계
### 헥사고날 아키텍처
#### 내부 영역
- `sign` Spring Web Application 초기화 모듈
- `sign-config` Spring Web Application 설정 모듈
- `sign-common` 내부 및 외부 영역 공통 모듈
- `sign-domain` 비즈니스 로직, 벨리데이션 과 정책이 정의된 도메인 영역 모듈
- `sign-application` 유즈케이스 구현, 트랜잭션 관리 등의 응용 영역 모듈
#### 외부 영역
- `sign-adapter-repository-jpa` Jpa Repository 구현체 모듈
- `sign-adapter-storage-filesystem` 파일시스템 스토리지 구현체 모듈
- `sign-adapter-storage-ftp` 파일시스템 FTP 구현체 모듈
- `sign-adapter-web` Web 공통 모듈
- `sign-adapter-web-internal` 내부 API 용 모듈

### 도메인 주도 개발
#### 핵심 도메인
- `Documents` 문서
  - `Document` 문서
  - `Base Document` 기초 문서
    - `Template Document` 서식 문서
  - `Meta Document` 메타 문서
    - `Standard Document` 일반 문서
    - `Group Document` 그룹 문서
  - `Associate` 연관자
    - `Publisher` 발행자
    - `Signer` 서명자
    - `Cc` 참조자
#### 지원 도메인
- `Resources` 리소스
  - `Resource` 리소스
  - `Resource Reference` 리소스 참조
#### 일반 도메인
- `Storage` 스토리지

---

## 주요 기능
- 서식 문서 업로드
- 일반 문서, 그룹 문서 등록 및 발행
- 서명자 서명
- 문서 조회

---

### 통합 테스트
```bash
./gradlew clean test
```
테스트 소스 경로 : sign/src/test

---

### Spring REST Docs

```bash
./gradlew clean build
```
Docs 생성 경로 : docs/adoc/internal/internal.html

---