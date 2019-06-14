public class Mexico implements IGDP
{
	protected String getAgriculture()
	{
		return "$50000000 MXN";
	}

	protected String getTourism()
	{
		return "$100000 MXN";
	}

	@Override
	public void printGDP() {
		System.out.println("GDP of Mexico:\n");
		System.out.println("   - Agriculture: " + getAgriculture());
		System.out.println("   - Tourism: " + getTourism());
	}
}