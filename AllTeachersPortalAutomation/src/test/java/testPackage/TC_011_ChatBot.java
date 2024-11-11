package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

@Listeners(utils.CustomTestListener.class)
public class TC_011_ChatBot extends BaseClass{
	
	
	@BeforeTest
	public void testDetails()
	{
		sheetName="ChatBot";
	}
	
	
    @Test(dataProvider = "sendData")
	public void validateChatBotAI(String testNameDetails,String authorName, String category,String username, String password)
	{
	test = extent.createTest(testNameDetails);
    test.assignAuthor(authorName);
    test.assignCategory(category);
    
    
    
    login
    .Enter_the_username(username)
    .Enter_the_password(password)
    .Click_on_the_loginButton()
    .click_on_ChatBot_Menu()
    .enter_the_Prompt();
	}
	

}
