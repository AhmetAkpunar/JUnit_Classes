package day07_dropDown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C03_BasicAuthentication {

    WebDriver driver;
    @Before
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        Thread.sleep(3000);
    }
    @After
    public void teardown(){
        driver.close();
    }

    @Test
    public void test() throws InterruptedException {



        // 1- Bir class olusturun : BasicAuthentication
        // 2- https://the-internet.herokuapp.com/basic_auth sayfasina gidin

        driver.get("https://the-internet.herokuapp.com/basic_auth");


        // 3- asagidaki yontem ve test datalarini kullanarak authentication’i
        // yapin


        // Html komutu : https://username:password@URL
        //                Username    : admin
        //                password    : admin

        Thread.sleep(3000);
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        // 4- Basarili sekilde sayfaya girildigini dogrulayin

        String actualSonucYazisi =
                driver.findElement(By.tagName("p")).getText();

        String expectedKelime ="Congratulations";

        Assert.assertTrue(actualSonucYazisi.contains(expectedKelime));


    }
}
