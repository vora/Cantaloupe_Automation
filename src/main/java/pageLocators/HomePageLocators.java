package pageLocators;

import org.openqa.selenium.By;

public class HomePageLocators {

    public By carousel = By.xpath("//ul[@class='control-dots']/li"); ////ul[@class='control-dots']/li"
    public By corousalHeaders = By.xpath("//*[@id='__next']/main/div/div/div[2]/div/div/div/div/ul/li/div/h1");
    public By moreCardimg = By.xpath("//img[@alt='Cantaloupe More Card']");
    public By logoImg = By.xpath("//img[@alt='cantaloupe more logo']");
    public By cpayCardImge = By.xpath("//img[@alt='cpay Card']");
    public By morePassImg = By.xpath("//img[@alt='Cantaloupe Payment Options']");
    public By moreTransactionImg = By.xpath("//img[@alt='Google Wallet/Apple Pay Card']");
    public By firstHeader = By.xpath("//*[text()='Reload your pass with a credit or debit card']");
    public By secondHeader = By.xpath("//*[text()='Your More Pass Works with Apple Wallet']");
    public By thirdHeader = By.xpath("//*[text()='Add a CPay pass to pay with digital tokens']");
    public By fourthHeader = By.xpath("//*[text()='View your transactions, get help, and more ']");
    public By alreadyHaveAnAccountLink = By.xpath("//*[text()='Already have an account?']");
    public By createAccountButton = By.xpath("//*[text()='Create account']");
    public By errorStatus503 = By.xpath("//*[text()='Request failed with status code 503'");
    public By copyRights = By.xpath("//*[contains(text(),'© 2022 Cantaloupe, Inc, All Rights Reserved.']");
   // public By logoImg = By.xpath("//img[@src='images/logo-more-small.svg']");
    public By signInButton = By.xpath("//*[text()='']']");
    public By createAccountBigButton = By.xpath("//*[@class='styles__Container-sc-ajoebm-0 cUxQNN']/button");
    public By alreadyHaveAccountButton = By.xpath("//*[text()='Already have an account?']");
    public By continueWithEmail = By.xpath("//*[text()='continue with email']");
    public By createAccountText = By.xpath("//*[text()='Create Account']");












}