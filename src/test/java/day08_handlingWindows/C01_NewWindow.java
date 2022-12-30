package day08_handlingWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_NewWindow {
        /*
        Selenium 4 ile windows konusunda yeni bir özellik geldi

        Istersek kontrollü olarak driver için yeni bir window veya tab
        oluşturabilirz. Bu durumda driver'ımız da otomatik olarak yeni
        sayfaya geçmiş olur

        Test'i,n lerleyen aşamalrında yeniden eski sayfalara dönüş görevi varsa
        o sayfadayken o sayfanın windowHandle Degeri alınıp save edilir. O
        sayfaya geçmek istendiğinde driver.switchTo().window
        (IstenenSayfanınWindowHandleDegeri) kodu ile o sayfaya geçiş yapılır.
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

        driver.get("https://amazon.com/");

        Thread.sleep(3000);

        // Test'in ilerleyen aamalarında yeniden amazon sayfasına dönmek
        // gerekiyorsa, amazon sayfasındayken bu window'un windowHandle
        // değerini alıp kaydetmeliyiz

        String ilkSayfaHandleDegeri = driver.getWindowHandle();

        // yeni bir tab'da wisequarter.com'a gidin ve gittiğimizi test edin

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://wisequarter.com");
        String expectedKelime="wisequarter";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedKelime));

        // wisequarter testini yaptıktan sonra
        // yeniden amazon'un açık olduğu tab'a geçin
        // ve amazon anasayfanın açık olduğunu test edin

        driver.switchTo().window(ilkSayfaHandleDegeri);
        Thread.sleep(2000);


        expectedKelime ="amazon";
        actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedKelime));


    }
}
