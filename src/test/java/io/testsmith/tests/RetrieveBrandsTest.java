package io.testsmith.tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RetrieveBrandsTest {
    private Playwright playwright;
    private APIRequestContext request;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(System.getProperty("base.url", "https://api.practicesoftwaretesting.com")));
    }

    @Test
    public void testShouldRetrieveAtLeastTwoBrands() {
        APIResponse response = request.get("/brands");
        Assert.assertEquals(response.status(), 200, "Unexpected status code");

        String responseBody = response.text();
        JsonArray data = JsonParser.parseString(responseBody).getAsJsonArray();
        Assert.assertTrue(data.size() >= 2, "Expected at least 2 brands, but got " + data.size());
    }

    @AfterClass
    public void teardown() {
        request.dispose();
        playwright.close();
    }
}
