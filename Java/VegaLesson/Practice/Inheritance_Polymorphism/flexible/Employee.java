package oop.flexible;

/*
 * 추상클래스의 특징
 * Employee emp = new Employee() -- 불가, 추상 클래스 스스로 객체 생성 불가 emp.manageSalary() 해도 그 안에 내용이 없기 때문..
 * Employee mgr = new Manager() -- 가능. 자식 객체를 생성해서 자신 타입으로 설정하는 것은 가능.
 * Employee clerk = new MereClere() -- 가능.
 * abstract method와 concrete method 모두 선언 가능.
 */
public abstract class Employee {

	private String name;
	protected double salary;

	public Employee() {
		super();//object클래스에서 상속. 이걸 안쓴 것과 동일함. 자동으로 추가됨. 생성자 만들시ㅏ
	}//ㅇㅣ것잉 없으면 에러남. 
	
	//Constructor overloading
	public Employee(String name, double salary) {
		System.out.println("employee 생성자 호출");
		this.name = name;
		this.salary = salary;
	}

	//concrete method: 바디가 있는 메소드
	public String getName() {
	    return this.name;
	}

	public double getSalary() {
	    return this.salary;
	}
	
	protected String getDetails() {
		return "[name]" + name + "\n" + "[salary]" + salary;
	}
	
	public abstract void manageSalary(double rate);
	//바디, 즉 중괄호가 없는 abstract method. 클래스도 추상이 되어야 함 = class앞에 abstract적어주자.
	//자식 클래스에게 method 구현을 강제하고 싶을 때.
}
