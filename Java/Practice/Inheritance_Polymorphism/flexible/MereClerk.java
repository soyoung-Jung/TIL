package oop.flexible;

/* 평사원 클래스 */
public class MereClerk extends Employee {
    public MereClerk (String name, double salary) {
//        this.name = name;
//        this.salary = salary;//나한테 없지만 부모한테 있는 변수를 참조할 수 있음. 부모에 protected로 접근제한자로 설정되어 있음. private이라면 접근 못함.
    	super(name,salary);
    	System.out.println("미어클럭 생성자 호출");
    }
    
    @Override
    public void manageSalary(double rate) {
        salary = salary+ salary*(rate/100);
    }
}
