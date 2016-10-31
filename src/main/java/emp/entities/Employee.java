package emp.entities;

public class Employee {
	private Integer id;
	
	private Long cnp;
	
	private String firstName;
	
	private String lastName;
	
	private String dateOfBirth;
	
	private String address;
	
	private boolean married;
	
	private boolean blackListed;
	
	public Employee(){
		
	}
	
	/*public Employee(){
		setId(0);
		setCnp(0L);
		setFirstName("default");
		setLastName("default");
		setDateOfBirth("default");
		setAddress("default");
		setMarried(false);
		setBlackListed(false);
	}*/
	
	public Employee(Integer id, Long cnp, String firstName, String lastName, String dateOfBirth, String address, boolean married, boolean blackListed){
		setId(id);
		setCnp(cnp);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setAddress(address);
		setMarried(married);
		setBlackListed(blackListed);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCnp() {
		return cnp;
	}

	public void setCnp(Long cnp) {
		this.cnp = cnp;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public boolean isBlackListed() {
		return blackListed;
	}

	public void setBlackListed(boolean blackListed) {
		this.blackListed = blackListed;
	}
	
}
