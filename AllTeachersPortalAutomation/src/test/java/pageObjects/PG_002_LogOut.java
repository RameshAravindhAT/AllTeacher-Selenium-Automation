package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import projectSpecifications.BaseClass;

public class PG_002_LogOut extends BaseClass {
	
	private WebDriver driver;

	
	
	  public PG_002_LogOut(WebDriver driver) {
		  
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	  }
	

    public PG_002_LogOut Click_On_Prompt(String PromptActions)
    {

		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		  
		  try {
			 
			  driver.findElement(By.xpath("//button[text()='"+PromptActions+"']")).click();
			  
		        reportStep(methodName + " " + PromptActions, "pass");
		        logger.info(methodName+"is clicked");
		    } catch (Exception e) {
		        logger.error(methodName+" " + PromptActions+" Is not clicked");
		        e.printStackTrace(); 
		    }
		    return this;
    }
    
    public PG_002_LogOut verify_the_Results(String expectedURL)
    {
    	 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
    	 try {
			  
			  String currentUrl = driver.getCurrentUrl();
				
             Assert.assertEquals(currentUrl,expectedURL);
          
		        reportStep(methodName + " " +expectedURL , "pass");
		        logger.info(methodName+"is clicked");
		    } catch (Exception e) {
		        logger.error(methodName+" " + expectedURL+" is not verified");
		        e.printStackTrace(); 
		    }
		    return this;
    }
    
    
    
}


    

