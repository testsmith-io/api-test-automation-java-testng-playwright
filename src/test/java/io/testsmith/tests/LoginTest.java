package io.testsmith.tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private Playwright playwright;
    private APIRequestContext request;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(System.getProperty("base.url", "https://api.practicesoftwaretesting.com")));
    }

    @Test
    public void testShouldReturnAccessTokenOnSuccessfulLogin() {
        JsonObject payload = new JsonObject();
        payload.addProperty("email", "customer@practicesoftwaretesting.com");
        payload.addProperty("password", "welcome01");

        APIResponse response = request.post("https://api.practicesoftwaretesting.com/users/login", RequestOptions.create()
                                                                                                                 .setData(payload));
        Assert.assertEquals(response.status(), 200, "Unexpected status code");

        String responseBody = response.text();
        JsonObject responseBodyJson = JsonParser.parseString(responseBody).getAsJsonObject();
        Assert.assertTrue(responseBodyJson.has("access_token"), "Response does not contain 'access_token'");

    }

    @AfterClass
    public void teardown() {
        request.dispose();
        playwright.close();
    }
}
