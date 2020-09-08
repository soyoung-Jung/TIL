# Spring 개요

### 0. 프레임워크 vs. 라이브러리

***"제어권의 차이"***

- 프레임워크: 개발자에게 제어권이 없다. = 객체 생성을 개발자가 하지 않는다.

  프레임워크에게 제어권을 주는 이유

  	1. 개발자가 비즈니스 로직에 더 집중할 수 있도록
   	2. byte code(개발자가 설정하지 않은 디자인패턴을 자동으로 설정하게 해준다. ex. singleton pattern)

- 라이브러리: 개발자에게 제어권이 있다. = 객체 생성을 개발자가 한다.



### 1. 스프링 프레임워크

> 정의

Java 엔터프라이즈 개발을 편하게 해주는 오픈소스 경량급 애플리케이션 프레임워크이다.

- 경량급 애플리케이션: EJB에 회의론적인 입장으로, WAS와 같은 기술이 없이 POJO만으로 엔터프라이즈 개발을 해줄 수 있게 된 것.

> 특징

1. 컨테이너 역할: 스프링컨테이너가 java 객체의 Life Cycle을 관리.
2. DI 지원: Spring은 설정 파일이나 어노테이션을 통해서 객체 간의 의존관계를 설정한다.
3.  AOP: Spring은 트랜잭션이나 로깅, 보안과 같이 공통적으로 필요로 하는 모듈들을 실제 핵심 모듈에서 분리해서 적용가능.
4. POJO지원: Spring 컨테이너에 저장되는 Java객체는 특정한 인터페이스를 구현하거나, 특정 클래스를 상속받지 않아도 된다.



### 2. IoC: 제어의 역전

> 정의

***"인스턴스의 생성부터 소멸까지 인스턴스 생명주기 관리를 개발자가 아닌 컨테이너가 대신한다."***

> 컨테이너

= Bean Factory, Application Context(Bean Factory를 상속한 확장판),  IoC컨테이너, Spring Bean 컨테이너, DI 컨테이너

--> 객체를 생성하는 컨테이너. 그 안에는 getBean()메소드가 있다. getBean메소드로 생성된 객체를 가져올 수 있다.

![](C:\TIL\Spring\spring_assets\BeanFactory.PNG)

이를 통해서 `new`키워드로 객체를 생성하지 않게 되었다.

> 구현 방법

1. DI: 의존성 주입
2. DL: 의존하고 있는 객체를 찾는 것. (WAS에서 주로 쓰는 방법)



### 3. DI: 의존성 주입

> 정의

***"각 클래스간의 의존관계를 빈 설정(Bean Definition) 정보를 바탕으로 컨테이너가 자동으로 연결해주는 것"***

> 구현방법

1. Setter Injection(아규먼트를 한번에 하나씩 전달) - `<property>` 태그
2. Constructor Injection(아규먼트를 한번에 여러개 전달) - `<constructor-arg>` 태그
3. Method Injection

> 사용이유

- 코드의 단순화
- 컴포넌트 간(객체/클래스 간) 결합도 제거



------



### * 용어정리

- `bean` : **스프링에서 제어권을 가지고 직접 만들어 관계를 부여하는 오브젝트**
  Java Bean, EJB의 Bean과 비슷한 오브젝트 단위의 애플리케이션 컴포넌트이다. 하지만 스프링을 사용하는 애플리케이션에서 만들어지는 모든 오브젝트가 빈은 아니다. 스프링의 빈은 스프링 컨테이너가 생성하고 관계설정, 사용을 제어해주는 오브젝트를 말한다.
- `bean factory` : **스프링의 IoC를 담당하는 핵심 컨테이너**
  Bean을 등록/생성/조회/반환/관리 한다. 보통 bean factory를 바로 사용하지 않고 이를 확장한 application context를 이용한다. BeanFactory는 bean factory가 구현하는 interface이다. (getBean()등의 메서드가 정의되어 있다.)
- `application context` : **bean factory를 확장한 IoC 컨테이너**
  Bean의 등록/생성/조회/반환/관리 기능은 bean factory와 같지만, 추가적으로 spring의 각종 부가 서비스를 제공한다. ApplicationContext는 application context가 구현해야 하는 interface이며, BeanFactory를 상속한다.
- `configuration metadata` : **application context 혹은 bean factory가 IoC를 적용하기 위해 사용하는 메타정보**
  스프링의 설정정보는 컨테이너에 어떤 기능을 세팅하거나 조정하는 경우에도 사용하지만 주로 bean을 생성/구성하는 용도로 사용한다.
- `container (ioC container)` : **IoC 방식으로 bean을 관리한다는 의미에서 bean factory나 application context를 가리킨다.**
  application context는 그 자체로 ApplicationContext 인터페이스를 구현한 오브젝트를 말하기도 하는데, 하나의 애플리케이션에 보통 여러개의 ApplicationContext 객체가 만들어진다. 이를 통칭해서 spring container라고 부를 수 있다.

------

 IoC의 사용목적 및 용어정리에 참고하기 좋은 사이트

https://jongmin92.github.io/2018/02/11/Spring/spring-ioc-di/

