public class Request {

    private String apiKey;
    private String username;
    private RequestAction action;
    private String drug;
    private int quantity;
    private Address address;

    public Request() {
    }

    public Request(String apiKey, String username, RequestAction action, String drug, int quantity, Address address) {
        this.apiKey = apiKey;
        this.username = username;
        this.action = action;
        this.drug = drug;
        this.quantity = quantity;
        this.address = address;
    }

    public Request(String apiKey, String username, RequestAction action, String drug) {
        this.apiKey = apiKey;
        this.username = username;
        this.action = action;
        this.drug = drug;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUsername() {
        return username;
    }

    public RequestAction getAction() {
        return action;
    }

    public String getDrug() {
        return drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public Address getAddress() {
        return address;
    }
}
