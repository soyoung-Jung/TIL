# Java Abstract Class   & Interface & Keyword(final, static) & Collections

## 1. Abstract Class

> 정의

바디만 있고 선언만 되어있는 메소드 = 추상메소드 를 가지고 있는 클래스.

> 특징

- 추상클래스를 상속받는 자식 클래스는 추상메소드를 반드시 구현해야 한다.
- 추상클래스는 클래스와 메소드 앞에 `abstract`키워드를 넣어야 한다.
- 추상클래스로 객체를 생성할 수 없다. 하지만 추상클래스를 상속받은 클래스는 객체를 생성할 수 있다.
- 일반 메소드도 포함할 수 있다.

> 장점

오버라이딩을 자식클래스에게 강제시키고 싶을 때 사용할 수 있다.



## 2. Interface

> 정의

모든 메소드가 추상메소드인 클래스

변수: 지정할 수는 있으나 묵시적으로 상수. 즉,  `public static final` 

메소드: void만 쓰더라도`public abstract`로 설정됨.

> 특징

- 클래스라는 키워드를 쓰지 않고 `interface`라는 키워드로 interface를 생성한다.

- interface를 상속받을 때는 `implement`라는 키워드로 상속받는다.
- 메소드는 모두 `public abstract`
- 여러개의 interface를 상속, 즉 implement받을 수 있다.
- 접근제한자는 public과 default만 할 수 있음( 클래스와 동일)
- 클래스와 동일하게 인터페이스를 상속받은 클래스는 해당 인터페이스로 객체를 생성할 수 있다. 
- 인터페이스는 다른 인터페이스를 `extends`키워드를 사용하여 상속받는다.
- 인터페이스의 메소드를 상속받는 클래스에서 구현할 때 접근 제어자는 public이여야 한다.

> 장점

공통 기능의 일관성을 제공한다. 공동 작업을 위한 인터페이스를 제공한다.(협업 시 용이)

> 인터페이스의 사용 이유

1. 완벽한 추상화 달성
2. 다중상속 기능 지원
3. 약한 결합을 이루게 해줌
4. 다형성 사용



> 추상클래스와 인터페이스 예제코드

객체 생성시 타입을 상속받은 추상클래스나 인터페이스로 지정할 수 있다. 타입에 따라 사용할 수 있는 메소드가 변한다.

```java
public class interfaceTest {

	public static void main(String[] args) {

		//Bird 객체를 생성하는 방법
		Bird bird1 = new Bird();
		bird1.eat();
		bird1.walk();
		bird1.fly();
		
		Animal bird2 = new Bird();
		bird2.eat();
		bird2.walk();
		
		Flyable bird3 = new Bird();
		bird3.fly();
		
		//Airplane 객체를 생성하는 방법은 2개
	}

}
```



** 부모 클래스에 기본 생성자가 없는 경우 자식 클래스에서 super로 불러와야 하는 이유 **

super, this 를 사용하여 생성자를 호출하지 않는 경우, 묵시적으로 super(); 이 호출 된다. 이 때, 부모 클래스에 기본생성자가 아닌 어떤 값을 파라미터로 받는 생성자가 있다면, 필요한 것은 super();가 아니라 super(파라미터);가 되어야 한다. 따라서 묵시적으로 생기는 super();로는 부족하기 때문에 명시적으로 super(파라미터);를 써줘야 한다.





## 3. final keyword

- final class

  상속을 못하게 함.

- final method 

  override 못하게 함.

- **final variable**

  **상수, 즉 변하지 못하게 함.**

  선언시 초기화 안 할 수도 있음.(blank final variable) 원래는 선언 시 초기화를 해주어야 함.

final variable

상수는 선언과 동시에 초기화하는 것이 원칙인데, 그것을 안하게 할 수 있음.  생성자에서 초기화시킬 수 있음. 그것이 blank final variable. 

실습: chap06.class1.overriding



## 4. static keyword

- static 변수
  - 클래스 전체에서 사용되는 전역 변수. 각 객체마다 동일한 변수를 사용하기 위해서는 static변수를 쓴다. 객체마다 달라야 하는 변수라면, 논스태틱으로 해야 함. 
  - static변수를 사용하느 메소드는 자동으로 static메소드가 됨.![](C:\TIL\Java\staticConcept.PNG)
  - 다른 클래스에서 스태틱 변수를 참조할 때는 객체를 생성하지 않고 그냥 `class.변수명`으로 하면 된다. --> static 메소드나 변수는 객체 생성과 무관하다.

실습: chap07.class2.statics MyCount  class

```java
public class MyCountTeest {

	public static void main(String[] args) {
		MyCount c1 = new MyCount();
		MyCount c2 = new MyCount();
		
		System.out.println(c1.getSerialNumber());
        //1, c1이 null이 되면 에러가 남. 주소로 찾아가기 때문;...
		c1 = null;
		System.out.println(c1.getCounter()); //static이기 때문에 이것이 권장되지 않음. //2, 객			체 생성을 두번 했기 때ㅔ문에, c1을 null로 주솟값을 초기화 해도, 이 문장은 여전히 실행됨. 
		//클래스 메소드(static메소드)나 클래스 변수(static 변수)는 객체 생성과 무관함.(객체의 주소로 		접근하지 않음. 클래스에 속한 변수나 메소드이기 때문) 하지만 객체명.스태틱변수는 권장하지 않는 사항		임.
		
		System.out.println(c2.getSerialNumber()); //1, 객체 생성마다 serial넘버는 초기화가 됨. 
		System.out.println(MyCount.getCounter()); //클래스명.변수명 으로 하는 것이 더 좋음. //2
		
	}

}

```





## 5.Collections (자료구조)

> 배열의 취약점

배열의 사이즈를 지정해주어야 한다. 

: db에서 각 row를 객체로 갖고 와서 객체 배열로 저장할 때, row가 몇건인지, 즉 객체를 몇개 생성해야되는지를 `select count(*)`로 알아내야 함. 매우 비효율적.

> Collection

객체 저장시, 크기가 자동으로 늘어남.

- 종류

  | 종류      | 특징                                                        |
  | --------- | ----------------------------------------------------------- |
  | Set 계열  | 중복 허용 X, 순서 유지 X                                    |
  | List 계열 | 중복 허용 O, 순서 유지 O                                    |
  | Map 계열  | key와 value의 쌍으로 저장된다.(key, value 모두 객체여야 함. |

collection은 java.util pkg에 존재한다.

> collectoin의 구조도?

![](C:\TIL\Java\CollectionConstructure.PNG)



#### 1) ArrayList

#### 2) HashSet

#### 3) HashMap







> list 객체 만드는 법. ArrayList는 클래스임. 상속관계에 따라 여러가지가 읶음. List타입으로 ArrayLisst를만드는 방법을 가장 많이 씀.
>
> 실습: chap09 ArrayListTest

 chap09 ArrayListTest에서 chap07.object의 마이데이트객체를 arrayList에다가 저장하기. 

실습: personentity를 복사해서 usinglist로 변환해서 만들어보기.

## 6. iterator

어레이리스트에 있는 값을 포륩을 돌면서 꺼낼 수도 있지만, iterator메소드를 사용해 접근할 수도 있음. set이나 list계열에서 사용 가능함. 

hasNext가 트루면 다음 엘리먼트가 있다는 것. hasNExt가 트루인 경우에 계속 돌겠다. 와일문으로! 

어레이리스트를 이터레이터로 변환을 한 뒤, hasnext나 next메소드를 사용한다.

어레이리스트에는 iterator라는 메소드가 있음. 

??뭔말.... 소속관계가 어케됨..?



enhanced가 나오기 전에는, set과 list가 포문도는 법이 달랐음. 그것을 통합을 해준 것 이 iterator.. 그치만 이젠 enhanced for 가 있어서 ㄱㅊ

실습: chap09.collections

## 7. set(has set)

실습: chap09.collections, HashSetTest

 equals 와 hashcocde메소드 둘 다 오버라이딩 해야 함.

개발자가 만든 클래스도 값이 똑같은 경우에 배재시키고 싶으면 이퀄즈와 해시코드메소드를 둘다 오버라이딩 해줘야 함. string, integer와 같은 객체는 이미 오버라이딩 됐음. 

그것을 오버라이딩 할 수 있는 자동툴이 있음.



이퀄즈는 오브젝트 클래스에서 주소를 비교하게 돼있음. 하지만 년월일을 비교하도록 바꿈.

해시코드는 오브젝트 클래스에서 해시코드를 비교하게 돼있음. 하지만 멤버변수를 비교해서 그것이 같으면 동일한 해시코드가 나오게끔 함.



## 8. map

콜렉션이 아니고 맵이라고 하는 인터페이스가 따로 있음. 

키로 특정 밸류를 찾기 때문에 빠름. 

실습: chap09.collections.HashMapTest



키자체가 셋이기 때문에, 순서대로 나오지 않음. 