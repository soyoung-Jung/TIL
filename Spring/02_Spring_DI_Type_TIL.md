# DI 구현방법(유형) & Bean 등록 메타정보 등록 전략

### DI 구현방법

스프링에서 의존성을 주입하는 방법은 3가지이다.

1.  setter injection: 의존성을 입력 받는 setter 메서드를 만들고 이를 통해 의존성을 주입한다. 
2. consturctor injection: 필요한 의존성을 포함하는 클래스의 생성자를 만들고 이를 통해 의존성을 주입한다. 
3. method injection: 의존성을 입력 받는 일반 메서드를 만들고 이를 통해 의존성을 주입한다.

configuration metadata를 바탕으로 의존성을 주입한다.



### Bean 등록 메타정보 구성

bean들의 의존관계는 bean 등록 메타정보, 즉  Configuration Metadata를 통해 알 수 있다. (메타데이터를 통해 beans를 관리하기 때문이다.) 

Bean 등록 메타정보 구성 전략은 총 3가지이다. 각 전략 별 DI 유형이 어떻게 달라지는지 알아보자. 

#### 1. XML 설정 단독 사용

모든 bean을 명시적으로 XML에 등록한다.

> 장점

생성되는 모든 Bean을 XML에서 확인할 수 있다

> 단점

Bean의 개수가 많아지면 XML 파일을 관리하기 번거로울 수 있다. 

여러 개발자가 같은 설정파일을 공유해서 개발하다 보면 설정파일을 동시에 수정하다가 충돌이 일어나는 경우도 적지 않다.

DI에 필요한 적절한 setter 메서드 또는 constructor가 코드 내에 반드시 존재해야 한다.

> bean 클래스 설정

<bean> 태그를 사용하여 설정한다.

```java
	<!-- StringPrinter 클래스를 Bean으로 등록 -->
	<bean id="strPrinter" class="myspring.di.xml.StringPrinter"/>
```

------

> ##### Setter Injection

<property> 태그를 사용한다.

```java
<!-- setter injection -->
<!-- setName("스프링") -->
<property name="name" value="스프링" />
<!-- setPrinter(new StringPrinter()) -->
<property name="printer" ref="strPrinter"/>
```

- name: bean의 argument인 변수명을 의미한다.
- value &  ref: 단순한 값 설정시 value를 사용하고, 객체를 설정할 시 ref 속성을 사용.

> **Constructor Injection**

<constructor-arg>태그를 사용한다.

```java
<!-- constructor injection 설정 -->
<constructor-arg index="0" value="생성자"/>
<constructor-arg index="1" ref="conPrinter"/>
```

- index: 생성자에 들어오는 argument의 index를 의미한다.
- 클래스에 기본생성자가 없고 아규먼트가 있는 생성자만 있을 때에는 꼭 Constructor Injection을 사용해야 한다.

> **Method Injection**



#### 2. Annotation + XML 설정

Bean으로 사용될 클래스에 특별한 어노테이션(Annotation)을 부여해주면 이런 클래스를 자동으로 찾아서 Bean으로 등록한다.

Bean Scanning: @Component 어노테이션이 선언된 클래스를 자동으로 찾아서 Bean으로 등록 해주는 방식

변수를 props로서 따로 빼서 변경을 용이하게 할 수 있다.

> 장점

XML 문서 생성과 관리에 따른 수고를 덜어주고 개발 속도를 향상시킬 수 있다.

> 단점

애플리케이션에 등록될 Bean이 어떤 것들이 있고, Bean들 간의 의존관계가 어떻게 되는지를 한눈에 파악할 수 없다

> 클래스를 bean으로 등록

1. `@Component`를 bean클래스로 설정하고자 하는 클래스에 넣어준다.

   `@Component(id)`와 같이 id를 명시해줄 수도 있으며, id를 명시하지 않으면 자동으로 클래스명(소문자로 시작)과 같게 설정된다.

2. xml파일에 어노테이션이 **선언된 클래스들을 스캔**하기 위한 설정을 한다. (bean 클래스가 소속한 패키지 설정)

   아래와 같이 하지 않으면 모든 패키지를 탐색하게 된다.

   ```java
   	<!-- Componnent Auto Scanning을 위한 설정 -->
   <context:component-scan base-package="myspring.di.annotation"/>
   ```

------

> **Setter Injection**

전략 2의 경우, setter메소드가 없어도 가능하다. 즉, 변수를 선언하는 곳 앞에다가 어노테이션을 붙인다.  어노테이션 안에 직접 값을 주어도 되고, props로 따로 뺀 경우에는 xml에 다음과 같이 설정한다.

```java
	<!-- Properties file 정보 설정 -->
	<context:property-placeholder location="classpath:config/values.properties"/>
```

- 변수가 일반 값일 경우 `@Value`

- 변수가 객체일 경우 `@Autowired` 혹은 `@Resource`

  - `@Autowired` vs. `@Resource`

    @Autowired는 type기반, @Resource는 name기반! 

  ```java
  	@Value("${myName}")
  	String name;
  	
  //	@Autowired
  //	@Qualifier("${myPrinter}"
  	@Resource(name="${myPrinter}")
  ```

> **Constructor Injection**

생성자의 아규먼트 앞에 어노테이션을 붙인다. @Resource는 생성자에 붙일 수 없다. 
@Autowired는 생성자 위에 붙인다.

```java
@Autowired
public HelloBean(@Value("어노테이션생성자")String name, @Qualifier("consolePrinterBean") IPrinter printer) {
	public HelloBean(String name, IPrinter printer) {
		System.out.println("Overloaded HelloBean constructor 호출됨!!" + name + " " + printer.getClass().getName());
		this.name = name;
		this.printer = printer;
	}
```



** 그 외 Bean을 등록하는 Annotation

![](C:\TIL\Spring\spring_assets\Bean등록.PNG)

> annotation 정리

| 어노테이션 | 기능                                                         |
| ---------- | ------------------------------------------------------------ |
| @Autowired | 의존하는 객체를 자동으로 주입해 주는 어노테이션(Type 기반)   |
| @Resource  | 의존하는 객체를 자동으로 주입해 주는 어노테이션(name 기반)   |
| @Value     | 단순한 값을 주입해주는 어노테이션                            |
| @Qualifier | @Autowired가 type기반으로 찾기 때문에, 같은 타입의 다른 name(id)들이 여러개 있을 경우, id를 설정해주는 것. |
| @Component | 어떤 클래스를 bean으로 등록할지 알려주는 것.                 |



#### 3. Annotation only(no xml)

- Spring JavaConfig 프로젝트는 XML이 아닌 자바 코드를 이용해서 컨테이너를 설정할 수 있는 기능을 제공한다.

- @Configuration 어노테이션과 @Bean 어노테이션을 이용해서 스프링 컨테이너에 새로운 빈 객체를 제공할 수 있다.
- @Configuration 어노테이션이 쓰여진 클래스가 xml과 같은 기능을 하여 의존관계와 무엇을 bean으로 설정할 지 등을 정의한다. 메소드를 만들고 해당 객체를 우리가 직접 생성을 하며, bean의 id는 메소드 명이 된다.
- 테스트 케이스를 작성할 때 `@ContextConfiguration`어노테이션을 사용하고, 그 안에 @Configuration이 있는 자바파일, 즉 설정클래스가 무엇인지 알려준다. 또한 loader를 AnnotationConfigContextLoader.class로 설정한다.?? 무엇인지 명확하게 알기!
- @Configuration이 붙은 클래스에 `@ComponentScan(basePackages = {"myspring.di.annotation"})`과 같이 붙여 메소드를 직접 작성하지 않고, @Component가 붙은 클래스를 가져와 설정할 수도 있다.

>annotation 정리

| 어노테이션      | 기능                                                         |
| --------------- | ------------------------------------------------------------ |
| @Configuration  | 클래스에 @Configuration 어노테이션을 선언하는 것은 스프링 IoC 컨테이너가 해당 클래스를 Bean 정의의 설정으로 사용한다는 것을 나타낸다. Spring의 새로운 자바 설정 지원의 핵심 부분은 @Configuration 어노테이션이 붙은 클래스이다. 이러한 클래스들은 스프링 IoC 컨테이너가 관리하는 객체의 인스턴스화, 설정, 초기화 로직을 정의하는 @Bean 어노테이션이 붙은 메서드들로 이루어져 있다. |
| @Bean           | 전략 2의 @Component와 동일하게 bean으로 지정할 객체를 지정한다. (@Component와 다르게 메소드 위에 작성한다.) @Bean이 적용된 메서드의 이름을 Bean의 식별값으로 사용한다. |
| @PropertySource | 주입하고자 하는 값을 props로 뺀 경우에 이 어노테이션에 props의 위치를 알려준다. |

> 참고할 소스

- HelloConfig.java(설정 클래스)

```java
//package & import  생략

@Configuration
@PropertySource("classpath:config/values.properties")
public class HelloConfig {
	@Autowired
	private Environment env;//props값을 가져오게 해주는 객체 선언, getProperty 메소드를 가지고 있다.
	
	@Bean
	public Hello hello()  {
		Hello hello = new Hello();
		hello.setName(env.getProperty("configName"));
		hello.setPrinter(strPrinter());
		return hello;
	}
	
	@Bean
	public Printer strPrinter() {
		Printer printer = new StringPrinter();
		return printer;
	}
	
	@Bean
	public Printer conPrinter() {
		Printer printer = new ConsolePrinter();
		return printer;
	}
}

```

- Test 클래스

```java
//package & import  생략

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HelloConfig.class, loader = AnnotationConfigContextLoader.class)
public class HelloConfigTest {
	@Autowired
	Hello hello;
	
	@Test
	public void bean() {
		System.out.println(hello.sayHello());
	}
}
```



> 설정클래스에 @ComponentScan을 사용한 경우

```java
//package & import  생략

@Configuration
@PropertySource("classpath:config/values.properties")
@ComponentScan(basePackages = {"myspring.di.annotation"})
public class HelloBeanConfig {

}
```

```java
//package & import  생략

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HelloBeanConfig.class,
		loader = AnnotationConfigContextLoader.class)
public class HelloBeanConfigTest {
	@Autowired
	HelloBean hello;
	
	@Test
	public void config() {
		System.out.println(hello.sayHello());
	}
}

```

