package oop.flexible;

/*
 * �߻�Ŭ������ Ư¡
 * Employee emp = new Employee() -- �Ұ�, �߻� Ŭ���� ������ ��ü ���� �Ұ� emp.manageSalary() �ص� �� �ȿ� ������ ���� ����..
 * Employee mgr = new Manager() -- ����. �ڽ� ��ü�� �����ؼ� �ڽ� Ÿ������ �����ϴ� ���� ����.
 * Employee clerk = new MereClere() -- ����.
 * abstract method�� concrete method ��� ���� ����.
 */
public abstract class Employee {

	private String name;
	protected double salary;

	public Employee() {
		super();//objectŬ�������� ���. �̰� �Ⱦ� �Ͱ� ������. �ڵ����� �߰���. ������ ����ä�
	}//���Ӱ��� ������ ������. 
	
	//Constructor overloading
	public Employee(String name, double salary) {
		System.out.println("employee ������ ȣ��");
		this.name = name;
		this.salary = salary;
	}

	//concrete method: �ٵ� �ִ� �޼ҵ�
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
	//�ٵ�, �� �߰�ȣ�� ���� abstract method. Ŭ������ �߻��� �Ǿ�� �� = class�տ� abstract��������.
	//�ڽ� Ŭ�������� method ������ �����ϰ� ���� ��.
}
