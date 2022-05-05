package pageLocators;

import org.openqa.selenium.By;

public class SignInWithExistingAccountLocators {

    public By signInText = By.xpath("//*[@id='login']/div[1]/h1");
    public By emailInput = By.xpath("//input[@id='id_email']");
    public By emailInputClearIcon = By.xpath("//span[@class='styles__ClearButton-sc-ghu8hj-4 gTnkhk']/img/@src");
    public By errorMessages = By.xpath("//*[@id='login']/div[1]/div/div/span");
    public By passwordInput = By.xpath("//input[@id='id_password']");
    //public By noPasswordEntered = By.xpath("//span[@class='styles__Msg-sc-1730kyp-1 fAhVqa']");
    public By showPasswordEye = By.xpath("//span[@class='styles__ClearButton-sc-ghu8hj-4 gTnkhk']/img/@src");
    public By nextButton = By.xpath("//button[@class='styles__Button-sc-1bmw993-0 iNrErr']");
    public By backToLoginText = By.xpath("//div[@class='styles__Text-sc-1r7ypxo-0 fwwrnt']");
    public By forgotPasswordLink = By.xpath("//*[@id='login']/div[1]/div[3]");
    public By signInButton = By.xpath("//*[@id='login']/div[2]/button");
    public By backToLogin = By.xpath("//div[@class='styles__Text-sc-1r7ypxo-0 fwwrnt']");


    public By carousel = By.xpath("//*ul[@class='control-dots']/li");
    public By corousalHeaders = By.xpath("//h1[@class='styles__Title-sc-1wf7u0n-1 huvhqC']");
}

