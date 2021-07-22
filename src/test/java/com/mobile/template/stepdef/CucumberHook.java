package com.mobile.template.stepdef;


import com.automation.platform.config.TapBeansLoad;
import com.automation.platform.ui.UiBasePage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CucumberHook {
    private static final Logger logger = LoggerFactory.getLogger(CucumberHook.class);
    private UiBasePage uiBasePage = (UiBasePage) TapBeansLoad.getBean(UiBasePage.class);


    @Before
    public void checkReporterRunning(Scenario scenario) {

    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            uiBasePage.embedScenarioScreenshot(scenario);
        }

    }


}