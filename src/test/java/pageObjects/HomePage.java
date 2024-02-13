package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//a[normalize-space()='For Enterprise']")
	WebElement forEnterpriseBtn;
	 
	 	public void navigateToHomePage() {
	 		driver.navigate().back();
	 	}
	    public void clickForEnterprise() {
	        forEnterpriseBtn.click();
	    }

}
