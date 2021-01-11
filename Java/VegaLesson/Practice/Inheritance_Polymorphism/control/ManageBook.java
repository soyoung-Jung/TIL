package workshop.book.control;

import workshop.book.entity.Magazine;
import workshop.book.entity.Novel;
import workshop.book.entity.Publication;
import workshop.book.entity.ReferenceBook;//import �ؾ߿���°�? ��..

public class ManageBook {

	public static void main(String[] args) {
		
		ManageBook mngb = new ManageBook();
		
		Publication[] pbs = new Publication[5];
		
		pbs[0] = new Magazine("����ũ�μ���Ʈ","2007-10-01",328,9900,"�ſ�");
		pbs[1] = new Magazine("�濵����ǻ��","2007-10-03",316,9000,"�ſ�");
		pbs[2] = new Novel("���߿�","2007-07-01",396,9800,"����������������","����Ҽ�");
		pbs[3] = new Novel("���ѻ꼺","2007-04-14",383,11000,"����","���ϼҼ�");
		pbs[4] = new ReferenceBook("�ǿ��������α׷���","2007-01-14",496,25000,"����Ʈ�������");

		System.out.println("===Book �������");
		
		for (Publication pb : pbs) {
			System.out.println(pb);
//			System.out.println("�������� ���� ��" + pb.getPrice());
////			System.out.println("�������� ���� ��" + modifyPrice.newPrice); ���� �ҷ���... �� �޼ҵ� �ȿ� ����..?
		}
		
		System.out.println("===�������� ������");
		System.out.println(pbs[2].getTitle() + " : " + pbs[2].getPrice());
		
		mngb.modifyPrice(pbs[2]);
		
		System.out.println("====�������� ������");
		System.out.println(pbs[2].getTitle() + " : " + pbs[2].getPrice());
	}
	//����ȯ �ʿ� ����. �ֳ�, 
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
