package webTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class seleniumSimples {

    WebDriver driver;

    @BeforeTest
    public void inicio(){
        System.setProperty("webdriver.chrome.driver", //define uma propriedade do sistema e aponta onde está o driver do chrome
                "my_drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void finalizar(){
        driver.quit();
    }
    @Test
    public void consultarCursoPython() {


        driver.get("https://www.alura.com.br/");
        //get() para fazer simples consultas na página, inspecionar elementos
        //navigate() para interagir com a página

        driver.manage().window().maximize();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).click();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys("python");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys(Keys.ENTER);

        assertThat(driver.findElement(By.cssSelector("#busca-form > h2")).getText(), is("O que você quer aprender?"));

    }
    @Test
    public void consultarCursoJava() {

        driver.get("https://www.alura.com.br/");

        driver.manage().window().maximize();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).click();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys("java");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys(Keys.ENTER);

        assertTrue(driver.findElement(By.cssSelector("#busca-form > h2")).getText().contains("quer aprender?"));
    }

}
