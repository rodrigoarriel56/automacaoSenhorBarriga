/**
 * @author Rodrigo Arriel	
 * @data 
 *
 */

package com.srbarriga.web;

import java.io.File;
import java.io.IOException;

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

public class CadastroConta {
	
	private static Logger logger = LoggerFactory.getLogger(CadastroConta.class);
	private String URL = "https://srbarriga.herokuapp.com/login";
	
	Utils utils = new Utils();

	@Test
    public void CT03_testeCadastroConta() throws IOException, InterruptedException {
    	
    	utils.driverChrome();    	    	   	
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(2000);
        
        
        logger.info("CT-03: TESTE CADASTRO CONTA");
        logger.info("Acessa a URL: " + URL );
        driver.get("https://srbarriga.herokuapp.com/login");
        driver.manage().window().maximize();
        Thread.sleep(2000);
                
                      
        logger.info("Step 1 -> Preenche usuário, senha e clica em Entrar");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[1]")).sendKeys("bruno.bianchini@yaman.com.br");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input[1]")).sendKeys("automacao");
        driver.findElement(By.xpath("/html/body/div[2]/form/button")).click();
        Thread.sleep(2000);
        
        
        logger.info("Step 2 -> No menu, clica em  Contas >> Adicionar.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'navbar-brand')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Contas')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(text(),'Adicionar')]")).click();
        Thread.sleep(2000);
          
        
        logger.info("Step 3 -> Preenche o campo nome da conta e clica em Salvar.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Salvar')]")));        
        driver.findElement(By.xpath("//label[contains(text(),'Nome')]/following::input[1]")).sendKeys("Conta Bigorna 10");
        driver.findElement(By.xpath("//*[contains(text(),'Salvar')]")).click();
        Thread.sleep(2000);
        
        
        logger.info("Step 4 -> Valida mensagem de cadastro de conta com sucesso.");
        driver.findElement(By.xpath("//*[contains(text(),'Conta adicionada com sucesso!')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\ProvasRodrigo\\Workspace\\Git\\automacaoSenhorBarriga\\Evidencias\\print_CadastroConta.jpg")); //Foi apenas inserido o caminho das evidencias
        Thread.sleep(2000);
        
        
        logger.info("Step 5 -> Compara os dados cadastrados.");
        String dadosPagina = driver.getPageSource();
        equals(dadosPagina.contains("Conta Bigorna 10"));
        Thread.sleep(2000);
        
        logger.info("Step 6 – Clicar em Sair.");
        driver.findElement(By.xpath("//*[contains(text(),'Sair')]")).click();
        Thread.sleep(2000);
        
                
        logger.info("Step 7 -> Fechando o browser. ");
        driver.close();
    }
}
