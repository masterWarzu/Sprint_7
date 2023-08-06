package ru.yandex.praktikum.scooter.courier;

import lombok.Data;

@Data

public class CourierCredsWithoutLogin
{
    private String login;
    private String password;

    public CourierCredsWithoutLogin(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public static CourierCredsWithoutLogin from(Courier courier)
    {
        return new CourierCredsWithoutLogin("", courier.getPassword());
    }
}
