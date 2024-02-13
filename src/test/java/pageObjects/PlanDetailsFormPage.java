package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlanDetailsFormPage extends BasePage {

	public PlanDetailsFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//input[@aria-label='Number of users']")
	private WebElement numberOfUsersInput;

	@FindBy(xpath = "//span[contains(text(),'Continue')]")
	private WebElement continueButton;
	
	@FindBy(xpath="//span[contains(text(), 'No thanks')]")
	private WebElement discountClose_loc;


	public void fillPlanDetails() {
        //checking if discount popup is displayed and closing it 
        try{ 
        	if(discountClose_loc.isDisplayed())
        		discountClose_loc.click();
        	}
        	catch(Exception e){}
        
		// Generate a random number between 5 and 50
		int randomNumberOfUsers = (int) (Math.random() * (50 - 5 + 1)) + 5;

		// Convert the random number to a String
		String randomNumberOfUsersString = String.valueOf(randomNumberOfUsers);
		
		// Use sendKeys to input the random number into the WebElement
		numberOfUsersInput.sendKeys(randomNumberOfUsersString);
		
		//click on continue button 
		continueButton.click();
	}
}
