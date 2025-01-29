package io.testsmith.tests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class RetrieveInvoicesTest {
    private String accessToken;

    @BeforeClass
    public void loginAndRetrieveAccessToken() {
        try (Playwright playwright = Playwright.create()) {
            APIRequestContext request = playwright.request().newContext();
            JsonObject payload = new JsonObject();
            payload.addProperty("email", "customer@practicesoftwaretesting.com");
            payload.addProperty("password", "welcome01");

            APIResponse response = request.post( "https://api.practicesoftwaretesting.com/users/login", RequestOptions.create().setData(payload));
            Assert.assertEquals(response.status(), 200, "Unexpected status code");

            String responseBody = response.text();
            JsonObject responseBodyJson = JsonParser.parseString(responseBody).getAsJsonObject();
            Assert.assertTrue(responseBodyJson.has("access_token"), "Response does not contain 'access_token'");
            accessToken = responseBodyJson.get("access_token").getAsString();
        }
    }

    @Test
    public void testShouldRetrieveInvoicesWithValidToken() {
        try (Playwright playwright = Playwright.create()) {
            APIRequestContext request = playwright.request().newContext(new APIRequest.NewContextOptions()
                    .setExtraHTTPHeaders(Map.of("Authorization", "Bearer " + accessToken)));

            APIResponse response = request.get("https://api.practicesoftwaretesting.com/invoices");
            Assert.assertEquals(response.status(), 200, "Unexpected status code");

            String responseBody = response.text();
            JsonArray data = JsonParser.parseString(responseBody).getAsJsonObject().getAsJsonArray("data");
            Assert.assertTrue(data.size() >= 15, "Expected at least 15 invoices, but got " + data.size());
        }
    }
}
