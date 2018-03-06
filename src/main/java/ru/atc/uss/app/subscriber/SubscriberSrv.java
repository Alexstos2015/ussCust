package ru.atc.uss.app.subscriber;

import java.util.List;
import java.util.TreeMap;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberSrv {

    public static List<SubscriberDo> createSubscriber(String marketCode, String accountType, String pricePlan, TreeMap<String, String> ctnSimMap, int benCount, String inn, boolean isB2c, int ban, String comverseBalance, String comverseOfferName, boolean isRegToComverse) throws Exception {
        SubscriberBo subscriberBo = new SubscriberBo(marketCode, accountType, pricePlan, ctnSimMap, benCount, inn, ban, comverseBalance, comverseOfferName, isRegToComverse);
        return subscriberBo.create(isB2c);
    }
}
