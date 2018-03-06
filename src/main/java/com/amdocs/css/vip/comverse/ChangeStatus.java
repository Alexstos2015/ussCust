package com.amdocs.css.vip.comverse;

import com.comverse.customercare.SubscriberUpdateRatingStatusInputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.amdocs.css.vip.comverse.ComverseSecurity.sapiUser;
import static com.amdocs.css.vip.comverse.ComverseSecurity.securityToken;

/**
 * Команда смены статуса абонента в Comverse.
 *
 * @author Mikhail Chernykh {@literal <mchernykh@at-consulting.ru>}
 */
class ChangeStatus extends CustomerCareAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeStatus.class);

    ChangeStatus() throws IOException {
    }

    void change(String subscriber, String status) throws Exception {
        try {
            SubscriberUpdateRatingStatusInputMessage message = new SubscriberUpdateRatingStatusInputMessage();
            message.setSubscriberId(createSubscriberIdentifier(subscriber));
            message.setPostRatingState(status);
            message.setSecurityToken(securityToken());
            message.setUserIdName(sapiUser());
            customerCare.subscriberUpdateRatingStatus(message);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
