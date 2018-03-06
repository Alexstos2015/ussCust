package ru.atc.uss.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("USSTestHelper");
        TabPane tabPane = new TabPane();
        Tab tabSubscriberCreator = new Tab ("CREATE SUBSCRIBER");
        Parent rootSubscriberCreator = FXMLLoader.load(getClass().getResource("/fxml/subscribercreator.fxml"));
        tabSubscriberCreator.setContent(rootSubscriberCreator);
        Tab tabPricePlanManager = new Tab ("CHANGE PRICE PLAN");
        Parent rootPricePlanManager = FXMLLoader.load(getClass().getResource("/fxml/priceplanmanager.fxml"));
        tabPricePlanManager.setContent(rootPricePlanManager);
        Tab tabServiceManager = new Tab ("CHANGE SERVICE");
        Parent rootServiceManager = FXMLLoader.load(getClass().getResource("/fxml/servicemanager.fxml"));
        tabServiceManager.setContent(rootServiceManager);
        Tab tabStatusManager = new Tab ("CHANGE STATUS");
        Parent rootStatusManager = FXMLLoader.load(getClass().getResource("/fxml/statusmanager.fxml"));
        tabStatusManager.setContent(rootStatusManager);
        Tab tabTestCaseFormatter = new Tab ("FORMAT TEST CASE");
        Parent rootTestCaseFormatter = FXMLLoader.load(getClass().getResource("/fxml/testcaseformatter.fxml"));
        tabTestCaseFormatter.setContent(rootTestCaseFormatter);
        tabPane.getTabs().addAll(tabSubscriberCreator, tabPricePlanManager, tabServiceManager, tabStatusManager, tabTestCaseFormatter);
        Scene scene = new Scene(tabPane, 630, 630);
        stage.setScene(scene);
        stage.show();
    }
}
