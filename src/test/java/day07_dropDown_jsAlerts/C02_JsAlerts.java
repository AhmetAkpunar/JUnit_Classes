package day07_dropDown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_JsAlerts {

    // Gerekli ayarlamaları yapıp
    // https://the-internet.herokuapp.com/javascript_alerts adresine gidin
    // 3 test methodu oluşturup, her method'da bir jsAlert'a basın
    // İlgili method'ları kullanın

    static WebDriver driver;
    @BeforeClass
    public static void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        Thread.sleep(3000);
    }
    @AfterClass
    public static void teardown(){
        driver.close();
    }

    @Test

    public void test01(){

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        // 1. Alert'e tıklayalım
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        // Alert'teki yazının "I am a JS Alert" olduğunu test edelim
        // Sağa tıklamaya izin vermiyorsa

        String actualAlertYazisi = driver.switchTo().alert().getText();
        String expectedAlertYazisi = "I am a JS Alert";
        Assert.assertEquals(expectedAlertYazisi,actualAlertYazisi);

        // Ok tuşuna basalım

        driver.switchTo().alert().accept();

    }

    @Test
    public void test02() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // 2. Alert'e tıklayalım
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']"));

        // cancel'e basıp, çıkan sonuç yazısının "You clicked: Cancel" çıktğını
        // test edin
        Thread.sleep(3000);
        driver.switchTo().alert().dismiss();

        String actualSonucYazisi = driver.findElement(By.xpath("//*[text()" +
                "='You clicked: Cancel']")).getText();


        String expectedSonucYazisi = "You clicked: Cancel";
        Assert.assertTrue(expectedSonucYazisi.equals(actualSonucYazisi));

    }

    @Test
    public void test03() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // 3.Alert'e tıklayalım
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();

        // Çıkan prompt ekranına "Abdullah" yazdıralım ve OK tuşuna basarak
        // alert'i kapatalım


        driver.switchTo().alert().sendKeys("Abdullah");
        Thread.sleep(3000);

        driver.switchTo().alert().accept();
        // Çıkan sonuç yazısının Abdullah içerdiğini test edelim

        String actualSonucYazisi = driver.findElement(By.xpath("//*[@id" +
                "='result']")).getText();

        String expectedKelime = "Abdullah";

        Assert.assertTrue(actualSonucYazisi.contains(expectedKelime));


    }

}
