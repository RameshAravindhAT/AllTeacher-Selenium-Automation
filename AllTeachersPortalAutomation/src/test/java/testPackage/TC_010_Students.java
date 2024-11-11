package testPackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

@Listeners(utils.CustomTestListener.class)
public class TC_010_Students extends BaseClass{
	

	
	@BeforeTest
	public void testDetails()
	{
		sheetName="Students";
	}


@Test(dataProvider = "sendData")
public void validateStudentsRecord(String testNameDetails,String authorName, String category,String username, String password, String message,String classDetails, String studentId, String expectedStudentId)
{
	test = extent.createTest(testNameDetails);
    test.assignAuthor(authorName);
    test.assignCategory(category);
    
    login
    .Enter_the_username(username)
    .Enter_the_password(password)
    .Click_on_the_loginButton()
    .VerifyToastMessage(message)
    .click_On_StudentsModule()
    .Verify_the_Students_Page()
    .Click_on_the_classDetail(classDetails)
    .Click_On_the_student(studentId)
    .Verify_the_StudentDetail(expectedStudentId);
}

}

