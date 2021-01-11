# Java Polymorphism

> 다형성이란?

한 reference변수가 다른 형태의 객체를 참조할 수 있다.

= 한 가지 타입(객체타입)을 갖는 변수가 다른 객체를 참조할 수 있음. 

= 부모 클래스의 타입으로 변수의 타입을 지정하고, 그 변수는 자식클래스로 생성한 객체를 담을 수 있음.



ex. Manager 클래스가 Employee 클래스를 상속하고 있는 경우,

Employee emp = new Manager(); (O)



> Heterogenous Collection(polymorphic arguments)

다른 Class의 객체로 이루어진 집합.

```java
 Employee[] staff = new Employee[1024];
 staff[0] = new Manager();
 staff[1] = new Employee();
 staff[2] = new Engineer();
```

Employee타입의 배열 안에 각각 다른 클래스의 객체가 들어가 있다.  staff에 for문을 돌면서 각 객체별로 if와 instanceof를 사용하여 다른 메소드 등을 구현할 수 있다.

** 부모 클래스에 없는 메소드를 사용하고 싶을 때는 형변환을 한 후 사용해야 한다.



>다형성의 장점

하나의 부모 객체배열에 자식 객체들을 담아서 메소드를 자식객체별로 처리할 수 있다. 즉, 객체별로 다른 메소드를 따로 작성할 필요가 없어진다.