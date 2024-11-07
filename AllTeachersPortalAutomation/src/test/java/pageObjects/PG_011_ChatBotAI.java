package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;

import projectSpecifications.BaseClass;
import utils.ChatBotPrompts;

public class PG_011_ChatBotAI extends BaseClass{

	
	public PG_011_ChatBotAI(WebDriver driver) {
		  this.driver = driver; 
	        PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Message chatbot']")
	public WebElement ChatBotInputTextArea;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement promptSubmit;
	
	int count =2;
	
	public PG_011_ChatBotAI enter_the_Prompt()
	{
		 String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().replace("_", " ");
		try {
			ChatBotPrompts chatBot = new ChatBotPrompts(driver);
			
			Object[] prompts = chatBot.getChatBotPrompts(); 
			
			int count=0;
			
			for (Object prompt : prompts) {
	            String promptText = (String) prompt; 
		            ChatBotInputTextArea.sendKeys(promptText);
		            ExtentTest node = test.createNode(promptText); 
		            Thread.sleep(1000);
		            promptSubmit.click();
		            Thread.sleep(10000);
		            js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		            WebElement lastDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                    By.xpath("(//input[@placeholder='Message chatbot']//preceding::div[contains(@class,'container')])[last()]")
		                ));
		            List<WebElement> responseSpans = lastDiv.findElements(By.xpath(".//span"));
		            
		            if (responseSpans.size() == 1) {
		                String responseText = responseSpans.get(0).getText();
		                System.out.println(responseText);
		                node.info(responseText);
		            } else {
		                for (WebElement span : responseSpans) {
		                	 node.info(span.getText());
		                }
		            }  
			 }
			count++;
			if(count==94)
			{
				reportStep(methodName," pass"); 
	            logger.info(methodName);
			}
	    } catch (Exception e) {
	        logger.error(methodName+" "+"Is not clicked");
	        e.printStackTrace(); 
	    }
	    return this;
		
	}



}
