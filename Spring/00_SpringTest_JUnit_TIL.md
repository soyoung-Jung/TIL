# jUnit & Spring-Test

### 단위테스트

단위테스트란, 소스 코드의 특정 모듈이 의도된 대로 정확히 작동하는지 검증하는 절차, 즉 **모든 함수와 메소드에 대한 테스트 케이스(Test case)를 작성**하는 절차. 

jUnit과 Spring Test모두 단위테스트를 위한 것이다.

### jUnit

> 정의

Java에서 독립된 단위테스트(Unit Test)를 지원해주는 프레임워크이다.

> 특징

- assert메소드로 테스트 케이스의 수행 결과를 판별한다. 
  `assertEquals(예상 값, 실제 값)`
- 테스트를 지원하는 어노테이션 제공(@Test @Before @After)

> 테스트를 지원하는 어노테이션

**@Test**

- @Test가 선언된 메서드는 테스트를 수행하는 메소드가 된다.
-  Junit은 각각의 테스트가 서로 영향을 주지 않고 독립적으로 실행됨을 원칙으로 하므로 @Test 마다 객체를 생성한다.

**@Ignore**

- @Ignore가 선언된 메서드는 테스트를 실행하지 않게 한다. 

**@Before**

- @Before가 선언된 메서드는 @Test 메소드가 실행되기 전에 반드시 실행되어 진다.
- @Test 메소드에서 공통으로 사용하는 코드를 @Before 메소드에 선언하여 사용하면 된다.

![](C:\TIL\Spring\spring_assets\jUnit.PNG)



> 테스트 결과를 확인하는 단정(assert) 메소드

| assertEquals(a, b);      | 객체 A와 B의 값이 일치함을 확인                              |
| ------------------------ | ------------------------------------------------------------ |
| assertArrayEquals(a, b); | 배열 A와 B의 값이 일치함을 확인                              |
| assertSame(a, b);        | 객체 A와 B가 같은 객체임을 확인,<br />assertEquals 메서드는 두 객체의 값이 같은지 확인하고, assertSame메서드는 두 객체의 레퍼런스가 동일한가를 확인한다. (== 연산자) |
| assertTrue(a);           | 조건 A가 참인가를 확인한다.                                  |
| assertNotNull(a);        | 객체 A가 null이 아님을 확인한다.                             |



------

### Spring-Test

> 정의 및 필요성

Spring-Test는 jUnit을 Wrapping한 것으로, jUnit을 베이스로 한다. 즉, jUnit을 확장한 스프링의 테스트 라이브러리이다. jUnit을 사용하면 테스트하기에 최적화 되어있지 않기 때문에 Spring-Test로 단위테스트를 한다.

> pring-Test에서 테스트를 지원하는 어노테이션

**@RunWith(SpringJUnit4ClassRunner.class)**

- @RunWith는 jUnit 프레임워크의 테스트 실행방법을 확장할 때 사용하는 어노테이션
- SpringJUnit4ClassRunner라는 클래스를 지정해주면 jUnit이 테스트를 진행하는 중에 <u>ApplicationContext를 만들고 관리하는 작업을 진행</u>해 준다.



**@ContextConfiguration**

- 스프링 빈(Bean) 설정 파일의 위치를 지정할 때 사용되는 어노테이션이다.(xml 파일 위치 알려주기)

  ```java
  @ContextConfiguration(locations = "classpath:config/spring_beans.xml")
  ```

--> 위 두 어노테이션 실행시, **Spring Bean Container 객체생성을 안해도 된다 **

​	= BeanFactory factory = new GenericXmlApplicationContext(xml위치)할 필요가 없다.

두 어노테이션 모두 클래스 맨 위에 작성해야 한다.



**@Autowired**

- 스프링DI에서 사용되는 특별한 어노테이션이다.

- 해당 변수에 자동으로 빈(Bean)을 매핑 해준다. 

- 스프링 빈(Bean) 설정 파일을 읽기 위해 굳이 GenericXmlApplicationContext를 사용할 필요가 없다. = `factory.getBean()` 을 할 필요가 없다.

  ```java
  @Autowired
  Hello hello;
  ```

  Hello의 빈 객체를 가져와서 hello라는 변수에 담는 것. 실행 순서 첫째로 변수명과 같은 id를 xml에서 가져와서 실행한다.

**@Qualifier**

- 변수명을 id에 맞게 설정하지 않고, 변수명과 다른 id의 클래스를 가져오고 싶으면 `@Qualifier`를 사용하여 가져온다.

- @Autowired와 함께 쓰이는 것이 일반적이다.

  ```java
  @Autowired
  @Qualifier("helloC")
  Hello hello;
  ```

  HelloC의 빈 객체를 가져와서 hello라는 변수에 담는 것. 빈의 id는 HelloC로 설정되어 있지만 변수를 hello로 주는 것이다. 타입이 Hello인 이유는, 빈 helloC의 클래스는 Hello 클래스이기 때문에..

  ** 하나의 클래스로 여러개의 bean을 만들 수 있다. 단, bean의 id는 unique해야한다. (하나의 클래스로 여러 객체를 만드는 것과 같은 맥락)