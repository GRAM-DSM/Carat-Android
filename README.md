# Carat-Android
## 사용 기술
- Kotlin
- Retrofit
- Glide
- Coroutine
- MVP

## 기능분담
- 안민희
    - 회원가입&로그인
    - 타임라인
- 정유빈
    - 글쓰기
    - 프로필

## 기간  
From 2020/07/27  
To 2020/09/08

## Github 관리
- 기능별로 브랜치를 생성 후 각 기능을 맡은 담당이 개발
- 기능이 완성되면 pull request로 코드 리뷰 후 master에 merge
- 기능 단위별 브랜치는 다음과 같다. (SignInUp, Timeline, Writing, Profile)

## Resource 관리
Naming
- View_ID: where_description_what
- Layout_ID: what_description
- drawable_FileName: what_description

Value  
- color는 colors.xml에 관리
- string은 string.xml에 관리

코드 정리
- Ctrl + Alt + l(소문자 L)
- Lint 사용(Analyze > Inspect Code > OK)