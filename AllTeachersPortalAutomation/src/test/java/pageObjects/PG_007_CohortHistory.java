package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;

public class PG_007_CohortHistory extends BaseClass{
	
	private WebDriver driver;
	
	public PG_007_CohortHistory (WebDriver driver)
	{
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="(//p)[1]")
	public WebElement CohortInfopage;
	
	@FindBy(xpath ="((//div)[5]/div)[2]")
	public WebElement toastMessage;
	
	@FindBy(xpath ="((//div)[5]/div)[2]")
	public List <WebElement> lastupdatehistory;
	 
	 
	 
	 
	 public PG_007_CohortHistory verify_CohortInfo_Page()
	 {

		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		    
		 try {
			 
			 Assert.assertEquals(CohortInfopage.getText(),"Cohort info");
		        reportStep(methodName, "Pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        logger.error(methodName, e);
		    }
		    
		    return this;
	 }
	 
	 public PG_007_CohortHistory scroll_to_the_lastUpdate()
	 {

		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		    
		 try {
			 for (int i = 0; i < 1; i++) {
				    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				    Thread.sleep(2000);
				}
			 Thread.sleep(3000);
			 WebElement lastrecordelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//div)[5]/div)[2]")));
				wait.until(ExpectedConditions.elementToBeClickable(lastrecordelement)).click();
			    reportStep(methodName, "Pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        logger.error(methodName, e);
		    }
		    
		    return this;
	 }
	 
	 public PG_008_DeleteCohort navigate_to_Delete()
	 {
		 return new PG_008_DeleteCohort(driver);
	 }
	 
	 

}
