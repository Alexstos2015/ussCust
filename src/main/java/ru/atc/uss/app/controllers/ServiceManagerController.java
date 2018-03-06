package ru.atc.uss.app.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.subscriberservice.SubscriberServiceSrv;
import ru.atc.uss.app.util.Validator;

/**
 * Контроллер подключения/отключения услуги обаненту
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class ServiceManagerController extends BaseController {

    @FXML public RadioButton rbAdd;
    @FXML public RadioButton rbDelete;
    @FXML public TextField txtfBan;
    @FXML public TextField txtfCtn;
    @FXML public TextField txtfSoc;
    @FXML public Button btnChange;
    @FXML public TextArea txtaResult;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManagerController.class);

    public void changeSerivce(ActionEvent actionEvent) throws Exception {
        runWorker(actionEvent);
    }

    protected Task runFunctionalWorker()
    {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                disableElements(true);
                Validator validator = new Validator();
                validator.validateChangeServiceFormFields(txtfBan.getText(), txtfCtn.getText(), txtfSoc.getText());
                if(validator.isSuccess())
                {
                    validator.validateService(SubscriberServiceSrv.changeService(Integer.parseInt(txtfBan.getText()), txtfCtn.getText(), txtfSoc.getText(), (rbAdd.isSelected()) ? 'A' : 'D'));
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
        rbAdd.setDisable(isDisable);
        rbDelete.setDisable(isDisable);
        txtfBan.setDisable(isDisable);
        txtfCtn.setDisable(isDisable);
        txtfSoc.setDisable(isDisable);
        txtaResult.setDisable(isDisable);
        btnChange.setDisable(isDisable);
    }
}
