package ru.atc.uss.app.subscriber;

import ru.atc.uss.app.util.NapiErrorHandler;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberDo {

    private boolean isCreateToEnsSuccess;
    private boolean isRegToAppSuccess;
    private boolean isRegToEnsSuccess;
    private boolean isRegToComverseSuccess;
    private String resultCode;
    private String resultDescription;
    private int ban;
    private int ben;
    private String simNum;
    private String applicationId;
    private String ctn;

    public SubscriberDo()
    {
        isCreateToEnsSuccess = true;
        isRegToEnsSuccess = true;
        isRegToAppSuccess = true;
        isRegToComverseSuccess = true;
        ctn = "0";
    }

    public boolean isCreateToEnsSuccess() {
        return isCreateToEnsSuccess;
    }

    public boolean isRegToAppSuccess() {
        return isRegToAppSuccess;
    }

    public void setIsRegToAppSuccess(boolean isRegToAppSuccess) {
        this.isRegToAppSuccess = isRegToAppSuccess;
    }

    public boolean isRegToEnsSuccess() {
        return isRegToEnsSuccess;
    }

    public void setIsRegToEnsSuccess(boolean isRegToEnsSuccess) {
        this.isRegToEnsSuccess = isRegToEnsSuccess;
    }

    public boolean isRegToComverseSuccess() {
        return isRegToComverseSuccess;
    }

    public void setIsRegToComverseSuccess(boolean isRegToComverseSuccess) {
        this.isRegToComverseSuccess = isRegToComverseSuccess;
    }

    public String getSimNum() {
        return simNum;
    }

    public void setSimNum(String simNum) {
        this.simNum = simNum;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCtn() {
        return ctn;
    }

    public void setCtn(String ctn) {
        this.ctn = ctn;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public int getBen() {
        return ben;
    }

    public void setBen(int ben) {
        this.ben = ben;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.isCreateToEnsSuccess = resultCode.equals("00000");
        this.resultCode = resultCode;
        this.resultDescription = NapiErrorHandler.errorsMap.get(resultCode);
    }

}
