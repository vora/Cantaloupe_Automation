package tests;

import base.BaseActions;
import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageActions.AccountCreation;
import pageActions.SignInWithExistingAccountActions;
import pageLocators.AccountCreationLocators;

import java.awt.*;
import java.io.IOException;

public class AccountCreationTest extends TestBase {

    AccountCreation accountCreation = new AccountCreation();
    AccountCreationLocators accountCreationLocators = new AccountCreationLocators();
    SignInWithExistingAccountActions signInWithExistingAccountActions = new SignInWithExistingAccountActions();
    BaseActions baseActions = new BaseActions();


    public AccountCreationTest() throws IOException {
    }

    @Test
    //Validate all errors such as empty input field, then error message, then existing email error message and login button flow
    public void verifyNavigationBackToHomePage() throws AWTException {

        //Regex, error message,enter email, tick mark, check for existing email error, allow to login, if new email - allow user to next screen
        //Validate create new email, tick mark, next button enabled and screen 2
        accountCreation.validateEmptyField();
        accountCreation.verifyNextButtonEnabled();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.clickBackToLoginOptionsLink();
    }

    //Validate create new email, tick mark, next button enabled and screen 2
    @Test
    public void verifyEmailUniqueness() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
    }

    @Test
    public void verifyPrefilledEmail_CreationFlow() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
        accountCreation.prefilledEmail(properties.getProperty("createNewEmail"));
        accountCreation.verifyEmailIsEditable(properties.getProperty("existingAccountEmail"));
        accountCreation.verifyPlaceHolders();
    }

    // Verify that, user is not able to use an already registered email id from other account
    @Test
    public void validateExisting_EmailError() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.existingEmailError_AccountCreation(properties.getProperty("existingAccountEmail"));
    }

    // validate login through existing user
    @Test
    public void verifySignInDisabled_existingEmail() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.loginThroughExistingEmail(properties.getProperty("existingAccountEmail"));
        signInWithExistingAccountActions.verifySignInDisabled();
    }

    @Test

    //Validate all errors such as empty input field, then error message, then existing email error message and login button flow
    public void verifyEmailEnteredIsValid() throws IOException, AWTException {
        // Verify email entered, tick mark
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.enteredEmaiCheck(properties.getProperty("createNewEmail"));
    }

    //Verify if the email is editable
    @Test
    //Validate all errors such as empty input field, then error message, then existing email error message and login button flow
    public void verifyEmailIsEditable() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verrifyTickMark();
        //Feedback - assert text entered n getAttribute - Remove js
        //accountCreation.enteredEmaiCheck(properties.getProperty("createNewEmail"));
        accountCreation.verifyEmailIsEditable(properties.getProperty("existingAccountEmail"));
    }

    //Verify wrong password fields
    @Test
    public void verifyPassword() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.tryWrongPassword(properties.getProperty("wrongPassword"));
        baseActions.clearData(driver.findElement(By.id("id_password")));
        accountCreation.validateErrorMessageForPassword(properties.getProperty("wrongPassword"));
        accountCreation.tryConfirmPassword(properties.getProperty("wrongPassword"));
        accountCreation.validateErrorMessageForConfirmPassword(properties.getProperty("wrongPassword"));
    }

    //Verify if both the password and confirm password matches adn click on show icon and hide icon
    @Test
    public void showPasswordAndHide() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.tryWrongPassword(properties.getProperty("wrongPassword"));
        baseActions.clearData(driver.findElement(By.id("id_password")));
        accountCreation.validateErrorMessageForPassword(properties.getProperty("wrongPassword"));
        accountCreation.tryConfirmPassword(properties.getProperty("wrongPassword"));
        accountCreation.validateErrorMessageForConfirmPassword(properties.getProperty("wrongPassword"));
        accountCreation.clickShowPassword();
        accountCreation.clickHidePassword();
    }

    @Test
    //Check if password/confirmPassword fields are editable, masked
    public void checkPasswordFields_Masked() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.isPasswordFieldEditable();
        accountCreation.isConfirmPasswordFieldEditable();
        accountCreation.checkPasswordMasked();
        accountCreation.confirmPasswordMasked();

    }

    //verify if the password and confirm passwords are a match
    @Test
    public void verifyPasswordAndConfirmPassword() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verifyPasswordAndConfirmPassword(properties.getProperty("verifyPassword"), properties.getProperty("verifyConfirmPassword"));
    }

    //verify personal details section
    @Test
    public void verifyPersonalDetailsSection() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verifyPasswordAndConfirmPassword(properties.getProperty("createAccountPassword"), properties.getProperty("createAccountConfirmPassword"));
        accountCreation.verifyFirstNameInput(properties.getProperty("firstName"));
        accountCreation.verifyLastNameInput(properties.getProperty("lastName"));
        accountCreation.verifyPhoneNoInput(properties.getProperty("mobileNumber"));
        accountCreation.verifyAddressInput(properties.getProperty("streetAddress"), properties.getProperty("city"), properties.getProperty("state"), properties.getProperty("zipcode"));
    }


    //verify if checkbox is not checked and validate the links
    @Test
    public void verifyConsentSection() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verifyPasswordAndConfirmPassword(properties.getProperty("createAccountPassword"), properties.getProperty("createAccountConfirmPassword"));
        accountCreation.verifyFirstNameInput(properties.getProperty("firstName"));
        accountCreation.verifyLastNameInput(properties.getProperty("lastName"));
        accountCreation.verifyPhoneNoInput(properties.getProperty("mobileNumber"));
        accountCreation.verifyAddressInput(properties.getProperty("streetAddress"), properties.getProperty("city"), properties.getProperty("state"), properties.getProperty("zipcode"));
        accountCreation.isCheckboxPreChecked();
        accountCreation.verifyTermsAndPrivacy();
    }

    @Test
    public void completeRegistration() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria("test@yopmail1.com");
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verifyPasswordAndConfirmPassword(properties.getProperty("createAccountPassword"), properties.getProperty("createAccountConfirmPassword"));
        accountCreation.verifyFirstNameInput(properties.getProperty("firstName"));
        accountCreation.verifyLastNameInput(properties.getProperty("lastName"));
        accountCreation.verifyPhoneNoInput(properties.getProperty("mobileNumber"));
        accountCreation.verifyAddressInput(properties.getProperty("streetAddress"), properties.getProperty("city"), properties.getProperty("state"), properties.getProperty("zipcode"));
        accountCreation.isCheckboxPreChecked();
        accountCreation.verifyTermsAndPrivacy();
        accountCreation.verifyAnyErrorsDisplayedOnScreen();
        accountCreation.clickCompleteButton();
        accountCreation.confirmRegistration();

    }

    //Verify if all the fields are in editable mode
    @Test
    public void verifyIfFieldsAreEditable() throws IOException, AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.isEmailFieldEditable();
        accountCreation.isPasswordFieldEditable();
        accountCreation.isConfirmPasswordFieldEditable();
        accountCreation.isFirstNameEditable();
        accountCreation.isLastNameEditable();
        accountCreation.isMobileEditable();
        accountCreation.isStreetAddressEditable();
        accountCreation.isCityEditable();
        accountCreation.isStateEditable();
        accountCreation.isZipcodeEditable();
    }

    @Test
    public void verifyFirstNameRegex() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verifyFirstNameRegex(properties.getProperty("firstName"));
    }

    @Test
    public void verifyLastNameRegex() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verifyFirstNameRegex(properties.getProperty("lastName"));
    }

    @Test
    public void verifyMobile() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        baseActions.enterData(accountCreationLocators.phoneNoInput, properties.getProperty("mobileNumber"));
        accountCreation.regexPhoneNo(properties.getProperty("mobileNumber"));
        accountCreation.verifyPhoneNoFormat();
    }

    @Test
    public void verifyMobileUniqueNessTick() throws AWTException, IOException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        baseActions.enterData(accountCreationLocators.phoneNoInput, properties.getProperty("newPhoneNo"));
        accountCreation.regexPhoneNo(properties.getProperty("newPhoneNo"));
        accountCreation.verifyPhoneNoFormat();
        accountCreation.verifyIfMobileNoISNew();
    }

    @Test
    public void verifyMobileUniqueNessdError() throws AWTException, IOException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        baseActions.enterData(accountCreationLocators.phoneNoInput, properties.getProperty("usedPhoneNo"));
        accountCreation.regexPhoneNo(properties.getProperty("usedPhoneNo"));
        accountCreation.verifyPhoneNoFormat();
        accountCreation.verifyIfMobileNoIsUsed();
    }


    @Test
    public void verifyError_After10DigitsInputOnly() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        baseActions.enterData(accountCreationLocators.phoneNoInput, properties.getProperty("existingMobileNo"));
        accountCreation.verifyErrorAfter10DigitsEntry();
    }

    @Test
    public void verifyAddressFields() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        accountCreation.enterAddressAndCityDetails(properties.getProperty("streetAddress"), properties.getProperty("city"));
        accountCreation.regexCompleteAddress(properties.getProperty("streetAddress"), properties.getProperty("regexFinalAddress"));
    }

    @Test
    public void verifyTOC_PPNavigations() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verifyNextButtonEnabled();
        baseActions.scrollDown();
        accountCreation.navigateToTOC();
        accountCreation.navigateBackToRegistration();
        accountCreation.navigateToPP();
        accountCreation.navigateBackToRegistration();
    }

    @Test
    public void verifyInputsBeforeAndAfterNavigations() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
        accountCreation.verifyPasswordAndConfirmPassword(properties.getProperty("createAccountPassword"), properties.getProperty("createAccountConfirmPassword"));
        accountCreation.verifyFirstNameInput(properties.getProperty("firstName"));
        accountCreation.verifyLastNameInput(properties.getProperty("lastName"));
        accountCreation.verifyPhoneNoInput(properties.getProperty("mobileNumber"));
        accountCreation.verifyAddressInput(properties.getProperty("streetAddress"), properties.getProperty("city"), properties.getProperty("state"), properties.getProperty("zipcode"));
        accountCreation.isCheckboxPreChecked();
        accountCreation.verifyTermsAndPrivacy();
        accountCreation.checkInputsAfterNavigation();
    }

    @Test
    public void verifyScreenForSignInSignOut() throws IOException, AWTException {
        accountCreation.verifyScreenForSignInAndOut();
    }

    @Test
    public void backToLoginLink() throws AWTException {
        accountCreation.clickCreateButton();
        accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
        accountCreation.verrifyTickMark();
        accountCreation.verifyNextButtonEnabled();
        baseActions.scrollDown();
        accountCreation.clickBackToLoginOptions();
    }

    //@Test

    //Validate all errors such as empty input field, then error message, then existing email error message and login button flow
    // public void verifyWrongEmail(String wrongEmail, String wrongPassword, String wrongFirstName, String wrongLastName, String wrongMobileNumber, String wrongStreetaddtress, String wrongcity, String wrongState, String wrongZipcode,) throws IOException, AWTException
    //{
    //   accountCreation.clickCreateButton();
    //accountCreation.verifyWrongEmail();
    //}



    //Validate all errors such as empty input field, then error message, then existing email error message and login button flow
    //public void verifyWrongEmail(String wrongEmail, String wrongPassword, String wrongFirstName, String wrongLastName, String wrongMobileNumber, String wrongStreetaddtress, String wrongcity, String wrongState, String wrongZipcode,) throws IOException, AWTException
    @Test
    public void verifyWrongMobile() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            accountCreation.verifyWrongMobile(properties.getProperty("wrongMobileNumber"));
        }
    }

    @Test
    public void verifyWrongEmail() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            baseActions.clearData(driver.findElement(accountCreationLocators.emailIdInputField));
            accountCreation.verifyWrongEmail(properties.getProperty("wrongEmail"));
        }
    }

    @Test
    public void verifyWrongFirstName() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            accountCreation.verifyWrongFirstName(properties.getProperty("wrongFirstName"));
        }
    }

    @Test
    public void verifyWrongLastName() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            accountCreation.verifyWrongLastName(properties.getProperty("wrongLastName"));
        }
    }

    @Test
    public void verifyWrongStreetAddress() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            accountCreation.verifyWrongStreetAddress(properties.getProperty("wrongStreetAddress"));
        }
    }

    @Test
    public void verifyWrongCity() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            accountCreation.verifyWrongCity(properties.getProperty("wrongCity"));
        }
    }

    @Test
    public void verifyWrongState() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            accountCreation.verifyWrongState(properties.getProperty("wrongState"));
        }
    }

    @Test
    public void verifyWrongZipcode() throws AWTException {
        {
            accountCreation.clickCreateButton();
            accountCreation.validateEmailCriteria(properties.getProperty("createNewEmail"));
            accountCreation.verifyNextButtonEnabled();
            accountCreation.verifyWrongZipcode(properties.getProperty("wrongZipcode"));
        }
    }
}