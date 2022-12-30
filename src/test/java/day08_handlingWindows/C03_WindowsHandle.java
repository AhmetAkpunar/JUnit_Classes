package day08_handlingWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class C03_WindowsHandle {

    WebDriver driver;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void test01(){

        // https://the-internet.herokuapp.com/iframe adresine gidin
        driver.get("https://the-internet.herokuapp.com/iframe");
        String ilkSayfaWHD = driver.getWindowHandle();



        // elemantal selenium linkini tıklayın
        driver.findElement(By.xpath("//*[text()='Elemental Selenium']")).click();
        Set<String> tumWHD = driver.getWindowHandles();
        String ikinciSayfaWHD="";
        for (String eachWHD:tumWHD
        ) {
            if (!eachWHD.equals(ilkSayfaWHD)) {
                ikinciSayfaWHD=eachWHD;
            }
        }

        // yeni tab'a geçip sayfadaki en büyük yazının "Elemental Selenium"
        // olduğunu test edin
        driver.switchTo().window(ikinciSayfaWHD);

        WebElement enBuyukYazi = driver.findElement(By.tagName("h1"));
        String actualEnBuyukStr = enBuyukYazi.getText();
        String expectedEnBuyukStr ="Elemental Selenium";
        Assert.assertTrue(expectedEnBuyukStr.equals(actualEnBuyukStr));


        // ilk sayfaya geri donup sayfadaki yazinin
        // "An iFrame containing the TinyMCE WYSIWYG Editor" oldugunu test edin
        driver.switchTo().window(ilkSayfaWHD);
        WebElement ilkSayfaStr = driver.findElement(By.tagName("h3"));
        String expectedSayfaStr = "An iFrame containing the TinyMCE WYSIWYG " +
                "Editor";
        String actualSayfaStr = ilkSayfaStr.getText();
        Assert.assertTrue(expectedSayfaStr.equals(actualSayfaStr));
    }
}
