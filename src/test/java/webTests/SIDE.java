package webTests;

// Generated by Selenium IDE

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class SIDE {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "my_drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        js = (JavascriptExecutor) driver; //permite executar comandos JavaScript no navegador diferentes partes do código.
        vars = new HashMap<String, Object>();
        //HashMap é usado para armazenar variáveis ou objetos que serão compartilhados entre
        //O tipo definido para as chaves é "String"
        //e o tipo definido para os valores é "Object".
        //O HashMap pode armazenar informações, como dados de teste, configurações, valores de entrada ou qualquer outra informação
        //que precise ser acessada ou modificada em diferentes partes do código de teste.
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void consultarCurso1() {

        driver.get("https://www.alura.com.br/");
        driver.manage().window().maximize();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).click();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys("python");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys(Keys.ENTER);
        assertTrue(driver.findElement(By.cssSelector("#busca-form > h2")).getText().contains("quer aprender?"));
    }
    @Test
    public void consultarCurso2() {
        driver.get("https://www.alura.com.br/");

        driver.manage().window().maximize();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).click();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys("java");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys(Keys.ENTER);
        assertTrue(driver.findElement(By.cssSelector("#busca-form > h2")).getText().contains("quer aprender?"));
    }
}
