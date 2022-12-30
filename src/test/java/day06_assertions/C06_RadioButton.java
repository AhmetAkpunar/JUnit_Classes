package day06_assertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C06_RadioButton {

    // Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın.
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @After
    public void teardown(){
        driver.close();
    }


    @Test
    public void test01() throws InterruptedException {
        //  a. Verilen web sayfasına gidin.
        //       https://facebook.com
        driver.get("https://facebook.com");
        //  b. Create an account buton’una basin
        driver.findElement(By.xpath("//a[text()='Yeni Hesap Oluştur']")).click();
        Thread.sleep(2000);

        //  c. Radio button elementlerini locate edin ve size uygun olani secin
        driver.findElement(By.xpath("(//label[@class='_58mt'])[2]"));
        driver.findElement(By.xpath("(//input[@class='_8esa'])[2]")).click();
        Thread.sleep(3000);
    }


}
