package pageActions;

import base.BaseActions;
import base.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageLocators.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;


@Test(dataProvider = "CantaloupeTestData", dataProviderClass = DataProvider.class)
public class SignInWithExistingAccountActions extends TestBase {

        BaseActions baseActions = new BaseActions();
        LandingScreen loginScreen = new LandingScreen();
        HomePageLocators homePageLocators = new HomePageLocators();
        SignInWithExistingAccountLocators signInWithExistingAccountLocators = new SignInWithExistingAccountLocators();

        AccountCreationLocators accountCreationLocators = new AccountCreationLocators();
        LoggedInUserLocators loggedInUserLocators = new LoggedInUserLocators();
        CreateOrLoginInitialLocators createOrLoginInitialLocators = new CreateOrLoginInitialLocators();



    String emailErrorText = "Please enter your email.";

    public SignInWithExistingAccountActions() throws IOException {
    }

    //Click on already have an account link
    public void clickAlreadyExistingAccountLink()
        {
            driver.findElement(loginScreen.alreadyHaveAccountButton).isEnabled();
            driver.findElement(loginScreen.alreadyHaveAccountButton).click();
            driver.findElement(homePageLocators.continueWithEmail).click();
        }

        //clicks on continue with email link
        public void clickContinueWithEmail()
        {
            driver.findElement(accountCreationLocators.continueWithEmail).isEnabled();
            driver.findElement(accountCreationLocators.continueWithEmail).click();
        }


        //Verify error messages displayed whn there are no data entered
        public void validateErrorMessages() throws AWTException {
            driver.findElement(signInWithExistingAccountLocators.emailInput).click();
            baseActions.randomClickBasedOnOS();
            driver.findElement(signInWithExistingAccountLocators.passwordInput).click();
            baseActions.randomClickBasedOnOS();
            String emailErrorText = "Please enter your email.";
            String passwordErrorText = "Please enter your password.";
            List<String> getAllErrors = baseActions.getListElements(signInWithExistingAccountLocators.errorMessages);
            if((getAllErrors.contains(emailErrorText)) && (getAllErrors).contains(passwordErrorText))
            {
                Assert.assertTrue(true, "Error messages are displayed");
            }
            else
            {
                Assert.assertTrue(false, "Error messages are missing");
            }
        }



    //Validate all the fields on Sign in screen and enter data in email and password fields and verify sign in button mode
    public void verifySignInScreen(String existingAccountEmail, String existingAccountPassword) throws IOException, AWTException {


        driver.findElement(createOrLoginInitialLocators.continueWithEmail).click();
        String signInText = driver.findElement(signInWithExistingAccountLocators.signInText).getText();

        if(signInText.equalsIgnoreCase("Sign In")) {
            Boolean emailInnut = driver.findElement(signInWithExistingAccountLocators.emailInput).isEnabled();
            Boolean passwordInput = driver.findElement(signInWithExistingAccountLocators.passwordInput).isEnabled();
            Boolean forgotPasswordLink = driver.findElement(signInWithExistingAccountLocators.forgotPasswordLink).isEnabled();

            Assert.assertTrue(emailInnut);
            Assert.assertTrue(passwordInput);
            Assert.assertTrue(forgotPasswordLink);

            driver.findElement(signInWithExistingAccountLocators.emailInput).sendKeys(existingAccountEmail);
            baseActions.randomClickBasedOnOS();
            driver.findElement(signInWithExistingAccountLocators.passwordInput).sendKeys(existingAccountPassword);
            baseActions.randomClickBasedOnOS();

            Boolean buttonNotEnabled = driver.findElement(signInWithExistingAccountLocators.signInButton).isEnabled();


            if(buttonNotEnabled==false)
            Assert.assertTrue(true, "Button should be enabled and is working as expected");
        }

            else {
                Assert.assertTrue(false, "Sign in button is not working as expected");
            }
        }

    //Verify if the sign in button is in disabled mode when there is no data entered
    public void verifySignInDisabled()
    {
        WebElement continueWithEmail = driver.findElement(homePageLocators.continueWithEmail);
        continueWithEmail.click();
        WebElement buttonNotEnabled = driver.findElement(signInWithExistingAccountLocators.signInButton);
        if(buttonNotEnabled.isEnabled())
        {
            Assert.assertTrue(false, "Sign iin button is enabled and is not working as expected");
        }
        else
        {
            Assert.assertTrue(true, "Sign in button is wprking according to the functionality");
        }
    }


    //Verify user is able to login successfully
    @Test(dataProvider = "CantaloupeTestData", dataProviderClass = DataProvider.class)
    public void clickSignIn(String existingAccountEmail, String existingAccountPassword) throws IOException, AWTException {
            verifySignInScreen(existingAccountEmail, existingAccountPassword);
            driver.findElement(signInWithExistingAccountLocators.signInButton).click();
           if(driver.findElement(loggedInUserLocators.profileLink).isEnabled())
           {
               Assert.assertTrue(true, "User is logged in successfully and is on the initial home screen");
           }
           else
           {
               Assert.assertTrue(false, "User is not logged in successfully and is not on the initial home screen");
           }

           }

        //lick sign out link
    public void clickSignOut()
    {
        driver.findElement(loggedInUserLocators.signOutLink).click();
    }

    public void clickProfileLink()
    {
        driver.findElement(loggedInUserLocators.profileLink).click();
    }


}
