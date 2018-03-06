package ru.atc.uss.app.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.subscriberpriceplan.SubscriberPricePlanSrv;
import ru.atc.uss.app.util.Validator;

/**
 * Контроллер изменения тарифного плана абоненту
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class PricePlanManagerController extends BaseController {

    @FXML public TextField txtfBan;
    @FXML public TextField txtfCtn;
    @FXML public ComboBox cbRsnCode;
    @FXML public TextField txtfSourceSoc;
    @FXML public TextField txtfTargetSoc;
    @FXML public Button btnChange;
    @FXML public TextArea txtaResult;

    private static final Logger LOGGER = LoggerFactory.getLogger(PricePlanManagerController.class);

    public void changePricePlan(ActionEvent actionEvent) throws Exception {
        runWorker(actionEvent);
    }

    protected Task runFunctionalWorker()
    {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                disableElements(true);
                Validator validator = new Validator();
                validator.validateChangePricePlanFormFields(txtfBan.getText(), txtfCtn.getText(), txtfSourceSoc.getText(), txtfTargetSoc.getText());
                if(validator.isSuccess())
                {
                    validator.validatePricePlan(SubscriberPricePlanSrv.changePricePlan(Integer.parseInt(txtfBan.getText()), txtfCtn.getText(), txtfSourceSoc.getText(), txtfTargetSoc.getText(), cbRsnCode.getValue().toString()));
                    txtaResult.appendText(validator.getDescription() + "\n");
                }
                else txtaResult.appendText(validator.getDescription() + "\n");

                disableElements(false);
                isDone=true;
                return true;
            }
        };
    }

    private void disableElements(boolean isDisable)
    {
        txtfBan.setDisable(isDisable);
        txtfCtn.setDisable(isDisable);
        txtfSourceSoc.setDisable(isDisable);
        txtfTargetSoc.setDisable(isDisable);
        txtaResult.setDisable(isDisable);
        btnChange.setDisable(isDisable);
    }
}
