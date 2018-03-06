package ru.atc.uss.app.subscriber;

import com.amdocs.css.vip.napi.NapiBaseHandler;
import com.vip.ensemble.napi.ActivateCTN;
import com.vip.ensemble.napi.CreateBen;
import com.vip.ensemble.napi.CreateSubscriberAppl;
import com.vip.ensemble.napi.CreateTentativeBan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amdocs.css.vip.comverse.ComverseSrv;
import ru.atc.uss.app.data.srv.PrepayRegistrationSrv;
import ru.atc.uss.app.util.Config;

import java.util.*;

/**
 * Создание абонента
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
class SubscriberBo extends NapiBaseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberBo.class);

    private String marketCode;
    private String accountType;
    private String pricePlan;
    private int ban;
    private int ben;
    private boolean isRegToComverse;
    private String comverseBalance;
    private String comverseOfferName;
    private TreeMap<String, String> ctnSimMap;
    private int benCount;
    private String inn;
    private SubscriberDo subscriberDo;
    private List<SubscriberDo> subscriberDoList;

    public SubscriberBo(String marketCode, String accountType, String pricePlan, TreeMap<String, String> ctnSimMap, int benCount, String inn, int ban, String comverseBalance, String comverseOfferName, boolean isRegToComverse) throws Exception {
        super();
        this.marketCode = marketCode;
        this.accountType = accountType;
        this.pricePlan = pricePlan;
        this.ben = Config.DEFAULT_BEN_NUM;
        this.ban = ban;
        this.ctnSimMap = ctnSimMap;
        this.ctnSimMap = ctnSimMap;
        this.inn = inn;
        this.benCount = benCount;
        this.comverseBalance = comverseBalance;
        this.comverseOfferName = comverseOfferName;
        this.isRegToComverse = isRegToComverse;
        subscriberDo = new SubscriberDo();
        subscriberDo.setResultCode(napiDo.getResultCode());
        subscriberDoList = new ArrayList<SubscriberDo>();
        subscriberDoList.add(subscriberDo);
    }

    public List<SubscriberDo> create(boolean isB2C) throws Exception {

        //Если не произошла успешная аутентификация napi
        if (!subscriberDo.isCreateToEnsSuccess())
            return subscriberDoList;
        int ctnCounter = 0;
        int ctnCountForBen = getCtnCountForBen();
        if(isB2C)
            inn = "000000000000";
        LOGGER.info("inn = " + inn);
        for (Map.Entry<String, String> item : ctnSimMap.entrySet()) {
            subscriberDo = new SubscriberDo();
            subscriberDo.setCtn(item.getKey());
            subscriberDo.setSimNum(item.getValue());
            if (ban == 0)
                createTentativeBan();
            subscriberDo.setBan(ban);
            createSubscriberApplication();
            if (ctnCounter == ctnCountForBen) {
                createBen();
                ben++;
            }
            activateCtn();
            subscriberDo.setIsRegToEnsSuccess(PrepayRegistrationSrv.registerToEnsemble(subscriberDo.getBan()));
            LOGGER.info("Registration to Ensemble: " + subscriberDo.isRegToEnsSuccess());
            subscriberDo.setIsRegToAppSuccess(PrepayRegistrationSrv.registerToApplication(subscriberDo.getBan()));
            LOGGER.info("Registration to application: " + subscriberDo.isRegToAppSuccess());
            if (isRegToComverse) {
                subscriberDo.setIsRegToComverseSuccess(ComverseSrv.registerSubscriber(subscriberDo.getCtn(), comverseBalance, comverseOfferName));
                LOGGER.info("Registration to Comverse: " + subscriberDo.isRegToComverseSuccess());
            }
            subscriberDo.setBen(ben);
            subscriberDoList.add(subscriberDo);
            ctnCounter++;
        }
        return subscriberDoList;
    }

    private void createTentativeBan() throws Exception {
        LOGGER.info("Create tentative ban (start)");
        CreateTentativeBan crTentativeBan = new CreateTentativeBan(sessionId);
        crTentativeBan.setACTV_DATE(logicalDate);
        crTentativeBan.setMARKETCODE(marketCode);
        crTentativeBan.setBIRTH_DATE(Config.BIRTH_DATE);
        crTentativeBan.setCUST_GENDER(Config.CUST_GENDER);
        crTentativeBan.setCUST_NATIONALITY(Config.CUST_NATIONALITY);
        crTentativeBan.setACCOUNT_TYPE(accountType);
        crTentativeBan.setLAST_BUSINESS_NAME(Config.BUSINESS_NAME);
        crTentativeBan.setFIRST_NAME(Config.FIRST_NAME);
        crTentativeBan.setADDITIONAL_TITLE(Config.ADDITIONAL_TITLE);
        crTentativeBan.setNAME_TITLE(Config.NAME_TITLE);
        crTentativeBan.setNAME_FORMAT(Config.NAME_FORMAT);
        crTentativeBan.setADR_PRIMARY_LN(Config.ADR_PRIMARY_LN);
        crTentativeBan.setADR_CITY(Config.ADR_CITY);
        crTentativeBan.setADR_HOUSE_NO(Config.ADR_HOUSE_NO);
        crTentativeBan.setADR_APT_NM(Config.ADR_APT_NM);
        crTentativeBan.setADR_COUNTRY(Config.ADR_COUNTRY);
        crTentativeBan.setCUST_DOC_TYPE(Config.CUST_DOC_TYPE);
        crTentativeBan.setCUST_DOC_ID(Config.CUST_DOC_ID);
        crTentativeBan.setCUST_DOC_NO(Config.CUST_DOC_NO);
        crTentativeBan.setCUST_DOC_DATE(Config.CUST_DOC_DATE);
        crTentativeBan.setADR_POST_CODE(Config.ADR_POST_CODE);
        crTentativeBan.setGUR_TAX_NUMBER(inn);
        crTentativeBan.setCUST_KPP(Config.CUST_KPP);
        crTentativeBan.setADR_STREET_TYPE(Config.ADR_STREET_TYPE);
        crTentativeBan.setADR_PLACE_TYPE(encode(Config.ADR_PLACE_TYPE));
        crTentativeBan.setSALES_ENTITY_CODE(Config.getSalesEntityCode());
        crTentativeBan.setBRANCH_CODE(Config.getBranchCode());
        LOGGER.info(Config.getSalesRep());
        LOGGER.info(Config.getSalesRepCode());
        crTentativeBan.setSALES_REP(Config.getSalesRep());
        crTentativeBan.setSALES_REP_CODE(Config.getSalesRepCode());
        crTentativeBan.callService();
        ban = crTentativeBan.getBAN();
        LOGGER.info("ban = " + ban);
        subscriberDo.setResultCode(crTentativeBan.getSTATE());
        LOGGER.info("Create tentative ban (stop): " + subscriberDo.getResultCode() + " : " + subscriberDo.getResultDescription());
    }

    private void createBen() throws Exception {
        LOGGER.info("Create ben (start)");
        CreateBen crtBen = new CreateBen(sessionId);
        crtBen.setBAN(subscriberDo.getBan());
        crtBen.setACTIVITY_RSN_CODE(Config.activityRsnCodeList.NR.getId());
        crtBen.setDELIVERY_TYPE(Config.DELIVERY_TYPE);
        crtBen.setNAME_FORMAT(Config.NAME_FORMAT);
        crtBen.setLAST_BUSINESS_NAME(Config.BUSINESS_NAME);
        crtBen.setFIRST_NAME(Config.FIRST_NAME);
        crtBen.setNAME_TITLE(Config.NAME_TITLE);
        crtBen.setADR_STREET_TYPE(Config.ADR_STREET_TYPE);
        crtBen.setADR_PLACE_TYPE(Config.ADR_PLACE_TYPE);
        crtBen.setADR_CITY(Config.ADR_CITY);
        crtBen.setADR_COUNTRY(Config.ADR_COUNTRY);
        crtBen.setADR_HOUSE_NO(Config.ADR_HOUSE_NO);
        crtBen.setADR_PRIMARY_LN(Config.ADR_PRIMARY_LN);
        crtBen.callService();
        subscriberDo.setBen(crtBen.getBEN());
        LOGGER.info("ben = " + subscriberDo.getBen());
        subscriberDo.setResultCode(crtBen.getSTATE());
        LOGGER.info("Create ben (stop): " + subscriberDo.getResultCode() + " : " + subscriberDo.getResultDescription());
    }

    private void createSubscriberApplication() throws Exception {
        LOGGER.info("Create subscriber application (start)");
        CreateSubscriberAppl crSubApp = new CreateSubscriberAppl(sessionId);
        crSubApp.setBAN(subscriberDo.getBan());
        crSubApp.setSUBSCRIBER_NO(subscriberDo.getCtn());
        crSubApp.setSALES_ENTITY_CODE(Config.getSalesEntityCode());
        crSubApp.setBRANCH_CODE(Config.getBranchCode());
        crSubApp.setSALES_REP(Config.getSalesRep());
        crSubApp.setSALES_REP_CODE(Config.getSalesRepCode());
        crSubApp.setAPPLICATION_TYPE(Config.APPLICATION_TYPE);
        crSubApp.setREGISTRATION_DATE(logicalDate);
        crSubApp.setSERVICE_MODE(Config.SERVICE_MODE);
        crSubApp.callService();
        subscriberDo.setApplicationId(crSubApp.getAGREEMENT_NUM());
        LOGGER.info("applicationId = " + subscriberDo.getApplicationId());
        subscriberDo.setResultCode(crSubApp.getSTATE());
        LOGGER.info("Create subscriber application (stop): " + subscriberDo.getResultCode() + " : " + subscriberDo.getResultDescription());
    }

    private void activateCtn() throws Exception {
        LOGGER.info("Activate ctn (start)");
        LOGGER.info("ctn = " + subscriberDo.getCtn());
        ActivateCTN actCtn = new ActivateCTN(sessionId);
        actCtn.setBAN(subscriberDo.getBan());
        actCtn.setBEN(ben);
        actCtn.setSUBSCRIBER_NO(subscriberDo.getCtn());
        actCtn.setSIM(subscriberDo.getSimNum());
        actCtn.setPRODUCT_CODE(Config.DEFAULT_PRODUCT_CODE);
        actCtn.setPRICE_PLAN(pricePlan);
        actCtn.setDEPOSIT_REQ_IND(Config.DEPOSIT_REQ_IND);
        actCtn.setACTV_DATE(logicalDate);
        actCtn.setACTIVITY_RSN_CODE(Config.activityRsnCodeList.NCTN.getId());
        actCtn.setAGREEMENT_NUM(subscriberDo.getApplicationId());
        actCtn.setPSOCBUFROWCOUNT(Config.PSOCBUFROWCOUNT);
        actCtn.setPSOCFTRBUFROWCOUNT(Config.PSOCFTRBUFROWCOUNT);
        actCtn.setROWCOUNT(Config.ROWCOUNT);
        actCtn.callService();
        subscriberDo.setResultCode(actCtn.getSTATE());
        LOGGER.info("Activate ctn (stop): " + subscriberDo.getResultCode() + " : " + subscriberDo.getResultDescription());
    }

    private int getCtnCountForBen()
    {
        int div = ctnSimMap.size()/benCount;
        int mod = ctnSimMap.size()%benCount;
        return (mod == 0)? div: div + 1;
    }

    private String encode(String str) {
        char encoded[] = new char[str.length()];
        try {
            byte strByteArray[] = str.getBytes("windows-1251");
            for( int i = 0; i<str.length(); i++ ) {
                // 0xFF - неявно преобразует byte в тип integer, после чего идёт нормальное преобразование в двухбайтовый char
                // Иначе при преобразовании отрицательный b[i] преобразуется в двухбайтовое отрицательное число
                char c = (char)(0xFF & strByteArray[i]);
                encoded[i] = c;
            }
            return new String(encoded);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
