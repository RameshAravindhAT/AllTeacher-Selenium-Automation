package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;


@Listeners(utils.CustomTestListener.class)
public class TC_006_UpdateCohort extends BaseClass{
	
	public class TC_005_ViewCohort extends BaseClass{
		
		@BeforeTest
		public void testDetails()
		{
			sheetName="UpdateCohort";
		}
	
	
	@Test(dataProvider = "sendData")
	public void validateUpdateCohorts(String testNameDetails,String authorName, String category,String username, String password, String message,String cohortSelection, String date,String CohortDetails,String syllableDetails)
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
        .Click_on_Edit_Button(cohortSelection)
        .Verify_the_Edit_Page()
        .Verify_the_CohortName(CohortDetails)
        .Verify_the_Syllable(syllableDetails)
        .setting_up_the_Date(date)
        .Update_the_Words_and_Scentences()
        .Click_On_Update_Button()
        .Updating_the_Comments()
        .verify_the_toastMessage();
	}
	
	}

}
