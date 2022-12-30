package day08_handlingWindows;

import org.junit.Assert;
import org.junit.Test;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C04_TestBaseIlkTest extends TestBase {

    @Test
    public void test01(){

        // amazon'a gidin
        driver.get("https://amazon.com/");

        // amazon'a gittiğinizi test edin
        String expectedKelime ="amazon";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedKelime));


        ReusableMethods.bekle(3);
    }
}
