package ru.atc.uss.app.subscriberpriceplan;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberPricePlanSrv {

    public static SubscriberPricePlanDo changePricePlan(int ban, String subscriberNo, String sourcePricePlan, String targetPricePlan, String rsnCode) throws Exception {
        SubscriberPricePlanBo subscriberPricePlanBo = new SubscriberPricePlanBo(ban, subscriberNo, sourcePricePlan, targetPricePlan, rsnCode);
        return subscriberPricePlanBo.change();
    }
}
