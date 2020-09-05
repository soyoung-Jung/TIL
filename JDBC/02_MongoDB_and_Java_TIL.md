# MongoDB와 Java (MongoDB Java에서 handling하기)

### 코딩순서

#### 0.  dependency 설정

#### 1. mongoClient  만들기

```java
MongoDatabase database = mongoClient.getDatabase("java_db");

```



#### 2. db연결하기

```java
MongoDatabase database = mongoClient.getDatabase("java_db");

```

mongoDB의 쿼리인 use database 명령어와 동일하다.

#### 3. collection만들기

```java
MongoCollection<Document> collection = database.getCollection("java_col");

```

인자값으로 collection명을 주고, 해당 collection이 이미 존재하지 않는다면 새롭게 생성한다.

* `createCollection();`의 경우, collection이 이미 존재하는 경우 오류가 나기 때문에 권장되지 않는다. 하지만,  document를 삽입하지 않아도 collection을 볼 수 있다는 장점이 있다.



#### 4. document 만들기

```java
Document user1 = new Document("_id", 1)
				.append("userid", "mongo")
				.append("name","몽고")
				.append("gender", "여")
				.append("addr", "서울");
```

key와 value의 형태로 field와 value를 인자로 주면서 document 객체를 생성한다. append의 방식으로 field를 추가할 수 있다.

- 여러 객체를 담을 때, ArrayList에 담아와서 처리한다.



- 쿼리 코딩 시 try-catch 에러가 나지 않는 이유
  - SQLException은 Exception의 하위 클래스. 즉, checkedException(컴파일러가 체크해줌)

  - mongoWriteException은 RuntimeException의 하위 클래스임. 따라서 컴파일 타임에는 트라이캐치하라는 에러가 나지 않음.  따라서 개발자가 알아서 해야 함.  즉, uncheckedException. 

    

원하는 조건주기 2개방법.

1. Document를 만들어서 find안에 집어넣기. 

2. Filters.eq를 만들어서 find안에 집어넣기.

   Filters.eq("gender(key)", "여(value)")

조건을 여러개줄때는 document로 중첩해서 주기. 

