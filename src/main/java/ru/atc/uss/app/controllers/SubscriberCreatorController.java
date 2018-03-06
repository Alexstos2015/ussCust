package ru.atc.uss.app.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.data.srv.CtnStockSrv;
import ru.atc.uss.app.subscriber.SubscriberSrv;
import ru.atc.uss.app.util.Config;
import ru.atc.uss.app.util.Validator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Контроллер создания абонента
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class SubscriberCreatorController extends BaseController {

    @FXML public RadioButton rbB2b;
    @FXML public RadioButton rbB2c;
    @FXML public TextField txtfBenCount;
    @FXML public TextField txtfCtnCount;
    @FXML public TextField txtfSubscriberCount;
    @FXML public TextField txtfMarketCode;
    @FXML public TextField txtfNgp;
    @FXML public TextField txtfAccountType;
    @FXML public TextField txtfPricePlan;
    @FXML public TextField txtfInn;
    @FXML public TextField txtfComverseBalance;
    @FXML public TextField txtfComverseOfferName;
    @FXML public TextField txtfBan;
    @FXML public Button btnCreate;
    @FXML public TextArea txtaResult;
    @FXML public CheckBox cbCreateToComverse;
    @FXML public CheckBox cbCreateNewBan;

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberCreatorController.class);

    public void initialize(URL location, ResourceBundle resources) {

        txtfBenCount.setText(Config.BEN_COUNT);
        txtfCtnCount.setText(Config.CTN_COUNT);
        txtfSubscriberCount.setText(Config.SUBSCRIBER_COUNT);
        txtfMarketCode.setText(Config.getCountry().equals("RUS") ? Config.MARKET_CODE_RUS : Config.MARKET_CODE_KZ);
        txtfNgp.setText(Config.NGP);
        txtfAccountType.setText(Config.getCountry().equals("RUS") ? Config.ACCOUNT_TYPE_RUS : Config.ACCOUNT_TYPE_KZ);
        txtfPricePlan.setText(Config.getCountry().equals("RUS") ? Config.PRICE_PLAN_RUS : Config.PRICE_PLAN_KZ);
        txtfInn.setText(Config.getCountry().equals("RUS") ? Config.INN_RUS : Config.INN_KZ);
        txtfComverseBalance.setText(Config.COMVERSE_BALANCE);
        txtfComverseOfferName.setText(Config.OFFER_NAME);
        cbCreateNewBan.setSelected(true);
        txtfBan.setText("0");
        txtfBan.setDisable(true);

        //Дизейблим блок Comverse для Казахстана, так как там менять данные нельзя из-за отсутствия тестового сервиса
        if(Config.getCountry().equals("KZ"))
        {
            cbCreateToComverse.setDisable(true);
            txtfComverseBalance.setDisable(true);
            txtfComverseOfferName.setDisable(true);
        }
    }

    public void createSubscriber(ActionEvent actionEvent) throws Exception {
        runWorker(actionEvent);
    }

    public Task runFunctionalWorker()
    {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                disableElements(true);
                Validator validator = new Validator();
                validator.validateCreateSubscriberFormFields(txtfBenCount.getText(), txtfCtnCount.getText(), txtfSubscriberCount.getText(), txtfMarketCode.getText(), txtfNgp.getText(), txtfAccountType.getText(), txtfPricePlan.getText(), txtfInn.getText(), (cbCreateNewBan.isSelected() ? "999999999" : txtfBan.getText()) /*Если создаем новый бан, то в сервис будем отправлять 0, но чтобы валидация прошла в validateCreateSubscriberFormFields передаем 999999999*/, txtfComverseBalance.getText(), txtfComverseOfferName.getText());
                if (validator.isSuccess()) {
                    for (int i = 0; i < Integer.parseInt(txtfSubscriberCount.getText()); i++)
                    {
                        TreeMap<String, String> ctnStockMap = CtnStockSrv.getCtnStockMap(Integer.parseInt(txtfCtnCount.getText()), txtfNgp.getText());
                        validator.validateCtnStock(ctnStockMap, Integer.parseInt(txtfCtnCount.getText()));
                        if (validator.isSuccess())
                        {
                            validator.validateSubscriber(SubscriberSrv.createSubscriber(txtfMarketCode.getText(), txtfAccountType.getText(), txtfPricePlan.getText(), ctnStockMap, Integer.parseInt(txtfBenCount.getText()), txtfInn.getText(), rbB2c.isSelected(), Integer.parseInt(txtfBan.getText()), txtfComverseBalance.getText(), txtfComverseOfferName.getText(), cbCreateToComverse.isSelected()));
                            txtaResult.appendText(validator.getDescription() + "\n");
                        }
                        else txtaResult.appendText(validator.getDescription());
                    }
                }
                else{
                    txtaResult.appendText(validator.getDescription());
                }
                disableElements(false);
                isDone=true;
                return true;
            }
        };
    }

    public void changeValueCbCreateToComverse(ActionEvent actionEvent)
    {
        if (cbCreateToComverse.isSelected()){
            txtfComverseBalance.setDisable(false);
            txtfComverseOfferName.setDisable(false);
        }
        else {
            txtfComverseBalance.setDisable(true);
            txtfComverseOfferName.setDisable(true);
        }
    }

    public void changeValueCbCreateNewBan(ActionEvent actionEvent)
    {
        if (cbCreateNewBan.isSelected()){
            txtfBan.setDisable(true);
            txtfBan.setText("0");
        }
        else {
            txtfBan.setDisable(false);
            txtfBan.setText("999999999");
        }
    }


    private void disableElements(boolean isDisable)
    {
        rbB2c.setDisable(isDisable);
        rbB2b.setDisable(isDisable);
        txtfBenCount.setDisable(isDisable);
        txtfCtnCount.setDisable(isDisable);
        txtfSubscriberCount.setDisable(isDisable);
        txtfMarketCode.setDisable(isDisable);
        txtfNgp.setDisable(isDisable);
        txtfAccountType.setDisable(isDisable);
        txtfPricePlan.setDisable(isDisable);
        txtfInn.setDisable(isDisable);
        cbCreateToComverse.setDisable(Config.getCountry().equals("KZ") ? true : isDisable);
        if (cbCreateToComverse.isSelected()) {
            txtfComverseBalance.setDisable(isDisable);
            txtfComverseOfferName.setDisable(isDisable);
        }
        btnCreate.setDisable(isDisable);
        txtaResult.setDisable(isDisable);
    }
}
