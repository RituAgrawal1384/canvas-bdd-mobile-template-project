package com.mobile.template.stepdef;


import com.automation.tat.config.TapBeansLoad;
import com.mobile.template.screen.LoginPage;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;


public class LoginStep {
    private static final Logger logger = Logger.getLogger(LoginStep.class);

    private LoginPage loginPage = (LoginPage) TapBeansLoad.getBean(LoginPage.class);

    @Given("^I login to app using email \"([^\"]*)\"$")
    public void loginToApp(String emailId) {
        loginPage.loginToApp(emailId);
    }

}