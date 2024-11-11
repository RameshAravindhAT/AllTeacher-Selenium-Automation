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

public class PG_005_Cohorts extends BaseClass{
	
	private WebDriver driver;
	
	  public PG_005_Cohorts(WebDriver driver) {
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	}


	
	@FindBy(xpath="//*[contains(text(),'Class:')]/following::div[1]/span[1][text()='Class III']")
	public WebElement classDetails;

	@FindBy(xpath ="//p[text()='vowels']")
	public List <WebElement> cohortName;
	
	
	

	public PG_005_Cohorts View_the_CohortDetails(String CohortDetails)
	{ 
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

    try {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[name()='svg'][@class='MuiSvgIcon-root MuiSvgIcon-colorPrimary MuiSvgIcon-fontSizeMedium css-kpzolb'])[2]"))).click();
    	
    	  reportStep(methodName, "Pass");
          logger.info("Click on view on a cohort card");
    }
   catch (Exception e) {
        reportStep(methodName, "Fail: " + e.getMessage());
        logger.error("Failed to click on buttons", e);
    }
    return this;
}
	
	public PG_005_Cohorts Verify_the_CohortDetails(String expectedCohortDetails) throws InterruptedException
	{ String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
    try {
    	WebElement cohortPage =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[text()='Cohort detail']/following::div/div)[4]/p[1]")));
    	String actualCohortName = cohortPage.getText().replace(":", "").trim();
    	Assert.assertEquals(actualCohortName,expectedCohortDetails);
        reportStep(methodName+" "+expectedCohortDetails, "Pass");
        logger.info("Verified the cohort details");
    } catch (Exception e) {
    	reportStep(methodName," Fail: " + e.getMessage());
        logger.error("Failed to click on Cohort Module", e);
    }
    return this;
}
	
	public PG_006_UpdateCohort Click_on_Edit_Button(String cohortSelection)
	{
	    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
	    
		 try {
			 
			 System.out.println(cohortSelection);
			 
			 String combinedXpath ="((((//p[text()='My cohorts']//following::div)[1]//p)//following::p[text()='"+cohortSelection+"']//following::div)[14]/button)[1]";
			 
			 WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(combinedXpath)));
			 button.click();
			 
			 Thread.sleep(2000);
		        reportStep(methodName, "Pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        reportStep(methodName, "Fail: " + e.getMessage());
		        logger.error(methodName, e);
		    }
		    
		    return new PG_006_UpdateCohort(driver);
		}
	
	 public PG_007_CohortHistory Selection_of_Cohort_to_ViewHistory(String cohortSelection)
	 {

		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		    
		 try {
			 
			 String combinedXpath ="((((//p[text()='My cohorts']//following::div)[1]//p)//following::p[text()='"+cohortSelection+"']//following::div)[14]/button)[4]";
			 WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(combinedXpath)));
			 button.click();
			 Thread.sleep(2000);
		        reportStep(methodName, "Pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        logger.error(methodName, e);
		    }
		    
		    return new PG_007_CohortHistory(driver);
	 }
	 
		
	 public PG_008_DeleteCohort Click_Delete_to_RemoveCohort(String cohortSelection)
	 {

		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		    
		 try {
			 
			 String combinedXpath ="((((//p[text()='My cohorts']//following::div)[1]//p)//following::p[text()='"+cohortSelection+"']//following::div)[14]/button)[3]";
			 
			 WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(combinedXpath)));
			 button.click();
		     reportStep(methodName, "Pass");
		     logger.info(methodName);
		    } catch (Exception e) {
		        logger.error(methodName, e);
		    }
		    
		    return new PG_008_DeleteCohort(driver);
	 }
	 

}
	

