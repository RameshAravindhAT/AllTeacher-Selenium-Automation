package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

@Listeners(utils.CustomTestListener.class)
public class TC_009_Language extends BaseClass{
	

	
	@BeforeTest
	public void testDetails()
	{
		sheetName="Languages";
	}


@Test(dataProvider = "sendData")
public void validatelanguages(String testNameDetails,String authorName, String category,String username, String password, String message,String languages,String classDetails, String expectedlanguages)
{
	test = extent.createTest(testNameDetails);
    test.assignAuthor(authorName);
    test.assignCategory(category);
    
    login
    .Enter_the_username(username)
    .Enter_the_password(password)
    .Click_on_the_loginButton()
    .VerifyToastMessage(message)
    .click_On_languageModule()
    .Click_on_the_language(languages)
    .Click_on_the_ConfirmButton()
    .Verify_the_toast_Message_and_syllable_verified(classDetails,expectedlanguages);
}

}
