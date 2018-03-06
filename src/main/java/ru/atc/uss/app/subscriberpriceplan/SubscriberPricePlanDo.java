package ru.atc.uss.app.subscriberpriceplan;

import ru.atc.uss.app.util.NapiErrorHandler;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberPricePlanDo {

    private boolean isSuccess;
    private String resultCode;
    private String resultDescription;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public SubscriberPricePlanDo()
    {
        isSuccess = false;
    }

    public void setResultCode(String resultCode) {
        this.isSuccess = resultCode.equals("00000");
        this.resultCode = resultCode;
        this.resultDescription = NapiErrorHandler.errorsMap.get(resultCode);
    }
}
