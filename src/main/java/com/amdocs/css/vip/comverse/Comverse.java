package com.amdocs.css.vip.comverse;

import com.comverse.CustomerCareService;
import com.comverse.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSession;

/**
 * Вспомогательные функции по работе с Comverse.
 *
 * @author Sergey Morgunov {@literal <smorgunov@at-consulting.ru>}
 */
public final class Comverse {

    private static final Logger LOGGER = LoggerFactory.getLogger(Comverse.class);

    static final CustomerCareService CUSTOMER_CARE_SERVICE = new CustomerCareService();
    static final SubscriberService SUBSCRIBER_SERVICE = new SubscriberService();

    static {
        //Workaround
        //Чтобы обойти ошибку несоответствия адреса в сертификате и фактически вызываемого адреса
        //Некий аналог -Dweblogic.security.SSL.ignoreHostnameVerification=true
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
    }

    private Comverse() {
    }

    /**
     * Создание абонента.
     * @param subscriber Номер абонента
     */
    public static void createSubscriber(final String subscriber, final String offerName) throws Exception {
        try {
            new CreateSubscriber().create(subscriber, offerName);
        } catch (Exception e) {
            LOGGER.error("Can't create subscriber in comverse", e);
            throw new Exception("Can't create subscriber in comverse", e);
        }
    }

    /**
     * Изменение баланса абонента.
     * @param subscriber Номер абонента
     * @param balanceDelta Величина, на которую изменяется баланс
     * @param currency Валюта
     */
    public static void changeBalance(String subscriber, double balanceDelta, String currency) throws Exception {
        try {
            new ChangeBalance().change(subscriber, balanceDelta, currency);
        } catch (Exception e) {
            throw new Exception("Can't change subscriber balance", e);
        }
    }

    /**
     * Установление точного значения баланса.
     * @param subscriber Номер абонента
     * @param balance Значение баланса
     * @param currency Валюта
     */
    public static void setBalance(String subscriber, double balance, String currency) throws Exception {
        try {
            new ChangeBalance().set(subscriber, balance, currency);
        } catch (Exception e) {
            LOGGER.error("Can't set subscriber balance", e);
            throw new Exception("Can't set subscriber balance", e);
        }
    }

    /**
     * Изменение статуса абонента.
     * @param subscriber Номер абонента
     * @param status Статус
     */
    public static void changeStatus(String subscriber, String status) throws Exception {
        try {
            new ChangeStatus().change(subscriber, status);
        } catch (Exception e) {
            LOGGER.error("Can't change subscriber status", e);
            throw new Exception("Can't change subscriber status", e);
        }
    }
}
