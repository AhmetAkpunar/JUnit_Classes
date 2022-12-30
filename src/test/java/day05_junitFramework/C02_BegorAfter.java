package day05_junitFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_BegorAfter {

    // 3 farklı test method'u oluşturun
    // her bir method'un başında driver'ı oluşturup
    // 1- amazon.com
    // 2- wisequarter.com
    // 3. youtube.com' a gidip
    // title'ları yazdırın ve method'dan sonra driver'ı kapatın
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Before method'u çalıştı");
    }


    @After
    public void tearDown(){

        System.out.println("teardown method'u çalıştı");
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {
        driver.get("http://amazon.com/");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
    }

    @Test
    public void test02() throws InterruptedException {
        driver.get("http://wisequarter.com/");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
    }

    @Test
    public void test03() throws InterruptedException {
        driver.get("http://youtube.com/");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
    }
}
