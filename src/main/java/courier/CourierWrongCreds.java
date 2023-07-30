package courier;

import lombok.Data;

@Data

public class CourierWrongCreds
{
    private String login;
    private String password;

    public CourierWrongCreds(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public static CourierWrongCreds from(Courier courier)
    {
        return new CourierWrongCreds(courier.getLogin()+"1", courier.getPassword());
    }
}
