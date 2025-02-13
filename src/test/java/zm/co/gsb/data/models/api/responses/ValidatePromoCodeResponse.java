package zm.co.gsb.data.models.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidatePromoCodeResponse {

    @JsonProperty("result")
    private Result result;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("dataStructure")
    private Object dataStructure;

    @JsonProperty("additionalData")
    private Object additionalData;

    @JsonProperty("userInfo")
    private Object userInfo;

    @JsonProperty("isSuccessfull")
    private boolean isSuccessfull;

    public ValidatePromoCodeResponse() {
    }

    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    public Object getDataStructure() {
        return dataStructure;
    }
    public void setDataStructure(Object dataStructure) {
        this.dataStructure = dataStructure;
    }
    public Object getAdditionalData() {
        return additionalData;
    }
    public void setAdditionalData(Object additionalData) {
        this.additionalData = additionalData;
    }
    public Object getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }
    public boolean isSuccessfull() {
        return isSuccessfull;
    }
    public void setSuccessfull(boolean successfull) {
        isSuccessfull = successfull;
    }

    @Override
    public String toString() {
        return "ValidatePromoCodeResponse{" +
                "result=" + result +
                ", data=" + data +
                ", dataStructure=" + dataStructure +
                ", additionalData=" + additionalData +
                ", userInfo=" + userInfo +
                ", isSuccessfull=" + isSuccessfull +
                '}';
    }

    public static class Result {

        @JsonProperty("errorDescription")
        private String errorDescription;

        @JsonProperty("additionalInfo")
        private String additionalInfo;

        @JsonProperty("eventData")
        private Object eventData;

        @JsonProperty("closedOdds")
        private Object closedOdds;

        @JsonProperty("errorCode")
        private int errorCode;

        @JsonProperty("resultCode")
        private int resultCode;

        @JsonProperty("errorCodeDescription")
        private String errorCodeDescription;

        // Default constructor
        public Result() {
        }

        // Getters and Setters
        public String getErrorDescription() {
            return errorDescription;
        }
        public void setErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
        }
        public String getAdditionalInfo() {
            return additionalInfo;
        }
        public void setAdditionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
        }
        public Object getEventData() {
            return eventData;
        }
        public void setEventData(Object eventData) {
            this.eventData = eventData;
        }
        public Object getClosedOdds() {
            return closedOdds;
        }
        public void setClosedOdds(Object closedOdds) {
            this.closedOdds = closedOdds;
        }
        public int getErrorCode() {
            return errorCode;
        }
        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
        public int getResultCode() {
            return resultCode;
        }
        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }
        public String getErrorCodeDescription() {
            return errorCodeDescription;
        }
        public void setErrorCodeDescription(String errorCodeDescription) {
            this.errorCodeDescription = errorCodeDescription;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "errorDescription='" + errorDescription + '\'' +
                    ", additionalInfo='" + additionalInfo + '\'' +
                    ", eventData=" + eventData +
                    ", closedOdds=" + closedOdds +
                    ", errorCode=" + errorCode +
                    ", resultCode=" + resultCode +
                    ", errorCodeDescription='" + errorCodeDescription + '\'' +
                    '}';
        }
    }

    public static class Data {

        @JsonProperty("isValid")
        private boolean isValid;

        // Default constructor
        public Data() {
        }

        // Getters and Setters
        public boolean isValid() {
            return isValid;
        }
        public void setValid(boolean valid) {
            isValid = valid;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "isValid=" + isValid +
                    '}';
        }
    }
}

