package lms.co.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;
public class Book extends ResourceSupport{

    private String isbn;

    @NotBlank
    private String title;

    private String author;
    
    private String publisher;
    private String status;
    private String borrower;
    private String borrowDate;
    private String returnDate;
    
    public Book() {
    }

	public Book(String isbn, String title, String author,
			String publisher, String status, String borrower,
			String borrowDate, String returnDate) {
        this.isbn = isbn;
        this.title = title;
        this.author =author;
        this.publisher = publisher;
        this.status = status;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

    @Override
    public String toString() {
        return "Book{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + '}';
    }
}
