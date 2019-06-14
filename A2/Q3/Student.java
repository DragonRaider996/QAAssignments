public class Student
{
	protected String bannerID;
	protected String firstName;
	protected String lastName;
	protected String email;

	public Student()
	{
		bannerID = null;
		firstName = null;
		lastName = null;
		email = null;
	}

	public String getBannerID() {
		return bannerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setBannerID(String bannerID)
	{
		this.bannerID = bannerID;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

}