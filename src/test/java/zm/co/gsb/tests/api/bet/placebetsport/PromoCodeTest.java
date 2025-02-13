package zm.co.gsb.tests.api.bet.placebetsport;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import zm.co.gsb.api.ApiClient;
import zm.co.gsb.data.models.api.requests.ValidatePromoCodeRequest;
import zm.co.gsb.data.models.api.responses.ValidatePromoCodeResponse;

public class @Issue("BUG-1234") {

    private static final ApiClient apiClient = new ApiClient();

    @DataProvider(name = "promoCodes")
    public Object[][] promoCodes() {
        return new Object[][]{
                {"VALID_CODE_BOUNDARY_LOWER", 1, true},
                {"VALID_CODE_BOUNDARY_LOWER+1", 2, true},
                {"VALID_CODE_BOUNDARY_UPPER", 100, true},
                {"VALID_CODE_BOUNDARY_LOWER-1", -1, false},
                {"VALID_CODE_BOUNDARY_LOWER-+1", 101, false},
                {"INVALID_CODE_VALID_SYMBOLS_BOUNDARY_LOWER", 1, false},
                {"INVALID_CODE_INVALID_SYMBOLS_BOUNDARY_UPPER", 100, false},
                {"", 0, false},
                {null, 0, false}
        };
    }

    @Issue("BUG-1234")
    @Test(dataProvider = "promoCodes")
    @Description("API: Validate promo codes")
    @Severity(SeverityLevel.NORMAL)
    @TmsLinks({
            // TBD
            // @TmsLink("PROMO_TC_01"),
            // @TmsLink("PROMO_TC_02")
    })
    public void testValidatePromoCode(String promoCode, int currencyId, boolean expectedValid) {
        ValidatePromoCodeRequest request = new ValidatePromoCodeRequest();
        request.setCode(promoCode);
        request.setCurrencyId(currencyId);

        ValidatePromoCodeResponse response = apiClient.postAsObject(
                "/dynamic/execute/Sport/ValidatePromoCode",
                request,
                ValidatePromoCodeResponse.class
        );

        Assert.assertTrue(response.isSuccessfull(), "API call should be successful");

        Assert.assertEquals(response.getData().isValid(), expectedValid,
                "The promo code validity did was not correct");
    }
}

