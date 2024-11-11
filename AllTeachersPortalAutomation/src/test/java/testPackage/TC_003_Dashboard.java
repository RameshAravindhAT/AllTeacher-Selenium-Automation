package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

@Listeners(utils.CustomTestListener.class)
public class TC_003_Dashboard extends BaseClass{
	
	@BeforeTest
	public void testDetails()
	{
		sheetName="Dashboard";
	}
	
	
	@Test(dataProvider = "sendData")
	public void validateExistingCohortDetails(String testNameDetails,String authorName, String category,String username, String password, String message, String classdetails) throws InterruptedException
	{
		
		test = extent.createTest(testNameDetails);
        test.assignAuthor(authorName);
        test.assignCategory(category);
        
		login
		.Enter_the_username(username)
		.Enter_the_password(password)
		.Click_on_the_loginButton()
		.VerifyToastMessage(message)
		.Click_On_ClassDetails(classdetails);

		
	}

}
