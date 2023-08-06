package ru.yandex.praktikum.scooter.courier;

import lombok.Data;

@Data

public class CourierCredsWithoutPassword
{
    private String login;
    private String password;

    public CourierCredsWithoutPassword(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public static CourierCredsWithoutPassword from(Courier courier)
    {
        return new CourierCredsWithoutPassword(courier.getLogin(), "");
    }
}
