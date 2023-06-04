package webTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Evidences;
import utils.Logs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
public class seleniumSimples {

    WebDriver driver;
    Evidences evidences;
    Logs logs;
    static String dataHora = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
    @BeforeTest
    public void inicio(){
        System.setProperty("webdriver.chrome.driver", //define uma propriedade do sistema e aponta onde está o driver do chrome
                "my_drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        evidences = new Evidences();
        logs = new Logs();
    }

    @AfterTest
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void consultarCursoPython() throws IOException {
        String testCase = "consultar curso python";

        logs.registerCSV("2021-05-02 20-36-12", "consultar curso Python", "iniciou o teste");

        driver.get("https://www.alura.com.br/");
        //get() para fazer simples consultas na página, inspecionar elementos
        //navigate() para interagir com a página
        driver.manage().window().maximize();
        evidences.print(driver, testCase, dataHora, "Passo 1 - abriu o site e maximizou a janela");

        driver.findElement(By.id("header-barraBusca-form-campoBusca")).click();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys("python");
        evidences.print(driver, testCase, dataHora,"Passo 2 - digitou nome do curso no campo de pesquisa");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys(Keys.ENTER);

        assertTrue(driver.findElement(By.cssSelector("#busca-form > h2")).getText().contains("quer aprender?"));
        evidences.print(driver, testCase, dataHora, "Passo 3 - apareceu a lista de cursos");
    }
    @Test
    public void consultarCursoJava() throws IOException {
        String testCase = "consultar curso java";
        driver.get("https://www.alura.com.br/");

        driver.manage().window().maximize();
        evidences.print(driver, testCase, dataHora,"Passo 1 - abriu o site e maximizou a janela");

        driver.findElement(By.id("header-barraBusca-form-campoBusca")).click();
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys("java");
        evidences.print(driver, testCase, dataHora, "Passo 2 - digitou nome do curso no campo de pesquisa");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys(Keys.ENTER);

        assertTrue(driver.findElement(By.cssSelector("#busca-form > h2")).getText().contains("quer aprender?"));
        evidences.print(driver, testCase, dataHora, "Passo 3 - apareceu a lista de cursos");
    }
}
