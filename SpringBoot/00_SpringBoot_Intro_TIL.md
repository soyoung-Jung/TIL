#  Spring Boot 개요

> ### 스프링부트란?

스프링의 하위 프로젝트 중 하나로, 다음과 같은 특징을 갖고 있다.

- Tomcat 등 서버가 내장되어 있다.
- 내부적으로 설정이 자동화되어 있다.
- xml으로 설정하지 않는다.



> ### 스프링부트 프로젝트 생성 방법

1. Springi Initializr 사용하기
2. Spring Tool Suite로 만들기

![](.\SpringBoot_assets\springBoot_initializer.PNG)

- Group: 자신의 프로젝트를 식별해주는 고유 아이디
- Artifact: 버전 정보를 생략한 이름, 보통 프로젝트 id와 동일하게 작성한다.
- Dependencies: 생성시 의존성을 추가할 수 있다.



> ### 스프링 부트 프로젝트의 구조

크게 main(배포용)과 test(테스트용)로 나뉜다.

| 구조                                      | 역할                                                         |
| ----------------------------------------- | ------------------------------------------------------------ |
| **src/main**/java                         | 자바 source 파일들                                           |
| src/main/resources/application.properties | 프라퍼티 값들을 모아 놓은 파일, 이 곳에서 port번호를 변경함. (=스프링에서 직접 만든 values.properties) |
| src/main/resources/static                 | html, css와 같은 정적 파일들                                 |
| src/main/resources/templates              | jsp, thumeleaf와 같은 동적 파일들                            |
| **src/test**/java                         | 자바 테스트 파일들                                           |



> ### @SpringBootApplication(스프링부트의 자동설정)

스프링부트 프로젝트 생성을 하고 나면,  Application.java가 자동으로 src/main/java의 패키지 밑에 생성되어 있다. 그 안에는 @SpringBootApplication 어노테이션이 있다. 그것은 3개의 어노테이션을 합친 것과 같다.

@SpringBootApplication 

*=  @SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration*



**@SpringBootConfiguration**

스프링의 전략3에서 @Configuration으로 설정 클래스를 정했던 것과 같이 설정 클래스를 정하는 것. 

**@ComponenetScan**

@Repository, @Configuration, @Service 등 스프링 빈을 나타내는 어노테이션을 @ComponentScan이 붙은 클래스가 위치해 있는 현재 패키지를 기준 으로 그 아래 패키지까지 찾아내서 스프링 빈으로 등록한다. 즉, project 생성시 정해준 default 팩키지 부터 scanning을 한다. 

:arrow_right: 따라서 defaul 패키지 밑에 서브 패키지를 만들어 @ComponentScan을 붙여야 한다.

**@EnableAutoConfiguration**

bean 들을 자동적으로 컨테이너에 등록하는 역할을 한다. bean들의 목록은 spring-boot-autoconfigure-2.X.X.RELEASE.jar 파일 안 spring.factories내에 선언된 @Configuration 설정 클래스들을 모두 Load한다.

:arrow_right:스프링 부트 프로젝트를 기본적으로 웹 프로젝트로 만들 수 있는 기본값이 설정되어 있다. 우리가 직접 설정하지 않아도!



> ### 스프링부트 배너 변경하기

파일명 `banner.txt`를 `src/main/resources`에 저장하고 내용을 변경하면 기본 스프링 배너에서 다른 것으로 변경가능하다.

```txt
====================================================================
My Spring Boot Booy버전: ${spring-boot.version} / 어플리케이션버전: ${application.version}
====================================================================
```



> ### 스프링부트 프로젝트 jar 파일로 생성

개발할 경우 STS에서 프로젝트를 만들지만, 배포하기 위해서는 jar파일로 압축을 해야 한다.

- 실행순서

  1.  Run As -> Maven Build -> Goals : package -> Run

  2. pom.xml에 <packaging>jar</packaging> 추가
  3. cmd 창에서 실행해보기

:warning: STS와 cmd에서 동시에 실행시킬 수 없다. port의 충돌이 나기 때문!

***

프로젝트의 서버는 기동 되었지만, 표현할 페이지가 없기 때문에 `resources/static`에 index.html파일을 추가하여 확인해볼 수 있다.

