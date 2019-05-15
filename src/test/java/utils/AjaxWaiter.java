package utils;

import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

public class AjaxWaiter
{
    private static final String DOCUMENT_READY_STATE_SCRIPT = "return document.readyState";
    private static final String AJAX_WAIT_SCRIPT = "return typeof jQuery != 'undefined' && jQuery.active != 0";
    private static final String ANGULAR_WAIT_SCRIPT = "return angular.element(document).injector().get('$http').pendingRequests.length != 0";

    //-----

    public static void waitForAjaxLoad(Browser browser)
    {
        waitForReadyState(browser, AJAX_WAIT_SCRIPT);
    }

    public static void waitForAjaxLoadAngular(Browser browser)
    {
        waitForReadyState(browser, ANGULAR_WAIT_SCRIPT);
    }

    private static void waitForReadyState(Browser browser, String script)
    {
        try
        {
            sleep(250L);
            JavascriptExecutor executor = (JavascriptExecutor) browser.getDriver();
            boolean stillRunningAjax = Boolean.valueOf(executor.executeScript(script).toString());
            boolean isDocumentReadyState = executor.executeScript(DOCUMENT_READY_STATE_SCRIPT).equals("complete");

            int i = 0;
            while (!isDocumentReadyState && stillRunningAjax && i < 30)
            {
                i++;
                sleep(TimeUnit.SECONDS.toMillis(1L));
                stillRunningAjax = Boolean.valueOf(executor.executeScript(script).toString());
            }
        }
        catch (Exception e)
        {
            sleep(1000);
        }

    }

    private static void sleep(long durationInMillisecond)
    {
        try
        {
            Thread.sleep(durationInMillisecond);
        }
        catch (InterruptedException e1)
        {
            // noop
        }
    }
}
