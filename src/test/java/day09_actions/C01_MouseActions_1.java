package day09_actions;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;
import utilities.TestBase;

import java.util.Set;

public class C01_MouseActions_1 extends TestBase {

    @Test
    public void tes01(){

        //  1- Yeni bir class olusturalim: MouseActions1
        //  2- https://the-internet.herokuapp.com/context_menu sitesine gidin
        driver.get("https://the-internet.herokuapp.com/context_menu ");
        //  3- Cizili alan uzerinde sag click yapin
        Actions actions = new Actions(driver);
        WebElement ciziliAlanElementi = driver.findElement(By.xpath("// " +
                "div[@id='hot-spot']"));

        actions.contextClick(ciziliAlanElementi).perform();
        ReusableMethods.bekle(2);
        //  4- Alert’te cikan yazinin “You selected a context menu” oldugunu
        //     test edin.
        String expectedAlertYazisi ="You selected a context menu";
        String actualAlertYazisi = driver.switchTo().alert().getText();

        Assert.assertTrue(expectedAlertYazisi.equals(actualAlertYazisi));

        //  5- Tamam diyerek alert’i kapatalim
        driver.switchTo().alert().accept();
        //  6- Elemental Selenium linkine tiklayalim
        String ilkSayfaWHD = driver.getWindowHandle();

        driver.findElement(By.linkText("Elemental Selenium")).click();

        Set<String> tumWHD = driver.getWindowHandles();

        String ikinciSayfaWHD ="";

        for (String eachWHD:tumWHD) {

            if (!eachWHD.equals(ilkSayfaWHD)) {
                ikinciSayfaWHD=eachWHD;
            }
        }

        //  7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test
        //     edelim//
        driver.switchTo().window(ikinciSayfaWHD);


        String expectedTagYazisi = "Elemental Selenium";
        String actualTagYazisi = driver.findElement(By.tagName("h1")).getText();

        Assert.assertTrue(expectedTagYazisi.equals(actualTagYazisi));

        System.out.println(ilkSayfaWHD);
        System.out.println(ikinciSayfaWHD);


        ReusableMethods.bekle(5);
    }

}
