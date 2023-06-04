package webTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
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

    @BeforeClass
    public void antesDeTudo() throws IOException {
        //executa uma vez apenas antes do teste
        logs = new Logs();
        logs.initializeCSV(dataHora);
        System.setProperty("webdriver.chrome.driver", //define uma propriedade do sistema e aponta onde está o driver do chrome
                "my_drivers/chromedriver.exe");
    }

    @BeforeMethod
    public void inicio(){
        //executa antes de cada teste
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        evidences = new Evidences();
    }

    @AfterMethod
    public void finalizar(){

        driver.quit();
    }

    @Test
    public void consultarCursoPython() throws IOException {
        String testCase = "consultar curso python";

        logs.registerCSV(testCase, "iniciou o teste");

        driver.get("https://www.alura.com.br/");
        //get() para fazer simples consultas na página, inspecionar elementos
        //navigate() para interagir com a página
        logs.registerCSV(testCase, "abriu o site");
        driver.manage().window().maximize();
        logs.registerCSV(testCase, "maximizou a janela");
        evidences.print(driver, testCase, dataHora, "Passo 1 - abriu o site e maximizou a janela");

        driver.findElement(By.id("header-barraBusca-form-campoBusca")).click();
        logs.registerCSV(testCase, "clicou no campo de busca");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys("python");
        logs.registerCSV(testCase, "escreveu na caixa de pesquisa");
        evidences.print(driver, testCase, dataHora,"Passo 2 - digitou nome do curso no campo de pesquisa");
        driver.findElement(By.id("header-barraBusca-form-campoBusca")).sendKeys(Keys.ENTER);
        logs.registerCSV(testCase, "apertou enter");

        assertTrue(driver.findElement(By.cssSelector("#busca-form > h2")).getText().contains("quer aprender?"));
        logs.registerCSV(testCase, "confirmou o texto da página de pesquisa");
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
