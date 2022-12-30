package day07_dropDown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class
C01_HandleDropDownMenu {

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://amazon.com/");
    }
    @After
    public void teardown(){
        driver.close();
    }


    @Test

    public void test() throws InterruptedException {

        // Dropdown menuden istediğimizi seçebilmek için öncelikle Select
        // class'ından bir select object'i oluşturmamız gerekir
        // ancak select object'i oluşturmak için Select class'ının constructor'ı
        // handle edeceğimiz weblelemnt'i istediğinden selecet object'i
        //  oluşturmadan önce dropdown webelementini oluşturmamız gerekir


        // 1- handle ettiğimiz  dropdown'ı weblementi oluşturduk
        WebElement ddm = driver.findElement(By.xpath("//select[@id" +
                "='searchDropdownBox']"));

        // 2- select class'ından obje oluşturmalaıyız
        Select select = new Select(ddm);

        // 3- arama kutusu yanındaki dropdown menuden book seçin

        select.selectByValue("search-alias=stripbooks-intl-ship");
        //select.selectByIndex(5);
        // select.selectByVisibleText("Books");


        // arama kutusuna java yazdırıp aramayı yapın
        WebElement aramaKutusu = driver.findElement(By.id(
                "twotabsearchtextbox"));
        aramaKutusu.sendKeys("Java" + Keys.ENTER);

        // title'ın Java içerdiğini test edin
        String expectedKelime ="Java";
        String actualKelime = driver.getTitle();
        Assert.assertTrue(actualKelime.contains(expectedKelime));

        // dropdown menüden books seçeneğinin seçildiğini test edin

        /*
            Locate ettiğimiz elementi bulamazsa
            NoSuchElementExpception sayfa
            yenilendiği için var olan bir elementi kullanamazsa
            StaleElementReferenceException verir

         */
        // Seçilen option üzerindeki yazıyı getirme

        ddm = driver.findElement(By.xpath("//select[@id" +
                "='searchDropdownBox']"));

        select = new Select(ddm);
        select.selectByVisibleText("Books");

        String expectedSecilecekOption ="Books";

        String actualSecilenOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(expectedSecilecekOption,actualSecilenOption);


        // Dropdown menüdeki seçenek sayısının 24 olduğunu test edin

        List<WebElement> optionsWebelementiListesi = select.getOptions();

        int actualOptionSayisi = optionsWebelementiListesi.size();
        int expectedOptionSayisi = 28;

        Assert.assertEquals(expectedOptionSayisi,actualOptionSayisi);



        Thread.sleep(3000);
    }

}
