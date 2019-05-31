public class TestQueries {
    public static String invalidAuthenticationQuery = "{\n" +
            "   \"apikey\":\"ab882jjhas8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkey\",\n" +
            "   \"action\":\"QUERY\",\n" +
            "   \"drug\":\"abcd\"\n" +
            "}";

    public static String invalidAuthenticationResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Authentication Failure\"\n" +
            "}";

    public static String invalidAuthorizationQuery = "{\n" +
            "   \"apikey\":\"ab882jjhas8989lkxl;TRUEklasdf8u22j\",\n" +
            "   \"username\":\"rhawkeyQUERY\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"abcd\"\n" +
            "}";

    public static String invalidAuthorizationResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Not Authorized\"\n" +
            "}";

    public static String invalidDrugQuery = "{\n" +
            "   \"apikey\":\"ab882jjhas8989lkxl;TRUEklasdf8u22j\",\n" +
            "   \"username\":\"rhawkeyQUERY\",\n" +
            "   \"action\":\"QUERY\",\n" +
            "   \"drug\":\"abcd\"\n" +
            "}";

    public static String invalidDrugQueryResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Unknown Drug\"\n" +
            "}";

    public static String validQuery = "{\n" +
            "   \"apikey\":\"ab882jjhas8989lkxl;TRUEklasdf8u22j\",\n" +
            "   \"username\":\"rhawkeyQUERY\",\n" +
            "   \"action\":\"QUERY\",\n" +
            "   \"drug\":\"Drug1\"\n" +
            "}";

    public static String validQueryResponse = "{\n" +
            "   \"status\":200,\n" +
            "   \"count\":100\n" +
            "}";

    public static String invalidAuthenticationShip = "{\n" +
            "   \"apikey\":\"ab882jjhas8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug1\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Street\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String invalidAuthorizationShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkey\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug1\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Street\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String invalidDrugShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Street\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String invalidDrugShipResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Unknown Drug\"\n" +
            "}";

    public static String invalidWrongAddressShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug1\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Streetsss\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String invalidWrongAddressShipResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Unknown Address\"\n" +
            "}";

    public static String invalidEmptyAddressShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug1\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String invalidEmptyAddressShipResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Unknown Address\"\n" +
            "}";

    public static String invalidNullAddressShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug1\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String invalidNullAddressShipResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Unknown Address\"\n" +
            "}";

    public static String invalidDrugQuantityShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug2\",\n" +
            "   \"quantity\":200,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Street\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String invalidDrugQuantityShipResponse = "{\n" +
            "   \"status\":500,\n" +
            "   \"error\":\"Insufficient Stock\"\n" +
            "}";

    public static String validShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug3\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Street\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String validConsecutiveShip = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"SHIP\",\n" +
            "   \"drug\":\"Drug5\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Street\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";

    public static String validShipResponse = "{\n" +
            "   \"status\":200,\n" +
            "   \"estimateddeliverydate\":\"05-31-2019\"\n" +
            "}";

    public static String invalidAction = "{\n" +
            "   \"apikey\":\"ab882jjhasTRUE8989lkxl;klasdf8u22j\",\n" +
            "   \"username\":\"rhawkeySHIP\",\n" +
            "   \"action\":\"ABCD\",\n" +
            "   \"drug\":\"Drug5\",\n" +
            "   \"quantity\":100,\n" +
            "   \"address\":{  \n" +
            "      \"customer\":\"Anant\",\n" +
            "      \"street\":\"Street\",\n" +
            "      \"city\":\"Halifax\",\n" +
            "      \"province\":\"Nova Scotia\",\n" +
            "      \"country\":\"Canada\",\n" +
            "      \"postalCode\":\"B3J 222\"\n" +
            "   }\n" +
            "}\n";
}
