package com.mobile.template.stepdef;


import com.automation.platform.config.TapBeansLoad;
import com.mobile.template.screen.LoginPage;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginStep {
    private static final Logger logger = LoggerFactory.getLogger(LoginStep.class);

    private LoginPage loginPage = (LoginPage) TapBeansLoad.getBean(LoginPage.class);

    @Given("^I login to app using email \"([^\"]*)\"$")
    public void loginToApp(String emailId) {
        loginPage.loginToApp(emailId);
    }

}