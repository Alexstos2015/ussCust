package com.amdocs.css.vip.comverse;

import com.comverse.CustomerCare;
import com.comverse.customercare.SubscriberIdentifier;
import ru.atc.uss.app.util.Config;

import javax.xml.ws.BindingProvider;
import java.io.IOException;

import static com.comverse.util.ComverseAttributeConvertUtils.toShortAttribute;
import static com.comverse.util.ComverseAttributeConvertUtils.toStringAttribute;

/**
 * Абстракция для команд в Comverse, связанных с сервисом CustomerCare.
 *
 * @author Sergey Morgunov {@literal <smorgunov@at-consulting.ru>}
 */
abstract class CustomerCareAction {

    final CustomerCare customerCare;

    CustomerCareAction() throws IOException {
        customerCare = Comverse.CUSTOMER_CARE_SERVICE.getCustomerCarePort();
        String endpoint = Config.getComverseCustomercareUrl();
        ((BindingProvider) customerCare).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    }

    SubscriberIdentifier createSubscriberIdentifier(String subscriber) {
        SubscriberIdentifier subscriberIdentifier = new SubscriberIdentifier();
        subscriberIdentifier.setSubscriberId(toStringAttribute(subscriber));
        subscriberIdentifier.setSubscriberExternalIdType(toShortAttribute((short) 1));
        return subscriberIdentifier;
    }

}
