package pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pom.objects.User;
import utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies(){
        return cookies;
    }

    private String parseJsonValueUsingGroovy(){
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll {it.@name =='woocommerce-login-nonce' }.@value"); // baeldung.com/rest-assured-groovy method
    }

    public String parseJsonValueUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element el = doc.selectFirst("#woocommerce-login-nonce");

        return el.attr("value");
    }

    public Response getAccount(){
        Cookies localCookies = new Cookies();

        Response response =
                given()
                    .baseUri(ConfigLoader.getInstance().getBaseUrl())
                    .cookies(localCookies)
                    .log().all()
                .when()
                    .get("/account")
                .then()
                    .log().all()
                    .extract().response();

        if (response.getStatusCode() != 200){
            throw new RuntimeException("Failed to fetch account, Status code is " + response.getStatusCode());
        }
        return response;
    }

    public Response register(User user){
        Cookies localCookies = new Cookies();
        Header header = new Header("content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUserName());
        formParams.put("demopwd", user.getPassword());
        formParams.put("email", user.getEmail());
        formParams.put("woocommerce-register-nonce", parseJsonValueUsingJsoup());
        formParams.put("register", "Register");

//        System.out.println("Form parameters: -------------------" + formParams);  // Log form parameters

        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl())
                        .headers(headers)
                        .formParams(formParams)
                        .cookies(localCookies)
//                        .log().all()
                .when()
                        .post("/account")
                .then()
//                        .log().all()
                        .extract()
                        .response();
        if (response.getStatusCode() != 301) {
            throw new RuntimeException("Failed to register the account " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}

//<input type="hidden" id="woocommerce-login-nonce" name="woocommerce-login-nonce" value="e4999f342e"/>