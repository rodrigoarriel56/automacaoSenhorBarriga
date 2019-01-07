package com.srbarriga.herokuapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {

	
	public static boolean getScreenShot(WebDriver driver, String nomePrint) throws Exception {
			
		SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMdd");
		Calendar data = Calendar.getInstance();
	
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:/Evidencias/screenshot_" + formatoData.format(data.getTime()) +".png"));
		return true;
			
    }
		
}


