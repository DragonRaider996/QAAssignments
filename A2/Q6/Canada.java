public class Canada implements IGDP
{
	protected String getAgriculture()
	{
		return "$50000000 CAD";
	}

	protected String getManufacturing()
	{
		return "$100000 CAD";
	}

	@Override
	public void printGDP() {
		System.out.println("GDP Of Canada:\n");
		System.out.println("   - Agriculture: " + getAgriculture());
		System.out.println("   - Manufacturing: " + getManufacturing());
	}
}