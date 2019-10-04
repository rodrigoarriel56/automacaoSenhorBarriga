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

public class ContaExistente {

	private static Logger logger = LoggerFactory.getLogger(Login.class);
	private String URL = "https://srbarriga.herokuapp.com/login";
	
	Utils utils = new Utils();
	
    @Test
    public void CT04_testeContaExistente_Negativo() throws IOException {
    	
    	// Diretório com webdrivers (*Chrome, Firefox e IE).
    	
    	utils.driverChrome();
                
        // Instancia chromeDriver e webDriverWait
    	
    	WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Com "implicitlyWait" é permitido a espera por qualquer comando no restante do código, 
        // onde se um elemento não estiver disponível, ele aguarda até o tempo definido no comando.
        // Abre a URL da aplicação e maximiza o browser para tela cheia.
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("CT-04: TESTE CONTA EXISTENTE");
        logger.info("Acessa a URL: " + URL );
        driver.get("https://srbarriga.herokuapp.com/login");
        driver.manage().window().maximize();
        
        // Aguarda a tela ser carrega pela presença do botão entrar.
        // Preenche o campo usuário e senha e clica no botao entrar.
        
        logger.info("Step 1 -> Preenche usuário, senha e clica em Entrar");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[1]")).sendKeys("bruno.bianchini@yaman.com.br");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input[1]")).sendKeys("automacao");
        driver.findElement(By.xpath("/html/body/div[2]/form/button")).click();
        
        // Aguarda a tela ser carrega pela presença do da label "Seu Barriga".
        // Clica no menu contas para o cadastro de uma nova conta.
        
        logger.info("Step 2 -> No menu, clica em  Contas >> Adicionar.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'navbar-brand')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Contas')]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Adicionar')]")).click();
        
        // Aguarda a tela ser carrega pela presença do botão salvar.
        // Preenche o campo nome, com o nome da conta a ser cadastrada.
        
        logger.info("Step 3 -> Preenche o campo nome da conta e clica em Salvar.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Salvar')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Nome')]/following::input")).sendKeys("ContaCorrente10");
        driver.findElement(By.xpath("//*[contains(text(),'Salvar')]")).click();
      
        // Valida a mensagem de conta já cadastrada, com o print da tela.
        
        logger.info("Step 4 -> Valida mensagem de cadastro de conta já existente.");
        driver.findElement(By.xpath("//*[contains(text(),'Já existe uma conta com esse nome!')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/Evidencias/print_ContaExistente.jpg"));
      
        //Compara com o Equals, os dados cadastrados.  
        logger.info("Step 5 -> Compara os dados cadastrados.");
        String dadosPagina = driver.getPageSource();
        equals(dadosPagina.contains("Conta Bigorna"));
        equals(dadosPagina.contains("ContaCorrente10")); //Verificar com Humberto sobre o Equal e AssertEqual

        // Fecha o browser
        logger.info("Step 6 -> Fechando o browser. ");
        driver.close();
        
    }

}
