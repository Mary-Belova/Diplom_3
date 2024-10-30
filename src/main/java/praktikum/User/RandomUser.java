package praktikum.User;

import org.apache.commons.lang3.RandomStringUtils;


public class RandomUser {
    private static String name;
    private static String email;
    private static String password;

    public RandomUser(String name, String email, String password) {
        RandomUser.name = name;
        RandomUser.email = email;
        RandomUser.password = password;
    }


    public static String getName() {
        return name;
    }

    public void setName(String name) {
        RandomUser.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        RandomUser.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        RandomUser.password = password;
    }

    public static RandomUser newRandomUser() {
        return new  RandomUser("Yoda2000", "Yodam@yandex.ru" + RandomStringUtils.randomAlphabetic(3), "123456");
    }

    public static RandomUser newRandomUserPasswordSevenCharacters() {
        return new  RandomUser("Yoda2000" + RandomStringUtils.randomAlphabetic(3), "Yodam@yandex.ru" + RandomStringUtils.randomAlphabetic(3), "1234567");
    }

    public static RandomUser newRandomUserInvalidPasswordFiveCharacters() {
        return new  RandomUser("Yoda2000" + RandomStringUtils.randomAlphabetic(3), "Yodam@yandex.ru" + RandomStringUtils.randomAlphabetic(3), "12345");
    }

}