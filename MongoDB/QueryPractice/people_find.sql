use test_db

db.createCollection("emp")

db.emp.insertOne({
emp_id:100,
emp_name: "gildong",
status: "A"
})

db.emp.find()

db.emp.isCapped()

db.createCollection("emp2", {capped:true,size:10000})

db.emp2.isCapped()

db.emp2.stats()

db.emp.stats()

show collections

db.emp2.drop()

//update emps set hire_date = date where id=100 // sql에서 컬럼 추가
db.emps.updateMany({},{$set:{hire_date: new Date()}})  //앞 중괄호는 웨어조건, 뒤 중괄호는!
db.emps.find()

db.emps.updateMany({},{$unset:{hire_date: ""}})

db.emps.stats()

db.emps.getIndexes()

db.emps.createIndex({emp_id:1})

use mymongo_db

db.createCollection("people")


db.people.insertOne(
 { user_id: "bcd001", age:45, status:'A' }
)

db.people.insertMany([
{ user_id: "bcd002", age:25, status:"B" },
 { user_id: "bcd003", age:50, status:"A" },
 { user_id: "bcd004", age:35, status:"A" },
 { user_id: "abc001", age:28, status:"B" }
])

//select * from people
db.people.find({}) //{}생략 가능

//select user_id, status from people
db.people.find({}, {user_id: 1, status: 1, _id: 0}) //_id는 나오는 것이 디폴트라 빼고 싶으면 0으로 해야 함.

//select * from people where status = 'A'
db.people.find({status:'A'})

//select user_id, status, age from people where status = 'A'
db.people.find({status:'A'}, {status:1, user_id:1, age:1, _id:0})

//select user_id, status, age from people where status!= "A"
db.people.find({status:{$ne:'A'}}, {user_id:1, status:1, age:1, _id:0})

//user_id abc 001이 아닌 document
//select user_id, status, age from people where user_id != "abc001"
db.people.find({user_id:{$ne:'abc001'}},{user_id:1,status:1,age:1,_id:0})

//select * from people where status = 'A' and age = 50
db.people.find({status:"A", age:50})

//select * from people where status = 'A' or age = 50 -- and보다 더욱 복잡함.
db.people.find({$or:[{status:"A"},{age:50}]})

//select * from people where status = "B" or age = 25
db.people.find({$or:[{status:"B"},{age:25}]})

//select * from people where age > 25 and age <= 50
db.people.find({age:{$gt:25,$lte:50}})

//select * from people whree age in (25, 45)
db.people.find({age:{$in:[25, 45]}})  //같은 필드에 대해서 or를 줄때는 in을 사용하면 됨.

////select * from people whree age not in (25, 45)
db.people.find({age:{$nin:[25, 45]}})

//select * from peple where user_id like '%cd%'
db.people.find({user_id:{$regex: /cd/}})

//select * from peple where user_id like 'bc%' start with
db.people.find({user_id:{$regex: /^bc/}})

//select * from peple where user_id like '%01' end with
db.people.find({user_id:{$regex:/01$/}})

//select * from peple where status = 'A' order by user_id
db.people.find({status:'A'}).sort({user_id:1})

//select * from peple where status = 'A' order by user_id desc
db.people.find({status:'A'}).sort({user_id:-1})

//select user_id, age from peple where age > 40 and user_id like %cd% order by user_id
db.people.find({age:{$gt:40},user_id:{$regex:/cd/}},{user_id:1,age:1,_id:0}).sort({user_id:1})

//select user_id,age,status from people where status in ('A', 'B') and age>=25 and age <= 45
db.people.find({status:{$in:['A','B']},age:{$gte:25, $lte:45}},{user_id:1,age:1,status:1,_id:0}).sort({user_id:-1})
//위에거 다시해보기1!!


//select * from where age < 20 limit 1
db.people.findOne({age:{$gt:20}})

//select count(*) from people
db.people.count()

//select count(*) from people where age > 30
db.people.count({age:{$gt:30}})

//user_id 필드가 없는 document 추가하기
db.people.insertOne({status:"B", age:25})
db.people.find()

//select count(user_id) from peple : use_id 컬럼의 값이 존재하는 row count
db.people.count({user_id:{$exists:true}})

//select distinct(status) from people
db.people.aggregate([{$group:{_id:"$status"}}]) //agrregate는 필수족으로 []이 필요함. _id로 그룹핑 해야되기 때문에 씀.

//첫번째꺼 스킵하고 3개를 보여주라는 것.
db.people.find().limit(3).skip(1)

//update people set status = 'c' where age > 45
db.people.updateMany({age:{$gt:45}},{$set:{status:'C'}})
db.people.find()

//update people set status = 'D' where age <=25
db.people.updateOne({age:{$lte:25}},{$set:{status:'D'}})
db.people.find()

//update people set age = age + 3 where status = 'A' 더할 때는 set이 아니라 inc
db.people.updateMany({status:'A'},{$inc:{age:3}})
db.people.find()

//RDB와 다르게 없는 필드(컬럼)를 업데이트해도 됨, 조건만 주면.
db.people.updateMany({age:28},{$set:{name:'gildong'}})
db.people.find()
db.people.find({name:{$exists:true}})

//delete from people where status = 'D'
db.people.find()
db.people.deleteMany({status:'D'})

//name 필드가 있는 document를 삭제한다.
db.people.deleteMany({name:{$exists:true}})
db.people.find()

//query plan 보기
db.people.find().explain()

