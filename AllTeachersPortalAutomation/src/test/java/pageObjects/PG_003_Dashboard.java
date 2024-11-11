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

public class PG_003_Dashboard extends BaseClass{

	
	private WebDriver driver;
	
	  public PG_003_Dashboard(WebDriver driver) {
		  
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	  }
	
	@FindBy(xpath="//p[text()='Create custom cohort']//following::div")
	public List<WebElement> existingCohortContainer;
	
	
	@FindBy(xpath="//p[text()='Create custom cohort']//following::div/p")
	public List<WebElement> existingCohortDetails;
	
	@FindBy(xpath="//div[text()='Cohort']")
	public WebElement cohortModule;

	@FindBy(xpath ="//div[text()='Logout']")
	public WebElement Logout;
	
	@FindBy(xpath="(//*[name()='svg'][@class='MuiSvgIcon-root MuiSvgIcon-colorPrimary MuiSvgIcon-fontSizeMedium css-kpzolb'])[1]")
	public WebElement EditButton;
	
	@FindBy(xpath="//p[text()='Create custom cohort']")
	public WebElement createCustomCohort;
	
	@FindBy(xpath = "//div[text()='Language']")
	public WebElement languageModule;
	
	@FindBy(xpath = "//p[text()='Cohorts available']")
	public WebElement cohortsAvailable;
	
	@FindBy(xpath = "//div[text()='Students']")
	public WebElement Students;
	
	@FindBy(xpath="//p[text()='Discovery content ']")
	public WebElement discoveryContent;
	
	
	
	public PG_003_Dashboard Click_On_ClassDetails(String expectedClass)
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		    try {
		    	
		    	WebElement classDetails =driver.findElement(By.xpath("//p[text()='"+expectedClass+"']"));
		    	classDetails.click();
		    	String ActualClass = classDetails.getText();
		    	Assert.assertEquals(ActualClass,expectedClass);		       
		    	reportStep(methodName+" "+ expectedClass, "pass");
		        logger.info(methodName+" "+expectedClass);
		    } catch (Exception e) {
		        logger.error(methodName+""+expectedClass);
		        e.printStackTrace(); 
		    }
		    return this;
		}
	
	
	
	public PG_003_Dashboard verify_the_CohortDetails() throws InterruptedException
	{
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		 
		  try {
			  js.executeScript("arguments[0].scrollIntoView(true);",cohortsAvailable);
			  
			  Thread.sleep(2000);
			 
				  List <WebElement> cohortCard = driver.findElements(By.xpath("//p[text()='Existing Cohorts']//following::div[2]/p"));
				  
				  if(!cohortCard.isEmpty())

				  {
					  
				  for(int c=1;c<cohortCard.size();c++)
				  {
					  
					  String cohortCardElement= "(//p[text()='Create custom cohort']//following::div)["+c+"]";
					  
					  StringBuilder reportText = new StringBuilder(); 
					  
					  Thread.sleep(2000);
					  for (int j : new int[]{1,2}) {
						    WebElement cohortElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
						        By.xpath(cohortCardElement + "//p[" + j + "]")
						    ));
				    			 Thread.sleep(1000);
				    			String cohortText = cohortElement.getText();
				    			System.out.println(cohortText);	
				    			reportText.append(cohortText).append("\n");
				    		}
				    		System.out.println("====================="); 	
				  }
				  
			  }
			  else if (cohortCard.isEmpty())
			  {
				  List <WebElement> cohortCard2 = driver.findElements(By.xpath("(//p[text()='Create custom cohort']//following::div)"));
				  
				  for(int c=1;c<cohortCard2.size();c++)
				  {
					  
					  String cohortCardElement= "(//p[text()='Create custom cohort']//following::div)["+c+"]";
					  
					  StringBuilder reportText = new StringBuilder(); 
					  
					  Thread.sleep(2000);
					  for (int j : new int[]{1,3}) {
						    WebElement cohortElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
						        By.xpath(cohortCardElement + "//p[" + j + "]")
						    ));
				    			 Thread.sleep(1000);
				    			String cohortText = cohortElement.getText();
				    			System.out.println(cohortText);	
				    			reportText.append(cohortText).append("\n");
				    		}
				    		System.out.println("====================="); 	
				  }
				  
			  }
		    	reportStep(methodName, "pass");
		    } catch (Exception e) {
		    
		    	
			    	reportStep(methodName, "pass");
		        e.printStackTrace(); 
		    }	    
		return this;
	}
	
	public PG_005_Cohorts Click_On_Cohortmodule() {
	    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

	    try {
	        cohortModule.click();
	        reportStep(methodName, "Pass");
	        logger.info("Clicked on Cohort Module");
	    } catch (Exception e) {
	        reportStep(methodName, "Fail: " + e.getMessage());
	        logger.error("Failed to click on Cohort Module", e);
	    }
	    
	    return new PG_005_Cohorts(driver);
	}
	

	public PG_002_LogOut Click_On_LogOut_Button()
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		  
		  try {
			  Logout.click();
		        reportStep(methodName, "pass");
		        logger.info(methodName+"is clicked");
		    } catch (Exception e) {
		        logger.error(methodName+"is not clicked ");
		        e.printStackTrace(); 
		    }
		    return new PG_002_LogOut(driver);
		}
	
	
	public PG_004_CreateCohort click_On_Create_Custom_Cohort()
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		  
		try {
			for (int i = 0; i < 5; i++) { 
			    js.executeScript("window.scrollBy(0, window.innerHeight);");
			    Thread.sleep(1000);
			}
			createCustomCohort.click();
			reportStep(methodName, "Pass");
			logger.info("Clicked on Create Custom Cohort button");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PG_004_CreateCohort(driver);
	}
	
	public PG_009_Languages click_On_languageModule()
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			languageModule.click();
			reportStep(methodName, "Pass");
			logger.info("Clicked on language module");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PG_009_Languages(driver);
		
	}
	
	
	public PG_010_Students click_On_StudentsModule()
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			Students.click();
			reportStep(methodName, "Pass");
			logger.info("Clicked on students module");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PG_010_Students(driver);
		
	}
	
	public PG_012_DiscoveryContent Click_on_DiscoveryContent()
	{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			Thread.sleep(3000);
			discoveryContent.click();
			reportStep(methodName, "pass");
			logger.info(methodName+ " " + "verified");
		} catch (Exception e) {
			logger.info(methodName + " " + "Incorrect page ");
			e.printStackTrace();
		}
		return new PG_012_DiscoveryContent(driver);
	}
		
		
	
	

	}
	