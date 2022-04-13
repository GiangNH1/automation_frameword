package com.nopcommerce.utilities;

import com.github.javafaker.Faker;
import com.nopcommerce.commons.GlobalConstants;

import java.util.Locale;

public class DataFaker {
    private Faker faker;

    public static DataFaker getData(){
        return new DataFaker();
    }

    public DataFaker(){
        faker = new Faker(new Locale("vi"));
    }

    public String getFirstName(){
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }

    public String getUserName(){
        return faker.name().username();
    }

    public String getEmail(){
        return GlobalConstants.getRandomEmail();
    }

    public String getPassword(){
        return faker.internet().password();
    }

    public String getCity(){
        return faker.address().city();
    }

    public String getAddress1(){
        return faker.address().firstName();
    }

    public String getAddress2(){
        return faker.address().lastName();
    }

    public String getZip(){
        return String.valueOf(faker.random().nextInt(70000, 100000));
    }

    public String getPhone(){
        return faker.phoneNumber().cellPhone();
    }
}
