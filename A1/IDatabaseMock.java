import java.util.HashMap;

public class IDatabaseMock implements IDatabase{

    private static HashMap<String,Integer> drugsDetails;

    public IDatabaseMock() {
        drugsDetails = new HashMap<>();
        drugsDetails.put("Drug1",100);
        drugsDetails.put("Drug2",100);
        drugsDetails.put("Drug3",100);
        drugsDetails.put("Drug4",100);
        drugsDetails.put("Drug5",100);
        drugsDetails.put("Drug6",100);
    }

    @Override
    public int getCount(String drug) {
        int count = drugsDetails.get(drug);
        return count;
    }

    @Override
    public boolean productExist(String drug) {
        if(drugsDetails.containsKey(drug)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean claimDrug(String drug, int amount) {
        int count = drugsDetails.get(drug);
        int newCount = count - amount;
        drugsDetails.put(drug,newCount);
        return true;
    }
}
