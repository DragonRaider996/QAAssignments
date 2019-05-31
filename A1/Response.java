public class Response {

    private int status;
    private String message;
    private boolean isError;
    private boolean isShipSuccess;
    private boolean isQuerySuccess;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        this.isError = false;
        this.isQuerySuccess = false;
        this.isShipSuccess = false;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setShipSuccess(boolean shipSuccess) {
        isShipSuccess = shipSuccess;
    }

    public void setQuerySuccess(boolean querySuccess) {
        isQuerySuccess = querySuccess;
    }

    public String getJsonString(){
        String responseJson = "";
        if(isError){
            responseJson = "{\n" +
                    "   \"status\":"+this.status+",\n" +
                    "   \"error\":\""+this.message+"\"\n" +
                    "}";
        }
        if(isShipSuccess){
            responseJson = "{\n" +
                    "   \"status\":"+this.status+",\n" +
                    "   \"estimateddeliverydate\":\""+this.message+"\"\n" +
                    "}";
        }

        if(isQuerySuccess){
            responseJson = "{\n" +
                    "   \"status\":"+this.status+",\n" +
                    "   \"count\":"+this.message+"\n" +
                    "}";
        }

        return responseJson;
    }
}
