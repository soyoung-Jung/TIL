package oop.flexible;

/* ���� Ŭ���� */
public class MereClerk extends Employee {
    public MereClerk (String name, double salary) {
//        this.name = name;
//        this.salary = salary;//������ ������ �θ����� �ִ� ������ ������ �� ����. �θ� protected�� ���������ڷ� �����Ǿ� ����. private�̶�� ���� ����.
    	super(name,salary);
    	System.out.println("�̾�Ŭ�� ������ ȣ��");
    }
    
    @Override
    public void manageSalary(double rate) {
        salary = salary+ salary*(rate/100);
    }
}
