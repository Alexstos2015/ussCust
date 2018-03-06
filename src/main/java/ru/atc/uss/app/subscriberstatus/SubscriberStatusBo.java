package ru.atc.uss.app.subscriberstatus;

import com.amdocs.css.vip.napi.NapiBaseHandler;
import com.vip.ensemble.napi.RestoreCTN;
import com.vip.ensemble.napi.SuspendCTN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amdocs.css.vip.comverse.ComverseSrv;

/**
 * Изменение статуса абоненту
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
class SubscriberStatusBo extends NapiBaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberStatusBo.class);

    private String subscriberNo;
    private int ban;
    private boolean isSuspendToEns;
    private String activityRsnCode;
    private String comverseStatus;
    private boolean isChangeToEns;
    private  boolean isChangeToComverse;

    private SubscriberStatusDo subscriberStatusDo;

    public SubscriberStatusBo(String subscriberNo, int ban, boolean isSuspendToEns, String activityRsnCode, String comverseStatus, boolean isChangeToEns, boolean isChangeToComverse) throws Exception {
        super();
        this.subscriberNo = subscriberNo;
        this.ban = ban;
        this.isSuspendToEns = isSuspendToEns;
        this.activityRsnCode = activityRsnCode;
        this.comverseStatus = comverseStatus;
        this.isChangeToEns = isChangeToEns;
        this.isChangeToComverse = isChangeToComverse;
        subscriberStatusDo = new SubscriberStatusDo();
        subscriberStatusDo.setResultCode(napiDo.getResultCode());
    }

    public SubscriberStatusDo change() throws Exception {

        if (isChangeToComverse) {
            subscriberStatusDo.setIsChangeToComverseSuccess(ComverseSrv.changeStatus(subscriberNo.replace(" ", ""), comverseStatus));
            LOGGER.info("Change status (" + comverseStatus + ") to Comverse: " + subscriberStatusDo.isChangeToComverseSuccess());
        }

        //Если не произошла успешная аутентификация napi
        if (!subscriberStatusDo.isChangeToEnsSuccess())
            return subscriberStatusDo;

        if (isChangeToEns && isSuspendToEns)
        {
            LOGGER.info("Suspend ctn to Ensemble (start)");
            SuspendCTN suspendCTN = new SuspendCTN(sessionId);
            suspendCTN.setBAN(ban);
            suspendCTN.setACTV_DATE(logicalDate);
            suspendCTN.setACTIVITY_RSN_CODE(activityRsnCode);
            suspendCTN.setSUBSCRIBER_NO(subscriberNo);
            suspendCTN.callService();
            subscriberStatusDo.setResultCode(suspendCTN.getSTATE());
            LOGGER.info("Suspend ctn to Ensemble (stop): " + subscriberStatusDo.getResultCode() + " : " + subscriberStatusDo.getResultDescription());
        }

        if (isChangeToEns && !isSuspendToEns)
        {
            LOGGER.info("Restore ctn  to Ensemble (start)");
            RestoreCTN restoreCTN = new RestoreCTN(sessionId);
            restoreCTN.setBAN(ban);
            restoreCTN.setACTV_DATE(logicalDate);
            restoreCTN.setACTIVITY_RSN_CODE(activityRsnCode);
            restoreCTN.setSUBSCRIBER_NO(subscriberNo);
            restoreCTN.callService();
            subscriberStatusDo.setResultCode(restoreCTN.getSTATE());
            LOGGER.info("Restore ctn to Ensemble (stop): " + subscriberStatusDo.getResultCode() + " : " + subscriberStatusDo.getResultDescription());
        }

        return subscriberStatusDo;
    }
}
