package com.srbarriga.herokuapp;

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

    private static String msgLogger = "";

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
                logger.info("N�o foi possivel encontrar o arquivo::" + arquivoConfig);
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

    
  
    
    
}
