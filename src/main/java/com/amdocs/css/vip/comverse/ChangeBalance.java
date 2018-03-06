package com.amdocs.css.vip.comverse;

import com.comverse.customercare.NamedBalanceInstanceObject;
import com.comverse.customercare.SubscriberAdjustBalanceInstanceInputMessage;
import com.comverse.customercare.SubscriberUpdateBalanceInstanceInputMessage;

import java.io.IOException;

import static com.comverse.util.ComverseAttributeConvertUtils.toNumericAttribute;
import static com.comverse.util.ComverseAttributeConvertUtils.toStringAttribute;
import static com.amdocs.css.vip.comverse.ComverseSecurity.sapiUser;
import static com.amdocs.css.vip.comverse.ComverseSecurity.securityToken;

/**
 * Команда смены баланса абонента в Comverse.
 *
 * @author Mikhail Chernykh {@literal <mchernykh@at-consulting.ru>}
 */
class ChangeBalance extends CustomerCareAction {

    ChangeBalance() throws IOException {
    }

    void change(String subscriber, double balanceDelta, String currency) throws Exception {
        SubscriberAdjustBalanceInstanceInputMessage request = createChangeBalanceRequest(subscriber, currency, balanceDelta);
        customerCare.subscriberAdjustBalanceInstance(request);
    }

    void set(String subscriber, double balance, String currency) throws Exception {
        try {
            SubscriberUpdateBalanceInstanceInputMessage message = new SubscriberUpdateBalanceInstanceInputMessage();
            message.setSubscriberId(createSubscriberIdentifier(subscriber));
            message.setUserIdName(sapiUser());
            message.setSecurityToken(securityToken());
            message.setCurrencyCode(currency);
            message.getNewBalances().add(createBalance("CORE BALANCE", balance));
            customerCare.subscriberUpdateBalanceInstance(message);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private NamedBalanceInstanceObject createBalance(String name, double balance) {
        NamedBalanceInstanceObject balanceInstance = new NamedBalanceInstanceObject();
        balanceInstance.setBalanceName(toStringAttribute(name));
        balanceInstance.setTotalBalance(toNumericAttribute(balance));
        return balanceInstance;
    }

    private SubscriberAdjustBalanceInstanceInputMessage createChangeBalanceRequest(String subscriber, String currency,
                                                                                   double balanceDelta) throws IOException {
        SubscriberAdjustBalanceInstanceInputMessage message = new SubscriberAdjustBalanceInstanceInputMessage();
        message.setSecurityToken(securityToken());
        message.setUserIdName(sapiUser());
        message.setSubscriberId(createSubscriberIdentifier(subscriber));
        message.setBalanceName("CORE BALANCE");
        message.setMtrComment("SCRIPT_ADJUST");
        message.setValueDelta(balanceDelta);
        message.setCurrencyCode(currency);
        return message;
    }

}
