package com.amdocs.css.vip.comverse;

import https.org_comverse_rtbd_sec.webservice.auth.SAMLSignOnService;
import https.org_comverse_rtbd_sec.webservice.auth.SingleSignOnWS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.util.Config;

import javax.xml.ws.BindingProvider;
import java.io.IOException;

/**
 * Авторизация для сервисов Comverse.
 *
 * @author Sergey Morgunov {@literal <smorgunov@at-consulting.ru>}
 */
@SuppressWarnings("checkstyle:javadocmethod")
public final class ComverseSecurity {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComverseSecurity.class);

    private static volatile ComverseSecurity instance;

    private final String sapiUser;
    private final String sapiRealm;
    private final String sapiPassword;

    private final SingleSignOnWS securityService;

    private long tokenExpirationDate;
    private String token;

    private ComverseSecurity() throws IOException {
        securityService = new SAMLSignOnService().getSingleSignOnWSPort();
        String endpoint = Config.getComverseSequrityUrl();
        ((BindingProvider) securityService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        sapiUser = Config.getComverseSequrityUser();
        sapiRealm = Config.getComverseSequrityRealm();
        sapiPassword = Config.getComverseSequrityPassword();
    }

    private static ComverseSecurity getInstance() throws IOException {
        if (instance == null) {
            synchronized (ComverseSecurity.class) {
                if (instance == null) {
                    instance = new ComverseSecurity();
                }
            }
        }
        return instance;
    }

    private String obtainSecurityToken() {
        LOGGER.debug("Starting getSecurityToken... ");
        String securityToken = null;
        try {
            securityToken = securityService.proxyLogin(sapiUser, sapiPassword, sapiRealm);
        } catch (Exception e) {
            LOGGER.error("Can't obtain security token for comverse one (SSOException) {}", e);
        }
        return securityToken;
    }

    private boolean isTokenTimeElapsed() {
        return token == null || tokenExpirationDate < System.currentTimeMillis();
    }

    public static String sapiUser() throws IOException {
        return getInstance().sapiUser;
    }

    private String getSecurityToken() {
        if (isTokenTimeElapsed()) {
            synchronized (this) {
                if (isTokenTimeElapsed()) {
                    String securityToken = obtainSecurityToken();
                    token = parseToken(securityToken);
                    tokenExpirationDate = parseExpirationDate(securityToken);
                }
            }
        }
        return token;
    }

    public static String securityToken() throws IOException {
        return getInstance().getSecurityToken();
    }

    private String parseToken(String securityToken) {
        String s1 = securityToken;
        int pos1 = s1.indexOf("Token");
        s1 = s1.substring(pos1 + 6);
        int pos2 = s1.indexOf("Token");
        s1 = s1.substring(0, pos2 - 2);
        return s1;
    }

    private long parseExpirationDate(String securityToken) {
        String s1 = securityToken;
        int pos1 = s1.indexOf("HardTimer");
        s1 = s1.substring(pos1 + 10);
        int pos2 = s1.indexOf("HardTimer");
        s1 = s1.substring(0, pos2 - 2);
        return System.currentTimeMillis() + Long.parseLong(s1);
    }

}
