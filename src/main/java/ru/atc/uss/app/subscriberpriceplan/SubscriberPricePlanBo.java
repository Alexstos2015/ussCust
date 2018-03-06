package ru.atc.uss.app.subscriberpriceplan;

import com.amdocs.css.vip.napi.NapiBaseHandler;
import com.vip.ensemble.napi.ChangePP;
import com.vip.ensemble.napi.StartServiceRequest;
import com.vip.ensemble.napi.StopServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Изменение тарифного плана абоненту
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
class SubscriberPricePlanBo extends NapiBaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberPricePlanBo.class);

    private int ban;
    private String subscriberNo;
    private String sourcePricePlan;
    private String targetPricePlan;
    private String rsnCode;
    private SubscriberPricePlanDo subscriberPricePlanDo;

    public SubscriberPricePlanBo(int ban, String subscriberNo, String sourcePricePlan, String targetPricePlan, String rsnCode) throws Exception {
        super();
        this.ban = ban;
        this.subscriberNo = subscriberNo;
        this.sourcePricePlan = sourcePricePlan;
        this.targetPricePlan = targetPricePlan;
        this.rsnCode = rsnCode;
        subscriberPricePlanDo = new SubscriberPricePlanDo();
        subscriberPricePlanDo.setResultCode(napiDo.getResultCode());
    }

    public SubscriberPricePlanDo change() throws Exception {

        //Если не произошла успешная аутентификация napi
        if (!subscriberPricePlanDo.isSuccess())
            return subscriberPricePlanDo;

        StartServiceRequest startSrvRequest = new StartServiceRequest(sessionId);
        ChangePP changePP = new ChangePP(sessionId);
        StopServiceRequest stopSrvRequest = new StopServiceRequest(sessionId);

        LOGGER.info("Start service request (start)");
        startSrvRequest.setRSN_CODE(rsnCode);
        startSrvRequest.setBAN(ban);
        startSrvRequest.setSUBSCRIBER_NO(subscriberNo);
        startSrvRequest.setACTV_DATE(logicalDate);
        startSrvRequest.callService();
        subscriberPricePlanDo.setResultCode(startSrvRequest.getSTATE());
        LOGGER.info("Start service request (stop): " + subscriberPricePlanDo.getResultCode() + " : " + subscriberPricePlanDo.getResultDescription());

        if (!subscriberPricePlanDo.getResultCode().equals("00000"))
            return subscriberPricePlanDo;

        LOGGER.info("Change price plan (start)");
        changePP.setBAN(ban);
        changePP.setSUBSCRIBER_NO(subscriberNo);
        changePP.setSOURCE_PRICE_PLAN(sourcePricePlan);
        changePP.setTARGET_PRICE_PLAN(targetPricePlan);
        changePP.callService();
        subscriberPricePlanDo.setResultCode(changePP.getSTATE());
        LOGGER.info("Change price plan (stop): " + subscriberPricePlanDo.getResultCode() + " : " + subscriberPricePlanDo.getResultDescription());

        if (!subscriberPricePlanDo.getResultCode().equals("00000"))
            return subscriberPricePlanDo;

        LOGGER.info("Stop service request (start)");
        stopSrvRequest.setBAN(ban);
        stopSrvRequest.setSUBSCRIBER_NO(subscriberNo);
        stopSrvRequest.setAPPROV_IND((byte)'Y');
        stopSrvRequest.callService();
        subscriberPricePlanDo.setResultCode(stopSrvRequest.getSTATE());
        LOGGER.info("Stop service request (stop): " + subscriberPricePlanDo.getResultCode() + " : " + subscriberPricePlanDo.getResultDescription());

        return subscriberPricePlanDo;
    }
}
