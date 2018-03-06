package ru.atc.uss.app.subscriberservice;

import com.amdocs.css.vip.napi.NapiBaseHandler;
import com.vip.ensemble.napi.ChangeService;
import com.vip.ensemble.napi.StartServiceRequest;
import com.vip.ensemble.napi.StopServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Подключение/отключение услуи абоненту
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
class SubscriberServiceBo extends NapiBaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceBo.class);

    private int ban;
    private String subscriberNo;
    private String soc;
    private SubscriberServiceDo subscriberServiceDo;

    public SubscriberServiceBo(int ban, String subscriberNo, String soc, char inclusionType) throws Exception {
        super();
        this.ban = ban;
        this.subscriberNo = subscriberNo;
        this.soc = soc;
        this.subscriberServiceDo = new SubscriberServiceDo();
        subscriberServiceDo.setInclusionType(inclusionType);
        subscriberServiceDo.setResultCode(napiDo.getResultCode());
    }

    public SubscriberServiceDo change()
    {
        //Если не произошла успешная аутентификация napi
        if (!subscriberServiceDo.isSuccess())
            return subscriberServiceDo;

        StartServiceRequest startSrvRequest = new StartServiceRequest(sessionId);
        ChangeService changeService = new ChangeService(sessionId);
        StopServiceRequest stopSrvRequest = new StopServiceRequest(sessionId);

        LOGGER.info("Start service request (start)");
        String rsnCode = "NR";
        startSrvRequest.setRSN_CODE(rsnCode);
        startSrvRequest.setBAN(ban);
        startSrvRequest.setSUBSCRIBER_NO(subscriberNo);
        startSrvRequest.setACTV_DATE(logicalDate);
        startSrvRequest.callService();
        subscriberServiceDo.setResultCode(startSrvRequest.getSTATE());
        LOGGER.info("Start service request (stop): " + subscriberServiceDo.getResultCode() + " : " + subscriberServiceDo.getResultDescription());

        if (!subscriberServiceDo.getResultCode().equals("00000"))
            return subscriberServiceDo;

        LOGGER.info("Change service (start)");
        changeService.setBAN(ban);
        changeService.setSUBSCRIBER_NO(subscriberNo);
        changeService.setSOC(soc);
        changeService.setINCLUSION_TYPE((byte) subscriberServiceDo.getInclusionType());
        changeService.callService();
        subscriberServiceDo.setResultCode(changeService.getSTATE());
        LOGGER.info("Change service (stop): " + subscriberServiceDo.getResultCode() + " : " + subscriberServiceDo.getResultDescription());

        if (!subscriberServiceDo.getResultCode().equals("00000"))
            return subscriberServiceDo;

        LOGGER.info("Stop service request (start)");
        stopSrvRequest.setBAN(ban);
        stopSrvRequest.setSUBSCRIBER_NO(subscriberNo);
        stopSrvRequest.setAPPROV_IND((byte) 'Y');
        stopSrvRequest.callService();
        subscriberServiceDo.setResultCode(stopSrvRequest.getSTATE());
        LOGGER.info("Stop service request (stop): " + subscriberServiceDo.getResultCode() + " : " + subscriberServiceDo.getResultDescription());

        return subscriberServiceDo;
    }
}
