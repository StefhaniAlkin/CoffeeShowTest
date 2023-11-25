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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
        driver.get("https://coffee-show.vercel.app/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Should open remove modal after click on remove item button")
    void shouldOpenEditModalAfterClickOnEditarItemButton(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
        button.click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        assertThat(modal).isNotNull();
    }

    @Test
    @DisplayName("Should close remove modal after click on close button")
    void shouldCloseRemoveModalAfterClickOnFecharButton(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
        button.click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));

        WebElement primeiroBotao = modal.findElement(By.tagName("button"));
        primeiroBotao.click();
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        });
    }

    @Test
    @DisplayName("Should open remove item modal after click on delete button")
    void shouldOpenRemoveItemModalAfterClickOnDeleteButton() throws InterruptedException {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
        Thread.sleep(3000);
        button.click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        modal.findElement(By.xpath("//td[p[text()='Deletar']]")).click();
        WebElement itemModal = driver.findElement(By.id("chakra-modal-:r3:"));
        assertThat(itemModal).isNotNull();
    }

    @Test
    @DisplayName("Should remove item with success after click on delete button")
    void shouldRemoveItemWithSuccessAfterClickOnDeleteButton() throws InterruptedException {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
        Thread.sleep(3000);
        button.click();
        WebElement modal = driver.findElement(By.id("chakra-modal-:R1qpf6:"));
        modal.findElement(By.xpath("//td[p[text()='Deletar']]")).click();
        WebElement deletarButtonModal = wait.until(ExpectedConditions.elementToBeClickable(By.className("chakra-button")));
        deletarButtonModal.click();
        WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toast-1-title")));
        assertThat(toast.getText()).isEqualTo("Item deletado com sucesso!");
    }
}
