package com.amdocs.css.vip.napi;

import ru.atc.uss.app.util.NapiErrorHandler;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class NapiDo {

    private boolean isSuccess;
    private String resultCode;
    private String resultDescription;

    public String getResultCode() {
        return resultCode;
    }

    public NapiDo()
    {
        isSuccess = true;
    }

    public void setResultCode(String resultCode) {
        this.isSuccess = resultCode.equals("00000");
        this.resultCode = resultCode;
        this.resultDescription = NapiErrorHandler.errorsMap.get(resultCode);
    }

}
