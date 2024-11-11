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


public class PG_004_CreateCohort extends BaseClass{
	
	private WebDriver driver;
	
	
	  public PG_004_CreateCohort(WebDriver driver) {
		  
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);;
	  }

	
	@FindBy(xpath="(//p[text()='Create cohort']/following::button/following::div)[3]/button[text()]")
	public List<WebElement> syllableCharacters;

	@FindBy(xpath="(//input[@type='text'])[1]")
	public WebElement cohortName;
	
	@FindBy(xpath="//button[text()='Confirm']")
	public WebElement Confirm;
	
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement Cancel;
	
	@FindBy(xpath="(//div[@role='alert']//div)[2]")
	public WebElement toastMessage;

	@FindBy(xpath="//p[text()='Eligible students']")
	public WebElement EligibleStudents;
	
	@FindBy(xpath="(//input[@type='text'])[1]")
	public WebElement inputareaforCohortName;
	
	@FindBy(xpath = "(//p[text()='Eligible students']/following::div)[4]/p[1]")
	public WebElement CohortDetails;
	
	@FindBy(xpath = "(//p[text()='Eligible students']/following::div)[4]/p[3]")
	public WebElement SyllableDetails;
	
	@FindBy(xpath = "//button[@id='create-and-go-next-button']")
	public WebElement Createandgonext;
	
	@FindBy(xpath = "//button[@id='cancel-button']")
	public WebElement cancel;
	
	@FindBy(xpath = "//span[text()='Add words']")
	public WebElement addWords;
	
	@FindBy(xpath = "//span[text()='Add sentences']")
	public WebElement addScentence;
	
	@FindBy(xpath ="//p[text()=' Click to add ']/following::div[1]/div[1]//div[contains(@class, 'MuiChip-colorPrimary')and contains(@class, 'css-17197bz')]")
	public List <WebElement> Easywords;
	
	@FindBy(xpath ="//p[text()=' Click to add ']/following::div[1]/div[2]//div[contains(@class, 'MuiChip-colorPrimary')and contains(@class, 'css-17197bz')]")
	public List <WebElement> Mediumwords;
	
	@FindBy(xpath ="//p[text()=' Click to add ']/following::div[1]/div[3]//div[contains(@class, 'MuiChip-colorPrimary')and contains(@class, 'css-17197bz')]")
	public List <WebElement> Hardwords;
	
	@FindBy(xpath ="//div[contains(@class, 'MuiGrid-root') and contains(@class, 'MuiGrid-item') and contains(@class, 'css-1d81pem')]/button")
	public WebElement confirmButton;
	
	@FindBy(xpath ="//p[text()='My cohorts']")
	public WebElement MyCohort;
	
	@FindBy(xpath ="//div[contains(@class,'MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 MuiCard-root css-3ozxh9')]/p[1]")
	public List<WebElement> verifyCreatedCohortName;
	

	
	public PG_004_CreateCohort choose_the_syllable(String syllable) throws InterruptedException
	{
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		 try {
			char[] charArray = syllable.toCharArray();
			 
			 for (int i=0;i<charArray.length;i++)
			 {
				 char character = charArray[i];
				 driver.findElement(By.xpath("(//p[text()='Create cohort']/following::button/following::div)[3]/button[text()='" + character + "']")).click();
				 Thread.sleep(1000);
			 }
			 
			 if(syllable.length()<5)
			 {
			     Confirm.click(); 
			     Thread.sleep(4000);
				 String cohortNamellimitWarnings ="Please select at least 5 syllables";
				 String actualtoastMessage =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='alert']//div)[2]"))).getText();
				 logger.info(actualtoastMessage);
				
				 if(actualtoastMessage.equals(cohortNamellimitWarnings))
				 {
					 reportStep(methodName+" "+syllable+" "+actualtoastMessage, "pass");
					 logger.info("Choosen the syllable");
					 driver.close();
				 }
			 }
			 else if(syllable.length()>5)
			 {
				 String syllablelengthWarnings ="Please enter a cohort name (20 char limit)";
				 
				 inputareaforCohortName.sendKeys("");
				 
				 Confirm.click();
				 
				 Thread.sleep(1000);
				 
				 String actualtoastMessage =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='alert']//div)[2]"))).getText();
				 
				 if(actualtoastMessage.equals(syllablelengthWarnings))
				 {
					 reportStep(methodName+" "+syllable+" "+actualtoastMessage, "pass");
					 logger.info(syllablelengthWarnings);
					 driver.close();
				 }
			 }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 return this;
	}
	
	public PG_004_CreateCohort enter_the_cohortName(String Cohortname)
	{
		System.out.println(Cohortname);
		logger.info(Cohortname);
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		 
		 try 
		 {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[1]")));
			 cohortName.sendKeys(Cohortname);
			 reportStep(methodName, "pass");
			 logger.info("Entered the Cohort Name");
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
			 
		 }
		 
		 return this;
		
	}
	
	public PG_004_CreateCohort Click_On_ConfirmButton()
	{
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		
		try {
			Confirm.click();
			
			reportStep(methodName, "Pass");
			logger.info("Confirm button has clicked");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return this;
	}
	
	public PG_004_CreateCohort verifying_the_toast(String expectedMessage)
	{
		
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		 
		 try {
			 
			 WebElement toastContent =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='alert']//div)[2]")));
			 
			 String actualtoastMessage = toastContent.getText();
			 
			 
			if(actualtoastMessage.equals("Cohort created successfully"))
			 {
				reportStep(methodName + " " + actualtoastMessage, "Pass");
				 logger.info("Toast has been verified");
				 			 }
			else if (actualtoastMessage.equals(expectedMessage))
			{
				reportStep(methodName + " " + actualtoastMessage, "Pass");

				driver.close();
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 
		 return this;
	}
	
	public PG_004_CreateCohort verify_EligibleStudentspage()
	{
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		
		try {
			wait.until(ExpectedConditions.visibilityOf(EligibleStudents));
			System.out.println(EligibleStudents.getText());
			Assert.assertEquals(EligibleStudents.getText(), "Eligible students");
			reportStep(methodName+ " ", "pass");
			logger.info("Verified the Eliglible Students page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return this;
	}
	
	public PG_004_CreateCohort Click_On_the_EliglibleStudents() throws InterruptedException
	{
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		 
		 try {
			    logger.info("Coming here");
			    List<WebElement> eligibleStudentlist = driver.findElements(By.xpath("//input[@type='radio']"));
			    
			    int studentsCount = eligibleStudentlist.size();
			    
			    int count =0;
			    for (WebElement radioElement : eligibleStudentlist) {
			    
			    	if(radioElement.isSelected())
			    	{
			    		count++;
			    	}
				}
			    
			    if(count==studentsCount)
			    {
			    	reportStep(methodName+" "+"All radio buttons for students are already selected.", "Pass");
		            logger.info("All radio buttons for students are already selected.");
		          driver.close();
			    }
			    else {
			        for (WebElement radioElement : eligibleStudentlist) {
			            if (!radioElement.isSelected()) {
			                radioElement.click();
			                Thread.sleep(3000);
			                WebElement dummyClickDiv = driver.findElement(By.xpath("(//input[@type='radio']/following::div)[1]"));
			                dummyClickDiv.click();
			            }
			        }
			    }
		 } catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		return this;
	}
	
	public PG_004_CreateCohort Click_On_Create_and_go_Next()
	
	{
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		 
		 try {
			    wait.until(ExpectedConditions.elementToBeClickable(Createandgonext));
			            Createandgonext.click();
			            logger.info("Button is clicked");
			            reportStep(methodName + " ", "pass");
			            Thread.sleep(8000);
			        } catch (Exception e) {
			            logger.error("An error occurred: " + e.getMessage());
			        }
			return this;
	}
	public PG_004_CreateCohort Click_On_AddWords() throws InterruptedException {
		
	    String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

	    try {
	        
	        for (int attempt = 1; attempt <= 4; attempt++) {
	        	wait.until(ExpectedConditions.elementToBeClickable(addWords)).click();
		        Thread.sleep(2000);
		        logger.info("Add word button is clicked");
		        
	            int Easycount = Easywords.size();
	            int Mediumcount = Mediumwords.size();
	            int Hardcount = Hardwords.size();
	            int[] counts = {Easycount, Mediumcount, Hardcount};
	            if (Easycount == 5 || Mediumcount == 5 || Hardcount == 5) {
	                wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
	                Thread.sleep(3000);
	                logger.info("Check for each field has count of 5");
	            }
	            
	            for (int i = 0; i < counts.length; i++) {
	                if (counts[i] < 5) {
	                    int RemainingelementCounttobeClick = 5 - counts[i];
	                    System.out.println("Remaining to click for difficulty " + (i + 1) + ": " + RemainingelementCounttobeClick);

	                    int divIndex = i + 1;

	                    List<WebElement> unSelectWords = driver.findElements(
	                        By.xpath("//p[text()=' Click to add ']/following::div[1]/div[" + divIndex + "]//div[contains(@class, 'MuiChip-colorSecondary') and contains(@class, 'css-11wbon5')]")
	                    );

	                    if (unSelectWords.isEmpty()) {
	                        WebElement moreButton = driver.findElement(By.xpath("(//span[text()='more...'])["+attempt+"]"));
	                        if (moreButton.isDisplayed()) {
	                            moreButton.click();
	                            Thread.sleep(5000);
	                        }
	                    } else {
	                        int wordsToClick = Math.min(RemainingelementCounttobeClick, unSelectWords.size());

	                        for (int k = 0; k < wordsToClick; k++) {
	                            Thread.sleep(1000);
	                            unSelectWords.get(k).click();
	                        }
	                    }
	                }
	            }
	        }

	        reportStep(methodName, "pass");
	        logger.info("Step is over");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return this;
	}


	public PG_004_CreateCohort Verify_the_Created_Cohort(String cohortName)
	{
		  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		  
		 try {
		        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
		        
			 Thread.sleep(3000);
			Assert.assertEquals(MyCohort.getText(), "My cohorts");
			 
			 String cohortNameExpected = cohortName.toUpperCase();

			 for (int i = 0; i < verifyCreatedCohortName.size(); i++) {
			     String actualCohortName = verifyCreatedCohortName.get(i).getText();
			     
			     if(actualCohortName.equalsIgnoreCase(cohortNameExpected))
			     {
			    	 Assert.assertEquals(actualCohortName,cohortNameExpected);
			    	 List <WebElement> Syllable = driver.findElements(By.xpath("//div[contains(@class,'MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 MuiCard-root css-3ozxh9')]/p[" + i + "]"));
			    	 for (WebElement syllable : Syllable) {
			    		 logger.info(syllable.getText());	
					}
			     }       
			 }
			 reportStep(methodName+" "+cohortName, "Pass");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
         return this;
		
	}
	

}
