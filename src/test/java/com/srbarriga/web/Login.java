/**
 * @author Rodrigo Arriel
 * @data 
 *
 */

package com.srbarriga.web;

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

import com.srbarriga.utils.Utils;


public class Login {
	
	private static Logger logger = LoggerFactory.getLogger(NovoUsuario.class);
	private String URL = "https://srbarriga.herokuapp.com/login";
	
	
	Utils utils = new Utils();
	
	@Test
    public void CT02_testeLogin() throws IOException, InterruptedException	{
		
		// Diretório com webdrivers (*Chrome, Firefox e IE).
		
		utils.driverChrome();
        
        // Instancia chromeDriver e webDriverWait
		
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
                 
        // Com "implicitlyWait" é permitido a espera por qualquer comando no restante do código, 
        // onde um elemento não estiver disponível, ele aguarda até o tempo definido no comando.
        // Abre a URL da aplicação e maximiza o browser para tela cheia.
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("CT-02: TESTE LOGIN");
        logger.info("Acessa a URL: " + URL );
        driver.get("https://srbarriga.herokuapp.com/login");
        driver.manage().window().maximize();
        
        logger.info("Step 1 -> Acessar aba Login e inserir e-mail cadastrado anteriormente");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[1]")).sendKeys("bruno.bianchini@yaman.com.br");
        
        
        logger.info("Step 2 -> Inserir a senha cadastrada anteriormente");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input[1]")).sendKeys("automacao");
        
        
        logger.info("Step 3 -> Clicar no botão Entrar e valida mensagem de boas vindas");
        driver.findElement(By.xpath("/html/body/div[2]/form/button")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Bem vindo, Bruno Bianchini!')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\ProvasRodrigo\\Workspace\\Git\\automacaoSenhorBarriga\\Evidencias\\print_Login.jpg"));
        
        
        logger.info("Step 4 – Clicar em Sair.");
        driver.findElement(By.xpath("//*[contains(text(),'Sair')]")).click();
        
        
        logger.info("Step 5 – Fechar navegador.");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.close();

        
        
	}

}
