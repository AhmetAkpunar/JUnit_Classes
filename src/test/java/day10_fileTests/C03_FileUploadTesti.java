package day10_fileTests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ReusableMethods;
import utilities.TestBase;

import java.awt.geom.RectangularShape;

public class C03_FileUploadTesti extends TestBase {

    @Test
    public void test01(){

        // 1.https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");
        // 2.chooseFile butonuna basalim
        // 3.Yuklemek istediginiz dosyayi secelim.



        /*
            Bu görevi yapabilmek için chooseFile butonuna basıldığında açılan bilgisayarımızdaki file
            dosyalarını click yapabilmemiz gerekir. Ancak selenium bilgisayarımızdaki dosyları click
            yapmaz

             Bunun yerine söyle bir çözüm üretebiliriz
             1- chooseFile butonunu locate ederiz
             2- upload edileek dosyanın yolunu(path) oluşturun
             3- chooseFile butonuna sendKesy ile dosya yolunu yollayın
         */

        WebElement chooseFileButonu = driver.findElement(By.xpath("//*[@id='file-upload']"));

        ReusableMethods.bekle(3);
        String dosyaYolu = System.getProperty("user.home")+"/Desktop/merhabaJava.docx";

        chooseFileButonu.sendKeys(dosyaYolu);

        //"C:\Users\ahmet\Desktop\merhabaJava.docx"




        // 4.Upload butonuna basalim.
        driver.findElement(By.xpath("//*[@id='file-submit']")).click();

        // 5.“File Uploaded!” textinin goruntulendigini test edelim.

        WebElement fileUploadElementi = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(fileUploadElementi.isDisplayed());

        ReusableMethods.bekle(3);
    }


}
