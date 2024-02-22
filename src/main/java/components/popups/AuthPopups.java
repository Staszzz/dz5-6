package components.popups;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthPopups extends AbsCommon implements IPopup{

    public AuthPopups(WebDriver driver) {
        super(driver);
    }

    private String authPopupSelector = "#__PORTAL__ > div";
    private String login = System.getProperty("login");
    private String password = System.getProperty("password");

    @Override
    public void popupShouldBeVisible() {
        waitTools.waitElementPresent(By.cssSelector(authPopupSelector));
    }

    @Override
    public void popupShouldNotBeVisible() {
        waitTools.waitNotElementPresent(By.cssSelector(authPopupSelector));
    }

    public void enterDataEmail() {
        driver.findElement(By.xpath("//div[./input[@name='email']]")).click();

        WebElement emailInputField = driver.findElement(By.cssSelector("input[name='email']"));
        waitTools.waitForCondition(ExpectedConditions.visibilityOf(emailInputField));
        emailInputField.sendKeys(login);
    }

    public void enterDataPassword() {
        WebElement passwordInputField = driver.findElement(By.cssSelector("input[type='password']"));
        driver.findElement(By.xpath("//div[./input[@type='password']]")).click();
        waitTools.waitForCondition(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.sendKeys(password);
    }

    public void clickSignInBtnPopups() {
        driver.findElement(By.cssSelector("#__PORTAL__ button")).click();
    }
}
