package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

@Listeners(utils.CustomTestListener.class)
public class TC_008_DeleteCohort extends BaseClass {
	
	
	@BeforeTest
	public void testDetails()
	{
		sheetName="DeleteCohort";
	}


@Test(dataProvider = "sendData")
public void validatehistoryOfCohorts(String testNameDetails,String authorName, String category,String username, String password, String message,String cohortSelection, String promptActions, String cohortExists)
{
	test = extent.createTest(testNameDetails);
    test.assignAuthor(authorName);
    test.assignCategory(category);
    
    login
    .Enter_the_username(username)
    .Enter_the_password(password)
    .Click_on_the_loginButton()
    .VerifyToastMessage(message)
    .Click_On_Cohortmodule()
    .Click_Delete_to_RemoveCohort(cohortSelection)
    .Choose_the_prompts_and_verify_the_toastMessage(promptActions, cohortExists);
}

}

