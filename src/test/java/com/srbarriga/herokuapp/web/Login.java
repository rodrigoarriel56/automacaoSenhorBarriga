package com.srbarriga.herokuapp.web;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fredi.roldan
 * @data 04.01.2019
 *
 */
public class Login {

	private static Logger logger = LoggerFactory.getLogger(Login.class);
	private String URL = "https://srbarriga.herokuapp.com/login";

	@Test
    public void CT02_testeLogin() throws Exception {
    	
    	// Diretório com webdrivers (*Chrome, Firefox e IE).
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");
        
        // Instancia chromeDriver e webDriverWait
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Com "implicitlyWait" é permitido a espera por qualquer comando no restante do código, 
        // onde se um elemento não estiver disponível, ele aguarda até o tempo definido no comando.
        // Abre a URL da aplicação e maximiza o browser para tela cheia.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Acessa a URL: " + URL );
        driver.get("https://srbarriga.herokuapp.com/login");
        driver.manage().window().maximize();
        
        // Aguarda a tela ser carrega pela presença do botão entrar.
        // Preenche os campos email, senha e clica no botão entrar e em seguinda clica no botão sair.
        logger.info("Step 1 -> Preenche usuário, senha e clica em Entrar");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[1]")).sendKeys("automacao@yaman.com.br");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input")).sendKeys("automacao");
        driver.findElement(By.xpath("//*[contains(text(),'Entrar')]")).click();
        
        // Valida mensagem de Bem Vindo após o Login com o print da tela.
        logger.info("Step 2 -> Salva Evidência com o print da tela de [Bem Vindo]");
        driver.findElement(By.xpath("//*[contains(text(),'Bem vindo, automacao!')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/Evidencias/Print_BemVindo.png"));
        
        //Compara com o Equals, os dados cadastrados.
        logger.info("Step 3 -> Compara os dados cadastrados.");
        String dadosPagina = driver.getPageSource();
        equals(dadosPagina.contains("automacao@yaman.com.br"));
        equals(dadosPagina.contains("automacao"));
        
        // Fecha o browser
        logger.info("Step 6 -> Fechando o browser. ");
        driver.close();
    }
}

