# Entity Mapping

> ### 연관관계 매핑

JPA에서는 Mybatis와 달리 Entity간 관계를 맺을 때 상대 테이블의 pk를 멤버변수로 갖는 것이 아니라 **Entity 객체 자체**를 통째로 참조한다.

```java
// Mybatis
private Integer categoryNo;

// JPA
private Category category;
```

- 방향

  구현 서비스에 따라 단방향/양방향 관계를 선택한다.

  - 단방향 관계: 두 Entity 관계 시, 한 쪽의 Entity만 참조.
  - 양방향 관계: 두 Entity 관계 시, 양 쪽이 서로 참조.

- 다중성

  두 Entity는 다음 중 하나의 관계를 갖는다.

  - `@ManyToOne` - 다대일(N:1)
  - `@OneToMany` - 일대다(1:N)
  - `@OneToOne` - 일대일(1:1)
  - `@ManyToMany` - 다대다(N:N)

- 연관 관계의 주인(Owner) - 양방향 관계를 맺을 시

  두 Entity 중 주인이 되는 Entity는 두 테이블 중 **외래키**를 갖는 Entity이다.

  주인은 외래키를 관리(등록, 수정, 삭제)할 수 있고, 주인이 아니면 읽는 것만 가능하다.

> ### ManyToOne

Entity자신을 기준으로 했을 때 다대일 인 경우. 

*ex. Book Entity 기준으로 다수의 책이 하나의 카테고리에 들어갈 수 있다.*

```java
@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer no;
	
	@Column( name="name", nullable=false, length=100 )
	private String name;
	
    // getter , setter 생략
}

@Entity
@Table( name="book")
public class Book {
	@Id
	@Column(name="no")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer no;
	
	@Column(name="title", nullable=false, length=200)
	private String title;
	
    //Book Entity기준으로 ManyToOne임.
	@ManyToOne
	@JoinColumn(name ="category_no")
	private Category category;
	
	// getter , setter 생략
}
```

- `@JoinColumn`

  외래키 매핑 시 사용. 생략 시, 

  *필드명 + "_" + 참조하는 테이블(ex. Category Entity) 기본키(@Id) 컬럼명*

  - name속성: 매핑할 외래키 이름 지정. 

> ### OneToMany

*ex. Category Entity에서 Book Entity와 연관관계를 맺으며 **양방향**관계를 만들어 보자* 

--> 이미 `Book -> Category`로 단방향을 맺은 상태에서 + `Category -> Book`의 단방향을 추가하여 양방향 만들기.

```java
@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer no;
	
	@Column( name="name", nullable=false, length=100 )
	private String name;

    //category가 주인이 아님을 알려준다. (Book Entity에서 Category 참조시 작성한 필드명)
	@OneToMany(mappedBy="category")
	private List<Book> books = new ArrayList<Book>();
	
	// getter , setter 생략
```

- mappedBy 속성: 양방향 연관관계에서 주인을 정하는 방법. 
  - 주인은 mappedBy 속성을 사용하지 않는다.

> ### OneToOne

비즈니스 특징/확장성에 따라 어떤 Entity가 외래키를 가질 지 정한다.

*ex. 유저와 블로그*(유저 조회 시 자동으로 블로그 Entity도 조회 - User가 블로그 외래키를 가지고 있다.)

```java
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="no")
	private Integer no;
	
	@Column(name="id")
	private String id;

	@OneToOne
	@JoinColumn(name="blog_no")
	private Blog blog;
}


@Entity
@Table(name="blog")
public class Blog {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="no")
	private Integer no;
	
	@Column(name="name")
	private String name;
    
    //양방향 맺을 시
    //@OneToOne(mappedBy = "blog")
    //private User user;
}
```

> ### ManyToMany

다대다 관계일 경우, 각 테이블의 외래키를 복합키로 갖는 pk테이블이 생긴다. 

JPA에서는 `@JoinTable`어노테이션을 통해 생성할 수 있다. 

```java
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="no")
	private Integer no;
	
	@Column(name="id")
	private String id;

	@ManyToMany
	@JoinTable(name="user_product",
				joinColumns = @JoinColumn(name = "user_no"),
				inverseJoinColumns = @JoinColumn(name = "product_no"))
	private List<Product> product = new ArrayList<Product>();
}
```

- `@JoinTable`속성
  - name: 새로 생성할 테이블(중간 테이블)의 이름 정의
  - joinColumns: join을 수행할 **자신의** 칼럼 이름
  - inverseJoinColumns = join을 수행할 **다른 컬럼 이름**작성

- 중간 테이블에서 컬럼 추가하기

  - Cart Entity를 정의한 뒤, User와 Product Entity의 pk를 별도 객체로 정의하여 Cart Entity에서 해당 객체들을 복합키로 갖도록 해야 한다.

  ```java
  @Entity
  @Table(name="cart")
  @IdClass(CartId.class)
  public class Cart {
  	@Id
  	@ManyToOne
  	@JoinColumn(name="user_no", nullable=false)
  	private User user;
  	
  	@Id
  	@ManyToOne
  	@JoinColumn(name="product_id", nullable=false)
  	private Product item;
  	
  	@Column(name="count", nullable=false)
  	private Integer count;
  }
  
  
  public class CartId implements Serializable{
  	private Integer user;
  	private Integer product;
  	
  	// getter setter 생략
  }
  ```

- `@IdClass`어노테이션을 사용하였다.



> 참조

https://victorydntmd.tistory.com/208?category=795879