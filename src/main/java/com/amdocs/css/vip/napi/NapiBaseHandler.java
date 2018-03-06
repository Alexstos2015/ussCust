package com.amdocs.css.vip.napi;

import com.vip.ensemble.napi.Authenticator;
import com.vip.ensemble.napi.GetLogicalDate;
import com.vip.ensemble.napi.NAPIGeneric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.util.Config;

/**
 * Обработка базовых функций Napi
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class NapiBaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NapiBaseHandler.class);

    protected NapiDo napiDo;

    protected int sessionId;
    protected String logicalDate;

    protected NapiBaseHandler() throws Exception {
        napiDo = new NapiDo();
        auth();
        setLogicalDate();
    }


    private void auth() throws Exception {
        String wsnAddr = Config.getNapiWsnAddr();
        LOGGER.info("Auth");
        try {
            if (!NAPIGeneric.init(wsnAddr)) {
                throw new Exception();
            }
        }catch (Exception e){
            napiDo.setResultCode("ER034");
            LOGGER.error("NAPIGeneric don't init", e);
        }

        String statusCode = "ER034";
        Authenticator authenticator = new Authenticator();
        try {
            authenticator.setWSNADDR(wsnAddr);
            authenticator.setUSER_ID(Config.getNapiUserId());
            authenticator.setPASSWORD(Config.getNapiPassword());
            authenticator.callService();
            statusCode = authenticator.getSTATE();
            if (!statusCode.equals("00000")) {
                throw new Exception();
            }
        }catch (Exception e){
            napiDo.setResultCode("ER034");
            LOGGER.error("Auth error. NAPI Error code: " + statusCode, e);
        }
        sessionId = authenticator.getSESSION_ID();
        napiDo.setResultCode(statusCode);
    }

    private void setLogicalDate()
    {
        GetLogicalDate logicalDateObj = new GetLogicalDate(sessionId);
        logicalDateObj.callService();
        logicalDate = logicalDateObj.getDATE();
    }
}
