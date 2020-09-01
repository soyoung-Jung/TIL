package oop.flexible;

/* 관리자 클래스 */
public class Manager extends Employee {
    
	private String dept;
	
    public Manager (String name, double salary) {
//        this.name = name;//private이기 때문에 이 방식으로 초기화 시키지 못한다. 따라서 부모에서 아규먼트 받는 생성자를 새롭게 만들어서 받을 수 있게 함.
//        this.salary = salary;
    	super(name,salary);//부모 생성자 호출해서 name과 salary를 초기화시킬 수 있다. super라는 키워드 앞에는 어떤 문장도 올 수 없음. 가장 처음으로 와야함.
    	System.out.println("매니저 생성자 호출");
    }
    
    public Manager (String name, double salary, String dept) {
//    	super(name,salary); 이렇게 하지 말고 위에 이미 있으니, 생성자끼리 호출할 수 있음. 그때 this()사용하기.
    	this(name,salary); //같은 클래스 내부에 있는 다른 생성자를 호출할 때. super와 마찬가지로 위에 뭐가 있으면 안됨. 
    	this.dept = dept;
    	
    }
    
    public String getDept() {
		return dept;
	}
    
    //반드시 상속해야 하는 메소드.
    @Override
    public void manageSalary(double rate) {
        salary = salary+ salary*(rate/100);
        salary += 20; // 20만원을 추가로 받는다.
    }
    public Manager() {
		// TODO Auto-generated constructor stub
	}
    
    @Override
    public String getDetails() {
    	return super.getDetails() + "\n[부서명]" + dept;
    }
}
