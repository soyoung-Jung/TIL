# [기초-입출력] 정수 2개 입력받아 그대로 출력하기

### 문제

https://codeup.kr/problem.php?id=1013

### 풀이

```python
a, b = map(int,input().split())
print(a,b)
```



### 관련 개념

> #### map 함수

`map` 함수는 반복가능한 `iterable`객체를 받아서, 각 요소에 함수를 적용해주는 함수.

```python
map(적용시킬 함수, 적용할 요소들)
```



> #### split 함수

문자열 나누기 함수.

```python
문자열.split()
문자열.split("-")
문자열.split(",")
```

괄호 안에 아무 값도 넣어주지 않으면 공백을 기준으로 문자열을 나눈다. 나눈 값은 리스트에 하나씩 들어가게 된다.