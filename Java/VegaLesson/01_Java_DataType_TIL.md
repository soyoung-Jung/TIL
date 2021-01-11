# DataType

## 1. Primitive Data Type

기본 데이터 타입으로 단일 값을 갖는다. 

| type                        | default value                      |
| --------------------------- | ---------------------------------- |
| 논리형: true/false          | false                              |
| 문자형: char                | '\u0000'                           |
| 정수형: byte/short/int/long | int(정수형 literal의 data type)    |
| 실수형: float/double        | double(실수형 literal의 data type) |



## 2. reference Data Type (객체에 대한 주소 값)

primitive data type을 제외한 모든 data는 reference data type에 해당한다.

reference type의 변수는 `new` 를 이용해 객체의 주소를 가르키는 역할을 한다.

객체 자체는 heap영역에 올라가 값을 가진다. 값이 할당되지 않은 경우 default value는 `null`.

![](C:\TIL\Java\reference data type.PNG)



> 객체의 생성과 메모리 할당 순서

- 객체 생성 방법

   `[클래스명] [변수명] = new [클래스명]();` ex. Dog d = new Dog();

- 객체 생성시, 메모리 할당 순서
  1. 메모리 할당 (stack에 reference 변수, heap에 객체)
  2. 객체의 멤버 변수 초기화
     - default value로 초기화
     - 명시적 초기화(내가 설정한 값으로 초기화)
     - constructor에 의한 초기화(생성자에서 인자로 값을 받아서)
  3. 객체의 주소 값이, reference 변수에 할당.



## 3. Array

array를 통해 동일한 타입의 여러 데이터를 하나의 이름으로 관리할 수 있다.

array는 객체이며, 생성도 객체와 마찬가지로 `new`키워드를 통해 한다. 

- array 선언

  `int[] numbers;`

- array 생성

  `numbers = new int[10]`, []안에 배열의 크기를 넣는다.

- array 초기화

### 1. primitive type Array

1. 선언

   `int[ ] numbers = null;` 

   - stack에 numbers변수가 할당되고 그 안에 null 값이 있다.

   - heap영역에 아무것도 생기지 않은 상태.

2. 생성

   `numbers = new int[10];`

   - heap영역에 int의 default 값인 0을 담은 공간 10개가 생기고 그것을 가르키는 하나의 주소가 생긴다. 

   - stack영역의 numbers는 heap영역의 배열의 주소를 reference하고 있다.

3. 초기화

   `for ( int inx = 0 ; inx < numbers.length ; inx ++ ) `

   `{numbers[inx] = inx + 1; System.out.println( numbers[inx] ); }`

   - stack영역의 numbers변수는 여전히 배열의 주소를 가르키고 있다.
   - heap영역의 초기값이 있던 공간 10군데에 1~10의 값이 하나씩 들어간다.

### 2. reference type array

1. 선언

   `Date[ ] dates = null;` 

   primitive와 동일.

2. 생성

   `dates = new Date[10];`

   primitive와 동일. 대신 객체의 default값인 null로 10개의 공간이 채워짐.

3. 초기화

   ` dates[0] = new Date( 22, 7, 1964 ); ` 

   `dates[1] = new Date( 12, 7, 2006 ); `

   `System.out.println( dates[0].getDay() );`

   ![](C:\TIL\Java\referenceArray.PNG)

   

