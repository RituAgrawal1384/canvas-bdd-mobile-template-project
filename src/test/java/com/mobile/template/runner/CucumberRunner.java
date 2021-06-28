package com.mobile.template.runner;

import com.automation.tat.config.Configvariable;
import com.automation.tat.config.TapBeansLoad;
import com.automation.tat.filehandling.FileReaderUtil;
import com.automation.tat.reporting.TapReporting;
import com.automation.tat.selenium.SeleniumBase;
import com.mobile.template.utils.HelperMethods;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.TimeZone;

@ComponentScan(basePackages = {"com.mobile.template", "com.automation.tat"})
@Configuration
@CucumberOptions(
        monochrome = true,
        features = "classpath:features",
        glue = {"com/mobile/template/stepdef", "com/automation/tat/tapsteps"},
        tags = {"@test_tat", "~@ignore"},

        plugin = {"pretty",
                "html:reports/cucumber/cucumber-html",
                "json:reports/cucumber/cucumber.json",
                "usage:reports/cucumber-usage.json",
                "junit:reports/newrelic-report/cucumber-junit.xml"}
)

public class CucumberRunner extends AbstractTestNGCucumberTests {

    private static final Logger LOGGER = Logger.getLogger(CucumberRunner.class);

    private Configvariable configvariable;
    private SeleniumBase seleniumBase;
    private HelperMethods helperMethods;

    @BeforeSuite(alwaysRun = true)
    public void setUpEnvironmentToTest() {
        // write if anything needs to be set up once before tests run. e.g. connection to database
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        TapBeansLoad.setConfigClass(CucumberRunner.class);
        TapBeansLoad.init();
        configvariable = (Configvariable) TapBeansLoad.getBean(Configvariable.class);
        seleniumBase = (SeleniumBase) TapBeansLoad.getBean(SeleniumBase.class);
//        helperMethods = (HelperMethods) TapBeansLoad.getBean(HelperMethods.class);
//        LOGGER.info("Setting environment file....");
//        configvariable.setupEnvironmentProperties(System.getProperty("app.env"), System.getProperty("app.lbu"));
//        LOGGER.info("Setting localization file....");
//        helperMethods.loadLocalizationFile(System.getProperty("app.lbu"), System.getProperty("app.language"), System.getenv("DEVICEFARM_DEVICE_PLATFORM_NAME"));
//        seleniumBase.initializeSeleniumFramework();
    }

    @AfterSuite(alwaysRun = true)
    public void cleanUp() {
        // close if something enabled in @before suite. e.g. closing connection to DB
        LOGGER.info("Copying and generating reports....");
        String deviceFarmLogDir = System.getenv("DEVICEFARM_LOG_DIR");
        TapReporting.generateReportForJsonFiles(deviceFarmLogDir);
        // so it might get exception
        LOGGER.info("Quiting driver if needed....");
        if (SeleniumBase.driver != null) {
            SeleniumBase.driver.quit();
        }

        FileReaderUtil.deleteFile("reports/mobile-app-test-results.pdf");
        TapReporting.detailedReport("reports/cucumber/cucumber.json", "mobile-app");

    }

}