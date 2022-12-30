package day05_junitFramework;


import org.junit.Ignore;
import org.junit.Test;

public class C01_ElvedaMainMethod {


    @Test
    public void test01(){

        System.out.println("Test 01 çalıştı!");
        // @Test notasyonu sayesinde her bir test methodu bağımsız çalışabilir
        // eğer testin içerisinde bir assertion yoksa
        // kod problem yaşanmadan çalışıp bittiğinde
        // JUnit test passed olarak raporlar
    }

    @Test @Ignore
    public void test02(){

        System.out.println("Test 02 çalıştı!");
        // @Ignore notasyonu yazıldığı test method'unu çalıştırılmamasını sağlar
    }

    @Test
    public void test03(){

        System.out.println("Test 03 çalıştı!");
    }

}





