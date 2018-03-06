package ru.atc.uss.app.data.srv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.util.Config;
import ru.atc.uss.app.util.DAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeMap;

/**
 * Подбор данных для создания абонентов
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class CtnStockSrv {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtnStockSrv.class);

    public static TreeMap<String, String> getCtnStockMap (int count, String ngp) throws IOException {

        String rangeStart = Config.getRangeStart();
        String rangeEnd = Config.getRangeEnd();
        TreeMap<String, String> ctnStockList = new TreeMap<String, String>();
        Connection connection;
        PreparedStatement linkStatement = null;
        ResultSet linkResultSet = null;
        try {
            DAO dao = new DAO();
            connection = dao.getConnection(Config.getEnsembleConnectionString(), Config.getEnsembleLogin(), Config.getEnsemblePassword());

            String query = "SELECT CTN, PAIRED_RESOURCE FROM CTN_STOCK WHERE CTN_STATUS = 'AA' AND NGP = " + ngp + " AND PAIRED_RESOURCE IS NOT NULL AND ROWNUM <= " + count + " AND CTN BETWEEN " + rangeStart + " AND " + rangeEnd + " ORDER BY CTN ASC";
            LOGGER.info("Get ctn list: " + query);
            linkStatement = connection.prepareStatement(query);
            linkResultSet = linkStatement.executeQuery();

            while (linkResultSet.next()) {
                String ctn = linkResultSet.getString("CTN");
                LOGGER.info("ctn = " + ctn);
                String sim = linkResultSet.getString("PAIRED_RESOURCE");
                LOGGER.info("sim = " + sim);
                ctnStockList.put(ctn, sim);
            }
        } catch (Exception e) {
            LOGGER.error("Ctn stock list can't be filled", e);
        }

        finally {
            try {
                assert linkResultSet != null;
                linkResultSet.close();
                assert linkStatement != null;
                linkStatement.close();
            } catch (Exception e) {
                LOGGER.error("Connection can't be closed", e);
            }
        }
        return ctnStockList;
    }
}

