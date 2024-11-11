package pageObjects;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;

public class PG_006_UpdateCohort extends BaseClass {
	
	private WebDriver driver;
	
	public PG_006_UpdateCohort (WebDriver driver)
	{
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="((//p[text()='My cohorts']//following::div)[1]//p)//following::p[text()='Cohort Name c1']")
	public WebElement cohortSelection;
	
	@FindBy(xpath="(//p)[1]")
	public WebElement EditCohortpage;
	
	@FindBy(xpath="((//p[text()='Edit cohorts']//following::div)[6]//following::p)[2]")
	public WebElement syllableDetails;

	@FindBy(xpath ="(//input)[1]")
	public WebElement cohortName;
	
	@FindBy(xpath ="//input[@type='date']")
	public WebElement date;
	
	@FindBy(xpath ="//div[contains(@class, 'MuiGrid-root MuiGrid-item css-iu8cs2')]/div/p/following::div[1]/div")
	public List <WebElement> words;
	
	@FindBy(xpath ="(//div[contains(@class, 'MuiGrid-root MuiGrid-item css-iu8cs2')]/div)[1]/div/div[contains(@class,'MuiChip-colorSecondary')]")
	public List <WebElement> Easywords;
	
	@FindBy(xpath ="(//div[contains(@class, 'MuiGrid-root MuiGrid-item css-iu8cs2')]/div)[2]/div/div[contains(@class,'MuiChip-colorSecondary')]")
	public List <WebElement> Mediumwords;
	
	@FindBy(xpath ="(//div[contains(@class, 'MuiGrid-root MuiGrid-item css-iu8cs2')]/div)[3]/div/div[contains(@class,'MuiChip-colorSecondary')]")
	public List <WebElement> Hardwords;
	
	@FindBy(xpath ="(//textarea)[1]")
	public WebElement updateTextArea;
	
	@FindBy(xpath ="//button[text()='confirm']")
	public WebElement confimButton;
	
	@FindBy(xpath ="((//div)[5]/div)[2]")
	public WebElement toastMessage;

	
	public PG_006_UpdateCohort Verify_the_Edit_Page()
	{
	    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
	    
		 try {
			 
			 wait.until(ExpectedConditions.visibilityOf(EditCohortpage));
			
			 Assert.assertEquals(EditCohortpage.getText(),"Edit cohorts");
		        reportStep(methodName, "Pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        reportStep(methodName, "Fail: " + e.getMessage());
		        logger.error(methodName, e);
		    }
		    
		    return this;
		}
	
	public PG_006_UpdateCohort Verify_the_CohortName(String cohortDetail)
	{
	    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
	    
		 try {
			 
			 @Nullable
			String extractingCohortDetails = cohortName.getAttribute("value");
			
			 Assert.assertEquals(extractingCohortDetails,cohortDetail);
		        reportStep(methodName, "Pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        reportStep(methodName, "Fail: " + e.getMessage());
		        logger.error(methodName, e);
		    }
		    
		    return this;
		}
	
	public PG_006_UpdateCohort Verify_the_Syllable(String syllables)
	{
	    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
	    
		 try {
			 
			 StringBuilder concatenatedString = new StringBuilder();

			 String[] syllable = syllableDetails.getText().split(",");

			 for (int i = 0; i < syllable.length; i++) {
			     String syllableJoin = syllable[i].trim();
			     concatenatedString.append(syllableJoin);
			 }
			 String result = concatenatedString.toString();
			 
			 System.out.println(result);
			
			 Assert.assertEquals(result,syllables);
		        reportStep(methodName+""+syllables, "Pass");
		        logger.info(methodName);
		    } catch (Exception e) {
		        reportStep(methodName, "Fail: " + e.getMessage());
		        logger.error(methodName, e);
		    }
		    
		    return this;
		}
	
	public PG_006_UpdateCohort setting_up_the_Date(String Date)
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
         try { 
        	  String dateValue = Date;
        	 js.executeScript("arguments[0].value = arguments[1];", date, dateValue);
		        reportStep(methodName, "pass");
		        logger.info("Date is setted up");
		    } 
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		    return this;
		
	}
	
	public PG_006_UpdateCohort Update_the_Words_and_Scentences()
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		
		
		try {
			
			String[] taskCategory = {"assignment", "assessment", "homework"};

			for (int i = 0; i < taskCategory.length; i++) { 
				WebElement taskXpath = driver.findElement(By.xpath("((//div[contains(@class,'MuiTablePagination-actions')])[1]//following::p[text()='"+taskCategory[i]+"']//following::div/p[text()='Word :'])[1]"));
				
			    js.executeScript("arguments[0].scrollIntoView(true);", taskXpath);
			    Thread.sleep(2000);
			    System.out.println(taskCategory[i]);
			    
			    String[] wordCategory = {"easy", "medium", "hard"};
			    for (int k = 0; k < wordCategory.length; k++) { 
			    	String combinedXpath = "((//div[contains(@class,'MuiTablePagination-actions')])[1]//following::p[text()='"+taskCategory[i]+"']//following::div/p[text()='Word :'])[1]//following::div/p[text()='"+wordCategory[k]+"']//following::div[contains(@class,'MuiChip-colorSecondary')]";
			        Thread.sleep(2000);
			        List<WebElement> wordslist = driver.findElements(By.xpath(combinedXpath));
			        System.out.println(wordCategory[k]);
			        for (int j = 0; j <=2; j++) {
			            wordslist.get(j).click();
			            Thread.sleep(1000);
			        }
			    }
			}
		        reportStep(methodName, "pass");
		        logger.info("");
		    } 
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		    return this;
		}
	
	public PG_006_UpdateCohort Click_On_Update_Button()
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try
		{
			WebElement updateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Update cohort']")));
		    js.executeScript("arguments[0].scrollIntoView(true);", updateButton);
		    
		    updateButton.click();
			
		    reportStep(methodName, "pass");
	        logger.info("");
		}
		catch(Exception e)
		{
			reportStep(methodName, "Fail: " + e.getMessage());
	        logger.error(methodName, e);
			e.printStackTrace();
		}
		return this;
	}
	
	public PG_006_UpdateCohort Updating_the_Comments()
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try
		{
			updateTextArea.sendKeys("Testing Comments");
		    
		   confimButton.click();
			
		    reportStep(methodName, "pass");
	        logger.info("");
		}
		catch(Exception e)
		{
			reportStep(methodName, "Fail: " + e.getMessage());
	        logger.error(methodName, e);
			e.printStackTrace();
		}
		return this;
	}
	
	public PG_006_UpdateCohort verify_the_toastMessage()
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try
		{
			updateTextArea.sendKeys("Testing Comments");
		    
		   confimButton.click();
		   
		   WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='alert']//div)[2]")));
		   
		   if(toastMessage.getText().equals("Cohort updated successfully"))
		   {
			   Assert.assertEquals(toastMessage.getText(),"Cohort updated successfully");
			   reportStep(methodName, "pass");
		        logger.info("Cohort Updates Successfully");   
		   }
		   else if(toastMessage.getText().equals("End Date must be today or later"))
		   {
			   Assert.assertEquals(toastMessage.getText(),"End Date must be today or later");
			   reportStep(methodName,"End Date must be today or later"+ "pass");
		        logger.info("Cohort not update due to past date"); 
			   
		   }
		}
		catch(Exception e)
		{
			reportStep(methodName, "Fail: " + e.getMessage());
	        logger.error(methodName, e);
			e.printStackTrace();
		}
		return this;
	}
	
	
	public PG_007_CohortHistory naviagtetoHistoryCohort()
	{
		return new PG_007_CohortHistory(driver);
	}

}
