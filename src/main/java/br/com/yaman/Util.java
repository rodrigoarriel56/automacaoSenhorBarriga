package br.com.yaman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.time.LocalDateTime;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Util {

    private static Properties prop; 
    private static Logger logger = LoggerFactory.getLogger(Util.class);


    public static String getCaminhoAbsolutoAplicacao() {
        String temp = new File("").getAbsolutePath();
        temp = temp.substring(2);
        temp = temp.replace("\\", "/");
        return temp;
    }

    public static Properties getConfigPropertiesAplicacao(){
        if (prop!= null) return prop;

        prop = new Properties();
        InputStream input = null;

        try {

            String arquivoConfig = getCaminhoAbsolutoAplicacao() + "/config.properties";
            input = new FileInputStream(arquivoConfig);
            logger.info("Arquivo de configuracao:" + arquivoConfig);
            if(input == null){
                logger.info("Não foi possivel encontrar o arquivo::" + arquivoConfig);
                return null;
            }

            prop = new Properties();
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }

    public static boolean verificaSeUrlEstaOnline(String url) {
        return Util.pingURL(url, RunAplicacao.SLEEP_TIMEOUT);
    }
  
    private static boolean pingURL(String url, int timeout) {
        url = url.replaceFirst("^https", "http"); 

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
        }
    }
    
}
