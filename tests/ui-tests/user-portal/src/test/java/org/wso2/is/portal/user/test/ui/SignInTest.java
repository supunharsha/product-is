/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.is.portal.user.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.iam.userportal.actionobject.LoginPageAction;

/**
 * UI Tests for Sign In.
 */
public class SignInTest {

    private static LoginPageAction loginPageAction = new LoginPageAction();
    private static WebDriver driver;
    private static String loginPage = "https://localhost:9292/user-portal/login";
    private static String adminPage = "https://localhost:9292/user-portal/";
    private static String usernameRecoveryPage = "https://localhost:9292/user-portal/recovery/username";
    private static String passwordRecoveryPage = "https://localhost:9292/user-portal/recovery/password";

    @Test(groups = "signInTest")
    public void loadLoginPage() throws Exception {
        driver = new HtmlUnitDriver();
        driver.get(loginPage);
        driver.close();
    }

    @Test(groups = "signInTest", dependsOnMethods = "loadLoginPage")
    public void testLogin() throws Exception {
        driver = new HtmlUnitDriver();
        driver.get(loginPage);
        String username = "admin";
        String password = "admin";
        loginPageAction.login(driver, username, password);
        Assert.assertEquals(driver.getCurrentUrl(), adminPage,
                "This current page is not the admin user page.");
        driver.close();
    }

    @Test(groups = "signInTest", dependsOnMethods = "loadLoginPage")
    public void testClickUsernameRecovery() throws Exception {
        driver = new HtmlUnitDriver();
        driver.get(loginPage);
        loginPageAction.clickForgetUsername(driver);
        Assert.assertEquals(driver.getCurrentUrl(), usernameRecoveryPage,
                "This current page is not the username recovery page.");
        driver.close();
    }

    @Test(groups = "signInTest", dependsOnMethods = "loadLoginPage")
    public void testClickPasswordRecovery() throws Exception {
        driver = new HtmlUnitDriver();
        driver.get(loginPage);
        loginPageAction.clickForgetPassword(driver);
        Assert.assertEquals(driver.getCurrentUrl(), passwordRecoveryPage,
                "This current page is not the password recovery page.");
        driver.quit();

    }
}
