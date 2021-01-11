package workshop.book.entity;

public class Novel extends Publication{
	private String author;
	private String genere;
	
	public Novel () {
		
	}
	
	public Novel(String title, String publishingDate, int page, int price, String author, String genre) {
		super(title, publishingDate, page, price);
		setAuthor(author);
		setGenre(genre);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genere;
	}

	public void setGenre(String genre) {
		this.genere = genre;
	}
	
	
}
