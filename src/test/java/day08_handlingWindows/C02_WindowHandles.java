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

public class C02_WindowHandles {

    /*

     */

    WebDriver driver;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void teardown(){
        driver.quit();
    }

    @Test
    public void test01() throws InterruptedException {

        // ● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

        // ● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        String expectedSayfaYazisi = "Opening a new window";
        String actualSayfaYazisi =
                driver.findElement(By.tagName("h3")).getText();


        Assert.assertEquals(actualSayfaYazisi,expectedSayfaYazisi);

        // ● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
        String ilkSayfaHandleDegeri = driver.getWindowHandle();

        // ● Click Here butonuna basın.
        driver.findElement(By.xpath("//*[text()='Click Here']")).click();
        Thread.sleep(3000);

         /*
               Kontrolsüz açılan taba geçiş yapmak için
            1- İlk sayfada iken o sayfanın WHD değerini alıp kaydedin
            2- İkinci açıldıktan sonra getWindowHandles() kullanarak açık olan
               açık olan tüm sayfaların WH değerlerini bir Set olarak kaydedin
            3- Şuanda elimizde 2 elementli bir Set var,
               Elementlerden biri birinci sayfanın WHD'si
               1. sayfanın WHD'ne eşit olmayan, 2.sayfanın WHD olur
            4- Bu şekilde ikinci sayfanın WHD elde edildikten sonra WHD'leri
               kullanılarak sayfalar arasında geçiş yapılabilir
         */

        Set<String> tumWindowHandleDegerleriSeti = driver.getWindowHandles();

        String ikinciSayfaWHD="";
        for (String eachWHD:tumWindowHandleDegerleriSeti) {
            if (!eachWHD.equals(ilkSayfaHandleDegeri)) {
                ikinciSayfaWHD =eachWHD;
            }
        }

        // ● Acilan yeni pencerenin sayfa başlığının (title) “New Window”
        driver.switchTo().window(ikinciSayfaWHD);

        String expectedNewTitle = "New Window";
        String actualNewTile = driver.getTitle();
        Assert.assertEquals(expectedNewTitle,actualNewTile);

        //  oldugunu dogrulayin.
        // ● Sayfadaki textin “New Window” olduğunu doğrulayın.
        WebElement yeniSayfaText =driver.findElement(By.tagName("h3"));
        String actualSayfaText = yeniSayfaText.getText();
        String expectedSayfaText ="New Window";
        Assert.assertTrue(actualSayfaText.equals(expectedSayfaText));

        // ● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The
        //  Internet” olduğunu doğrulayın.
        driver.switchTo().window(ilkSayfaHandleDegeri);
        expectedTitle = "The Internet";
        actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        Thread.sleep(5000);
    }
}
