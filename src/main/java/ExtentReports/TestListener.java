package ExtentReports;

import base.TestBase;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class TestListener extends TestBase implements ITestListener {


    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        String targetLocation = null;
        try {
            String errorName = result.getMethod().getMethodName();
            targetLocation = getScreenshot(driver, errorName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

        assert targetLocation != null;
        try {
            targetLocation = convertTo64BaseString();

            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(targetLocation).build());

        }  catch (IOException e) {
            ExtentTestManager.getTest().info("An exception occured while taking screenshot " + e.getCause());
        }

    }

    public void onTestSkipped(ITestResult result) {

        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }


    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
         File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/TestReport/" + screenshotName + " " + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    //Convert image to 64 base string
    public String convertTo64BaseString() throws IOException {
       File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       String path = getScreenshot(driver, "errorFile");
       FileUtils.copyFile(file, new File(path));
       byte[] image = IOUtils.toByteArray(new FileInputStream(path));
       return Base64.getEncoder().encodeToString(image);
    }

}