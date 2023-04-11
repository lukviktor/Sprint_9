package praktikum;

import io.qameta.allure.Description;
import net.datafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String name;

    private final boolean expectedResult;

    public AccountTest(String name, boolean expectedResult) {
        this.name = name;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "Verified data: {0}, expected result - {1}")
    public static Object[][] getNameData() {
        Faker faker = new Faker();
        return new Object[][]{

                //Количество символов 3 - true
                {faker.text().text(1) + " " + faker.text().text(1), true},

                //Количество символов 19 - true
                {faker.text().text(9) + " " + faker.text().text(9), true},

                //Количество символов 2 без пробела имя и фамилия - false
                {faker.text().text(1) + faker.text().text(1), false},

                //Количество символов 20 - false
                {faker.text().text(10) + " " + faker.text().text(9), false},

                //Только имя
                {faker.text().text(19), false}
        };
    }

    @Test
    @Description("Verified data")
    public void checkNameTest() {
        Account account = new Account(name);
        assertThat(account.checkNameToEmboss(), equalTo(expectedResult));
    }

}