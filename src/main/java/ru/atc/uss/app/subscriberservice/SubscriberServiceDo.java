package ru.atc.uss.app.subscriberservice;

import ru.atc.uss.app.util.NapiErrorHandler;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberServiceDo {

    private boolean isSuccess;
    private String resultCode;
    private String resultDescription;
    private char inclusionType;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setInclusionType(char inclusionType) {
        this.inclusionType = inclusionType;
    }

    public char getInclusionType() {
        return inclusionType;
    }

    public SubscriberServiceDo()
    {
        isSuccess = false;
    }

    public void setResultCode(String resultCode) {
        this.isSuccess = resultCode.equals("00000");
        this.resultCode = resultCode;
        this.resultDescription = NapiErrorHandler.errorsMap.get(resultCode);
    }
}
