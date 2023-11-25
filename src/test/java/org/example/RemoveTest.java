package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveTest {
    WebDriver driver;
    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Should open remove modal after click on remove item button")
    void shouldOpenEditModalAfterClickOnEditarItemButton() throws InterruptedException {

        driver.get("https://coffee-show.vercel.app/");
        Thread.sleep(1000);
        driver.findElement(By.id("delete")).click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        assertThat(modal).isNotNull();
    }

    @Test
    @DisplayName("Should close remove modal after click on close button")
    void shouldCloseRemoveModalAfterClickOnFecharButton() throws InterruptedException {

        driver.get("https://coffee-show.vercel.app/");
        Thread.sleep(1000);
        driver.findElement(By.id("delete")).click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));

        WebElement primeiroBotao = modal.findElement(By.tagName("button"));
        primeiroBotao.click();
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        });
    }
}
