package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

@Listeners(utils.CustomTestListener.class)
public class TC_004_CreateCohort extends BaseClass {
	
	@BeforeTest
	public void testDetails()
	{
		sheetName="CreateCohort";
	}
	
	
	@Test(dataProvider = "sendData")
    public void validateCreateCohort(String testNameDetails,String authorName, String category,String username, String password, String message, String classDetails, String syllable,String expectedtoastMessage, String cohortName, String expectedCohortName) throws InterruptedException {
		
		test = extent.createTest(testNameDetails);
        test.assignAuthor(authorName);
        test.assignCategory(category);
        
        
		    login
            .Enter_the_username(username)
            .Enter_the_password(password)
            .Click_on_the_loginButton()
            .VerifyToastMessage(message)
            .click_On_ClassDetails(classDetails)
            .click_On_Create_Custom_Cohort()
            .choose_the_syllable(syllable)
            .enter_the_cohortName(cohortName)
            .Click_On_ConfirmButton()
            .verifying_the_toast(expectedtoastMessage)
            .verify_EligibleStudentspage()
            .Click_On_the_EliglibleStudents()
            .Click_On_Create_and_go_Next()
            .Click_On_AddWords()
            .Verify_the_Created_Cohort(expectedCohortName);
    }
	

}
