# sign-server

문서 서명 솔루션 백앤드 프로젝트 입니다.

문서를 업로드하고, 서명자를 지정하여 서명 프로세스를 진행할 수 있습니다.

---

## 기술 스택
- Java 8
- Spring 4.3.30.RELEASE, Spring Web Services, Spring Data JPA, Spring REST Docs 
- Gradle 8.14.3
- H2

---

## 아키텍처
- 헥사고날 아키텍처
- 도메인 주도 개발

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