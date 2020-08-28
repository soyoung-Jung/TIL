# MongoDB Agrregation

> ### aggregation 함수의 사용 이유

1. 빅데이터 가공을 위한 새로운 방식의 필요
2. 기존 find() 함수의 제약
3. grouping, filtering과 같은 집계함수를 적용하기 위해서



> ### pipeline: aggregation의 가공 방식

한 데이터 처리 단계의 출력이 다음 단계의 입력으로 이어지는 형태로 연결된 구조.

아래는 pipeline 개념을 시각화로 나타낸 것. 

<img src="C:\TIL\TIL\MongoDB\mongoAssets\다운로드.png" style="zoom: 150%;" />



> ### aggregation 함수의 형태

```sql
db.[collection명].aggregate([{$func1},{$func2}])
```

`[ ]`안에 모든 내용이 들어가야 한다.

예시

```sql
db.orders.aggregate([
    {
    	$group: {_id: "$cust_id", total: {$sum: "$amount"}}
    },
    {
    	$match: {total: {$gte:1000}}
    }
])
```



> ### SQL vs. MongoDB Aggregation

| MongoDB  | SQL             |
| -------- | --------------- |
| $match   | where / having  |
| $group   | group by        |
| $sum     | sum() / count() |
| $sort    | order by        |
| $project | select          |
| $limit   |                 |



#### 1. $group & $sum ()

`$group:{_id: [그룹으로 지정할 필드], [alias]: {$실행시킬 함수: 함수가 적용되는 필드}}`

`_id`는 필수로 들어가야 한다.

필드 명 작성 방법: `"$필드명"` 

- 예시	

```sql
db.orders.aggregate([
	{
		$group:{
			_id: "$cust_id", --group by를 지정하지 않는 경우 null로 설정
			total: {$sum:"$price"} --sum을 1로 지정할 경우 count(*)와 																동일
		}
	}
])
```

- group by를 여러 필드로 설정할 경우: _id 밑에 중괄호를 추가하여 구분한다.

```sql
    {
        $group: {
            _id: {
                state: "$state",
                city: "$city"
            },
            total_pop: {$sum:"$pop"}
        },

    },
    {
        $match: {
            "_id.city": 'POINT BAKER' --위에서 구분한 그 그룹의 city에다가 조건을 주기 위해서
        }
    }
])
```



#### 2. $match

`$match:{alias 혹은 지정 필드:조건}`

$match는 조건을 주는 함수로서, SQL의 where절, having절과 같다. 

where절의 경우 $match조건을 선행으로 준 뒤 $gruop함수를 작성한다.

having 절의 경우 $match 함수를 $group함수 뒤에 작성한다.

- 예시

```sql
db.zips_col.aggregate([
    {
        $group: {
            _id: "$state",
            total_pop:  {$sum:"$pop"}
        }
    },
    {
        $match: {
            total_pop: {$gt:10000000} --위에서 설정한 alias를 사용
        }
    }
])
```



#### 3. $sort

`$sort: {필드 혹은 alias: 1 혹은 -1}`

SQL의 order by와 동일하다.

ascending: 1

descending: -1

- 예시

```sql
db.zips_col.aggregate([
    {$group:{_id:"$state", state_pop:{$sum:"$pop"}}},
    {$match: {state_pop: {$gte:1000*10000}}},
    {$sort: {state_pop: -1}},
])
```



#### 4. $project

`$proejc: {필드명:0 혹은 1}`

필드를 select하는 경우: 1

필드를 seelct하지 않는 경우: 0



#### 5. $limit

`$limit: {필드명: 보여줄 만큼의 갯수}`

몇 개의 document를 보여줄 것인지 지정한다.

- project와 limit의 예시

```sql
db.zips_col.aggregate([
    {
        $group: {
            _id: {
                state: "$state",
                city: "$city"
            },
            avg_pop: {$avg: "$pop"}
        }
    },
    {
        $match: {
            avg_pop: {$gt:30000}
        }
    },
    {
        $project: {
            "_id.city":1, avg_pop:1 -- _id에서 지정한 city만 보여준다. 설정하지 않을 						경우 default로 _id가 나오며 그 결과 city와 state 모두 출력된다.
        }
    },
    {
        $limit: 3
    }
])
```



#### 6. $unwind

: document내의 배열 필드를 기반으로 각각의 document로 분리

- 배열 필드 생성 예시

```sql
db.inventory.insertOne({
    id:1, item:'ABC1', sizes:["S","M","L"]
})
db.inventory.insertOne({
    id:1,item:"EDF1",sizes:["S","M","L","XL"]
})
db.inventory.find()
```

![](C:\TIL\TIL\MongoDB\mongoAssets\캡처.PNG)

- $unwind함수 실행

```sql
db.inventory.aggregate([
    {
        $unwind:"$sizes"
    }
])
```

![](C:\TIL\TIL\MongoDB\mongoAssets\캡처1.PNG)

- $group과 함께 $unwind 실행

```sql
db.orders.aggregate([
    {
        $unwind:"$items"
    },
    {
        $group:{
            _id:"$cust_id",
            qtysum:{$sum:"$items.qty"}
        }
    }
])
```

![](C:\TIL\TIL\MongoDB\mongoAssets\캡처2.PNG)