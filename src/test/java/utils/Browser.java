package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class Browser implements WebDriver
{
    private TestContext context;
    private WebDriver driver;

    public Browser(TestContext context, WebDriver driver)
    {
        this.context = context;
        this.driver = driver;
    }

    public void waitForAjax()
    {
        AjaxWaiter.waitForAjaxLoad(this);
    }

    public void waitForAjaxAngular()
    {
        AjaxWaiter.waitForAjaxLoadAngular(this);
    }

    public void waitAndClick(WebElement element)
    {
        element.click();
        waitForAjax();
    }

    public void waitAndSendKeys(WebElement element, String key)
    {
        element.sendKeys(key);
        waitForAjax();
    }

    //--

    public void get(String s)
    {
        driver.get(s);

        waitForAjax();
        waitForAjaxAngular();
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    public String getTitle()
    {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by)
    {
        return driver.findElements(by);
    }

    public WebElement findElement(By by)
    {
        return driver.findElement(by);
    }

    public String getPageSource()
    {
        return driver.getPageSource();
    }

    public void close()
    {
        driver.close();
    }

    public void quit()
    {
        driver.close();
    }

    public Set<String> getWindowHandles()
    {
        return driver.getWindowHandles();
    }

    public String getWindowHandle()
    {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo()
    {
        return driver.switchTo();
    }

    public Navigation navigate()
    {
        return driver.navigate();
    }

    public Options manage()
    {
        return driver.manage();
    }

    //Getters and Setters


    public TestContext getContext()
    {
        return context;
    }

    public void setContext(TestContext context)
    {
        this.context = context;
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }
}
