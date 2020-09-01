package oop.flexible;

public class FlexibleCompanyDemo {
    public static void main(String[] args) {
        //�Ʒ��� �ݺ����� �ڵ带 �������� �����ؼ� �����ϰ� ������.
    
        MereClerk mereClerk1 = new MereClerk("ö��", 100);
        MereClerk mereClerk2 = new MereClerk("����", 100);
        Manager manager = new Manager("ȫ�浿", 200);

        //Heterogenous Collection ����
        Employee[] emps = new Employee[3];
        emps[0] = new MereClerk("ö��", 100);
        emps[1] = new MereClerk("����", 100);
        emps[2] = new Manager("ȫ�浿", 200, "���ߺ�");
        
      
        System.out.println("���� �����Դϴ�.");
        printEmployeeInfo(emps);

        System.out.println("");
        
        
        System.out.println("�ø� ���� �����Դϴ�.");
        for (Employee emp : emps) {
			emp.manageSalary(10);
		}
        printEmployeeInfo(emps);
   }//main

	private static void printEmployeeInfo(Employee[] emps) {
		for (Employee emp : emps) {

			if(emp instanceof Manager) {
				//�μ��� �ѷ��ְ� �Ϸ���.
				Manager mgr = (Manager)emp; // if�� ������ ������. ù��° emp���� mereClerk��. �װ��� managerŸ������ ����ȯ �� �� ����. 
				System.out.print( mgr.getDept() + " ");
				
				//����ȯ �ѹ濡 �ϴ� ��
//				System.out.println(((Manager)emp).getDept());
			}
			System.out.println(emp.getName() + "�� ���� ������" + emp.getSalary() + "�����Դϴ�.");
		}
	}
    
}

