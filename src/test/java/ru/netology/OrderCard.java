package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCard {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Desktop/Java/OrderingCard/driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    void shouldSubmitAnApplication() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Иванов Иван");
        elements.get(1).sendKeys("+79270123456");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String expectedMessage = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actualMessage = driver.findElement(By.className("paragraph")).getText();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldBeFilledInWithNonValidData() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(By.className("input__control"));
        textFields.get (0).sendKeys("Ivanov Ivan");
        textFields.get (1).sendKeys("+79270123456");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.tagName("button")).click();
        String expectedMessage = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actualMessage = driver.findElement(By.className("input__sub")).getText();
        assertEquals(expectedMessage, actualMessage);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}