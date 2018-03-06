package com.amdocs.css.vip.comverse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.util.Config;

/**
 * Created by IIvankov on 11.01.2016.
 */
public class ComverseSrv {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComverseSrv.class);

    public static boolean registerSubscriber (String ctn, String balance, String offerName)
    {
        LOGGER.info("Reg to Comverse with params (ctn=" + ctn + ", balance=" + balance + ", offerName=" + offerName + ")");
        try {
            LOGGER.info("Comverse (create subscriber)");
            Comverse.createSubscriber("7" + ctn.replace(" ", ""), offerName);
            LOGGER.info("Comverse (change status)");
            Comverse.changeStatus("7" + ctn.replace(" ", ""), Config.DEFAULT_COMVERSE_STATUS);
            LOGGER.info("Comverse (set balance)");
            Comverse.setBalance("7" + ctn.replace(" ", ""), Float.parseFloat(balance), Config.CURRENCY);
            return true;
        } catch (Exception e){
            LOGGER.error("Subscriber can't be registered to Comverse");
            return false;
        }
    }

    public static boolean changeStatus (String ctn, String status)
    {
        LOGGER.info("Change status to Comverse with params (ctn=" + ctn + ", status=" + status + ")");
        try {
            LOGGER.info("Comverse (change status)");
            Comverse.changeStatus("7" + ctn.replace(" ", ""), status);
            return true;
        } catch (Exception e) {
            LOGGER.error("Status can't be changed to Comverse");
            return false;
        }
    }
}
