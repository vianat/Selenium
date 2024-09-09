package pom.api.actions;

import pom.objects.User;
import utils.FakerUtils;

public class DummyClass {
    public static void main(String[] args){
        String userName = "bratok" + new FakerUtils().generateRandomNumbers();
        User user = new User()
                .setUserName(userName)
                .setPassword("12345")
                .setEmail(userName + "@gmail.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        signUpApi.parseJsonValueUsingJsoup();
//        System.out.println(signUpApi.register(user));
//        System.out.println(signUpApi.parseJsonValueUsingJsoup());
        System.out.println("*********" + signUpApi.getCookies());

//        CartApi cartApi = new CartApi(signUpApi.getCookies());
//        cartApi.addToCart(1215,1);
//        System.out.println(cartApi.getCookies());
    }
}