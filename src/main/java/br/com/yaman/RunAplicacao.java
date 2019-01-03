package br.com.yaman;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yaman - Fredi Roldan
 */

public class RunAplicacao {

    public static final String UrlAplicacao = "https://srbarriga.herokuapp.com/login";
    public static boolean flag_Preenchido = false;
    public static int SLEEP_TIMEOUT = 10000;

    private static Logger logger = LoggerFactory.getLogger(RunAplicacao.class);
    private static Boolean verificaStatusOnline;
	private static WebDriverWait wait;
	public static void main(String[] args) throws Exception {

        WebDriver driver = inicializaChromeOptions(); 
        setResult(obtemDados(driver));
    }

	public static WebDriver inicializaChromeOptions() {
		String caminhoDoChromeDriver = Util.getConfigPropertiesAplicacao().getProperty("chromePath");
		logger.info("caminhoDoChromeDriver=" + caminhoDoChromeDriver);
		System.setProperty("webdriver.chrome.driver", caminhoDoChromeDriver);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
    
    private static void inicializaParametros() {
        verificaStatusOnline = new Boolean(Util.getConfigPropertiesAplicacao().getProperty("verificaStatusOnline"));
        SLEEP_TIMEOUT = (new Integer(Util.getConfigPropertiesAplicacao().getProperty("SLEEP_TIMEOUT")))*1000;
    }
    
    
    /**
	 * ACESSO A APLICAÇÃO DO SR. BARRIGA
	 * URL: https://srbarriga.herokuapp.com/login
     * @throws SiteNotFoundException 
     * @throws InterruptedException 
	 * 
	 */
	public static Aplicacao obtemDados(WebDriver driver) throws SiteNotFoundException, Exception {

		inicializaParametros();

		// Verifica se URL está acessivel através de um ping.
		RunAplicacao.flag_Preenchido = false;
		if (verificaStatusOnline) {
			if (!Util.verificaSeUrlEstaOnline(RunAplicacao.UrlAplicacao)) {
				throw new SiteNotFoundException("Site do Sr. Barriga aparentemente offline. Timeout = "
						+ (SLEEP_TIMEOUT / 1000) + "s.  URL acessada:" + RunAplicacao.UrlAplicacao);
			}
		}
	driver.get(RunAplicacao.UrlAplicacao);
        

	/**
	 * -------------------------------------------------------------------------------------------
	 * ============================ OBJETOS MAPEADOS DA TELA LOGIN ===============================
	 * -------------------------------------------------------------------------------------------
	 */
	    //usuário: seuemail@yaman.com.br
	    WebElement userInput = driver.findElement(By.xpath(".//*[@id='email']"));
	    userInput.sendKeys("frediroldan@yaman.com.br");
	    
	    //senha: sua senha
	    WebElement pwdInput = driver.findElement(By.xpath(".//*[@id='senha']"));
	    pwdInput.sendKeys("automacao");
	
	    //Botão Entrar
	    wait = new WebDriverWait(driver, 1);
	    WebElement btnEntrar = driver.findElement(By.xpath("/html/body/div[2]/form/button"));
	    btnEntrar.click();
        
   
    /**
	 * -------------------------------------------------------------------------------------------
	 * ================== OBJETOS MAPEADOS DO MENU> CONTAS - SUBMENU> ADICIONAR  =================
	 * -------------------------------------------------------------------------------------------
	 */
	    //Menu Contas
	    wait = new WebDriverWait(driver, 1);
	    WebElement menuContas = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[2]"));
	    menuContas.click();
	    
	    //SubMenu Adicionar
	    wait = new WebDriverWait(driver, 1);
	    WebElement subMenuAdd = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[2]/ul/li[1]/a"));
	    subMenuAdd.click();
        

    /**
	 * -------------------------------------------------------------------------------------------
	 * ========================== OBJETOS MAPEADOS DA TELA ADICIONAR =============================
	 * -------------------------------------------------------------------------------------------
	 */
        //Campo Nome
        wait = new WebDriverWait(driver, 1);
        driver.findElement(By.xpath(".//*[@id='nome']")).sendKeys("Conta Poupança");
        
        //Botão Salvar
        wait = new WebDriverWait(driver, 1);
        WebElement btnSalvar = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
        btnSalvar.click();

        driver.close();
        driver.quit();
		
	    RunAplicacao.flag_Preenchido = true;
	    return (null);
	}


		/**
		 * Methods Getters and Setters
		 * @param result
		 */
		public static void setResult(Aplicacao result) {
		}
}
   