import java.util.ArrayList;

public class IShipMateMock implements IShipMate {

    private static ArrayList<Address> addresses;

    public IShipMateMock() {
        addresses = new ArrayList<>();
        Address address1 = new Address();
        address1.city = "Halifax";
        address1.country = "Canada";
        address1.province = "Nova Scotia";
        address1.postalCode = "B3J 222";
        address1.customer = "Anant";
        address1.street = "Street";

        Address address2 = new Address();
        address2.city = "Halifax";
        address2.country = "Canada";
        address2.province = "Province";
        address2.postalCode = "B3J 122";
        address2.customer = "Pillai";
        address2.street = "Streets";

        addresses.add(address1);
        addresses.add(address2);
    }

    @Override
    public boolean isKnownAddress(Address address) {
        if (!isEmptyAddress(address)) {
            if (isAddressPresent(address)) {
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String shipToAddress(Address address, int count, String drugName) throws Exception {
        String deliverDate = "";
        if(isKnownAddress(address)){
            deliverDate = "05-31-2019";
        }else{
            throw new Exception("Unkown Address");
        }
        return deliverDate;
    }

    private boolean isEmptyAddress(Address address) {
        if ( (address.street != null && !address.street.isEmpty()) &&
                (address.customer != null  && !address.customer.isEmpty()) &&
                (address.postalCode != null && !address.postalCode.isEmpty()) &&
                (address.province != null && !address.province.isEmpty()) &&
                (address.country != null && !address.country.isEmpty()) &&
                (address.city != null && !address.city.isEmpty())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isAddressPresent(Address address) {
        boolean answer = false;
        for (int i = 0; i < addresses.size(); i++) {
            Address tempAddress = addresses.get(i);
            if (address.city.equals(tempAddress.city) &&
                    address.country.equals(tempAddress.country) &&
                    address.province.equals(tempAddress.province) &&
                    address.postalCode.equals(tempAddress.postalCode) &&
                    address.customer.equals(tempAddress.customer) &&
                    address.street.equals(tempAddress.street)) {
                answer = true;
            }
        }
        return answer;
    }

}
