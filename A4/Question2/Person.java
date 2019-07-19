public class Person {
	private String name;
	private PersonNumber personNumber;
	private PersonCredentials personCredentials;

	public Person(String name) {
		this.name = name;
		this.personNumber = new PersonNumber();
		this.personCredentials = new PersonCredentials();
	}

	public void setAreaCode(String areaCode) {
		this.personNumber.setAreaCode(areaCode);
	}

	public String getAreaCode() {
		return this.personNumber.getAreaCode();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.personNumber.setPhoneNumber(phoneNumber);
	}

	public String getPhoneNumber() {
		return this.personNumber.getPhoneNumber();
	}

	public void setLoginCredentials(String userName, String password) {
		this.personCredentials.setLoginCredentials(userName, password);
	}

	public boolean authenticateUser() {
		return this.personCredentials.authenticateUser();
	}
}