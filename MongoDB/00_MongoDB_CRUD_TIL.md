# MongoDB CRUD

## 1. Create/Insert

> DB 생성

지정한 DB명이 이미 존재하는 경우, 해당 DB로 switch되고 없는 경우 DB가 생성된다.

```sql
use [DB명]
```



> collection 생성

```sql
db.createCollection("users")
```



> 데이터베이스 현황 확인

```sql
db.stats()
```



> document 입력

- insertOne: 한개의 document 생성
- insertMany:  list of document  생성

collection이 존재하지 않는다면, document 삽입과 동시에 collection이 생성된다.

```sql
db.users.insertOne({field:value, field:value})
db.users.insertMany({ user_id: "bcd002", age:25, status:"B" },
 { user_id: "bcd003", age:50, status:"A" },
 { user_id: "bcd004", age:35, status:"A" },
 { user_id: "abc001", age:28, status:"B" })
```



## 2. Read

> document 읽기(검색)

- findOne: 매칭되는 한개의 document 검색(순서상 첫번째 document)
- find: 매칭되는 list of document 검색

```sql
db.users.find({조건}, {찾고자 하는 feild:1, 찾지 않으려는 field:0})
db.users.findOne()
```



>  sql vs. mongoDB 연산자

| sql        | MongoDB                          |
| :--------- | -------------------------------- |
| =          | $eq                              |
| \>         | $gt                              |
| \>=        | $gte                             |
| in         | $in                              |
| <          | $lt                              |
| <=         | $lte                             |
| !=         | $ne                              |
| not in     | $nin                             |
| like "%bc" | $regex: /bc/                     |
| order by   | .sort({user_id:1}) - desc경우 -1 |



> sql과 비교한 mongoDB 예제

```sql
//6.document select all
db.employees.find()

7.SELECT * FROM employees WHERE department='sales';
db.employees.find({department:'sales'})

//8.select * from employees where hire_date > "2017-01-01"
db.employees.find({hire_date:{$gt:ISODate("2017-01-01")}})

//9.select number,last_name,first_name from employees
db.employees.find({},{number:1, last_name:1, first_name:1, _id:0})

//11.select * from employees where number = 1001 and department = 'sales'
db.employees.find({number:1001, department:'sales'})

//12.select * from employees where number = 1002 or department = 'sales'
db.employees.find({$or:[{number:1002},{department:'sales'}]})

//13.select * from employees where number in (1001,1003)
db.employees.find({number:{$in:[1001,1003]}})

//14.select * from employees where number not in (1001,1003)
db.employees.find({number:{$nin:[1001,1003]}})

//16.select * from employees where firs_name like '%a%'
db.employees.find({first_name:{$regex:/a/}})

//17.select * from employees where first_name like 'B%'
db.employees.find({first_name:{$regex:/^B/}})

//18.select * from employees where last_name like '%h'
db.employees.find({last_name:{$regex:/h$/}})

//19.select * from employees order by department
db.employees.find({}).sort({department:1})

//20.select * from employees order by hire_date desc
db.employees.find({}).sort({hire_date:-1})

//21.select count(*) from employees
db.employees.count()

//24.select * from employees where status = 'A'
db.employees.find({status:'A'})

//26.status column이 존재하는 document 조회
db.employees.find({status:{$exists:true}})

//27.status column이 존재하지 않는 document 조회
db.employees.find({status:{$exists:false}})

//32.select distinct(department) from employees
db.employees.aggregate([{$group:{_id:'$department'}}])

//35.select * from employees where salary > 45000 and salary <= 60000
db.employees.find({salary:{$gt:45000, $lte: 60000}})

//36.update employees set salary = 57000 where number = 1005
db.employees.updateMany({number:1005},{$set:{salary:57000}})

//38.update employees set salary = salary + 100 where number in (1005,1006)
db.employees.updateMany({number:{$in:[1005, 1006]}}, {$inc:{salary:100}})
db.employees.find()

//39.delete from employees where status = 'A'
db.employees.deleteMany({status:'A'})
```



## 3. Update

```sql
db.users.updateOne()
db.users.updateMany({조건}, {$set:{field:value}})
```



## 4. Delete

```
db.users.deleteOne()
db.users.deleteMany({filed:value})
```



