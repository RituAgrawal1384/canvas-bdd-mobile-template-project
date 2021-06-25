package com.mobile.template.screen;

import com.automation.tat.config.Configvariable;
import com.automation.tat.ui.UiBasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

    @Autowired
    private UiBasePage uiBasePage;

    @Autowired
    private Configvariable configvariable;


    public String emailTextField = "${text.field.email}";
    public String buttonLocator = "${label.button}";
    public String staticText = "${page.label.text}";
    public String otpTextField = "${verification.text.field}";
    public String menuLocator = "${menu.icon}";
    public String languageLocator = "${Language}";


    public void enterEmailId(String userName) {
        uiBasePage.enterText(configvariable.expandValue(emailTextField), configvariable.expandValue(userName));
    }

    public void enterOTP(String otp) {
        char[] carr = otp.toCharArray();
        uiBasePage.enterText(configvariable.getFormattedString(configvariable.expandValue(otpTextField), "1"), Character.toString(carr[0]));
        uiBasePage.hideKeyboard();
        uiBasePage.enterText(configvariable.getFormattedString(configvariable.expandValue(otpTextField), "2"), Character.toString(carr[1]));
        uiBasePage.enterText(configvariable.getFormattedString(configvariable.expandValue(otpTextField), "3"), Character.toString(carr[2]));
        uiBasePage.enterText(configvariable.getFormattedString(configvariable.expandValue(otpTextField), "4"), Character.toString(carr[3]));
        uiBasePage.enterText(configvariable.getFormattedString(configvariable.expandValue(otpTextField), "5"), Character.toString(carr[4]));
        uiBasePage.enterText(configvariable.getFormattedString(configvariable.expandValue(otpTextField), "6"), Character.toString(carr[5]));
        uiBasePage.hideKeyboard();
    }

    public void clickButton(String buttonText) {
        uiBasePage.clickElement(configvariable.getFormattedString(configvariable.expandValue(buttonLocator), buttonText));
    }

    public boolean isStaticTextDisplayed(String text) {
        return uiBasePage.isElementDisplayed(configvariable.getFormattedString(configvariable.expandValue(staticText), text));
    }

    public void clickSubmitButton() {
        uiBasePage.swipeMobileDown("3000");
        uiBasePage.clickElement(configvariable.getFormattedString(configvariable.expandValue(buttonLocator), configvariable.expandValue("${submit.button.text}")));
    }

    public void clickMenuButton(String buttonText) {
        uiBasePage.clickElement(configvariable.getFormattedString(configvariable.expandValue(menuLocator), buttonText));
    }

    public void clickLanguage(String buttonText) {
        uiBasePage.clickElement(configvariable.getFormattedString(configvariable.expandValue(staticText), buttonText));
    }

    public void loginToApp(String userName) {
        enterEmailId(userName);
        clickSubmitButton();
        String otp = configvariable.expandValue("${login.otp}");
        enterOTP(otp);
    }
}
