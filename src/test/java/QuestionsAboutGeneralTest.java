import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandex.praktikum.MainPage;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsAboutGeneralTest extends BaseTest {
    private String browser;
    private String expectedText;
    private int index;

    public QuestionsAboutGeneralTest(String browser, String expectedText, int index) {
        this.browser = browser;
        this.expectedText = expectedText;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getQuestionsAboutGeneral() {
        return Arrays.asList(new Object[][]{
                {"chrome", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"chrome", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"chrome", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"chrome", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"chrome", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"chrome", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"chrome", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"chrome", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        });
    }

    @Test
    public void testQuestionsAboutGeneral() {
        setUpBrowser(browser);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookieButton();
        List<WebElement> items = driver.findElements(objMainPage.listFAQ);
        assertEquals("Количество элементов и ожидаемых текстов не совпадает", items.size(), 8);
        WebElement item = items.get(index);
        item.click();
        WebElement description = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='accordion__panel-" + index + "']")));
        assertEquals("Текст в выпадающем описании не совпадает", expectedText, description.getText());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
