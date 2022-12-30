package day09_actions;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

import java.security.Key;

public class C05_FormDoldurma extends TestBase {

    @Test
    public void test01(){

        // facebook.com adresine gidin
        driver.get("https://facebook.com/");
        // yeni hesap oluşturma linline tıklayın
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        // ilgili olanları faker kütüphanesiden değerlerle doldurun
        WebElement firstNameKutusu = driver.findElement(By.name("firstname"));

        Actions actions = new Actions(driver);

        Faker faker = new Faker();

        String email = faker.internet().emailAddress();
        actions
                .click(firstNameKutusu)
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys("23")
                .sendKeys(Keys.TAB)
                .sendKeys("March")
                .sendKeys(Keys.TAB)
                .sendKeys("1990")
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ARROW_RIGHT).perform();

        // kaydol butonuna basın
        WebElement signUp = driver.findElement(By.name("websubmit"));
        signUp.click();

        // Kayıt olamadığınızı test edin

        ReusableMethods.bekle(25);
        WebElement hataMesaji = driver.findElement(By.id("reg_error_inner"));



        Assert.assertTrue(hataMesaji.isDisplayed());

        ReusableMethods.bekle(25);
    }
}
