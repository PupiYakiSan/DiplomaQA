package ru.iteco.fmhandroid.ui.Service;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    public static String getValidLogin() {
        return "login2";
    }

    public static String getValidPassword() {
        return "password2";
    }

    public static String getInvalidLogin() {
        return String.valueOf(faker.name().username());
    }

    public static String getInvalidPassword() {
        return String.valueOf(faker.random());
    }

}
