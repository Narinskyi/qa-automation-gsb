package zm.co.gsb.data.models.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatePromoCodeRequest {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("CurrencyId")
    private int currencyId;

    public ValidatePromoCodeRequest() {
    }

    public ValidatePromoCodeRequest(String code, int currencyId) {
        this.code = code;
        this.currencyId = currencyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "ValidatePromoCodeRequest{" +
                "code='" + code + '\'' +
                ", currencyId=" + currencyId +
                '}';
    }
}

