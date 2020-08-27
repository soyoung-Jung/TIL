# Mongo DB Intro



### 0. 설치 

- MongoDB 설치

- robo3t 설치

  몽고디비 연결을 지원해주는 툴(MongoDB 관리 GUI 툴)



### 1. NoSQL 이해

- NoSQL: Not only SQL, sql뿐만 아니라 다른 기능을 제공한다는 뜻.

- 목적: 기존 RDMBS의 한계를 극복하기 위해. 

- 특징 

  - 고정된 스키마를 사용하지 않는다. = 다이나믹 스키마.
  - 수평적 확장에 적합하다.
  - 50% write > 인 경우 RDBMS 는 성능 저하 또는 불안정하기 때문에 NoSQL이 고려됨.

- 유형

  - key-value - Redis, Dynamo
  - document - MongoDB
  - graph - Neo4j
  - wide - Cassandra(대용량 데이터베이스를 넣을 수 있음)

  

### 2. MongoDB의 Terms/Concepts

| SQL      | MongoDB                     |
| -------- | --------------------------- |
| database | database(collection의 집합) |
| table    | collection(document의 집합) |
| row      | document(json 형태)         |
| column   | field                       |
| index    | index                       |



> document의 형태

key와 value를 가진 json데이터의 형태. 아래와 같이 editon이라는 키 값에 또다른 json형태가 들어갈 수 있다.

```json
{ "id":"01", "language":"java", "edition": { "first": "1st", "second":"2nd", "third":"third" } }
```

- 각각의 Document (Row) 마다 Field(Column)의 개수가 달라도 상관없다. RDMBS의 경우 column이 없는 경우, null값으로 들어간다.

  ![image-20200827111159826](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200827111159826.png)

### 3. MongoDB 생성 과정  - robo3t(GUI) & DataGrip

mymongo_db 생성(db생성) => my_col 생성(table 생성) => document 추가(row 추가) 의 과정을 거침. 

![image-20200827105757415](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200827105757415.png)

- _id 필드: auto increment로 id를 만들어주는 필드. 자동으로 생성됨. 



