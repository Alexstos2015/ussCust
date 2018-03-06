package com.amdocs.css.vip.comverse;

import com.comverse.Subscriber;
import com.comverse.subscriber.SubscriberIdentifier;
import ru.atc.uss.app.util.Config;

import javax.xml.ws.BindingProvider;
import java.io.IOException;

import static com.comverse.util.ComverseAttributeConvertUtils.toShortAttribute;
import static com.comverse.util.ComverseAttributeConvertUtils.toStringAttribute;

/**
 * Абстракция для команд в Comverse, связанных с сервисом Subscriber.
 *
 * @author Sergey Morgunov {@literal <smorgunov@at-consulting.ru>}
 */
class SubscriberAction {

    protected SubscriberAction() throws IOException {
        Subscriber subscriberService = Comverse.SUBSCRIBER_SERVICE.getSubscriberPort();
        String endpoint = Config.getComverseSubscriberUrl();
        ((BindingProvider) subscriberService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    }

    protected SubscriberIdentifier createSubscriberIdentifier(String subscriber) {
        SubscriberIdentifier subscriberIdentifier = new SubscriberIdentifier();
        subscriberIdentifier.setSubscriberId(toStringAttribute(subscriber));
        subscriberIdentifier.setSubscriberExternalIdType(toShortAttribute((short) 1));
        return subscriberIdentifier;
    }

}
