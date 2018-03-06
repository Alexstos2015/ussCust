package ru.atc.uss.app.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atc.uss.app.subscriberstatus.SubscriberStatusSrv;
import ru.atc.uss.app.util.Config;
import ru.atc.uss.app.util.Validator;

/**
 * Контроллер изменения статуса абоненту
 *
 * Created by IIvankov on 02.01.2016.
 */
public class StatusManagerController  extends BaseController {

    @FXML public TextField txtfCtn;
    @FXML public CheckBox cbChangeToEnsemble;
    @FXML public RadioButton rbSuspend;
    @FXML public RadioButton rbRestore;
    @FXML public TextField txtfBan;
    @FXML public CheckBox cbChangeToComverse;
    @FXML public ComboBox cbComverseStatus;
    @FXML public Button btnRun;
    @FXML public TextArea txtaResult;

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusManagerController.class);

    public StatusManagerController() {
    }

    public void changeStatus(ActionEvent actionEvent) throws Exception {
        runWorker(actionEvent);
    }

    protected Task runFunctionalWorker()
    {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                disableElements(true);
                Validator validator = new Validator();
                validator.validateChangeStatusFormFields(txtfCtn.getText(), txtfBan.getText(), cbChangeToEnsemble.isSelected(), cbChangeToComverse.isSelected());
                if (validator.isSuccess())
                {
                    int ban = 0;
                    try{
                        ban = Integer.parseInt(txtfBan.getText());
                    }catch (Exception e){};
                            validator.validateStatus(SubscriberStatusSrv.changeStatus(txtfCtn.getText(), ban, rbSuspend.isSelected(), (rbSuspend.isSelected()) ? Config.DEFAULT_SUSPEND_ACTIVITY_RSN_CODE : Config.DEFAULT_RESTORE_ACTIVITY_RSN_CODE, cbComverseStatus.getValue().toString(), cbChangeToEnsemble.isSelected(), cbChangeToComverse.isSelected()));
                    txtaResult.appendText(validator.getDescription() + "\n");
                }
                else txtaResult.appendText(validator.getDescription() + "\n");

                disableElements(false);
                isDone=true;
                return true;
            }
        };
    }

    public void selectCheckBox(ActionEvent actionEvent)
    {
        if (cbChangeToEnsemble.isSelected()){
            rbSuspend.setDisable(false);
            rbRestore.setDisable(false);
            txtfBan.setDisable(false);
        }
        else {
            rbSuspend.setDisable(true);
            rbRestore.setDisable(true);
            txtfBan.setDisable(true);
        }

        if (cbChangeToComverse.isSelected()) {
            cbComverseStatus.setDisable(false);
        }
        else {
            cbComverseStatus.setDisable(true);
        }
    }

    private void disableElements(boolean isDisable)
    {
        txtfCtn.setDisable(isDisable);
        cbChangeToEnsemble.setDisable(isDisable);
        if(cbChangeToEnsemble.isSelected()) {
            txtfBan.setDisable(isDisable);
            rbSuspend.setDisable(isDisable);
            rbRestore.setDisable(isDisable);
        }
        cbChangeToComverse.setDisable(isDisable);
        if(cbChangeToComverse.isSelected())
            cbComverseStatus.setDisable(isDisable);
        btnRun.setDisable(isDisable);
        txtaResult.setDisable(isDisable);
    }
}
