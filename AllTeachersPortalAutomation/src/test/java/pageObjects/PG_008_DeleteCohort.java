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

public class PG_008_DeleteCohort extends BaseClass {
	
	private WebDriver driver;

    public PG_008_DeleteCohort(WebDriver driver) {
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//p)[1]")
    public WebElement CohortInfopage;

    @FindBy(xpath = "//div[@role='presentation']//div[2]")
    public WebElement toastMessage;

    @FindBy(xpath = "((//div)[5]/div)[2]")
    public List<WebElement> lastupdatehistory;

    public PG_008_DeleteCohort Choose_the_prompts_and_verify_the_toastMessage(String PromptActions, String cohortExists) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");

        try {
            WebElement actualPrompt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'MuiDialogContent-root')]/p//following::button[text()='" + PromptActions + "']")));

            if (actualPrompt.getText().equalsIgnoreCase("Yes")) {
                driver.findElement(By.xpath("//button[text()='" + PromptActions + "']")).click();
                Thread.sleep(1000);
                String actualtoast = wait.until(ExpectedConditions.visibilityOf(toastMessage)).getText();
                Assert.assertEquals(actualtoast, "Cohort deleted successfully");
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//p[text()='My cohorts']//following::div[5]//div[*]/p)[text()='" + cohortExists + "']")));
                reportStep(methodName + " " + PromptActions, "pass" + " cohort is successfully deleted");
                logger.info(methodName + " is clicked");
            } else if (actualPrompt.getText().equalsIgnoreCase("No")) {
                driver.findElement(By.xpath("//button[text()='" + PromptActions + "']")).click();
                Thread.sleep(3000);
                WebElement cohortPresent = driver.findElement(By.xpath("(//p[text()='My cohorts']//following::div[5]//div[*]/p)[text()='" + cohortExists + "']"));
                if (cohortPresent.getText().equalsIgnoreCase(cohortExists)) {
                    reportStep(methodName + " " + PromptActions, "pass");
                }
            }
        } catch (Exception e) {
            logger.error(methodName + " " + PromptActions + " is not clicked");
            test.fail("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return this;
    }
}
