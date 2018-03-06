package com.amdocs.css.vip.comverse;

import com.comverse.customercare.SubscriberCreateFromOfferInputMessage;
import com.comverse.customercare.SubscriberExternalIdObject;
import com.comverse.customercare.SubscriberObjectWithNames;

import java.io.IOException;

import static com.comverse.util.ComverseAttributeConvertUtils.*;
import static com.amdocs.css.vip.comverse.ComverseSecurity.sapiUser;
import static com.amdocs.css.vip.comverse.ComverseSecurity.securityToken;

/**
 * Команда создания абонента в Comverse.
 *
 * @author Sergey Morgunov {@literal <smorgunov@at-consulting.ru>}
 */
public class CreateSubscriber extends CustomerCareAction {

    CreateSubscriber() throws IOException {
    }

    void create(final String subscriber, final String offerName) throws Exception {
        try {
            final int primaryOfferId = 62;
            SubscriberCreateFromOfferInputMessage message = new SubscriberCreateFromOfferInputMessage();
            SubscriberObjectWithNames newSubscriber = new SubscriberObjectWithNames();
            newSubscriber.setAttribs((byte) 0);
            newSubscriber.setAcctAccessAllowed(toBooleanAttribute(false));
            newSubscriber.setCurrencyCode(toShortAttribute((short) 3));
            newSubscriber.setExrateClass(toShortAttribute((short) 1));
            newSubscriber.setLanguageCode(toShortAttribute((short) 1));
            newSubscriber.setLiabilityRedirectFlag(toBooleanAttribute(false));
            newSubscriber.setMogId(toIntegerAttribute(4));
            newSubscriber.setNotificationLanguage(toShortAttribute((short) 3));
            newSubscriber.setPinChangeNeeded(toBooleanAttribute(false));
            newSubscriber.setPrimaryOfferId(toIntegerAttribute(primaryOfferId));
            newSubscriber.setPrivacyLevel(toShortAttribute((short) 0));
            newSubscriber.setRatingState(toShortAttribute((short) 1));
            newSubscriber.setResellerId(toIntegerAttribute(0));
            newSubscriber.setSubNotifLevel(toShortAttribute((short) 0));
            newSubscriber.setTimezone(toShortAttribute((short) 1));
            newSubscriber.setAdvtAllowed(toBooleanAttribute(false));
            SubscriberExternalIdObject obj = new SubscriberExternalIdObject();
            obj.setServiceExternalIdType(toShortAttribute((short) 1));
            obj.setServiceExternalId(toStringAttribute(subscriber));
            obj.setAttribs((byte) 0);
            newSubscriber.getExternalIds().add(obj);
            message.setNewSubscriber(newSubscriber);
            message.setUserIdName(sapiUser());
            message.setSecurityToken(securityToken());
            message.getSubscriberExternalIdList().add(obj);
            message.setPrimaryOfferName(offerName);
            message.setWaiveActivation(false);
            customerCare.subscriberCreateFromOffer(message);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
