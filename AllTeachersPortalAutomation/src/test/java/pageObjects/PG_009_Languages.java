package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import projectSpecifications.BaseClass;

public class PG_009_Languages extends BaseClass {
	
	private WebDriver driver;

	public PG_009_Languages(WebDriver driver) {
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='English']")
	public WebElement languages;

	@FindBy(xpath = "//p[text()='Select language']//following::button[text()='confirm']")
	public WebElement confirmButton;

	@FindBy(xpath = "//div[contains(@class,'MuiAlert-message')]")
	public WebElement toastmessage;

	public PG_009_Languages Click_on_the_language(String expectedlanguages) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			driver.findElement(By.xpath("//*[text()='" + expectedlanguages + "']")).click();
			reportStep(methodName + " " + expectedlanguages, "pass");
			logger.info(methodName + " " + expectedlanguages + "" + "is been clicked ");
		} catch (Exception e) {
			reportStep(methodName + " " + expectedlanguages, "fail");
			logger.info(methodName + " " + expectedlanguages + "" + "is not been clicked ");
			e.printStackTrace();
		}
		return this;
	}

	public PG_009_Languages Click_on_the_ConfirmButton() {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			confirmButton.click();
			reportStep(methodName, "pass");
			logger.info(methodName+ " " + "is been clicked ");
		} catch (Exception e) {
			logger.info(methodName + " " + "is not been clicked ");
			e.printStackTrace();
		}
		return this;
	}
	
	public PG_009_Languages Verify_the_toast_Message_and_syllable_verified(String classdetails, String expectedlanguages) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			
			String onSuccess="Language changed successfully";
			String onfailure="You are already in the "+expectedlanguages+" language.";
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(toastmessage));

			if(toastmessage.getText().equals(onSuccess))
			{
				Assert.assertEquals(toastmessage.getText(),onSuccess);
				PG_003_Dashboard dashboard = new PG_003_Dashboard(driver);
				Thread.sleep(2000);
				dashboard
				.click_On_ClassDetails(classdetails)
				.verify_the_CohortDetails();
				reportStep(methodName +" "+ onSuccess+" "+"and syllable is"+" "+expectedlanguages, "pass");
				logger.info(methodName+ " " + "is been clicked ");
			}
			else if(toastmessage.getText().equals(onfailure))
			{
				Assert.assertEquals(toastmessage.getText(),onfailure);
				reportStep(methodName +" "+  onfailure, "pass");
				driver.close();
				logger.info(methodName+ " " + "is been clicked ");
			}
		} catch (Exception e) {
			logger.info(methodName + " " + "is not been clicked ");
			e.printStackTrace();
		}
		return this;
	}


}