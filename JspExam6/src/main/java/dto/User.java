package dto;

public class User {
	
	//핃드
	private String id;
	private String name;
	private String address;
	private String email;
	
	//생성자
	public User() {}
	public User(String id, String name, String address, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
	}

	//getter, setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
