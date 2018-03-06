package ru.atc.uss.app.subscriberservice;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberServiceSrv {

    public static SubscriberServiceDo changeService(int ban, String subscriberNo, String soc, char inclusionType) throws Exception {
        SubscriberServiceBo subscriberServiceBo = new SubscriberServiceBo(ban, subscriberNo, soc, inclusionType);
        return subscriberServiceBo.change();
    }
}
