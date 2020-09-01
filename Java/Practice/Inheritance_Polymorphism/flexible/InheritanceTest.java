package oop.flexible;

public class InheritanceTest {
	public static void main(String[] args) {
		Manager manager = new Manager("ȫ�浿", 5000);
		
		//Manager ��ü�� �����ϴ� ���
		Manager manager2 = new Manager("���", 5500, "�λ��"); //1)
		Employee manager3 = new Manager("ŰƼ", 4500, "������"); //2) Manager�� Employee�� ��ӹް� �ֱ� ������, Ÿ���� �θ�Ÿ������ ������ �� ����.
		
		//manager3������ getDept�� �ȵ�. �ֳ�. Employee Ÿ�Կ��� ���� ����. Manager�ܵ����θ� ���� �ִ� �޼ҵ��̱� ����.
		//manager3.getDept(); -> �����ϰ� �ϴ� �� casting. ��� ��Ӱ��谡 �����ؾ� ��. �� �����ϰ� �ؾߵǴ°�? for������ �ϳ��� ���� �ٸ� Ŭ������ ��ü�� ���� �׿� ���� ������ �ʿ��� ��쵵 �����ϱ�.
		
		if(manager3 instanceof Manager) {//manager3�� manager�� ���� ������� ��ü���� Ȯ���ϴ� ��. �̷��� �ϴ� ���� �� ������. �ٸ� Ŭ�����κ��� ������� ��ü�� �� ���� �����ϱ� ���̽��� ��� ������ ��.
			Manager mgr = (Manager)manager3;//type casting, ����ȯ, ������ �޾Ƽ�.		
			System.out.println(mgr.getDept());
			
			System.out.println(((Manager)manager3).getDept());//�ѹ��� ����ȯ. ������ ���� �ʰ�. 
		}
		
		
		
		MereClerk clerk = new MereClerk("�Ѹ�", 3000);
		
		System.out.println(manager.getDetails());
		System.out.println(manager2.getDetails());
		System.out.println(clerk.getDetails());
		
//		System.out.println(manager.getName() + " " + manager.getSalary());
//		System.out.println(clerk.getName() + " " + clerk.getSalary());

	}
}
