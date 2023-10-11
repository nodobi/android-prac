### [Google Archicture](https://github.com/android/architecture-samples)를 따라 MVC 패턴의 코드를 MVP 코드로 리팩토링하는 과정을 기록하는 레포입니다.
Taehwan 님의 [Android MVP 무작정 따라하기]() 를 따라 챕터별 브랜치를 만드는 방식으로 기록, 진행합니다.

### 브랜치 설명
- [00-MVC](https://github.com/nodobi/design-patterns/tree/00-MVC)
  - MVC 패턴으로 작성된 실습 예제 코드
- [01-Presenter,View](https://github.com/nodobi/design-patterns/tree/01-Presenter%2CView)
  - 기존 MVC 패턴에서 GoogleArchicture를 따라 Contract, Presenter 를 추가
- [02-Adapter](https://github.com/nodobi/design-patterns/tree/02-Adapter)
  - AdapterContract 를 추가하여 Presenter 가 View를 통해 Adapter에 접근하는 것이 아닌 직접 접근 하도록 하였음
- [03-onClick](https://github.com/nodobi/android-architecture-prac/tree/03-onClick)
  - 람다식을 활용한 onClickFunc 를 ImageAdapterContract 에 추가하여 이벤트 처리를 MainPresenter 에서 할 수 있도록 하였음
- [04-Model](https://thdev.tech/androiddev/2017/01/29/Android-MVP-Google-Architecture-Model/)
  - 기존에 사용하는 ImageData 를 Google Architecture 의 [Model 정의](https://github.com/android/architecture-samples/tree/todo-mvp)에 따라 Repository, Local Data Source 로 나누어 처리할 수 있게 하였음
