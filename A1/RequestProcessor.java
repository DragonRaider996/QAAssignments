// YOU ARE ALLOWED TO MODIFY THIS FILE

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class RequestProcessor implements IRequestProcessor {
    private static final String ACTION = "action";
    private static final String APIKEY = "apikey";
    private static final String USERNAME = "username";
    private static final String DRUG = "drug";
    private static final String QUANTITY = "quantity";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String CUSTOMER = "customer";
    private static final String STREET = "street";
    private static final String PROVINCE = "province";
    private static final String COUNTRY = "country";
    private static final String POSTALCODE = "postalCode";
    private static final String QUERY = "QUERY";
    private static final String SHIP = "SHIP";
    private static final String INVALIDAUTHENTICATION = "Authentication Failure";
    private static final String INVALIDAUTHORIZATION = "Not Authorized";
    private static final String INVALIDDRUG = "Unknown Drug";
    private static final String UNKOWNADDRESS = "Unknown Address";
    private static final String INSUFFICIENTSTOCK = "Insufficient Stock";
    private static final String INTERNALERROR = "Internal Error";

    /*
        This is dependency injection. Everything the class and this method needs to do their job is
        passed to it. This allows you to perfectly test every aspect of your class by writing mock
        objects that implement these interfaces such that you can test every possible path through
        your code.
    */
    public String processRequest(String json,
                                 IAuthentication authentication,
                                 IShipMate shipMate,
                                 IDatabase database) {
        String responseString = "";
        int status = 0;
        String message = "";
        Response response;
        Request request = new Request();
        try {
            Object obj = new JSONParser().parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            //Fetching common request data from request json string.
            String apikey = (String) jsonObject.get(APIKEY);
            String username = (String) jsonObject.get(USERNAME);
            String actionString = (String) jsonObject.get(ACTION);
            String drug = (String) jsonObject.get(DRUG);
            int quantity = 0;
            Address address= new Address();
            RequestAction action;
            //Based on Action type fetching other request data.
            if (actionString.equals(QUERY)) {
                action = RequestAction.QUERY;
                request = new Request(apikey,username,action,drug);
            } else if (actionString.equals(SHIP)){
                action = RequestAction.SHIP;
                if(jsonObject.containsKey(QUANTITY)) {
                    quantity = (int) (long) jsonObject.get(QUANTITY);
                }
                JSONObject jsonAddress = new JSONObject();
                if(jsonObject.containsKey(ADDRESS)) {
                    jsonAddress = (JSONObject) jsonObject.get(ADDRESS);
                }
                String city = (String) jsonAddress.get(CITY);
                String customer = (String) jsonAddress.get(CUSTOMER);
                String street = (String) jsonAddress.get(STREET);
                String country = (String) jsonAddress.get(COUNTRY);
                String province = (String) jsonAddress.get(PROVINCE);
                String postalCode = (String) jsonAddress.get(POSTALCODE);
                address.customer = customer;
                address.city = city;
                address.country = country;
                address.street = street;
                address.postalCode = postalCode;
                address.province = province;
                request = new Request(apikey,username,action,drug,quantity,address);
            }else{
                action = null;
                request = new Request(apikey,username,action,drug);
            }
            //Authentication and Authorization is gonna be same for every action type request.
            if (authentication.authenticate(request.getApiKey())) {
                if (authentication.authorize(request.getUsername(),request.getAction())) {
                    //Making further actions based on type of action
                    if (action.equals(RequestAction.QUERY)) {
                        //Checking first if the drug exists in the database.
                        if (database.productExist(request.getDrug())) {
                            //If yes provide the count.
                            int count = database.getCount(request.getDrug());
                            status = 200;
                            response = new Response(status,Integer.toString(count));
                            response.setQuerySuccess(true);
                        } else {
                            status = 500;
                            message = INVALIDDRUG;
                            response = new Response(status,message);
                            response.setError(true);
                        }
                    } else {
                        //Checking if the drug exists in the database.
                        if (database.productExist(request.getDrug())) {
                            //If yes check the count
                            int drugCount = database.getCount(request.getDrug());
                            //Checking if the quantity asked is more than available or not
                            if (drugCount >= quantity) {
                                try {
                                    //Fetching the deliverdate and checking if the address is valid or not
                                    String deliveryDate = shipMate.shipToAddress(request.getAddress(), request.getQuantity(), request.getDrug());
                                    //Once everything is perfect claim the drug from the database.
                                    database.claimDrug(request.getDrug(), request.getQuantity());
                                    status = 200;
                                    response = new Response(status,deliveryDate);
                                    response.setShipSuccess(true);
                                } catch (Exception e) {
                                    status = 500;
                                    message = UNKOWNADDRESS;
                                    response = new Response(status,message);
                                    response.setError(true);
                                }
                            } else {
                                status = 500;
                                message = INSUFFICIENTSTOCK;
                                response = new Response(status,message);
                                response.setError(true);
                            }
                        } else {
                            status = 500;
                            message = INVALIDDRUG;
                            response = new Response(status,message);
                            response.setError(true);
                        }
                    }
                } else {
                    status = 500;
                    message = INVALIDAUTHORIZATION;
                    response = new Response(status,message);
                    response.setError(true);
                }
            } else {
                status = 500;
                message = INVALIDAUTHENTICATION;
                response = new Response(status,message);
                response.setError(true);
            }

        } catch (Exception e) {
            status = 500;
            message = INTERNALERROR;
            response = new Response(status,message + " : "+ e.toString());
            response.setError(true);
        }

        responseString = response.getJsonString();
        return responseString;
    }

    /*
        Insert all of your instantiation of mock objects and RequestProcessor(s)
        here. Then insert calls to all of your unit tests for the RequestProcessor
        class.  These tests should send different combinations of JSON strings
        to your class with mock objects such that you test all paths through the
        API.  Write one test function per "path" you are testing.  For example,
        to test authentication you would write two unit tests: authenticateSuccess()
        that passes JSON with a known API key that should be authenticated by your
        mock security object and tests for the correct JSON response from processRequest(),
        and authenticateFailure() that passes JSON with a bad API key that should fail to
        be authenticated by your mock security object and tests for the correct JSON
        response from processRequest().

        The runUnitTests() method will be called by Main.java. It must run your unit tests.
        All of your unit tests should System.out.println() one line indicating pass or
        failure with the following format:
        PASS - <Name of test>
        FAIL - <Name of test>
    */
    static public void runUnitTests() {
        IAuthenticationMock authenticationMock = new IAuthenticationMock();
        IDatabaseMock databaseMock = new IDatabaseMock();
        IShipMateMock shipMateMock = new IShipMateMock();
        RequestProcessor requestProcessor = new RequestProcessor();

        //Testing for invalid Authentication with Query Request.
        if (testInvalidAuthenticationQuery(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("INVALID AUTHENTICATION QUERY TEST - PASSED");
        } else {
            System.out.println("INVALID AUTHENTICATION QUERY TEST - FAILED");
        }

        //Testing for invalid Authorization with Query Request.
        if (testInvalidAuthorizationQuery(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("INVALID AUTHORIZATION QUERY TEST - PASSED");
        } else {
            System.out.println("INVALID AUTHORIZATION QUERY TEST - FAILED");
        }

        //Testing for invalid Drug Name with Query Request.
        if (testInvalidDrugQuery(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("INVALID DRUG QUERY TEST - PASSED");
        } else {
            System.out.println("INVALID DRUG QUERY TEST - FAILED");
        }

        //Testing for valid Query with Query Request.
        if (testValidQuery(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("VALID QUERY TEST - PASSED");
        } else {
            System.out.println("VALID QUERY TEST - FAILED");
        }

        //Testing for invalid Authentication with Ship Request.
        if (testInvalidAuthenticationShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("INVALID AUTHENTICATION SHIP TEST - PASSED");
        } else {
            System.out.println("INVALID AUTHENTICATION SHIP TEST - FAILED");
        }

        //Testing for invalid Authorization with Ship Request.
        if (testInvalidAuthorizationShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("INVALID AUTHORIZATION SHIP TEST - PASSED");
        } else {
            System.out.println("INVALID AUTHORIZATION SHIP TEST - FAILED");
        }

        //Testing for invalid Drug Name with Ship Request.
        if (testInvalidDrugShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("INVALID DRUG SHIP TEST - PASSED");
        } else {
            System.out.println("INVALID DRUG SHIP TEST - FAILED");
        }

        //Testing for empty address with Ship Request.
        if (testInvalidEmptyAddressShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("EMPTY ADDRESS SHIP TEST - PASSED");
        } else {
            System.out.println("EMPTY ADDRESS SHIP TEST - FAILED");
        }

        //Testing for null address with Ship Request.
        if (testInvalidNullAddressShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("NULL ADDRESS SHIP TEST - PASSED");
        } else {
            System.out.println("NULL ADDRESS SHIP TEST - FAILED");
        }

        //Testing for wrong address with Ship Request.
        if (testInvalidWrongAddressShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("WRONG ADDRESS SHIP TEST - PASSED");
        } else {
            System.out.println("WRONG ADDRESS SHIP TEST - FAILED");
        }

        //Testing for invalid drug quantity with Ship Request.
        if (testInvalidDrugQuantityShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("INVALID DRUG QUANTITY SHIP TEST - PASSED");
        } else {
            System.out.println("INVALID DRUG QUANTITY SHIP TEST - FAILED");
        }

        //Testing for valid Ship Request.
        if (testValidShip(requestProcessor, authenticationMock, shipMateMock, databaseMock)) {
            System.out.println("VALID SHIP TEST - PASSED");
        } else {
            System.out.println("VALID SHIP TEST - FAILED");
        }

        //Testing for consecutive valid Ship Request to check if the drug claims work properly or not.
        if(testConsecutiveShip(requestProcessor,authenticationMock,shipMateMock,databaseMock)){
            System.out.println("VALID CONSECUTIVE SHIP TEST - PASSED");
        }else{
            System.out.println("VALID CONSECUTIVE SHIP TEST - FAILED");
        }

        //Resting for invalid Action type.
        if(testInvalidAction(requestProcessor,authenticationMock,shipMateMock,databaseMock)){
            System.out.println("INVALID ACTION TEST - PASSED");
        }else{
            System.out.println("INVALID ACTION TEST - FAILED");
        }

    }

    private static boolean testInvalidAuthenticationQuery(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidAuthenticationQuery, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidAuthenticationResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidAuthorizationQuery(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidAuthorizationQuery, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidAuthorizationResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidDrugQuery(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidDrugQuery, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidDrugQueryResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testValidQuery(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.validQuery, authentication, shipMate, database);
        if (response.equals(TestQueries.validQueryResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidAuthenticationShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidAuthenticationShip, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidAuthenticationResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidAuthorizationShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidAuthorizationShip, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidAuthorizationResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidDrugShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidDrugShip, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidDrugShipResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidWrongAddressShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidWrongAddressShip, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidWrongAddressShipResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidEmptyAddressShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidEmptyAddressShip, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidEmptyAddressShipResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidNullAddressShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidNullAddressShip, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidNullAddressShipResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testInvalidDrugQuantityShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidDrugQuantityShip, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidDrugQuantityShipResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testValidShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.validShip, authentication, shipMate, database);
        if (response.equals(TestQueries.validShipResponse)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean testConsecutiveShip(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.validConsecutiveShip, authentication, shipMate, database);
        if (response.equals(TestQueries.validShipResponse)) {
            String secondResponse = requestProcessor.processRequest(TestQueries.validConsecutiveShip, authentication, shipMate, database);
            if(secondResponse.equals(TestQueries.invalidDrugQuantityShipResponse)){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean testInvalidAction(RequestProcessor requestProcessor, IAuthentication authentication, IShipMate shipMate, IDatabase database) {
        String response = requestProcessor.processRequest(TestQueries.invalidAction, authentication, shipMate, database);
        if (response.equals(TestQueries.invalidAuthorizationResponse)) {
            return true;
        } else {
            return false;
        }
    }

}
