package day11_seleniumWaits_cookies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.TestBase;

import java.time.Duration;

public class C01_ImplicitlyWait {

    WebDriver driver;
    @Test
    public void test01(){

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // Selenium 4'e kadar 15 saniyeyi belirtmek icin
        // Timeouts class'indan 15  saniye tanimliyorduk
        // Selenium4 ile birlikte zaman belirleme class'i Duration oldu

    }
}
