package workshop.book.entity;

public class Publication {
	private String title;
	private String publishDate;
	private int page;
	private int price;
	
	public Publication() {
	
	}
	
	public Publication(String title, String publishingDate, int page, int price) {
		setTitle(title);
		setPublishDate(publishingDate);
		setPage(page);
		setPrice(price);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return title;
	}
	
	
}
