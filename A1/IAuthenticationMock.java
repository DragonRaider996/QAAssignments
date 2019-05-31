public class IAuthenticationMock implements IAuthentication {

    @Override
    public boolean authenticate(String apiKey) {
        if (apiKey != null) {
            if (apiKey.length() > 0) {
                if (apiKey.contains("FALSE")) {
                    return false;
                } else if (apiKey.contains("TRUE")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean authorize(String username, RequestAction action) {
        if (username != null) {
            if (username.length() > 0 && action != null) {
                if (username.contains("SHIP") && action.equals(RequestAction.SHIP)) {
                    return true;
                } else if (username.contains("QUERY") && action.equals(RequestAction.QUERY)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
