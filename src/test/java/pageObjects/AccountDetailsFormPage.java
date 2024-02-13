package pageObjects;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelManager;

public class AccountDetailsFormPage extends BasePage{
	
	ExcelManager excelutility = new ExcelManager();


	public AccountDetailsFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//input[@aria-label='Full name']")
	private WebElement fullNameInput;
	@FindBy(xpath = "//input[@aria-label='Business email']")
	private WebElement emailInput;
	@FindBy(xpath = "//input[@aria-label='Job title']")
	private WebElement jobInput;
	@FindBy(xpath = "//input[@aria-label='Organization name']")
	private WebElement organizationNameInput;
	@FindBy(xpath="//span[contains(text(),'Continue')]")
	private WebElement continueButton;
	@FindBy(xpath="//span[contains(text(), 'No thanks')]")
	private WebElement discountClose_loc;

	public void fillForm(String name, String email, String job, String organization) throws InterruptedException {
		Thread.sleep(5000);
		fullNameInput.sendKeys("abc");
		emailInput.sendKeys("abc");
		jobInput.sendKeys("Engineer");
		organizationNameInput.sendKeys("xyz");
		continueButton.click();
	}
	
	  // Method to check if the error message is displayed
    public String getEmailErrorMessage() throws IOException {
        //checking if discount popup is displayed and closing it 
        try{ 
        	if(discountClose_loc.isDisplayed())
        		discountClose_loc.click();
        	}
        	catch(Exception e){}
        // Wait for the error message to be present in the DOM
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	// Using XPath to locate the second error message element
    	WebElement emailErrorElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cds-react-aria-14-validation-label\"]/span")));
    	
    	
        //write error message in excel sheet
		excelutility.setCellData("test_case_3", 0, 0, "Error Message");
		excelutility.setCellData("test_case_3", 0, 1, emailErrorElement.getText());
        // Return the text of the error message
        return emailErrorElement.getText();
        // Check if the error message is displayed
        //return emailErrorElement.isDisplayed();
     

    }
}
