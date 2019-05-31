// YOU ARE ALLOWED TO MODIFY THIS FILE
public interface IDatabase
{
    public int getCount(String drug);
    public boolean productExist(String drug);
    public void claimDrug(String drug, int amount);
}