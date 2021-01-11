package oop.flexible;

public class FlexibleCompanyDemo {
    public static void main(String[] args) {
        //아래의 반복적인 코드를 다형성을 적용해서 간단하게 만들어보자.
    
        MereClerk mereClerk1 = new MereClerk("철수", 100);
        MereClerk mereClerk2 = new MereClerk("영희", 100);
        Manager manager = new Manager("홍길동", 200);

        //Heterogenous Collection 생성
        Employee[] emps = new Employee[3];
        emps[0] = new MereClerk("철수", 100);
        emps[1] = new MereClerk("영희", 100);
        emps[2] = new Manager("홍길동", 200, "개발부");
        
      
        System.out.println("현재 월급입니다.");
        printEmployeeInfo(emps);

        System.out.println("");
        
        
        System.out.println("올린 후의 월급입니다.");
        for (Employee emp : emps) {
			emp.manageSalary(10);
		}
        printEmployeeInfo(emps);
   }//main

	private static void printEmployeeInfo(Employee[] emps) {
		for (Employee emp : emps) {

			if(emp instanceof Manager) {
				//부서명도 뿌려주게 하려고.
				Manager mgr = (Manager)emp; // if문 없으면 에러남. 첫번째 emp에는 mereClerk임. 그것을 manager타입으로 형변환 할 수 없음. 
				System.out.print( mgr.getDept() + " ");
				
				//형변환 한방에 하는 법
//				System.out.println(((Manager)emp).getDept());
			}
			System.out.println(emp.getName() + "의 현재 월급은" + emp.getSalary() + "만원입니다.");
		}
	}
    
}

