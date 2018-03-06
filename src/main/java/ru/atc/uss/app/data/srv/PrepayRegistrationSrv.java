package ru.atc.uss.app.data.srv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.util.Config;
import ru.atc.uss.app.util.DAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Регистрация абонентов в Ensemble и USS
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class PrepayRegistrationSrv {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrepayRegistrationSrv.class);

    public static boolean registerToEnsemble (int ban) throws IOException {

        Connection connection;
        PreparedStatement linkStatement = null;
        try {
            DAO dao = new DAO();
            connection = dao.getConnection(Config.getEnsembleConnectionString(), Config.getEnsembleLogin(), Config.getEnsemblePassword());
            String query = "UPDATE CUSTOMER SET REGISTER_IND = 'Y' WHERE CUSTOMER_ID = ?";
            LOGGER.info("Reg to Ens: " + query);

            linkStatement = connection.prepareStatement(query);
            linkStatement.setString(1, ban + "");
            try {
                linkStatement.executeUpdate();
                return true;
            } catch (Exception e) {return false;}

        } catch (Exception e) {
            LOGGER.error("CUSTOMER table can't be updated", e);
            return false;
        }

        finally {
            try {
                assert linkStatement != null;
                linkStatement.close();
            } catch (Exception e) {
                LOGGER.error("Connection can't be closed", e);
            }
        }
    }

    public static boolean registerToApplication (int ban) throws IOException {

        Connection connection;
        PreparedStatement linkStatement = null;
        try {
            DAO dao = new DAO();
            connection = dao.getConnection(Config.getUssdbConnectionString(), Config.getUssdbLogin(), Config.getUssdbPassword());
            String query = "UPDATE ECR9_BILLING_ACCOUNT SET REGISTER_IND = 'Y' WHERE BAN = ?";
            LOGGER.info("Reg to app: " + query);

            linkStatement = connection.prepareStatement(query);
            linkStatement.setString(1, ban + "");
            try {
                linkStatement.executeUpdate();
                return true;
            } catch (Exception e) {return false;}

        } catch (Exception e) {
            LOGGER.error("ECR9_BILLING_ACCOUNT table can't be updated", e);
            return false;
        }

        finally {
            try {
                assert linkStatement != null;
                linkStatement.close();
            } catch (Exception e) {
                LOGGER.error("Connection can't be closed", e);
            }
        }
    }
}
