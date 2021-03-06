# Java Inheritance

> 상속이란?

` class 자식클래스 extends 부모클래스` 형태로 작성하며, 부모 클래스의 변수와 메소드를 자식클래스가 자신의 것처럼 사용할 수 있다. 상속을 통해 코드재사용성을 높일 수 있다.

> 접근제한자

| 접근 제한자                 | 허용 범위                                               |
| --------------------------- | ------------------------------------------------------- |
| private                     | 클래스 내부에서만 참조 가능                             |
| default(아무것도 안쓰는 것) | 같은 디렉토리(같은 pkg)내의 다른 클래스에서 참조 가능   |
| protected                   | default 참조 범위 & 상속 관계 시, pkg관계없이 참조 가능 |
| public                      | 어디서나 참조가능                                       |

** 클래스는 public, default만 사용할 수 있음.

> overriding

자식 클래스에서 부모 메소드의 기능에 맞게 메소드 Body내용을 정의하는 것.

overriding시, **자식 메소드의 접근 범위  >= 부모 메소드의 접근범위**

> overloading

같은 메소드의 이름을 사용하되, 파라미터의 타입이나 갯수를 다르게 함으로써 메소드를 다중정의하는 것.

constructor도 overriding가능하다. 

> super

부모 객체를 가르킨다.

> constructor

부모 클래스에 기본 생성자가 없으면 자식 클래스에서 부모 클래스의 생성자를 호출해줘야 함. super를 꼭 해줘야 함.

> 추상클래스

추상메소드를 포함하고 있는 클래스로, 추상클래스를 상속받는 자식클래스는 추상메소드를 반드시 상속해야한다.