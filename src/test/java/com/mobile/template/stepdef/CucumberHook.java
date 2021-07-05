package com.mobile.template.stepdef;


import com.automation.platform.config.TapBeansLoad;
import com.automation.platform.ui.UiBasePage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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