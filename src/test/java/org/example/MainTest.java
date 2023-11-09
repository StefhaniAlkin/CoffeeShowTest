package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;
class MainTest {
    WebDriver driver;
    @BeforeEach
    void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup(); //sets up a valid driver for Chrome.
    }

    @Test
    @DisplayName("Should open register modal after click on adicionar item button")
    void shouldOpenRegisterModalAfterClickOnAdicionarItemButton() throws InterruptedException {

        driver.get("https://coffee-show.vercel.app/");
        Thread.sleep(1000);
        driver.findElement(By.id("create")).click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        assertThat(modal).isNotNull();
    }
}