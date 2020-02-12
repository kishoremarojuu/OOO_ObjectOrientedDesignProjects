package lms.co.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;
public class User extends ResourceSupport{

	@NotBlank
    private String userid;

    @NotBlank
    private String name;

	private String address;
    
	private String book1issue= "none";    
	private String book2issue= "none"; 
	private String book1return= "none"; 
	private String book2return= "none"; 
	private String status = "none"; 
	
	  

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User() {
    }

    public User(String id,String name, String address,
    		String book1issue,    
    		String book2issue, String book1return,
    		String book2return) {
    	this.userid = id;
        this.name = name;
        this.address = address;
        this.book1issue= book1issue;    
    	this.book2issue= book2issue;
    	this.book1return= book1return;
    	this.book2return= book2return;
    }

    

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
    public String getBook1issue() {
		return book1issue;
	}

	public void setBook1issue(String book1issue) {
		this.book1issue = book1issue;
	}

	public String getBook2issue() {
		return book2issue;
	}

	public void setBook2issue(String book2issue) {
		this.book2issue = book2issue;
	}

	public String getBook1return() {
		return book1return;
	}

	public void setBook1return(String book1return) {
		this.book1return = book1return;
	}

	public String getBook2return() {
		return book2return;
	}

	public void setBook2return(String book2return) {
		this.book2return = book2return;
	}

	@Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", address='" + address + '\'' + '}';
    }
}
