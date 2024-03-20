package api.endpoints;

public class Url {
    public static  String  base_url="https://simple-grocery-store-api.online";

    //APIs

    //Status API
    public static String getStatusurl=base_url+"/status";

    //Products APIs
    public static String getAllProductsurl=base_url+"/products";
    public static String getaProducturl=base_url+"/products/{productId}";

    // Cart APIs
    public static String createNewCarturl=base_url+"/carts";
    public static String AddNewCartItemurl=base_url+"/carts/{cartId}/items";
    public static String GetACarturl=base_url+"/carts/{cartId}";
    public static String GetCartItemsurl=base_url+"/carts/{cartId}/items";
    public static String modifyCartItemsurl=base_url+"/carts/{cartId}/items/{itemId}";
    public static String replaceCartItemsurl=base_url+"/carts/{cartId}/items/{itemId}";
    public static String deleteCartItemurl=base_url+"/carts/{cartId}/items/{itemId}";

    //Orders APIs

    public static String getAccessTokenurl=base_url+"/api-clients";
    public static String createNewOrderurl=base_url+"/orders";
    public static String UpdateOrderurl=base_url+"/orders/{orderId}";
    public static String getAllOrdersurl=base_url+"/orders";
    public static String deleteOrderurl=base_url+"/orders/{orderId}";





}
