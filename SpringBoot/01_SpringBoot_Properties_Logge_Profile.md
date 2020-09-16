# SpringBoot

> ### 웹어플리케이션 타입 지정

> 웹어플리케이션 타입

- WebApplicationType.SERVLET
-  WebApplicationType.REACTIVE 
-  WebApplicationType.NONE (웹어플리케이션으로 하지 않는 것. 탐캣 실행 x)

SpringApplication 객체를 생성하고, 그 객체의 `setWebApplicationType()`메소드를 통해 타입을 지정할 수 있다. 지정하지 않는 경우 default값은 servlet이다.

```java
SpringApplication application = new SpringApplication(MyspringbootApplication.class);
		//Application Type 설정
application.setWebApplicationType(WebApplicationType.SERVLET);
//application.setWebApplicationType(WebApplicationType.NONE);
application.run(args);
```



> ### Spring-Boot-Devtools(자동 restart)

소스의 내용이 바뀔 때마다 직접 restart(cold start)를 해주어야 하는 번거로움이 있다. devtools dependency를 추가해서 자동으로 재시작할 수 있다.

재시작을 제외하는 파일을 설정할 수 있고, devtool을 미적용시킬 수도 있다.

:warning: 개발환경에서만 쓰고, 운영에서는 쓸 수 없다.



> ### properties 사용하기

- properties에 있는 값을 자바 클래스에서 사용하려면 `@Value`를 사용해야 한다.

  ```java
  @Component
  @Order(1)
  public class MyRunner implements ApplicationRunner {
  @Value("${vega2k.name}")
  private String name;
  @Value("${vega2k.age}")
  private int age;
  public void run(ApplicationArguments args) throws Exception {
  System.out.println("***************");
  System.out.println(name);
  System.out.println(age);
  	}
  }
  ```

  - Runner클래스

    - Runner클래스. 어플리케이션이 시작하자마자 동작을 하는 클래스. spring bean으로 동작하기 위해서 `@Component`를 써준다.

    - ApplicationRunner를 implements 한다. => run메소드를 상속한다.

    - runner가 여러개일 경우, `@Order`를 사용해서 우선순위를 줄 수 있다. 숫자가 높을 수록 우선순위가 높다.

    

- properties안에서 properties의 값을 또 사용할 수도 있다.

  ```properties
  vega2k.name=길동
  vega2k.age=${random.int(1,100)}
  #위에 있는 길동 가져오기
  vega2k.fullName=홍 ${vega2k.name}
  ```

  

  > properties의 우선순위

  - 1-15순위까지 존재

    **4순위**: 커맨드 라인 아규먼트

    **15순위**:  JAR 안에 있는 application properties(src/main/resources 안에 있는 properties)

    :arrow_forward: jar로 묶어서 cmd에서 실행할 때 properties값을 주면 그것을 우선으로 적용한다.

    ![](C:\TIL\SpringBoot\SpringBoot_assets\jar_cmd.PNG)

  

  > type-safe property 클래스

  - 기존 properties 이용 시 문제

    `@Value("${vega2k.name}")
    private String name;`와 같이 properties의 key.value값을 직접 적어야 하기 때문에 **오타의 가능성**이 높다.

    => properties 클래스 안에 변수와 getter/setter 메소드를 작성하여, get메소드로 변수를 불러올 수 있다.

  - **@ConfigurationProperties**

    properties 클래스에 위 어노테이션을 작성하여 properties의 값을 받는다.

    위 어노테이션을 사용하기 위해서는 spring-boot-configuration-processor 의존성을 설치해야한다.

  - **@Componenet**

    properties 클래스를 bean으로 설정하기 위해 위 어노테이션을 작성한다. bean으로 설정되어 있기 때문에, **실행하고자 하는 클래스(Runner클래스)에서 @Autowired로 주입받아 사용할 수 있다.**

    *JsyProperties Class*

    ```java
    @Component
    @ConfigurationProperties("jsy")//properties파일의 key명
    public class JsyProperties {
    	private String name;//properties의 value명과 동일한 변수명
    	private int age;
    	private String fullName;
    	
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}
    	public int getAge() {
    		return age;
    	}
    	public void setAge(int age) {
    		this.age = age;
    	}
    	public String getFullName() {
    		return fullName;
    	}
    	public void setFullName(String fullName) {
    		this.fullName = fullName;
    	}
    }
    ```

    *Runner Class*

    ```java
    public class MyRunner implements ApplicationRunner{
    	@Value("${jsy.name}")
    	String name;
    	
    	@Autowired //properties Class 주입
    	private JsyProperties property;
        
        @Override
    	public void run(ApplicationArguments args) throws Exception {
    		System.out.println("jsy.age: " + property.getAge());
    		System.out.println(property.getFullName());
    		
    	}
    ```

    

> ### Profile

Profile을 통해 스프링부트 애플리케이션의 런타임 환경을 관리할 수 있다. 

:arrow_forward: 외부설정 파일(properties)에서  `spring.profiles.active` 키값을 통해 어떤 프로파일을 활성화 시킬지 결정한다. 테스트 환경(개발)과 프로덕션 환경(운영)에서 실행할 지를 결정할 수 있다.  `spring.profiles.active`의 값은 cmd에서 jar를 실행할 때 주어 변경할 수 있다.

- `@Profile()`

  spring.profiles.active=test라면, @Profile("test")가 적혀있는 클래스의 내용으로 실행된다. @Profile이 다르다면, @Bean의 이름은 같아도 상관없다. 

- 프로파일용 properties file로 설정 가능

  application-{profile}.properties

  `spring.profiles.active=test` 인 경우, application-test.properties파일이 적용된다.

  ![](.\SpringBoot_assets\profiles.PNG)

> ### Logging

- logging api

  - 사용이유

    log level에 따라 실행하고 말고를 결정할 수 있기 때문에.

  - 구성

    logging api는 `Logger`라는 인터페이스로 구성되어 있고, 그것의 구현체(로깅 퍼사드 인터페이스)가 여러가지 있다.(log4j, logBack, jul) 부트는 기본으로 Logback을 채택하였다.

    Logback을 쓰지 않으려면 pom.xml에 exclusion을 해주고, 다른 구현체를 include해야한다. (dependency 입력)

  - 기본 로거 설정

    - 파일 출력: logging.path를 지정하면 logs/spring.log 파일이 생성된다.

    -  로그 레벨 조정: logging.level.패키지명 = 로그 레벨

      ![](.\SpringBoot_assets\logger.PNG)

- log level

  error > warn > info > debug > trace

  - application-{profile}.properties에서 `logging.level.com.jsy.myspringboot=로그레벨`을 설정하면, 해당 로그레벨 이상만 나오게 된다. 
  - 부트의 디폴트 로그레벨은 info이다.

   



