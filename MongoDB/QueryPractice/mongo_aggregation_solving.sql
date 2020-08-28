use zips_db
db.zips_col.count()

db.zips_col.find().limit(20)

//select distinct(state) from zips
db.zips_col.aggregate([
    {
    $group: {_id:"$state"}
    }
])

//1. SQL: SELECT COUNT(*) AS count FROM zip
db.zips_cols.count() //아래와 같음. 이 정도는 이렇게 짧게 할 수 있음.
​db.zips_col.aggregate([
    {
        $group: {
            _id: null,
            count: {$sum:1}
        }
    }
])

//2. SQL: SELECT SUM(pop) as total_pop AS count FROM zip
db.zips_col.aggregate([
    {
        $group:{
            _id: null,
            total_pop: {$sum:"$pop"}
        }
    }
])
​

//3. SQL: SELECT state, SUM(pop) as total_pop FROM zip GROUP BY state
db.zips_col.aggregate([
    {
        $group: {
            _id: "$state",
            total_pop:{$sum:"$pop"}
        }
    }
])
​

//4. SQL : select city, sum(pop) as total_pop from zip group by city
db.zips_col.aggregate([
    {
        $group: {
            _id: "$city",
            total_pop: {$sum: "$pop"}
        }
    }

])
​

//5. SQL: SELECT state, SUM(pop) as total_pop FROM zip GROUP BY state ORDER BY as total_pop
db.zips_col.aggregate([
    {
        $group: {
            _id: "$state",
            total_pop: {$sum:"$pop"}
        }
    },
    {
        $sort: {
            total_pop: 1
        }
    }
])
​

//6. # SQL: SELECT COUNT(*) FROM zip WHERE state = 'MA'
db.zips_col.aggregate([
    {
        $match: {
            state: 'MA'
        }
    },
    {
        $group: {
            _id: null,
            count: {$sum:1}
        }
    }

])
​

//7. select state,sum(pop) as total_pop from zip where state = 'MA' group by state
db.zips_col.aggregate([
    {
        $match: {
            state: 'MA'
        }

    },
    {
        $group: {
            _id: "$state",
            total_pop: {$sum:"$pop"}
        }
    }

])
​//7.1 select city,sum(pop) as total_pop from zip where state = 'MA' group by state
db.zips_col.aggregate([
    {
        $match: {
            state: 'MA'
        }

    },
    {
        $group: {
            _id: "$city",
            total_pop: {$sum:"$pop"}
        }
    }

])

//7.2 select state,sum(pop) as total_pop from zip where state in ('DE', 'MS') group by state
db.zips_col.aggregate([
    {
        $match: {state: {$in:['DE','MS']}}
    },
    {
        $group: {
            _id: "$state",
            total_pop: {$sum:"$pop"}
        }
    }
])
​

//8. SELECT state, SUM(pop) as total_pop FROM zip GROUP BY state HAVING SUM(pop) > 10000000
db.zips_col.aggregate([
    {
        $group: {
            _id: "$state",
            total_pop:  {$sum:"$pop"}
        }
    },
    {
        $match: {
            total_pop: {$gt:10000000}
        }
    }
])
​

//9.1000만 이상의 state 별 총 인구를 state_pop 필드명으로 출력하고 _id는 출력하지 않기
//_id를 0으로 주기? -> 그룹바이 하고 매치한 다음에 프로젝트 연산자 쓰면 됨.
db.zips_col.aggregate([
    {
        $group: {
            _id: "$state",
            state_pop: {$sum:"$pop"}
        }
    },
    {
        $match: {
            state_pop: {$gt: 1000*10000}
        }
    },
    {
        $project: {
            _id:0,state_pop:1
        }
    },
    {
        $limit: 5
    }
])

//9.1 NY state의 city별 총 인구수를 city_pop 필드명으로 출력하고 _id는 출력하지 않는다.
db.zips_col.aggregate([
    {
        $match:{state:"NY"}
    },
    {
        $group:{
            _id: "$city",
            city_pop: {$sum:"$pop"}
        }
    },
    {
        $project:{
            _id:0, city_pop:1
        }
    },
    {
        $limit:5
    }
])

//10.1000만 이상의 state만 내림차순 정렬하여 3개만 가져오기
​db.zips_col.aggregate([
    {
        $group: {
            _id:"$state",
            total_pop: {$sum:"$pop"}
        },
    },
    {
        $match: {
            total_pop:{$gte: 1000*10000}
        }
    },
    {
        $sort: {total_pop:-1}
    },
    {
        $limit: 3
    }
])
//10.1000만 이상의 state만 내림차순 정렬하여 3개만 가져오기, _id는 출력하지 않고 인구수만 출력
db.zips_col.aggregate([
    {$group:{_id:"$state", state_pop:{$sum:"$pop"}}},
    {$match: {state_pop: {$gte:1000*10000}}},
    {$sort: {state_pop: -1}},
     {$project:{_id:0, state_pop:1}},
    {$limit:3}

    ])

//11.1000만 이상의 state 별 총 인구를 state_pop 필드명으로 출력하고,

// _id는 출력하지 않으며, 가장 많은 인구를 가진 3개만 출력하기

​

​

//12. select state, city, sum(pop) as total_pop from zip group by state,city
db.zips_col.aggregate([
    {
        $group: {
            _id: {
                state: "$state",
                city: "$city"
            },
            total_pop: {$sum:"$pop"}
        }
    }
])

db.zips_col.aggregate([
    {
        $group: {
            _id: {
                state: "$state",
                city: "$city"
            },
            total_pop: {$sum:"$pop"}
        }
    },
    {
        $project:{"_id.state":1, total_pop:1}
    }
])
​

//13. select state, city, sum(pop) as total_pop from zip GROUP BY state, city HAVING city = 'POINT BAKER'
db.zips_col.aggregate([
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
            "_id.city": 'POINT BAKER'//위에서 구분한 그 그룹의 city에다가 조건을 주기 위해서
        }
    }
])



//14. SELECT AVG(pop) FROM zip GROUP BY state, city
db.zips_col.aggregate([
    {
        $group: {
            _id: {
                state: "$state",
                city: "$city"
            },
            average: {$avg:"$pop"}
        }
    }
])
​

//15. select state,city, avg(pop) as avg_pop from zip GROUP BY state, city having avg_pop > 30000



l