package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

@Listeners(utils.CustomTestListener.class)
public class TC_005_ViewCohort extends BaseClass{
	
	@BeforeTest
	public void testDetails()
	{
		sheetName="ViewCohort";
	}

	@Test(dataProvider = "sendData")
	public void validateViewCohort(String testNameDetails,String authorName, String category,String username, String password, String message,String CohortDetails,String CohortExpected) throws InterruptedException
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
        .View_the_CohortDetails(CohortDetails)
        .Verify_the_CohortDetails(CohortExpected);
		
	}

}
