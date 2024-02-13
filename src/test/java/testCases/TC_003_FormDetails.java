package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountDetailsFormPage;
import pageObjects.ForEnterprisePage;
import pageObjects.ForUniversitiesPage;
import pageObjects.HomePage;
import pageObjects.PlanDetailsFormPage;

public class TC_003_FormDetails extends TC_002_LanguageLearning{
	  @Test(priority=6, groups= {"regression","masters"})
	    public void navigateToForm() throws InterruptedException, IOException {
	        // Initialize page objects
	        HomePage homePage = new HomePage(driver);
	        ForEnterprisePage forEnterprisePage = new ForEnterprisePage(driver);
	        ForUniversitiesPage forUniversitiesPage = new ForUniversitiesPage(driver);

	        
	        //navigate to home page 
//	        loger.info("navigate to home page");
//	        homePage.navigateToHomePage();
	        
	        // Navigate to "For Enterprise" and follow the steps
	        loger.info("click on for enterprise");
	        homePage.clickForEnterprise();
	        
	        //navigate to "for universities "
	        loger.info("click on for universities");
	        forEnterprisePage.clickForUniversities();
	        
	        //navigate to form
	        loger.info("navigate to form");
	        forUniversitiesPage.navigateToForm();
	    }
	  
	  @Test(priority=7, groups= {"sanity","masters"})
	  public void fillFormWithInvalidInput() throws InterruptedException, IOException{
		 //initialize with page objects
	        PlanDetailsFormPage planDetailsPage = new PlanDetailsFormPage(driver);
	        AccountDetailsFormPage accountDetailsPage = new AccountDetailsFormPage(driver);
		 //fill plan details 
	        loger.info("filling plan details page");
	        planDetailsPage.fillPlanDetails();
	        
	     //fill account details
	        loger.info("filling account details page");
	        String name = p.getProperty("name");
	        String email = p.getProperty("email");
	        String job = p.getProperty("job");
	        String organization = p.getProperty("organization");
	        accountDetailsPage.fillForm(name, email, job, organization);
	  }
	  @Test(priority =8, groups= {"sanity","masters"})
	  public void validateErrorMessage() throws InterruptedException, IOException{
		  
		  //initialize with page objects
	        AccountDetailsFormPage accountDetailsPage = new AccountDetailsFormPage(driver);
	       
	        //validate if error message is displayed
//	        if (accountDetailsPage.isEmailErrorDisplayed()) {
//	            System.out.println("Error Message is displayed");
//	        } else {
//	            System.out.println("Error Message is not displayed");
//	        }
	       
	    	// Get the actual error message
	        loger.info("extracting the error message");
	        String actualErrorMessage = accountDetailsPage.getEmailErrorMessage();
	        
	        //Print error message
	        System.out.println("The error message is :");
	        System.out.println(actualErrorMessage);
	       

	        // Define the expected error message
	        String expectedErrorMessage = "Please enter a valid email.";

	        // Assert whether the actual error message matches the expected error message
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message for invalid email is incorrect.");
		  
	  }
	  
	  
}

