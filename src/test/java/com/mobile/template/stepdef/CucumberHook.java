package com.mobile.template.stepdef;


import com.automation.tat.config.TapBeansLoad;
import com.automation.tat.ui.UiBasePage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;


public class CucumberHook {
    private static final Logger logger = Logger.getLogger(CucumberHook.class);

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