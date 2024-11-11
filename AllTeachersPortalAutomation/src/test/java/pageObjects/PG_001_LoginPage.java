package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;

public class PG_001_LoginPage extends BaseClass{

	
	
	  public PG_001_LoginPage(WebDriver driver) {
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	  }
	
	
	@FindBy(name ="email")
	public WebElement userName;
	
	@FindBy(name ="password")
	public WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement loginButton;
	
	@FindBy(xpath="//p[text()='Chatbot ']")
	public WebElement ChatBot;
	
	@FindBy(xpath ="((//div)[5]/div)[2]")
	public WebElement toastMessage;
	
	@FindBy(xpath ="(//div[contains(@class,'standardWarning')])[1]//div[2]")
	public WebElement dateWarning;
	
	@FindBy(xpath ="(//span[text()='Email']//following::span)[1]")
	public WebElement emailFieldwarnings;
	
	@FindBy(xpath ="(//span[text()='Password']//following::span)[1]")
	public WebElement passwordFieldwarnings;
	
	
	
	
	
	public PG_001_LoginPage Enter_the_username(String username) {
		
		reportStep("Opening the Browser","Pass");
		reportStep("Navigate to URL"+ " "+properties.getProperty("url"), "Pass");
		
	    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
	    try {
	        userName.sendKeys(username);
	        reportStep(methodName+" "+ username, "pass");
	        logger.info(methodName+" "+username);
	    } catch (Exception e) {
	        reportStep(methodName+" " + username, "fail");
	        logger.error(methodName+""+password);
	        e.printStackTrace(); 
	    }
	    return this;
	}
	
	
	
	public PG_001_LoginPage Enter_the_password(String passwordValue)
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try
		{
			password.sendKeys(passwordValue);
			reportStep(methodName + " " + passwordValue, "pass");
			logger.info(methodName+" "+passwordValue);
			
		}
		catch(Exception e)
		{
			reportStep(methodName + " " + passwordValue, "fail");
			logger.error(methodName+" "+passwordValue);
			 e.printStackTrace(); 
		}
		return this;
		
	}

	public PG_001_LoginPage Click_on_the_loginButton()
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try
		{
			loginButton.click();
			reportStep(methodName, "pass");
			logger.info(methodName);
			
		}
		catch(Exception e)
		{
			reportStep(methodName, "fail");
			 e.printStackTrace();
			 logger.error(methodName);
		}
		return this;
		
	}
	
	public PG_003_Dashboard VerifyToastMessage(String message) {
	 	String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

	    try {
	    	WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//div)[5]/div)[2]")));
	    	String actualMessage = toastMessage.getText();
	    	
	    	
	        	if  (toast.getText() != null) {
	        		String expectedMessage = message;
	        		if(toast.getText().equals("Login successful"))
	        		{
	        			 Assert.assertEquals(actualMessage, expectedMessage); 
	        			 
	        			driver.findElement(By.xpath("//p[text()='Desktop / Tab ']")).click();
	        			
	        			WebElement Homepage =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Dashboard']")));
	        			
	        			String dashboard = Homepage.getText();
	        			
	        			Assert.assertEquals(dashboard,"Dashboard");
	        		}
	        		else if(toast.getText().equals(message))
	        		{
	        			 Assert.assertEquals(actualMessage, expectedMessage);      			
	        		}
		            Assert.assertEquals(actualMessage, expectedMessage); 
		            reportStep(methodName + " " + actualMessage, "pass");
		            logger.info(methodName + " " + actualMessage);
		        }

	    } catch (Exception e) {
	    	WebElement emailfieldwarnings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Email']//following::span)[1]")));
	    	WebElement passwordwarnings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Password']//following::span)[1]")));
	    	
	    	String actualEmailwarnings = emailfieldwarnings.getText().trim().replaceAll("^[^a-zA-Z0-9]+", "");
	    	String actualPasswordwarnings =passwordwarnings.getText().trim().replaceAll("^[^a-zA-Z0-9]+", "");
	    
	    	String expectedMessagewarnings = message;
	    	
	    	 if(userName.getAttribute("value").isEmpty() && password.getAttribute("value").isEmpty())
	         {
	    		 String[] values = message.split("&");
	    		 Assert.assertEquals(actualEmailwarnings,values[0]);
	    	     Assert.assertEquals(actualPasswordwarnings,values[1]);
	    	     reportStep(methodName + " " + actualEmailwarnings+" "+actualPasswordwarnings, "pass");
   	             logger.info(methodName + " " + expectedMessagewarnings);
	         }
	    	 
	    	 else if (actualEmailwarnings.equals(expectedMessagewarnings)) {
            Assert.assertEquals(actualEmailwarnings, expectedMessagewarnings);
            reportStep(methodName + " " + expectedMessagewarnings, "pass");
            logger.info(methodName + " " + expectedMessagewarnings);
        } 
         else if(actualPasswordwarnings.equals(expectedMessagewarnings))
         {
            Assert.assertEquals(actualPasswordwarnings, expectedMessagewarnings);
            reportStep(methodName + " " + expectedMessagewarnings, "pass");
            logger.info(methodName + " " + expectedMessagewarnings);
            	      }
         else if (!userName.getAttribute("value").isEmpty() && !password.getAttribute("value").isEmpty())
    	 {
    		 String[] values = message.split("&");
    		 Assert.assertEquals(actualEmailwarnings,values[0]);
    	     Assert.assertEquals(actualPasswordwarnings,values[1]);
    	     reportStep(methodName + " " + actualEmailwarnings+" "+actualPasswordwarnings, "pass");
	             logger.info(methodName + " " + expectedMessagewarnings);
    	 }
            	else {
                    logger.error(methodName + expectedMessagewarnings);
                }
        	}
	    return new PG_003_Dashboard(driver);
	}
	
	public PG_011_ChatBotAI click_on_ChatBot_Menu()
	{
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		    try {
		    	wait.until(ExpectedConditions.visibilityOf(ChatBot)).click();
		    	Thread.sleep(2000);
		    	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		        reportStep(methodName,"pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        logger.error(methodName+" "+"Is not clicked");
		        e.printStackTrace(); 
		    }
		    return new PG_011_ChatBotAI(driver);
	}



}