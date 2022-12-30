package day09_actions;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C03_MoveToElemenT extends TestBase {

    @Test
    public void test01(){

        // 1- https://www.amazon.com/ adresine gidin
        driver.get("https://www.amazon.com/");
        // 2- Sag ust bolumde bulunan “Account & Lists” menusunun acilmasi
        // icin mouse’u bu menunun ustune getirin
        WebElement beklenevekElement = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(beklenevekElement).perform();

        // 3- “Create a list” butonuna basin

        driver.findElement(By.xpath("//span[text()='Create a List']"));
        // 4- Acilan sayfada “Your Lists” yazisi oldugunu test edin//
        WebElement yourListElementi = driver.findElement(By.xpath("//*[@id" +
                "='my-lists-tab']"));

        yourListElementi.click();

        Assert.assertTrue(yourListElementi.isDisplayed());

        ReusableMethods.bekle(5);
    }
}
