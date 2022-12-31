package day11_seleniumWaits_cookies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class C02_ExplicitlyWait {
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @After
    public void teardown(){

        driver.quit();
    }


    @Test
    public void implicitlyWait(){

        // 1. Bir class olusturun : WaitTest
        // 2. Iki tane metod olusturun : implicitWait() , explicitWait()
        // Iki metod icin de asagidaki adimlari test edin.

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // 3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // 4. Remove butonuna basin.
        driver.findElement(By.xpath("//*[text()='Remove']")).click();

        // 5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        WebElement itsGoneElementi = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(itsGoneElementi.isDisplayed());

        // 6. Add buttonuna basin
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // 7. It’s back mesajinin gorundugunu test edin//
        WebElement itsBackElementi = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(itsBackElementi.isDisplayed());
    }

    @Test
    public void explicitlyWait(){

        // 3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // 4. Remove butonuna basin.
        driver.findElement(By.xpath("//*[text()='Remove']")).click();

        // Explicitly wait için önce wait objesi oluşturalım
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

        // Bundan sonraki adımı belirlerken işlem yapmak isteidğimiz elemanın locate edilebilir
        // olup olmadığı önemlidir. Eğer locate edilebilirse önce locate edip, sonra wait objesi
        // ile o webelement bekletilebilir
        // Locate edilemiyorsa wait objesine bekleme emri locator olarak verilebilir

        // 5. “It’s gone!” mesajinin goruntulendigini dogrulayin.

        WebElement itsGoneElementi =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        Assert.assertTrue(itsGoneElementi.isDisplayed());


        // 6. Add buttonuna basin
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // 7. It’s back mesajinin gorundugunu test edin//
        WebElement itsBackElementi =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));

        Assert.assertTrue(itsBackElementi.isDisplayed());
    }


}
