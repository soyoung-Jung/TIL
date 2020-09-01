package oop.flexible;

public class InheritanceTest {
	public static void main(String[] args) {
		Manager manager = new Manager("홍길동", 5000);
		
		//Manager 객체를 생성하는 방법
		Manager manager2 = new Manager("펭수", 5500, "인사부"); //1)
		Employee manager3 = new Manager("키티", 4500, "관리부"); //2) Manager는 Employee를 상속받고 있기 때문에, 타입을 부모타입으로 지정할 수 있음.
		
		//manager3에서는 getDept가 안됨. 왜냐. Employee 타입에는 없기 때문. Manager단독으로만 갖고 있는 메소드이기 때문.
		//manager3.getDept(); -> 가능하게 하는 법 casting. 대신 상속관계가 존재해야 함. 왜 가능하게 해야되는가? for문으로 하나씩 돌때 다른 클래스의 객체가 오면 그에 따른 구현이 필요할 경우도 있으니까.
		
		if(manager3 instanceof Manager) {//manager3가 manager로 부터 만들어진 객체인지 확인하는 것. 이렇게 하는 것이 더 안전함. 다른 클래스로부터 만들어진 객체가 올 수도 있으니까 케이스를 모두 만들어야 함.
			Manager mgr = (Manager)manager3;//type casting, 형변환, 변수로 받아서.		
			System.out.println(mgr.getDept());
			
			System.out.println(((Manager)manager3).getDept());//한번에 형변환. 변수로 받지 않고. 
		}
		
		
		
		MereClerk clerk = new MereClerk("둘리", 3000);
		
		System.out.println(manager.getDetails());
		System.out.println(manager2.getDetails());
		System.out.println(clerk.getDetails());
		
//		System.out.println(manager.getName() + " " + manager.getSalary());
//		System.out.println(clerk.getName() + " " + clerk.getSalary());

	}
}
