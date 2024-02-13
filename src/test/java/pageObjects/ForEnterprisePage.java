package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForEnterprisePage extends BasePage{

	public ForEnterprisePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//div[@aria-label='Banner']//a[3]")
	WebElement universityBtn_loc;
	 
	 
	    public void clickForUniversities() {
	    	universityBtn_loc.click();
	    }

}
