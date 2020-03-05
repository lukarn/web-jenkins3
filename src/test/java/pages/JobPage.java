package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobPage extends Page {

    @Override
    public boolean isAt(){
        return this.activatePushesButton.isDisplayed();
    }


    @FindBy(css = "#activatePushes")
    private WebElement activatePushesButton;


    public JobPage(WebDriver driver)
    {
        super(driver);
    }






}
