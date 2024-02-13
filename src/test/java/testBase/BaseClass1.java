package testBase;
import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import java.net.URL;

import java.text.SimpleDateFormat;

import java.time.Duration;

import java.util.Date;

import java.util.Properties;
 
import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.Platform;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;

import com.aventstack.extentreports.model.Test;

public class BaseClass1 {
 
	public static WebDriver driver;

	public static Logger loger;

	public Properties p;

	@BeforeClass(groups= {"sanity","regression","masters"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException, InterruptedException {

		FileReader file = new FileReader(".//src//test//resources//config.properties");

		p=new Properties();

		p.load(file);

		loger=LogManager.getLogger(BaseClass1.class);

		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities=new DesiredCapabilities();

			//os

			if(os.equalsIgnoreCase("windows"))

			{

				capabilities.setPlatform(Platform.WIN11);

			}

			else if(os.equalsIgnoreCase("mac"))

			{

				capabilities.setPlatform(Platform.MAC);

			}

			else {

				System.out.println("No matching os");

				return;

			}

			//browser

			switch(br.toLowerCase()) {

			case "chrome":capabilities.setBrowserName("chrome");

			break;

			case "edge":capabilities.setBrowserName("MicrosoftEdge");

			break;

			default: System.out.println("No matching browser");

			return;

		}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		}

		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {

		// Locally

		switch(br.toLowerCase()) {

		case "chrome":driver=new ChromeDriver();

		break;

		case "edge":driver=new EdgeDriver();

		break;

		default: System.out.println("No matching browser");

		return;

		}

		}

		//driver=(WebDriver) new ChromeDriver();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL"));

		driver.manage().window().maximize();

	}

	@AfterClass(groups= {"sanity","regression","master"})

	public void tearDown() {

		driver.quit();

	}

	public String captureScreen(String tname) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot)driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath= System.getProperty("user.dir")+"\\screenShots\\"+ tname + "-" + timeStamp +".png";

		File targetFile= new File(targetFilePath);

		src.renameTo(targetFile);

		return targetFilePath;

	}
}
