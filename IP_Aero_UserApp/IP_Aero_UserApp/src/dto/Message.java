package dto;

public class Message {
	private int id;
	private String email;
	private String title;
	private String content;
	private int status;
	
	public Message() {
		
	}
	
	public Message(String email, String title, String content, int status) {
		super();
		this.email = email;
		this.title = title;
		this.content = content;
		this.status = status;
	}

	public Message(int id, String email, String title, String content, int status) {
		super();
		this.id = id;
		this.email = email;
		this.title = title;
		this.content = content;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
