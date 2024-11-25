import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import yandex.praktikum.MainPage;
import yandex.praktikum.OrderScooterFlowPage;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class OrderScooterTest extends BaseTest {
    private String browser;
    private boolean useButtonInUpSite;
    private String firstName;
    private String secondName;
    private String address;
    private String stationMetro;
    private String clientPhoneNumber;
    private String dayDelivery;
    private String colorScooter;
    private String commentForCourier;

    public OrderScooterTest(String browser, boolean useButtonInUpSite, String firstName, String secondName, String address,
                            String stationMetro, String clientPhoneNumber, String dayDelivery, String colorScooter, String commentForCourier) {
        this.browser = browser;
        this.useButtonInUpSite = useButtonInUpSite;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.stationMetro = stationMetro;
        this.clientPhoneNumber = clientPhoneNumber;
        this.dayDelivery = dayDelivery;
        this.colorScooter = colorScooter;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getDataForOrder() {
        return Arrays.asList(new Object[][]{
                {"chrome", true, "Владимир", "Крошка", "В тьму таракань", "Бульвар Рокоссовского", "80000000000",
                        "четверо суток", "чёрный жемчуг", "Хочу кататься"},
                {"firefox", false, "Петя", "Петушков", "В 146 школу", "Серпуховская", "80000000001",
                        "семеро суток", "серая безысходность", "Двоих выдержит?"}
        });
    }

    @Test
    public void orderScooter() {
        setUpBrowser(browser);
        MainPage objMainPage = new MainPage(driver);
        OrderScooterFlowPage objFlowPage = new OrderScooterFlowPage(driver);
        objMainPage.clickCookieButton();
        objMainPage.clickOrderButton(useButtonInUpSite);
        objFlowPage.fillFirstName(firstName);
        objFlowPage.fillSecondName(secondName);
        objFlowPage.fillAddress(address);
        objFlowPage.selectStationMetro(stationMetro);
        objFlowPage.fillClientNumber(clientPhoneNumber);
        objFlowPage.clickNextButton();
        java.time.LocalDate deliveryDate = java.time.LocalDate.now().plusDays(1);
        objFlowPage.fillDateDelivery(deliveryDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        objFlowPage.selectDayDelivery(dayDelivery);
        objFlowPage.selectColorScooter(colorScooter);
        objFlowPage.fillCommentForCourier(commentForCourier);
        objFlowPage.clickOrderButton();
        objFlowPage.clickYesButton();
        objFlowPage.verifySuccessOrderWindow();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
