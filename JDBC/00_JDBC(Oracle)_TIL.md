# JDBC

>###  JDBC란?

Java Database Connectivity.

- 자바 언어에서 Database에 접근할 수 있게 해주는 Programming API 로, java.sql 패키지에 존재한다. 여러 인터페이스와 클래스로 구성되어 있다.
- DB벤더들이 JDBC의 인터페이스를 구현해놓은 드라이버(클래스)를 제공한다.
- JDBC 사용시 특정 벤더에 종속되지 않도록 하기 위해서 노력함. 

> ### JDBC 사용을 위한 환경설정

- JDK 설치
- JDBC 드라이버 설치(DB 종류에 따라 다름)
- 클래스패스 설정(jar를 사용할 수 있도록)

> ### JDBC 코딩 절차

#### 1. Driver 등록(DriverManager)

```java
Class.forName("oracle.jdbc.driver.OracleDriver")
```

`Class.forName` 메소드는 아규먼트로 인터페이스/클래스를 받고, 그것의 객체를 반환해준다. 

드라이버 이름을 잘못주거나 클래스패스가 잘 잡혀있지 않는 등의 클래스를 못찾는 경우를 대비해서 예외 클래스를 만들어서 throws했다. 그래서 우리는 꼭 캐치를 해서 받아야한다. **=> 예외처리를 해야한다.**

팩토리 메소드(객체를 만들어내는 메소드)라고 할 수는 없지만, 팩토리 메소드와 동일한 목적을 갖고 있다. = 특정 DB에 종속되지 않게 하는 것.

*** `new ORacleDriver();`와 같은 형태로 쓰지 않는 이유 ***

위와 같이 쓸 경우 DB벤더의 클래스를 import해줘야 한다 = DB가 바뀔때마다 바꿔줘야 한다. = DB에 종속된다.

==> DB에 종속되지 않기 위해서.



#### 2. DBMS와 연결(Connection)

해당 Driver로부터 Connecton instance를 획득한다. 

```java
Connection conn = DriverManager.getConnection(String url, String user, String password) 
```

`getConnection` 메소드에서 throws를 했기 때문에 try-catch문으로 **예외처리를 해주어야 한다**. try 문 밖에 url, user, password의 변수를 설정할 수 있다.

Connection의 타입으로 connection하위 클래스인 오라클에서 제공하는 클래스(DriverManager)의 객체가 만들어짐. 

이렇게 만들어진 Connection Instance(conn)의 이름은 오라클의 경우 `T4CConnection`이다.



#### 3. Statement 생성(Statemenet)

위에서 획득한 Connection instance로부터 Statement instance를 획득한다.

```java
Statement stmt = conn.createStatement();
```

Statement instance(stmt)의 결과는 `OracleStatementWrapper`이다.

statement의 역할: sql을 db에 전송.



#### 4. SQL전송(ResultSet) & 5. 결과 받기

1. Statement method를 이용하여 SQL 실행

- Statement의 method는 select와 update에 따라 다르다.

  ```java
  String query = "select ID, LAST_NAME from EMP";
  
  stmp.executeQuery(query); //SELECT의 경우
  stmp.executeUpdate(query); //UPDATE의 경우, 오토커밋이 된다.
  ```

2. 실행 결과를 

   - ResultSet 타입의 변수에 저장한다.(select)

   - int 타입의 변수에 저장한다.(DML) --> 업데이트된 row의 갯수가 나옴.

   ```java
   ResultSet rset = stem.executeQuery(query);		
   ```

3. 변수에 while문을 돌려 가며 처리한다. 출력하고자 하는 column의 타입에 따라 다른 타입으로 받아올 수 있다. 컬럼명 혹은 인덱스로 받아올 수 있다.

   ```java
   while(rset.next()) {
    System.out.println( rset.getString( "ID" ) +  rset.getString( 2 ) );
   }
   ```

   - 예시: `rset.getString("컬럼명")`, `rset.getDate(index)`

   ** update의 경우 결과값이 하나밖에 없기 때문에 while문이 아닌 if문으로 해도 된다.

   

#### 6. 닫기

쓴 자원을 return해주어야 한다. 예외가 나더라도 실행될 수 있도록 finally안에서 처리해야한다. 또한 그 안에서 예외처리를 해주어야 한다.  null을 check하기 위한 조건문도 다는 것이 좋다.

```java
finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
```

- select

  rset.close();

  stmt.close();

  conn.close();

- DML

  stmt.close();

  conn.close();

> #### 상속 관계 표현 그림

![](C:\TIL\JDBC\JDBC_Interface_Inheritance_Relation.PNG)



> ### PreparedStatement

![](C:\TIL\JDBC\JDBC_PreparedStatement.PNG)

sql문 안에 ?를 넣고 ?의 값은 setString(인덱스,값)으 넣어준다.(int의 경우 setInt)

sql문은 prepareStatement()안에 넣고, executeUpdate();를 쓸 때는 넣지 않는다.

```java
PreparedStatement pstmt =
conn.preparedStatement( “ Insert into emp
values ( ?, ? ) “ );
pstmt.setString( 1, "21421" );
pstmt.setInt( 2, "Kim" );
pstmt.executeUpdate();
pstmt.setString( 1, "32211" );
pstmt.setInt( 2, "Hong" );
pstmt.executeUpdate();
```



> ### DAO vs. DTO

> #### DAO(Data Access Object)

DB에 접근하고 조작하기 위해 만든 object로 그 안에서 connection객체를 생성하는 등의 로직이 짜여있다. 

> #### DTO(Data Transfer Object) VO(Value Object, Read Only)로

로직이 없고, 순수 data 객체로, get/set메소드만 가진 클래스이다. DB에 존재하는 테이블의 데이터를 저장하는 객체이다.

> #### ORM

객체(아마도 VO)??와 관계형 데이터베이스의 데이터를 자동으로 매핑(연결)해주는 것

매핑 공식은 다음과 같다. 

> #### 결론

DAO로 DB에 접근해서 가져온 값을 DTO(VO)에 저장한다.

= DAO는 DB에 접근하여 테이블을 조작한 후, 쿼리의 결과를 DTO객체에 담아 servlet에 전달한다.





