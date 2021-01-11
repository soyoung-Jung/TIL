package workshop.person.control;

import workshop.person.entity.PersonEntity;

public class PersonManager {
	
	public PersonManager () {
		
	}

	public static void main(String[] args) {
		
		PersonManager pManager = new PersonManager();
		
		pManager.printTitle("@@@ �ι� ���� ��ȸ �ý��� @@@");
		pManager.printTitleLine();
		
		PersonEntity[] person = new PersonEntity[10];
		
		pManager.fillPerson(person);
		
		pManager.showPerson(person);
		
		pManager.findByGender(person, '��');
		
		pManager.printTitleLine();
		
		pManager.showPerson(person, "���ϴ�");
		
	
	}
	
	public void fillPerson(PersonEntity[] person) {
		
		person[0] = new PersonEntity("�̼�ȣ","7212121028102", "��õ ��籸", "032-392-2932");
		person[1] = new PersonEntity("���ϴ�","7302132363217", "���� ������", "02-362-1932");
		person[2] = new PersonEntity("�ڿ���","7503111233201", "���� ���ϱ�", "02-887-1542");
		person[3] = new PersonEntity("���μ�","7312041038988", "���� ������", "032-384-2223");
		person[4] = new PersonEntity("ȫ����","7606221021341", "���� ��õ��", "02-158-7333");
		person[5] = new PersonEntity("�̹̼�","7502142021321", "���� ������", "02-323-1934");
		person[6] = new PersonEntity("�ڼ���","7402061023101", "���� ���α�", "02-308-0932");
		person[7] = new PersonEntity("������","7103282025101", "���� ����", "02-452-0939");
		person[8] = new PersonEntity("Ȳ����","7806231031101", "��õ �߱�", "032-327-2202");
		person[9] = new PersonEntity("��ö��","7601211025101", "��õ ��籸", "032-122-7832");
		
	}
	
	public void showPerson(PersonEntity[] person) {
		for (PersonEntity entity  : person) {
			System.out.println();
			System.out.println("[�̸�]" + entity.getName() + "\t[����]" + entity.getGender() + "\t[��ȭ��ȣ]" + entity.getPhone());
			printItemLine();
		}
	}
	
	public void printTitle(String title) {
		System.out.println("\n" + title + "\n");
	}
	
	public void printTitleLine() {
		for(int i = 0; i < 60; i++) {
			System.out.print("=");
		}
		System.out.println();
	}
	
	public static void printItemLine() {
		for (int i = 0; i < 60; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public void findByGender(PersonEntity[] persons, char gender) {
		int countGender = 0;
		for (PersonEntity entity:persons) {
			if (entity.getGender() == gender) {
				countGender++;
			}
		}
//		return countGender;
		System.out.println("����: " + gender + "(��)��" + countGender + "�� �Դϴ�.");
		
	}
	
	public void showPerson(PersonEntity[] persons, String name) {
		System.out.println();
		System.out.println("--�̸�:" + name + "(��)�� ã�� ����Դϴ�. --");
		for (PersonEntity entity:persons) {
			if (entity.getName().equals(name)) {
				System.out.println("[�̸�]" + entity.getName() + "\n" + "[����]" + entity.getGender() + "\n" + "[��ȭ��ȣ]" + entity.getPhone() + "\n" + "[�ּ�]" + entity.getAddress());
			}
		}
	}
	

}
