public class Person {
	public String name;

	public Person() {
		name = "Rob";
	}

	public boolean isPersonRob() {
		return name.equals("Rob") && isRobStreet("Rob street") && isRobCity("Rob city") && isRobProvince("Rob province")
				&& isRobPostalCode("Rob postalcode");
	}

	private boolean isRobStreet(String street) {
		return street.equals("Rob street");
	}

	private boolean isRobCity(String city) {
		return city.equals("Rob city");
	}

	private boolean isRobProvince(String province) {
		return province.equals("Rob province");
	}

	private boolean isRobPostalCode(String postalCode) {
		return postalCode.equals("Rob postalcode");
	}

}