package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;


@Listeners(utils.CustomTestListener.class)
public class TC_001_Login extends BaseClass{
	
	@BeforeTest
	public void testDetails()
	{
		sheetName="Login";
	}
	
	
	@Test(dataProvider = "sendData")
    public void validateLogin(String testNameDetails,String authorName, String category,String username, String password, String message) throws InterruptedException {
		
		test = extent.createTest(testNameDetails);
        test.assignAuthor(authorName);
        test.assignCategory(category);
        
		    login
            .Enter_the_username(username)
            .Enter_the_password(password)
            .Click_on_the_loginButton()
            .VerifyToastMessage(message);
    }

}
