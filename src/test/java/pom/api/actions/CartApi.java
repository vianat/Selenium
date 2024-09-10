package pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utils.ConfigLoader;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class CartApi {
    private Cookies cookies;

    public CartApi(){}

    public CartApi(Cookies cookies){
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public Response addToCart(int productId, int quantity){
        Header header = new Header("content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);

        if (cookies == null) {cookies = new Cookies();}

        Response response =
                    given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl())
                        .headers(headers)
                        .formParams(formParams)
                        .cookies(cookies)
                        .log().all()
                    .when()
                        .post("/?wc-ajax=add_to_cart")
                    .then()
                        .log().all()
                        .extract()
                        .response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add the product " + productId + " to the cart" + ", HTTP STATUS CODE: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}