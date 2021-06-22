package ru.netology.selenium;

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
    public void shouldSubmitAnApplication() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Иванов Иван");
        elements.get(1).sendKeys("+79270123456");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.className("alert-success")).getText();
        assertEquals("Ваша заявка успешно отправлена!", text.trim());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
