package orders;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data

public class Orders
{
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;


    public Orders(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Orders getRandomOrder()
    {
        return new Orders(
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomNumeric(15),
                (int) (Math.random() * 99),
                "2020-06-06",
                RandomStringUtils.randomAlphabetic(15),
                new String[]{});
    }

    public static Orders getBlackOrder()
    {
        return new Orders(
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomNumeric(15),
                (int) (Math.random() * 99),
                "2020-06-06",
                RandomStringUtils.randomAlphabetic(15),
                new String[]{"BLACK"});
    }

    public static Orders getGreyOrder()
    {
        return new Orders(
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomNumeric(15),
                (int) (Math.random() * 99),
                "2020-06-06",
                RandomStringUtils.randomAlphabetic(15),
                new String[]{"GREY"});
    }

    public static Orders getBlackGreyOrder()
    {
        return new Orders(
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomAlphanumeric(15),
                RandomStringUtils.randomAlphabetic(15),
                RandomStringUtils.randomNumeric(15),
                (int) (Math.random() * 99),
                "2020-06-06",
                RandomStringUtils.randomAlphabetic(15),
                new String[]{"BLACK", "GREY"});
    }
}
