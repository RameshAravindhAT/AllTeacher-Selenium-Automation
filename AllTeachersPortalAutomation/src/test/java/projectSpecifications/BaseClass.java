package projectSpecifications;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageObjects.PG_001_LoginPage;
import utils.CustomTestListener;
import utils.ExcelReader;

public class BaseClass{
	
	
    public static Logger logger = Logger.getLogger(BaseClass.class);
	public static String logfile = Paths.get("src", "test", "java", "utils", "Log4j.properties").toAbsolutePath().toString();
	
	public static ExtentSparkReporter sparkreporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest node;
	public static String reportFilePath = Paths.get("ExtentReports","extentReports.html").toAbsolutePath().toString();
	
	public static Properties properties;
	public static FileInputStream file;
	public static String configFilePath = Paths.get("Properties", "Config.properties").toAbsolutePath().toString();
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	
	public static String testName, testDescription, author, category;
	public static String excelfilename = Paths.get("TestData", "AllTeacherTestDatas.xlsx").toAbsolutePath().toString();
    public static String sheetName;
    
    public static PG_001_LoginPage login;

    
    public static CustomTestListener listener = new CustomTestListener();

    
    

	
	
	@BeforeSuite
	public ExtentReports startReports() throws IOException
	{
		PropertyConfigurator.configure(logfile);
		
		logger.info("Log4j configured successfully.");
		
		properties = new Properties();
		
		file = new FileInputStream(configFilePath);
		
		properties.load(file);
		
		sparkreporter = new ExtentSparkReporter(reportFilePath);
		
	    extent = new ExtentReports();
	    
	    extent.attachReporter(sparkreporter);
	    
	    return extent;
		
	}

	
	
	@BeforeMethod
	public void setUp()
	{
	    //Initialize the Browser
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get(properties.getProperty("url"));
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		logger.info("Browser Launched and Navigate to the AllTeacher");
		
		js = (JavascriptExecutor) driver;
		
		login = new PG_001_LoginPage(driver);
		
		
	    
	}
	
	public void reportStep(String msg, String status) {
	    switch (status.toLowerCase()) {
	        case "pass":
	            test.pass(msg);
	            break;
	        default:
	            logger.warn("UNKNOWN STATUS: " + msg);
	            break;
	    }
	}
	
	
	@DataProvider (name="sendData")
    public String[][] fetchData() throws IOException {
        return ExcelReader.readexcelData(excelfilename, sheetName);
    }
	
	@AfterMethod
	public void tearDown()
	{
		
		driver.quit();
		reportStep("Closing the Browser", "Pass");
		logger.info("Browser Closed");
		logger.info("=============================================================");
		
	}
	
	@AfterSuite
	public void stopReports()
	{
		extent.flush();
	}

	
	
}
