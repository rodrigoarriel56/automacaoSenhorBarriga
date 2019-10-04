/**
 * @author Bruno Cesar Bianchini Gouveia
 * @data 28.01.2019
 *
 */

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

import com.srbarriga.herokuapp.Utils;

public class NovoUsuario {
	
	
	private static Logger logger = LoggerFactory.getLogger(NovoUsuario.class);
	private String URL = "https://srbarriga.herokuapp.com/login";
	
	
	Utils utils = new Utils();

	@Test
    public void CT01_testeCriarNovoUsuario() throws IOException	{
		
		// Diretório com webdrivers (*Chrome, Firefox e IE).
		
		utils.driverChrome();
        
        // Instancia chromeDriver e webDriverWait
		
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
                 
        // Com "implicitlyWait" é permitido a espera por qualquer comando no restante do código, 
        // onde se um elemento não estiver disponível, ele aguarda até o tempo definido no comando.
        // Abre a URL da aplicação e maximiza o browser para tela cheia.
        
        logger.info("Step 1 -> Acessar o site: https://srbarriga.herokuapp.com/cadastro");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("CT-01: TESTE NOVO USUÁRIO");
        logger.info("Acessa a URL: " + URL );
        driver.get("https://srbarriga.herokuapp.com/login");
        driver.manage().window().maximize();
                
        // Aguarda a tela ser carregada pela presença do botão entrar.
        // Preenche s camps email, senha e clica no botão entrar e em seguinda clica no botão sair.
        
      //Codigo esta errado, pois deveria acessar a funcionalidade de cadastrar novo usuário(Rodrigo Arriel)
        logger.info("Step 2 – Na aba \"Novo usuário\" inserir um nome, e-mail e senha e realizar o cadastro.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Novo usuário?')]")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Nome')]/following::input[1]")).sendKeys("Bruno Bianchini 3");
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[1]")).sendKeys("bruno.bianchini3@yaman.com.br");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input[1]")).sendKeys("automacao");
        driver.findElement(By.xpath("//*[contains(@value,'Cadastrar')]")).click();
              
        // Valida mensagem de "Usuário cadastrado" com o print da tela.
        
        logger.info("Step 3 – Validar texto \"Usuário inserido com sucesso\"");
        driver.findElement(By.xpath("//*[contains(text(),'Usuário inserido com sucesso')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/Evidencias/Print_CadastroUsuario.jpg"));
        
	}
}
