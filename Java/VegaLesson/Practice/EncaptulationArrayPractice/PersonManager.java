package workshop.person.control;

import workshop.person.entity.PersonEntity;

public class PersonManager {
	
	public PersonManager () {
		
	}

	public static void main(String[] args) {
		
		PersonManager pManager = new PersonManager();
		
		pManager.printTitle("@@@ 인물 정보 조회 시스템 @@@");
		pManager.printTitleLine();
		
		PersonEntity[] person = new PersonEntity[10];
		
		pManager.fillPerson(person);
		
		pManager.showPerson(person);
		
		pManager.findByGender(person, '여');
		
		pManager.printTitleLine();
		
		pManager.showPerson(person, "김하늘");
		
	
	}
	
	public void fillPerson(PersonEntity[] person) {
		
		person[0] = new PersonEntity("이성호","7212121028102", "인천 계양구", "032-392-2932");
		person[1] = new PersonEntity("김하늘","7302132363217", "서울 강동구", "02-362-1932");
		person[2] = new PersonEntity("박영수","7503111233201", "서울 성북구", "02-887-1542");
		person[3] = new PersonEntity("나인수","7312041038988", "대전 유성구", "032-384-2223");
		person[4] = new PersonEntity("홍정수","7606221021341", "서울 양천구", "02-158-7333");
		person[5] = new PersonEntity("이미숙","7502142021321", "서울 강서구", "02-323-1934");
		person[6] = new PersonEntity("박성구","7402061023101", "서울 종로구", "02-308-0932");
		person[7] = new PersonEntity("유성미","7103282025101", "서울 은평구", "02-452-0939");
		person[8] = new PersonEntity("황재현","7806231031101", "인천 중구", "032-327-2202");
		person[9] = new PersonEntity("최철수","7601211025101", "인천 계양구", "032-122-7832");
		
	}
	
	public void showPerson(PersonEntity[] person) {
		for (PersonEntity entity  : person) {
			System.out.println();
			System.out.println("[이름]" + entity.getName() + "\t[성별]" + entity.getGender() + "\t[전화번호]" + entity.getPhone());
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
		System.out.println("성별: " + gender + "(은)는" + countGender + "명 입니다.");
		
	}
	
	public void showPerson(PersonEntity[] persons, String name) {
		System.out.println();
		System.out.println("--이름:" + name + "(으)로 찾기 결과입니다. --");
		for (PersonEntity entity:persons) {
			if (entity.getName().equals(name)) {
				System.out.println("[이름]" + entity.getName() + "\n" + "[성별]" + entity.getGender() + "\n" + "[전화번호]" + entity.getPhone() + "\n" + "[주소]" + entity.getAddress());
			}
		}
	}
	

}
