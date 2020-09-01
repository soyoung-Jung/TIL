package workshop.book.control;

import workshop.book.entity.Magazine;
import workshop.book.entity.Novel;
import workshop.book.entity.Publication;
import workshop.book.entity.ReferenceBook;//import 해야와햐는것? 흠..

public class ManageBook {

	public static void main(String[] args) {
		
		ManageBook mngb = new ManageBook();
		
		Publication[] pbs = new Publication[5];
		
		pbs[0] = new Magazine("마이크로소프트","2007-10-01",328,9900,"매월");
		pbs[1] = new Magazine("경영과컴퓨터","2007-10-03",316,9000,"매월");
		pbs[2] = new Novel("빠삐용","2007-07-01",396,9800,"베르나르베르베르","현대소설");
		pbs[3] = new Novel("남한산성","2007-04-14",383,11000,"김훈","대하소설");
		pbs[4] = new ReferenceBook("실용주의프로그래머","2007-01-14",496,25000,"소프트웨어공학");

		System.out.println("===Book 정보출력");
		
		for (Publication pb : pbs) {
			System.out.println(pb);
//			System.out.println("가격정보 변경 전" + pb.getPrice());
////			System.out.println("가격정보 변경 후" + modifyPrice.newPrice); 어케 불러옴... 저 메소드 안에 변수..?
		}
		
		System.out.println("===가격정보 변경전");
		System.out.println(pbs[2].getTitle() + " : " + pbs[2].getPrice());
		
		mngb.modifyPrice(pbs[2]);
		
		System.out.println("====가격정보 변경후");
		System.out.println(pbs[2].getTitle() + " : " + pbs[2].getPrice());
	}
	//형변환 필요 없음. 왜냐, 
	public void modifyPrice(Publication pub) {
		if (pub instanceof Magazine) {
			int newPrice = (int)(pub.getPrice()*0.6);
			pub.setPrice(newPrice);
		}
		else if (pub instanceof Novel) {
			int newPrice = (int)(pub.getPrice() * 0.8);
			pub.setPrice(newPrice);
		}
		else {
			int newPrice = (int)(pub.getPrice() * 0.9);
		}
	}
}
