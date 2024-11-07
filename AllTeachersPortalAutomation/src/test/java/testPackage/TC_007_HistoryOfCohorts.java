package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

	
@Listeners(utils.CustomTestListener.class)
public class TC_007_HistoryOfCohorts extends BaseClass{
		
		@BeforeTest
		public void testDetails()
		{
			sheetName="HistoryOfCohort";
		}
	
	
	@Test(dataProvider = "sendData")
	public void validatehistoryOfCohorts(String testNameDetails,String authorName, String category,String username, String password, String message,String cohortSelection)
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
        .Selection_of_Cohort_to_ViewHistory(cohortSelection)
        .verify_CohortInfo_Page()
        .scroll_to_the_lastUpdate();
	    
	}

}

        
	