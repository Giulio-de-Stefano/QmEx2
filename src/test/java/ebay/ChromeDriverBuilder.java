package ebay;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static java.io.File.separator;
import static java.lang.System.getenv;
import static java.lang.System.setProperty;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ChromeDriverBuilder {
    private static String BASE_PATH = getenv("HOME") + separator + "Desktop" + separator + "exercise" + separator;

    public static ChromeDriver buildInstance() {
        final String CHROMEDRIVER_PATH = BASE_PATH + "chromedriver";
        setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        ChromeDriver webDriver = new ChromeDriver(buildDesiredCapabilities()); // start with a fresh session for each test
        webDriver.manage().timeouts().implicitlyWait(5, SECONDS);
        return webDriver;
    }

    private static DesiredCapabilities buildDesiredCapabilities() {
        ChromeOptions options = new ChromeOptions();
        installUblock(options);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }

    private static void installUblock(ChromeOptions options) {
        final String UBLOCK_ORIGIN_PATH = BASE_PATH + "ublock_origin_1_12_4.crx";
        options.addExtensions(new File(UBLOCK_ORIGIN_PATH));
    }
}
