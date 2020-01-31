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

public class NovoUsuario {
	
	
	private static Logger logger = LoggerFactory.getLogger(NovoUsuario.class);
	private String URL = "https://srbarriga.herokuapp.com/login";
	
	
	Utils utils = new Utils();

	@Test
    public void CT01_testeCriarNovoUsuario() throws IOException, InterruptedException	{
					
		utils.driverChrome();
        
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(2000);
        
        logger.info("Step 1 -> Acessar o site: https://srbarriga.herokuapp.com/cadastro");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("CT-01: TESTE NOVO USUÁRIO");
        logger.info("Acessa a URL: " + URL );
        driver.get("https://srbarriga.herokuapp.com/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
      
        logger.info("Step 2 – Na aba \"Novo usuário\" inserir um nome, e-mail e senha e realizar o cadastro.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(text(),'Novo usuário?')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[contains(text(),'Nome')]/following::input[1]")).sendKeys("Rodrigo Arrrieel");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[1]")).sendKeys("rodrigoarriell@gmail.com");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input[1]")).sendKeys("automacao");
        driver.findElement(By.xpath("//*[contains(@value,'Cadastrar')]")).click();
        Thread.sleep(2000);
              
        // Valida mensagem de "Usuário cadastrado" com o print da tela.
        
        logger.info("Step 3 – Validar texto \"Usuário inserido com sucesso\"");
        driver.findElement(By.xpath("//*[contains(text(),'Usuário inserido com sucesso')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\ProvasRodrigo\\Workspace\\Git\\automacaoSenhorBarriga\\Evidencias\\Print_CadastroUsuario.jpg"));
        Thread.sleep(2000);
        
        
        logger.info("Step 5 -> Fechando o browser. ");
        driver.close();

        
	}
}
