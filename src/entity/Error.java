package entity;

public class Error {
    String errorType;
    String errorCode;

    public Error(String errorType, String errorCode) {
        this.errorType = errorType;
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
