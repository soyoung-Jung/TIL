package oop.flexible;

/* ������ Ŭ���� */
public class Manager extends Employee {
    
	private String dept;
	
    public Manager (String name, double salary) {
//        this.name = name;//private�̱� ������ �� ������� �ʱ�ȭ ��Ű�� ���Ѵ�. ���� �θ𿡼� �ƱԸ�Ʈ �޴� �����ڸ� ���Ӱ� ���� ���� �� �ְ� ��.
//        this.salary = salary;
    	super(name,salary);//�θ� ������ ȣ���ؼ� name�� salary�� �ʱ�ȭ��ų �� �ִ�. super��� Ű���� �տ��� � ���嵵 �� �� ����. ���� ó������ �;���.
    	System.out.println("�Ŵ��� ������ ȣ��");
    }
    
    public Manager (String name, double salary, String dept) {
//    	super(name,salary); �̷��� ���� ���� ���� �̹� ������, �����ڳ��� ȣ���� �� ����. �׶� this()����ϱ�.
    	this(name,salary); //���� Ŭ���� ���ο� �ִ� �ٸ� �����ڸ� ȣ���� ��. super�� ���������� ���� ���� ������ �ȵ�. 
    	this.dept = dept;
    	
    }
    
    public String getDept() {
		return dept;
	}
    
    //�ݵ�� ����ؾ� �ϴ� �޼ҵ�.
    @Override
    public void manageSalary(double rate) {
        salary = salary+ salary*(rate/100);
        salary += 20; // 20������ �߰��� �޴´�.
    }
    public Manager() {
		// TODO Auto-generated constructor stub
	}
    
    @Override
    public String getDetails() {
    	return super.getDetails() + "\n[�μ���]" + dept;
    }
}
