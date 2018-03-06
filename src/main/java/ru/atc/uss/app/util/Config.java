package ru.atc.uss.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class Config {

    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String COMVERSE_BALANCE = "0";
    public static final String OFFER_NAME = "Deaf";
    public static final String DEFAULT_COMVERSE_STATUS = "Active";
    public static final String DEFAULT_SUSPEND_ACTIVITY_RSN_CODE = "WIB";
    public static final String DEFAULT_RESTORE_ACTIVITY_RSN_CODE = "RSBO";
    public static final String CURRENCY = "RUR";
    public static final String BEN_COUNT = "1";
    public static final String CTN_COUNT = "1";
    public static final String SUBSCRIBER_COUNT = "1";
    public static final String NGP = "1";

    //Для России
    public static final String SALES_ENTITY_CODE_RUS = "234";
    public static final String BRANCH_CODE_RUS = "234289";
    public static final String SALES_REP_RUS = "234289 Аликрон";
    public static final String SALES_REP_CODE_RUS = "П234289";
    public static final String MARKET_CODE_RUS = "VIP";
    public static final String ACCOUNT_TYPE_RUS = "13";
    public static final String PRICE_PLAN_RUS = "54ALL2";
    public static final String INN_RUS = "7743013901";


    //Для Казахстана
    public static final String SALES_ENTITY_CODE_KZ = "2260";
    public static final String BRANCH_CODE_KZ = "2260000";
    public static final String SALES_REP_KZ = "П2260000 ТОО Информатика-А";
    public static final String SALES_REP_CODE_KZ = "S2260000";
    public static final String MARKET_CODE_KZ = "KZT";
    public static final String ACCOUNT_TYPE_KZ = "1";
    public static final String PRICE_PLAN_KZ = "UNLIMIT";
    public static final String INN_KZ = "000000000000";

    public static final String BIRTH_DATE = "19891118";
    public static final String CUST_NATIONALITY = "RUS";
    public static final String BUSINESS_NAME = "Abonent";
    public static final String FIRST_NAME = "Super";
    public static final String ADDITIONAL_TITLE = "Super2";
    public static final String NAME_TITLE = "Mr.";
    public static final String ADR_PRIMARY_LN = "28 fevralya";
    public static final String ADR_CITY = "Moskva";
    public static final String ADR_HOUSE_NO = "4";
    public static final String ADR_APT_NM = "654";
    public static final String ADR_COUNTRY = "RUS";
    public static final String CUST_DOC_ID = "1236";
    public static final String CUST_DOC_NO = "123456";
    public static final String CUST_DOC_DATE = "20130126";
    public static final String ADR_POST_CODE = "141007";
    public static final String CUST_KPP = "123456789";
    public static final String ADR_STREET_TYPE  = "ул.";
    public static final String ADR_PLACE_TYPE = "UNDEF";
    public static final String APPLICATION_TYPE = "R";
    public static final String DEFAULT_PRODUCT_CODE = "GVOI";
    public static final byte CUST_GENDER = (byte)'M';
    public static final byte NAME_FORMAT = (byte)'P';
    public static final byte SERVICE_MODE = (byte)'F';
    public static final byte DEPOSIT_REQ_IND = (byte)'N';
    public static final byte DELIVERY_TYPE = (byte)'V';
    public static final short CUST_DOC_TYPE = (short) 1;
    public static final int PSOCBUFROWCOUNT = 0;
    public static final int PSOCFTRBUFROWCOUNT = 0;
    public static final int ROWCOUNT = 0;
    public static final int DEFAULT_BEN_NUM = 1;

    public enum activityRsnCodeList {
        NR("NR"), NCTN("NCTN");
        String id;
        activityRsnCodeList(String id){
            this.id = id;
        }
        public String getId(){return id;}
    }

    public static final String NAPI_ERRORS = "{  \n" +
            "   \"00000\":\"Success\",\n" +
            "   \"ER000\":\"System\",\n" +
            "   \"ER001\":\"No user\",\n" +
            "   \"ER002\":\"User locked\",\n" +
            "   \"ER003\":\"No session\",\n" +
            "   \"ER004\":\"No request\",\n" +
            "   \"ER005\":\"No page\",\n" +
            "   \"ER006\":\"Not found\",\n" +
            "   \"ER007\":\"No BAN SUB\",\n" +
            "   \"ER008\":\"Bad BAN SUB status\",\n" +
            "   \"ER009\":\"Allready started\",\n" +
            "   \"ER010\":\"Not started for BAN SUB\",\n" +
            "   \"ER011\":\"Incorrect PP\",\n" +
            "   \"ER012\":\"PP allready present\",\n" +
            "   \"ER013\":\"RSN is not supported\",\n" +
            "   \"ER014\":\"Incorrect SOC\",\n" +
            "   \"ER015\":\"SOC allready present\",\n" +
            "   \"ER016\":\"SOC conficts with current\",\n" +
            "   \"ER017\":\"SOC is not present\",\n" +
            "   \"ER018\":\"SOC not found\",\n" +
            "   \"ER019\":\"FTR not found\",\n" +
            "   \"ER020\":\"FTR allready included/excluded\",\n" +
            "   \"ER021\":\"FTR conflicts with current\",\n" +
            "   \"ER022\":\"PARM not found\",\n" +
            "   \"ER023\":\"PARAM incorrect\",\n" +
            "   \"ER024\":\"Mistake in save\",\n" +
            "   \"ER025\":\"BAN SUB - locked\",\n" +
            "   \"ER026\":\"BAN SUB - changed\",\n" +
            "   \"ER027\":\"No operation\",\n" +
            "   \"ER028\":\"No needfull parameters\",\n" +
            "   \"ER030\":\"No AT\",\n" +
            "   \"ER031\":\"No MKT\",\n" +
            "   \"ER032\":\"Invalid Curr\",\n" +
            "   \"ER033\":\"Invalid dealer\",\n" +
            "   \"ER034\":\"Tuxedo is not reachable\",\n" +
            "   \"ER035\":\"Built in feature\",\n" +
            "   \"ER036\":\"Incorrect Birth Day\",\n" +
            "   \"ER037\":\"Incorrect Gender\",\n" +
            "   \"ER038\":\"Incorrect Country\",\n" +
            "   \"ER039\":\"Customer document type incorrect\",\n" +
            "   \"ER040\":\"TAX number incorrect\",\n" +
            "   \"ER041\":\"NAME_TITLE incorrect\",\n" +
            "   \"ER042\":\"NAME_FORMAT incorrect\",\n" +
            "   \"ER043\":\"STREET_TYPE incorrect\",\n" +
            "   \"ER044\":\"PLACE_TYPE incorrect\",\n" +
            "   \"ER045\":\"USER_SHORT_NAME incorrect\",\n" +
            "   \"ER046\":\"Number of connections is exceeded\",\n" +
            "   \"ER047\":\"SIM is not present in system\",\n" +
            "   \"ER048\":\"SIM is not suitable for that subscriber\",\n" +
            "   \"ER049\":\"Incorrect WSN-address\",\n" +
            "   \"ER050\":\"Incorrect Activity_Date\",\n" +
            "   \"ER051\":\"Customer document id incorrect\",\n" +
            "   \"ER052\":\"Customer document no incorrect\",\n" +
            "   \"ER053\":\"Customer document date incorrect\",\n" +
            "   \"ER054\":\"No Prepay Registration\",\n" +
            "   \"ER055\":\"Incorrect IW Indicator_Id for that BAN/Subscriber\",\n" +
            "   \"ER056\":\"Incorrect IW Value\",\n" +
            "   \"ER057\":\"Incorrect Mode\",\n" +
            "   \"ER058\":\"Application already exists\",\n" +
            "   \"ER059\":\"Invalid operation for this BAN/Subscriber\",\n" +
            "   \"ER060\":\"IW Indicator_Id already exists for that BAN/Subscriber\",\n" +
            "   \"ER061\":\"Invalid Link type\",\n" +
            "   \"ER062\":\"Invalid Agreement type\",\n" +
            "   \"ER063\":\"Agreement_id incorrect\",\n" +
            "   \"ER064\":\"Agreement not expire able\",\n" +
            "   \"ER065\":\"Parameter not editable\",\n" +
            "   \"ER066\":\"Agreement already exists\",\n" +
            "   \"ER067\":\"Incorrect registration_date\",\n" +
            "   \"ER068\":\"РЎontract is already registrated\",\n" +
            "   \"ER069\":\"Incorrect application_type\",\n" +
            "   \"ER070\":\"Incorrect charge code\",\n" +
            "   \"ER071\":\"Different PP\",\n" +
            "   \"ER072\":\"Different currency\",\n" +
            "   \"ER073\":\"Ben is not found\",\n" +
            "   \"ER074\":\"Incorrect payment_method\",\n" +
            "   \"ER075\":\"Prepaid not found\",\n" +
            "   \"ER076\":\"Postpaid not found\",\n" +
            "   \"ER077\":\"Incorrect Last_Business_Name\",\n" +
            "   \"ER078\":\"Incorrect Adr_City\",\n" +
            "   \"ER079\":\"Different Account Category/Account type\",\n" +
            "   \"ER080\":\"Incorrect service mode\",\n" +
            "   \"ER081\":\"Invalid Product Code\",\n" +
            "   \"ER082\":\"Invalid ctn pattern\",\n" +
            "   \"ER083\":\"Operation is not allowed for this NGP\",\n" +
            "   \"ER084\":\"Not suitable CTN by status and paired_resource\",\n" +
            "   \"ER085\":\"Ban is in collection or parallel collection\",\n" +
            "   \"ER086\":\"Not enough credit limit\",\n" +
            "   \"ER087\":\"Not suitable CTN by Pair Condition HLR\",\n" +
            "   \"ER088\":\"Payment does not exist\",\n" +
            "   \"ER089\":\"Payment already exists\",\n" +
            "   \"ER090\":\"Not valid rms part\",\n" +
            "   \"ER091\":\"Payment source id not found\",\n" +
            "   \"ER092\":\"Incorrect payment amount\",\n" +
            "   \"ER093\":\"Incorrect cur_code\",\n" +
            "   \"ER094\":\"Incorrect BANK_PYM_ID\",\n" +
            "   \"ER095\":\"Ban , Subscriber , Invoice empty\",\n" +
            "   \"ER096\":\"Invoice_number not exist\",\n" +
            "   \"ER097\":\"Payment was changed\",\n" +
            "   \"ER098\":\"Incorrect payment backout reason\",\n" +
            "   \"ER099\":\"Incorrect payment type\",\n" +
            "   \"ER100\":\"Incorrect payment subtype\",\n" +
            "   \"ER101\":\"Payment is already cancelled by previous call\",\n" +
            "   \"ER102\":\"Incorrect Adr_Apt_Nm\",\n" +
            "   \"ER103\":\"Incorrect Adr_House_No\",\n" +
            "   \"ER104\":\"Incorrect Adr_Primary_Ln\",\n" +
            "   \"ER105\":\"Incorrect Media_Request_Ind\",\n" +
            "   \"ER106\":\"DOL_service is not found\",\n" +
            "   \"ER107\":\"PPs not compatible in contract\",\n" +
            "   \"ER108\":\"BAN suspended by financial activity\",\n" +
            "   \"ER109\":\"Credit limit for PP and account_type is not found\",\n" +
            "   \"ER110\":\"Incorrect delivery_type\",\n" +
            "   \"ER111\":\"Incorrect company_type\",\n" +
            "   \"ER112\":\"Incorrect bank_acct_no\",\n" +
            "   \"ER113\":\"Incorrect cust_kpp\",\n" +
            "   \"ER114\":\"Application does not exist\",\n" +
            "   \"ER115\":\"Invalid apartment_type\",\n" +
            "   \"ER116\":\"Invalid resource\",\n" +
            "   \"ER117\":\"FTR is Built In\",\n" +
            "   \"ER118\":\"Incorrect ph_type or attribute\",\n" +
            "   \"ER119\":\"Resource not found\",\n" +
            "   \"ER120\":\"Resource not captured\",\n" +
            "   \"ER121\":\"Resource already captured\",\n" +
            "   \"ER122\":\"Resource not compatible\",\n" +
            "   \"ER123\":\"Contract is too old\",\n" +
            "   \"ER124\":\"lid building_type\",\n" +
            "   \"ER125\":\"lid first_name\",\n" +
            "   \"ER126\":\"Ben not found\",\n" +
            "   \"ER127\":\"Month of previous Ben and new Ben are not identical\",\n" +
            "   \"ER128\":\"Not suitable TARGET_BAN\",\n" +
            "   \"ER129\":\"Future request for this operation is found\",\n" +
            "   \"ER130\":\"Source Payer Ban has only one active CTN\",\n" +
            "   \"ER131\":\"В Charge amount is zero or not found\",\n" +
            "   \"ER132\":\"Incorrect date\",\n" +
            "   \"ER133\":\"Incorrect BL_VAT_EXEMPT_IND\",\n" +
            "   \"ER134\":\"Line type is not valid\",\n" +
            "   \"ER135\":\"Incorrect lg type\",\n" +
            "   \"ER136\":\"Request_id not found or has incorrect status\",\n" +
            "   \"ER137\":\"Address data are not populated\",\n" +
            "   \"ER138\":\"Incorrect location_id\",\n" +
            "   \"ER139\":\"Name data are not populated\",\n" +
            "   \"ER140\":\"Incorrect count of objects\",\n" +
            "   \"ER141\":\"Incorrect lg_status\",\n" +
            "   \"ER142\":\"Customer info is not populated\",\n" +
            "   \"ER143\":\"Address data already exist\",\n" +
            "   \"ER144\":\"Name data already exist\",\n" +
            "   \"ER145\":\"Incorrect ph_no\",\n" +
            "   \"ER146\":\"Incorrect payment_id\",\n" +
            "   \"ER147\":\"No needful resource attributes\",\n" +
            "   \"ER148\":\"Invalid attribute name\",\n" +
            "   \"ER149\":\"Invalid attribute value\",\n" +
            "   \"ER150\":\"Resource already reserved on this request_id or ban\",\n" +
            "   \"ER151\":\"Dealer ban not found\",\n" +
            "   \"ER152\":\"Discount plan is not found\",\n" +
            "   \"ER153\":\"Free number for next CR-s\",\n" +
            "   \"ER154\":\"Ban is not satisfied to discount criterion\",\n" +
            "   \"ER155\":\"Discount is already exists or exists discount of other discount_category for this object\",\n" +
            "   \"ER156\":\"Feature param has secondary resource\",\n" +
            "   \"ER157\":\"Ben bill_cycle is not effective in cycle_control\",\n" +
            "   \"ER158\":\"Equipment of CTN is not transferred to dealer or returned\",\n" +
            "   \"ER159\":\"Current PP not equal to source PP\",\n" +
            "   \"ER160\":\"Found CPP by Air transactions on date\",\n" +
            "   \"ER161\":\"Invalid billing_cycle\",\n" +
            "   \"ER162\":\"Invalid Payer Ban\",\n" +
            "   \"ER163\":\"Invalid registation_type\",\n" +
            "   \"ER164\":\"Incorrect memo_type\",\n" +
            "   \"ER165\":\"Operation is not allowed for this NL\",\n" +
            "   \"ER166\":\"Invalid CTN status\",\n" +
            "   \"ER167\":\"Invalid NGP_TYPE\",\n" +
            "   \"ER168\":\"Invalid RESOURCE_TYPE\",\n" +
            "   \"ER169\":\"CTN not found in RM\",\n" +
            "   \"ER170\":\"Delete from CTN_STOCK failed\",\n" +
            "   \"ER171\":\"Delete from NGP_RANGES failed\",\n" +
            "   \"ER172\":\"Discount is not found for this object\",\n" +
            "   \"ER173\":\"Restricted feature\",\n" +
            "   \"ER174\":\"Parameter list is full\",\n" +
            "   \"ER175\":\"Duplicate feature parameter value\",\n" +
            "   \"ER176\":\"Parameter list is empty\",\n" +
            "   \"ER177\":\"Failed bill print data selection\",\n" +
            "   \"ER178\":\"Invalid bill print input parameter\",\n" +
            "   \"ER179\":\"Failed bill print insert request\",\n" +
            "   \"ER180\":\"Invalid destination\",\n" +
            "   \"ER181\":\"Save Payer Number list is failed\",\n" +
            "   \"ER182\":\"Duplicate data found\",\n" +
            "   \"ER183\":\"Incorrect free_number\",\n" +
            "   \"ER184\":\"Incorrect prefix_ind\",\n" +
            "   \"ER185\":\"Incorrect EXCL_DEF_ACT_FTR indicator\",\n" +
            "   \"ER186\":\"Incorrect activity code\",\n" +
            "   \"ER187\":\"Incorrect delimiter\",\n" +
            "   \"ER188\":\"Not allowed for prepaid\",\n" +
            "   \"ER189\":\"Invalid List Name\",\n" +
            "   \"ER600\":\"Duplicate resource\",\n" +
            "   \"ER601\":\"Resource was changed\",\n" +
            "   \"ER602\":\"Incorrect ph_id\",\n" +
            "   \"ER603\":\"Incorrect ph_key\",\n" +
            "   \"ER604\":\"Incorrect MOVE_DISCOUNT_IND\",\n" +
            "   \"ER605\":\"Corporate not found\",\n" +
            "   \"ER606\":\"Hierarchy_id and Ban are empty\",\n" +
            "   \"ER607\":\"Corporate and Ban are not corresponding\"\n" +
            "}";

    private static final String REGEXP_COUNTRY = "country=(.*);";
    private static final String REGEXP_NAPI_WSN_ADDR = "napi.wsn.addr=(.*);";
    private static final String REGEXP_NAPI_USER_ID = "napi.user.id=(.*);";
    private static final String REGEXP_NAPI_PASSWORD = "napi.password=(.*);";
    private static final String REGEXP_ENSEMBLE_CONNECTION_STRING = "ensemble.connection.string=(.*);";
    private static final String REGEXP_ENSEMBLE_LOGIN = "ensemble.login=(.*);";
    private static final String REGEXP_ENSEMBLE_PASSWORD = "ensemble.password=(.*);";
    private static final String REGEXP_COMVERSE_SEQURITY_URL = "comverse.sequrity.url=(.*);";
    private static final String REGEXP_COMVERSE_SEQURITY_USER = "comverse.sequrity.user=(.*);";
    private static final String REGEXP_COMVERSE_SEQURITY_REALM = "comverse.sequrity.realm=(.*);";
    private static final String REGEXP_COMVERSE_SEQURITY_PASSWORD = "comverse.sequrity.password=(.*);";
    private static final String REGEXP_COMVERSE_CUSTOMERCARE_URL = "comverse.customercare.url=(.*);";
    private static final String REGEXP_COMVERSE_SUBSCRIBER_URL = "comverse.subscriber.url=(.*);";
    private static final String REGEXP_USSDB_CONNECTION_STRING = "ussdb.connection.string=(.*);";
    private static final String REGEXP_USSDB_LOGIN = "ussdb.login=(.*);";
    private static final String REGEXP_USSDB_PASSWORD = "ussdb.password=(.*);";
    private static final String REGEXP_RANGE_START = "ctn.range.start=(.*);";
    private static final String REGEXP_RANGE_END = "ctn.range.end=(.*);";
    private static final String REGEXP_SALES_ENTITY_CODE = "sales.entity.code=(.*);";
    private static final String REGEXP_BRANCH_CODE = "branch.code=(.*);";
    private static final String REGEXP_SALES_REP = "sales.rep=(.*);";
    private static final String REGEXP_SALES_REP_CODE = "sales.rep.code=(.*);";


    private static String country; //RUS - для России, KZ - для Казахстана
    private static String napiWsnAddr;
    private static int napiUserId;
    private static String napiPassword;
    private static String ensembleConnectionString;
    private static String ensembleLogin;
    private static String ensemblePassword;
    private static String comverseSequrityUrl;
    private static String comverseSequrityUser;
    private static String comverseSequrityRealm;
    private static String comverseSequrityPassword;
    private static String comverseCustomercareUrl;
    private static String comverseSubscriberUrl;
    private static String ussdbConnectionString;
    private static String ussdbLogin;
    private static String ussdbPassword;
    private static String rangeStart;
    private static String rangeEnd;
    private static String salesEntityCode;
    private static String branchCode;
    private static String salesRep;
    private static String salesRepCode;

    public static String getCountry() {
        return country;
    }

    public static String getNapiWsnAddr() {
        return napiWsnAddr;
    }

    public static int getNapiUserId() {
        return napiUserId;
    }

    public static String getNapiPassword() {
        return napiPassword;
    }

    public static String getEnsembleConnectionString() {
        return ensembleConnectionString;
    }

    public static String getEnsembleLogin() {
        return ensembleLogin;
    }

    public static String getEnsemblePassword() {
        return ensemblePassword;
    }

    public static String getComverseSequrityUrl() {
        return comverseSequrityUrl;
    }

    public static String getComverseSequrityUser() {
        return comverseSequrityUser;
    }

    public static String getComverseSequrityRealm() {
        return comverseSequrityRealm;
    }

    public static String getComverseSequrityPassword() {
        return comverseSequrityPassword;
    }

    public static String getComverseCustomercareUrl() {
        return comverseCustomercareUrl;
    }

    public static String getComverseSubscriberUrl() {
        return comverseSubscriberUrl;
    }

    public static String getUssdbConnectionString() {
        return ussdbConnectionString;
    }

    public static String getUssdbLogin() {
        return ussdbLogin;
    }

    public static String getUssdbPassword() {
        return ussdbPassword;
    }

    public static String getRangeStart() {
        return rangeStart;
    }

    public static String getRangeEnd() {
        return rangeEnd;
    }

    public static String getSalesRepCode() { return salesRepCode; }

    public static String getSalesRep() { return salesRep; }

    public static String getBranchCode() {
        return branchCode;
    }

    public static String getSalesEntityCode() {
        return salesEntityCode;
    }

    private static String paramString;

    static  {
        try {
            paramString= readFile();

            /*Если в usstesthelper.config будут указаны 1,2,3 или 4 дополнительных конфига(sales.entity.code, branch.code, sales.rep, sales.rep.code),
            то соответствующие переменные примут их значение, для неуказаных конфигов будет проставляться дефолтное значение из конфига
            в зависимости от значения country
            */

            country = (getParam(REGEXP_COUNTRY).equals("KZ")) ? "KZ" : "RUS"; //Любое значение кроме KZ, в том числе и отсутствие конфига, воспринимается как "RUS"
            napiWsnAddr = getParam(REGEXP_NAPI_WSN_ADDR);
            napiUserId = Integer.parseInt(getParam(REGEXP_NAPI_USER_ID));
            napiPassword = getParam(REGEXP_NAPI_PASSWORD);
            ensembleConnectionString = getParam(REGEXP_ENSEMBLE_CONNECTION_STRING);
            ensembleLogin = getParam(REGEXP_ENSEMBLE_LOGIN);
            ensemblePassword = getParam(REGEXP_ENSEMBLE_PASSWORD);
            comverseSequrityUrl = getParam(REGEXP_COMVERSE_SEQURITY_URL);
            comverseSequrityUser = getParam(REGEXP_COMVERSE_SEQURITY_USER);
            comverseSequrityRealm = getParam(REGEXP_COMVERSE_SEQURITY_REALM);
            comverseSequrityPassword = getParam(REGEXP_COMVERSE_SEQURITY_PASSWORD);
            comverseCustomercareUrl = getParam(REGEXP_COMVERSE_CUSTOMERCARE_URL);
            comverseSubscriberUrl = getParam(REGEXP_COMVERSE_SUBSCRIBER_URL);
            ussdbConnectionString = getParam(REGEXP_USSDB_CONNECTION_STRING);
            ussdbLogin = getParam(REGEXP_USSDB_LOGIN);
            ussdbPassword = getParam(REGEXP_USSDB_PASSWORD);
            rangeStart = getParam(REGEXP_RANGE_START);
            rangeEnd = getParam(REGEXP_RANGE_END);
            salesEntityCode = (getParam(REGEXP_SALES_ENTITY_CODE).equals("")) ? ((country.equals("RUS")) ? SALES_ENTITY_CODE_RUS : SALES_ENTITY_CODE_KZ) : getParam(REGEXP_SALES_ENTITY_CODE);
            branchCode = (getParam(REGEXP_BRANCH_CODE).equals("")) ? ((country.equals("RUS")) ? BRANCH_CODE_RUS : BRANCH_CODE_KZ) : getParam(REGEXP_BRANCH_CODE);
            salesRep = (getParam(REGEXP_SALES_REP).equals("")) ? ((country.equals("RUS")) ? SALES_REP_RUS : SALES_REP_KZ) : convertWin1251ToUtf8(getParam(REGEXP_SALES_REP));
            salesRepCode = (getParam(REGEXP_SALES_REP_CODE).equals("")) ? ((country.equals("RUS")) ? SALES_REP_CODE_RUS : SALES_REP_CODE_KZ) : convertWin1251ToUtf8(getParam(REGEXP_SALES_REP_CODE));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String readFile() throws IOException {
        FileInputStream fis =  new FileInputStream("usstesthelper.config");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "windows-1251"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            br.close();
        }
    }

    public static String convertWin1251ToUtf8(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("windows-1251"), Charset.forName("UTF-8"));
    }

    private static String getParam(String regexp)
    {
        try {
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(paramString);
            matcher.find();
            return matcher.group(1);
        } catch (Exception e) {
            return "";
        }

    }
}
