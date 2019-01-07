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

public class ContaExistente {

    @Test
    public void CT4_testeContaExistente_Negativo() throws IOException {
    	
    	// Diretório com webdrivers (*Chrome, Firefox e IE).
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");
        
        // Instancia chromeDriver e webDriverWait
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        // Com "implicitlyWait" é permitido a espera por qualquer comando no restante do código, 
        // onde se um elemento não estiver disponível, ele aguarda até o tempo definido no comando.
        // Abre a URL da aplicação e maximiza o browser para tela cheia.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://srbarriga.herokuapp.com/login");
        driver.manage().window().maximize();
        
        // Aguarda a tela ser carrega pela presença do botão entrar.
        // Preenche o campo usuário e senha e clica no botao entrar.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Entrar')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input[1]")).sendKeys("automacao@yaman.com.br");
        driver.findElement(By.xpath("//label[contains(text(),'Senha')]/following::input")).sendKeys("automacao");
        driver.findElement(By.xpath("//*[contains(text(),'Entrar')]")).click();
        
        // Aguarda a tela ser carrega pela presença do da label "Seu Barriga".
        // Clica no menu contas para o cadastro de uma nova conta.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'navbar-brand')]")));
        driver.findElement(By.xpath("//*[contains(text(),'Contas')]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Adicionar')]")).click();
        
        // Aguarda a tela ser carrega pela presença do botão salvar.
        // Preenche o campo nome, com o nome da conta a ser cadastrada.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Salvar')]")));
        driver.findElement(By.xpath("//label[contains(text(),'Nome')]/following::input")).sendKeys("ContaCorrente1");
        driver.findElement(By.xpath("//*[contains(text(),'Salvar')]")).click();
      
        // Valida a mensagem de conta já cadastrada, com o print da tela.
        driver.findElement(By.xpath("//*[contains(text(),'Já existe uma conta com esse nome!')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/Evidencias/Print_ContaExistente_Negativo.png"));
      
        //Compara com o Equals, os dados inseridos com o dados cadastrados.
        String dadosPagina = driver.getPageSource();
        equals(dadosPagina.contains("automacao@yaman.com.br"));
        equals(dadosPagina.contains("automacao"));
        equals(dadosPagina.contains("ContaCorrente1"));

        // fecha o browser
        driver.quit();
    }

}
