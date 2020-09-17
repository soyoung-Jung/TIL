# SpringBoot Data

스프링부트가 지원하는 기본 DBCP는 Hikari이다.

> ### H2 DB

in-memory DB로써, 콘솔 기능이 있어 편리하다. 의존성을 넣어주면 편리하게 쓸 수 있다.

- 의존성

```xml
<dependency>
 <groupId>com.h2database</groupId>
 <artifactId>h2</artifactId>
 <scope>runtime</scope>
</dependency>
```

- H2 DB 기본 연결 정보 확인
  - `getURL()`
  - `getUserName()`

```java
try(Connection connection = dataSource.getConnection()){
System.out.println(connection.getMetaData().getURL());
System.out.println(connection.getMetaData().getUserName());
 }
```

- H2 사용방법
  1. `http://localhost:포트번호/h2-console` 로 접속
  2. url, username, password 치고 접속

> ### Maria DB

- 의존성 추가

  ```xml
  <dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.13</version>
  </dependency>
  ```

- MYSQL DataSource 설정

  스프링 프레임워크에서의 각 DB벤더의 driver와 유사

  ```properties
  spring.datasource.url=jdbc:mysql://127.0.0.1:3306/spring_db?useUnicode=true&charaterEnc
  oding=utf-8&useSSL=false&serverTimezone=UTC
  spring.datasource.username=spring
  spring.datasource.password=spring
  spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
  
  ```



***

> ### JPA



> 정의

JPA란 Java Persistence API의 약자로, ORM을 위한 자바 EE 표준. **자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스**

- ORM: B 테이블을 자바 객체로 매핑함으로써 객체간의 관계를 바탕으로 **SQL을 자동으로 생성**(Mapper는 SQL을 명시해주어야 한다.)

> Hibernate

JPA는 인터페이스이기 때문에, 그것을 구현한 구현체가 필요하다. Hibernate는 JPA의 구현체 중 하나이다.

![](.\SpringBoot_assets\HIBERNATE.PNG)

> ### Spring Data JPA

> 정의

Srpring Data JPA는 JPA를 쓰기 편하게 만들어놓은 모듈이다.  DB에 접근할 필요가 있는 대부분의 상황에서는 `Repository`를 정의하여 사용한다. Spring Data JPA는 Repository라는 인터페이스를 제공하는데, 사용자가 `Repository` 인터페이스에 정해진 규칙대로 메소드를 입력하면, Spring이 알아서 해당 메소드 이름에 적합한 쿼리를 날리는 구현체를 만들어서 Bean으로 등록해준다.

![](.\SpringBoot_assets\springDataJPA.PNG)

>**의존성 추가**

```XML
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

> **Entity 클래스 작성**

MyBatis와 다르게 JPA에서는 entity  클래스를 설계하고, table을 설계하고 구현을 한다. 따라서 클래스에 entity를 작성하면 그것이 테이블에 들어가게 된다.

```java
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
	
	@Column(unique = true)
	private String email;
	
	//각 변수에 대한 setter & getter 필요
    //toString & equals & hasCode 를 overriding하기도 한다.
}

```

- **@Entity**

  엔티티 클래스임을 지정하고, DB테이블과 매핑하는 객체를 난타낸다. * 엔터티: db 상의 table

- **@Id**

  테이블의 pk를 나타낸다.

- **@GeneratedValue(strategy = GenerationType.IDENTITY)**

  pk의 값을 자동 생성하기 위해 사용된다. strategy로는 `AUTO`, `IDENTITY`, `SEQUENCE`,` TABLE`이 있다. 

  - `AUTO`

    : hibernate_sequence 테이블이 만들어져 auto increment를 한다. 즉, hibernate가 관여하게 된다.

  - `IDENTITY`

    : MySql에서 auto increment를 관리

  - `SEQUENCE`

    : Oracle에서 auto increment를 관리

- **@Column**

  column을 명시한다. 굳이 명시하지 않아도 변수명이 자동으로 column이 된다.
  
  `(unique = true)`로 column의 값이 유일한 값을 갖게 할 수 있다.

> **Repository 인터페이스 작성**

UserRepository의 구현체를 따로 작성하지 않아도 Spring-Data-JPA가 자동적으로 해당 문자열 username에 대한 인수를 받아 자동적으로 DB Table과 매핑한다.

UserRepository가 상속받고 있는 JpaRepository의 부모 인터페이스 `CrudRepository`는 다음과 같은 메소드를 갖고 있다. 그 외에 추가하고자 하는 메소드를 `UserRepository`를 형식에 맞게 작성한다.

- CrudRepository 메소드

  ![](.\SpringBoot_assets\crud.PNG)

- UserRepository

```java
// JpaRepository<entity이름, id 타입>
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
	Optional<User> findByEmail(String email);
    //findById는 자동으로 optional이다.
}
```



> **JPA에 의한 데이터베이스 자동 초기화 설정**

application.properties에 db초기화 방식을 설정한다. validate는 운영단계에, 그 외는 개발단계에서 사용한다.

```properties
spring.jpa.hibernate.ddl-auto=create|create-drop|update|validate
```

- create
  JPA가 DB와 상호작용 시, 기존에 있던 테이블을 삭제 후 새로 만든다.
- create-drop
  JPA 종료 시점에 기존에 있던 테이블을 삭제한다.
- update
  기존 테이블은 유지하고, 새로운 것만 추가하며 기존 데이터를 유지한다. 변경된 부분만 반영한다.
- validate
  엔티티와 테이블이 정상 매핑 되어 있는지를 검증한다. 
  entity 클래스에 column이 추가 되어있는데, 테이블에는 없다면 에러가 나게 된다.

> **Optional 객체 | functional interface | lambda**

- Optional 객체

  - 정의

    Null Point Exception을 방지하기 위한 wrapper class.  어떤 메소드로부터 리턴 받은 객체가 null 일수도 있기 때문에 if문으로 일일이 처리하기보다 Optional타입의 객체 타입으로 받는 것.

  - 사용

    ```java
    public interface AccountRepository extends JpaRepository<Account, Long>{
    	Account findByUsername(String username);
    	Optional<Account> findByEmail(String email);
    ```

  - 메소드

    - `isPresent()`: 객체가 존재한다면 true, null이라면 false

    - `get()`: 내부 객체가 존재하는 경우에 쓸 수 있는 메소드로, 그 안의 객체를 꺼내올 수 있다.

    - `orElseThrow()`: 내부 객체를 반환하되 존재하지 않으면 파라미터에 있는 예외를 발생시킨다. 파라미터 안에는 `lambda` 식을 사용한다.

      ```java
      Optional<Account> optEmail = repository.findByEmail("dooly@com");
      System.out.println(optEmail.isPresent());
      Account account3 = optEmail.orElseThrow(
          () -> new RuntimeException("요청한 이메일 주소를 가진 Accountn가 없음!!"));
      System.out.println(account3);
      ```

- forEach메소드 사용하기

  - lambda

  - method reference

    ```java
    List<Account> accountList = repository.findAll();
    accountList.forEach(acct -> System.out.println(acct));
    accountList.forEach(System.out::println);
    ```

    

> **JPA 테스트하기** 

- Spring Boot Test

  두 어노테이션을 붙여서 SpringBootTest를 진행한다.

  - `@RunWith(SpringRunner.class)`

  - `@SpringBootTest`

  ```java
  @RunWith(SpringRunner.class)
  @SpringBootTest
  public class AccountRepositoryTest {
  	
      //Repository 주입받기
  	@Autowired
  	private AccountRepository repository;
  ```

- 등록(save)

  ```java
  	@Test
  	public void account() throws Exception {
  		//1. Account 객체 생성 -> 등록
  		Account account = new Account();
  		account.setUsername("lambda");
  		account.setPassword("1234");
  		account.setEmail("dooly@aa.com");
  		
  		Account addAccount = repository.save(account);//save 메소드는 등록을 하고 등록한 어카운트를 다시 반환함. 
  		System.out.println(addAccount.getId() + " " + addAccount.getUsername());
  		
  	}
  ```

- 조회

  `findByUserName("name")`  | `findByEmail("email")` | `findById("id")` | `findAll()`

  ```java
  @Test
  	public void finder() {
  		Account account = repository.findByUsername("lambda");
  		System.out.println(account);
  		Optional<Account> optional = repository.findById(100L);//findById(100L), findById(1L)
  		System.out.println(optional.isPresent()); 
  		if(optional.isPresent()) {
  			Account account2 = optional.get();  
  			System.out.println(account2);
  		}
  		Optional<Account> optEmail = repository.findByEmail("dooly@aa.com");
  		System.out.println(optEmail.isPresent());
  		Account account3 = optEmail.orElseThrow(() -> new RuntimeException("요청한 이메일 주소를 가진 Accountn가 없음!!"));
  		System.out.println(account3);
  		
  		List<Account> accountList = repository.findAll();
  		accountList.forEach(acct -> System.out.println(acct));
  		accountList.forEach(System.out::println);
          //method reference
  
  	}
  ```

- 업데이트

  업데이트 메소드가 따로 없기 때문에, id 등으로 조회해서 있는 경우에만 객체로 받아서 그 객체를 새롭게  `set`을 한 뒤, `save()`한다.

```java
	//업데이트하기
	@Test @Ignore
	public void update() {
		Optional<Account> optional = repository.findById(2L);
		if(optional.isPresent()) {//요청한 id를 가진 account가 있을때만 save하기
			Account account = optional.get();
			account.setEmail("dooly22@aa.com");
			repository.save(account);//등록이 아니라 업데이트가되는 것임. 이미 저 아이디가 있기 때문. 
```

> **web MVC와 붙이기(REST 방식)**

- RestUserController

```java
@RestController
public class RestUserController {
	private final UserRepository repository;
	
	public RestUserController(UserRepository repository) {
		this.repository = repository;//@Autowired가 아닌 기본생성자로 UserRepository 주입받기.
	}
	
	@PostMapping("/users")
	public User insert(@RequestBody User addUser) {
		return repository.save(addUser);
	}
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		Optional<User> optional = repository.findById(id);
		User user = optional.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		return user;
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetail) {
		//값 조회
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));//repository.findById(id)이 자체가 옵셔널이기 때문에 바로 붙일 수 있다.
		//값을 변경
		user.setName(userDetail.getName());
		user.setEmail(userDetail.getEmail());
		//DB에 save() 변경된 유저를 받아서 그 유저를 반환
		User updateUser = repository.save(user);
		return updateUser;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {//타입이 정해지지 않은 경우 물음표
		Optional<User> optional = repository.findById(id);
		//isEmpty: id와 매핑하는 User가 없으면,
		if(optional.isEmpty()) {
			return new ResponseEntity<>("요청한 user가 없습니다.", HttpStatus.NOT_FOUND);
		}
		//db에서 삭제하기
		repository.deleteById(id);
		return new ResponseEntity<>(id + "User가 삭제되었습니다", HttpStatus.OK);
	}
	
}
```



> **ReponseEntity**

- 정의

  *ResponseEntity* **represents the whole HTTP response: status code, headers, and body**. As a result, we can use it to fully configure the HTTP response.

  보낼 메세지와 http코드를 함께 보낼 수 있다.



> ### 사용자 정의 Exception

`RunTimeException`을 상속받아 새로운 예외처리 클래스를 만들어 사용할 수 있다.

- ResourceNotFoundException

  ```java
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public class ResourceNotFoundException extends RuntimeException {
  	private String resourceName;
  	private String fieldName;
  	private Object fieldValue;
  
  	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
  		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
  		this.resourceName = resourceName;
  		this.fieldName = fieldName;
  		this.fieldValue = fieldValue;
  	}
  
  	public String getResourceName() {
  		return resourceName;
  	}
  
  	public String getFieldName() {
  		return fieldName;
  	}
  
  	public Object getFieldValue() {
  		return fieldValue;
  	}
  }
  ```





***

참고

- jpa 개념

https://velog.io/@adam2/JPA%EB%8A%94-%EB%8F%84%EB%8D%B0%EC%B2%B4-%EB%AD%98%EA%B9%8C-orm-%EC%98%81%EC%86%8D%EC%84%B1-hibernate-spring-data-jpa

https://suhwan.dev/2019/02/24/jpa-vs-hibernate-vs-spring-data-jpa/

- lambda 개념

https://multifrontgarden.tistory.com/124?category=471239

- ResponseEntity

  https://www.baeldung.com/spring-response-entity