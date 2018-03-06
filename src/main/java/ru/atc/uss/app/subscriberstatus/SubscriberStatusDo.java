package ru.atc.uss.app.subscriberstatus;

import ru.atc.uss.app.util.NapiErrorHandler;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberStatusDo {

    private boolean isChangeToEnsSuccess;
    private boolean isChangeToComverseSuccess;
    private String resultCode;
    private String resultDescription;

    public boolean isChangeToEnsSuccess() {
        return isChangeToEnsSuccess;
    }

    public boolean isChangeToComverseSuccess() {
        return isChangeToComverseSuccess;
    }

    public void setIsChangeToComverseSuccess(boolean isChangeToComverseSuccess) {
        this.isChangeToComverseSuccess = isChangeToComverseSuccess;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public SubscriberStatusDo()
    {
        isChangeToEnsSuccess = true;
        isChangeToComverseSuccess = true;
    }

    public void setResultCode(String resultCode) {
        this.isChangeToEnsSuccess = resultCode.equals("00000");
        this.resultCode = resultCode;
        this.resultDescription = NapiErrorHandler.errorsMap.get(resultCode);
    }
}
