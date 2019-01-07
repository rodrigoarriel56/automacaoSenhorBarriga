package com.srbarriga.herokuapp.web;

import java.io.File;
import java.io.IOException;
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
public class NovoUsuario {
	
	private static Logger logger = LoggerFactory.getLogger(Login.class);
	private String URL = "https://srbarriga.herokuapp.com/login";

	@Test
    public void CT01_criarNovoUsuario() throws IOException {
    	
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
        logger.info("Step 1 -> Clica em Menu >> Novo Usuário e preenche os campos para o cadastro.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Novo usuário?')]")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Nome')]/following::input[1]")).sendKeys("automacao8");
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[contains(@id,'email')]")).sendKeys("automacao8@yaman.com.br");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input[contains(@id,'senha')]")).sendKeys("automacao123");
        driver.findElement(By.xpath("//*[contains(@value,'Cadastrar')]")).click();
      
        // Valida mensagem de "Usuário cadastrado" com o print da tela.
        logger.info("Step 2 -> Valida mensagem de cadastro de novo usuário com sucesso.");
        driver.findElement(By.xpath("//*[contains(text(),'Usuário inserido com sucesso')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/Evidencias/Print_CadastroUsuario.png"));
        
        //Compara com o Equals, os dados cadastrados.
        logger.info("Step 3 -> Compara os dados cadastrados.");
        String dadosPagina = driver.getPageSource();
        equals(dadosPagina.contains("automacao8"));
        equals(dadosPagina.contains("automacao8@yaman.com.br"));
        equals(dadosPagina.contains("automacao123"));
        equals(dadosPagina.contains("Usuário inserido com sucesso"));
        
        // Fecha o browser
        logger.info("Step 4 -> Fechando o browser. ");
        driver.close();
	}
}
