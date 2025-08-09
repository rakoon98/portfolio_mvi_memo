# 프로젝트 제목
포트폴리오용 간단 메모앱

## 프로젝트 설명
포트폴리오용으로 작성하여으며,
간단하게 Room 을 사용하여 메모앱을 목표로 아키텍처를 설계하였습니다.

아키텍처 MVI 를 보여주는 아키텍처 모습을 보여 주었습니다.
Intent 는 사용자 이벤트를 처리하는 중요한 개념이지만, 프로젝트가 복잡하지않은 내용이기에 제외하였습니다.
핵심 내용은 관심사 분리, UiState 를 통한 ui 관리, SideEffect 를 통한 effect 처리 등입니다.
아래의 프로젝트 구조와 같이 구조를 설계하여 관심사 분리를 행하였습니다.

-유지보수 용이성
-확장성
-테스트 용이성

을 표현하기 위한 구조 설계 라고 보면 됩니다.

## 💻 기술 스택
- **언어**: Kotlin
- **프레임워크**: Jetpack Compose, Hilt, 
- **아키텍처**: MVI, Repository
- **라이브러리**: Flow, Room..
- **도구**: Android Studio

# 프로젝트 구조
<pre>
├── data/               # 외부/내부 데이터 소스 (Room, Retrofit 등\
│    ├── model/          # DTO\
│    ├── source/         # Local/Remote DataSource\
│    └── repository/     # Repository 구현\

├── domain/            # 비즈니스 계층\
│    ├── model/         # UI에서 사용하는 순수 모델\
│    └── repository/    # 추상 인터페이스\

├── ui/                # presentation 계층 (page 단위)\
│    └── main/\
│        ├── MainScreen.kt\
│        ├── MainViewModel.kt\
│        └── MainUiState.kt\
│    └── navigation/\
│        └── NavHost.kt\
│        └── PageRouter.kt\

├── di/                # Hilt Module\
│    └── AppModule.kt\
│    └── RepositoryModule.kt\

├── util/              # 공통 유틸\
│    └── DateUtil.kt\

└── App.kt             # @HiltAndroidApp\
</pre>