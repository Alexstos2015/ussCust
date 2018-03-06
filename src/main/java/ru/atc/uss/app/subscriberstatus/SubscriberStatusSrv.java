package ru.atc.uss.app.subscriberstatus;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberStatusSrv {

    public static SubscriberStatusDo changeStatus(String subscriberNo, int ban, boolean isSuspendToEns, String activityRsnCode, String comverseStatus, boolean isChangeToEns, boolean isChangeToComverse) throws Exception {
        SubscriberStatusBo subscriberServiceBo = new SubscriberStatusBo(subscriberNo, ban, isSuspendToEns, activityRsnCode, comverseStatus, isChangeToEns, isChangeToComverse);
        return subscriberServiceBo.change();
    }
}
