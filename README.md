<a href="https://play.google.com/store/apps/details?id=inu.appcenter.bjj_android" target="_blank">
<img width="1024" height="500" alt="checkmate 판넬" src="https://github.com/user-attachments/assets/4814ab3f-6b18-4e54-9ad2-436904e23974" />
</a>

<br/>
<br/>

# 1. Project Overview
> CHECKMATE는 국내 외국인 유학생이 아르바이트를 신청할 때 복잡한 비자 규정과 제출 서류를 쉽고 정확하게 확인할 수 있도록 지원하는 서비스입니다.
  사용자의 비자 종류, TOPIK 등급, 학위 과정, 대학 인증 여부, 근무 업종 등의 정보를 바탕으로 근로 가능 여부를 자동으로 판별하며, 필요한 제출 서류를 안내하고 신청서를 PDF로 생성하여 이메일로 전송합니다.
  또한 다국어 지원을 통해 한국어에 익숙하지 않은 외국인 유학생도 쉽고 편리하게 서비스를 이용할 수 있도록 설계되었습니다.


<br/>
<br/>

# 2. Team Members
| 고지윤 | 안찬호 | 이준희 | 한선영 |
|:------:|:------:|:------:|:------:|
| <a href="https://github.com/jiyunio"><img src="https://avatars.githubusercontent.com/u/146628970?v=4" width="100"></a> | <a href="https://github.com/Junhee8649"><img src="https://avatars.githubusercontent.com/u/67620918?v=4" width="100"></a> | <a href="https://github.com/ACH1002"><img src="https://avatars.githubusercontent.com/u/103422938?v=4" width="100"></a> | <img src="https://cdn.simpleicons.org/figma" width="100" alt="Figma"> |
| BE, 기획 | FE | FE, 팀장 | Design, 기획 |

## Special Thanks
프로젝트 진행 중 도움을 주신 분들입니다.
- [박다인](https://github.com/da1nda2n) — 대학교 조회 API 정렬 지원 (BE)
- 양서린 — 프로젝트 디자인 지원 (Design)

<br/>
<br/>

# 3. Key Features
- **근로 신청서 및 제출 서류 PDF 자동 생성**:
  - 사용자 정보(비자 종류, TOPIK 등급, 학위 과정, 대학 인증 여부, 근무 업종 등)를 바탕으로 근로 가능 여부를 자동 판별합니다.
  - 판별 결과에 따라 필요한 제출 서류를 안내하고, 근로 신청서를 PDF로 자동 생성합니다.

- **이메일을 통한 PDF 자동 발송**:
  - 생성된 신청서 및 제출 서류 PDF를 이메일로 자동 발송합니다.

- **비자 유형별 제출 서류 안내**:
  - 비자 유형(D-2, D-4) 및 TOPIK 등급, 학위 과정에 따라 필요한 제출 서류를 안내합니다.

- **다국어 지원**:
  - 한국어에 익숙하지 않은 외국인 유학생도 쉽고 편리하게 서비스를 이용할 수 있도록 설계되었습니다.

<br/>
<br/>

# 5. Technology Stack
## 5.1 Backend
![Java](https://img.shields.io/badge/Java_17-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
 
<br/>

## 5.2 Authentication
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
 
<br/>

## 5.3 Document
![iText](https://img.shields.io/badge/iText_(HTML_to_PDF)-B22222?style=for-the-badge&logo=adobeacrobatreader&logoColor=white)
![JavaMail](https://img.shields.io/badge/JavaMail-D14836?style=for-the-badge&logo=gmail&logoColor=white)
 
<br/>

## 5.4 Infrastructure
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![AWS EC2](https://img.shields.io/badge/AWS_EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
 
<br/>

## 5.5 Tools
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

<br/>
<br/>

# 6. Project Structure
```plaintext
team15_server/
├── src/main/
│   ├── java/com/unithon/team15_server/
│   │   ├── Team15ServerApplication.java
│   │   │
│   │   ├── domain/
│   │   │   ├── certificate/            # 근로 신청서/제출 서류 판별·생성
│   │   │   │   ├── CertificateController.java
│   │   │   │   ├── CertificateService.java
│   │   │   │   ├── dto/                # CertificateReq, WorkingTimeLimitReq/Res, WorkTimeReq
│   │   │   │   ├── enums/               # UniversityYear, WorkingTimeLimit
│   │   │   │   └── implement/           # EmailSender, PdfGenerator
│   │   │   │
│   │   │   ├── employmentcheck/         # 근로 가능 여부 체크 플로우
│   │   │   │   ├── CheckStep.java
│   │   │   │   ├── EmploymentCheck.java / Controller / Repository / Service
│   │   │   │   └── dto/                 # DocumentInfoRes, EmploymentCheckRes, HomeInfoRes, StepInfoRes, TipInfoRes 등
│   │   │   │
│   │   │   ├── member/                  # 회원 가입/로그인/프로필
│   │   │   │   ├── Member.java / Controller / Repository / Service
│   │   │   │   ├── PasswordProcessor.java
│   │   │   │   ├── dto/                 # MemberSignInReq, MemberSignupReq, MemberProfile* 등
│   │   │   │   └── enums/                # MemberRole, ProfileField
│   │   │   │
│   │   │   └── web/
│   │   │       └── PolicyController.java
│   │   │
│   │   └── global/
│   │       ├── config/                  # EmailConfig, RedisConfig, SecurityConfig, SwaggerConfig, WebMvcConfig
│   │       ├── exception/                # CustomException, CustomExceptionHandler, ErrorCode, ErrorDTO
│   │       ├── jwt/                      # JwtProvider, JwtFilter, JwtValidFilter, MemberDetail(Service) 등
│   │       ├── translate/                # Translator, TranslateClient, TranslateRedisRepository, dto
│   │       ├── university/               # 대학 인증 정보 (controller/dto/entity/enums/loader/repository/service)
│   │       └── verification/             # 이메일 인증 (Email, EmailController, EmailService, dto)
│   │
│   └── resources/
│       ├── application.yml / application-dev.yml / application-prod.yml
│       ├── messages(_en/_ko).properties  # 다국어 메시지
│       ├── static/                       # css, font
│       └── templates/                    # certificate_en/ko.html, mail.html, policy/*
│
├── gradle/wrapper/
├── build.gradle
└── README.md
```
<br/>
<br/>
