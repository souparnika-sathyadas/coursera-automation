package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForUniversitiesPage extends BasePage{

	public ForUniversitiesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = "//a[normalize-space()='Solutions']")
	private WebElement solutionsLink;
	
	@FindBy(xpath = "//p[normalize-space()='Upskill teams of 5 to 125 employees']")
	private WebElement teamsLink;
	
	@FindBy(xpath = "//div[@class='css-1rltwv6']//span[normalize-space()='Get Started']")
	private WebElement getStartedButton;

	
	public void navigateToForm() {
		solutionsLink.click();
		teamsLink.click();
		getStartedButton.click();
	}

}
