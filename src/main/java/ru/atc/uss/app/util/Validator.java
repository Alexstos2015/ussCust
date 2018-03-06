package ru.atc.uss.app.util;

import ru.atc.uss.app.subscriberpriceplan.SubscriberPricePlanDo;
import ru.atc.uss.app.subscriberservice.SubscriberServiceDo;
import ru.atc.uss.app.subscriber.SubscriberDo;
import ru.atc.uss.app.subscriberstatus.SubscriberStatusDo;

import java.util.List;
import java.util.Map;

/**
 * Обработка входных значений, обработка результатов выполнения сценариев
 *
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class Validator {

    private boolean isSuccess;
    private String description;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getDescription() {
        return description;
    }

    public void validateCreateSubscriberFormFields (String benCount, String ctnCount, String subscriberCount, String marketCode, String ngp, String accountType, String pricePlan, String inn, String ban, String comverseBalance, String offerName)
    {
        isSuccess = true;

        if (isEmpty(benCount) || !isNaturalNumber(benCount) || Integer.parseInt(benCount) > 100) {
            isSuccess = false;
            description = "Поле \"Количество BEN\" не должно быть пустым, его значение должно быть натуральным числом, не превышающим 100\n";
            return;
        }
        if (isEmpty(ctnCount) || !isNaturalNumber(ctnCount) || Integer.parseInt(ctnCount) > 1000 || Integer.parseInt(ctnCount) < Integer.parseInt(benCount)) {
            isSuccess = false;
            description = "Поле \"Количество CTN\" не должно быть пустым, его значение должно быть натуральным числом от " + Integer.parseInt(benCount) + " до 1000\n";
            return;
        }
        if (isEmpty(subscriberCount) || !isNaturalNumber(subscriberCount) || Integer.parseInt(subscriberCount) > 100) {
            isSuccess = false;
            description = "Поле \"Количество абонентов\" не должно быть пустым, его значение должно быть натуральным числом, не превышающим 100\n";
            return;
        }
        if (isEmpty(marketCode) || !isCapitalLatinLetterSequence(marketCode) || marketCode.length() != 3) {
            isSuccess = false;
            description = "Поле \"Market code\" не должно быть пустым, его значение должно быть строкой, состоящей из 3 заглавных букв латинского алфавита\n";
            return;
        }
        if (isEmpty(ngp) || !isNaturalNumber(ngp) || Integer.parseInt(ngp) > 100) {
            isSuccess = false;
            description = "Поле \"NGP\" не должно быть пустым, его значение должно быть натуральным числом, не превышающим 100\n";
            return;
        }
        if (isEmpty(accountType) || !isNaturalNumber(accountType) || Integer.parseInt(accountType) > 1000) {
            isSuccess = false;
            description = "Поле \"Account type\" не должно быть пустым, его значение должно быть натуральным числом, не превышающим 1000\n";
            return;
        }
        if (isEmpty(pricePlan) || !isCapitalLatinLetterAndDigitSequence(pricePlan) || pricePlan.length() > 9) {
            isSuccess = false;
            description = "Поле \"Тарифный план\" не должно быть пустым, его значение должно быть строкой, состоящей не более чем из 9 заглавных букв латинского алфавита или цифр\n";
            return;
        }
        if (isEmpty(inn) || !isDigitSequence(inn) || !(inn.length() == 10 || inn.length() == 12)) {
            isSuccess = false;
            description = "Поле \"ИНН\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 10/12 (для юр./физ. лиц) символов\n";
            return;
        }
        if (isEmpty(ban) || !isDigitSequence(ban) || ban.length() != 9) {
            isSuccess = false;
            description = "Поле \"Ban\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 9\n";
            return;
        }
        if (isEmpty(comverseBalance) || !isNumber(comverseBalance) || Integer.parseInt(comverseBalance) > 1000000) {
            isSuccess = false;
            description = "Поле \"Comverse balance\" не должно быть пустым, его значение должно быть числом, не превышающим 1000000\n";
            return;
        }
        if (isEmpty(offerName)) {
            isSuccess = false;
            description = "Поле \"Offer name\" не должно быть пустым\n";
            return;
        }
        isSuccess = true;
    }

    public void validateChangePricePlanFormFields (String ban, String subscriberNo, String sourcePricePlan, String targetPricePlan)
    {
        isSuccess = true;

        if (isEmpty(ban) || !isDigitSequence(ban) || ban.length() != 9) {
            isSuccess = false;
            description = "Поле \"Ban\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 9\n";
            return;
        }

        if (isEmpty(subscriberNo) || !isDigitSequence(subscriberNo) || subscriberNo.length() != 10) {
            isSuccess = false;
            description = "Поле \"Ctn\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 10\n";
            return;
        }

        if (isEmpty(sourcePricePlan) || !isCapitalLatinLetterAndDigitSequence(sourcePricePlan) || sourcePricePlan.length() > 9) {
            isSuccess = false;
            description = "Поле \"Soc текущего ТП\" не должно быть пустым, его значение должно быть строкой, состоящей не более чем из 9 заглавных букв латинского алфавита или цифр\n";
            return;
        }

        if (isEmpty(targetPricePlan) || !isCapitalLatinLetterAndDigitSequence(targetPricePlan) || targetPricePlan.length() > 9) {
            isSuccess = false;
            description = "Поле \"Soc нового ТП\" не должно быть пустым, его значение должно быть строкой, состоящей не более чем из 9 заглавных букв латинского алфавита или цифр\n";
            return;
        }
        isSuccess = true;
    }

    public void validateChangeServiceFormFields (String ban, String subscriberNo, String soc)
    {
        isSuccess = true;

        if (isEmpty(ban) || !isDigitSequence(ban) || ban.length() != 9) {
            isSuccess = false;
            description = "Поле \"Ban\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 9\n";
            return;
        }

        if (isEmpty(subscriberNo) || !isDigitSequence(subscriberNo) || subscriberNo.length() != 10) {
            isSuccess = false;
            description = "Поле \"Ctn\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 10\n";
            return;
        }

        if (isEmpty(soc) || !isCapitalLatinLetterAndDigitSequence(soc) || soc.length() > 9) {
            isSuccess = false;
            description = "Поле \"Soc\" не должно быть пустым, его значение должно быть строкой, состоящей не более чем из 9 заглавных букв латинского алфавита или цифр\n";
            return;
        }
        isSuccess = true;
    }

    public void validateChangeStatusFormFields (String subscriberNo, String ban, boolean isChangeStatusToEns, boolean isChangeStatusToComverse)
    {
        isSuccess = true;

        if (isEmpty(subscriberNo) || !isDigitSequence(subscriberNo) || subscriberNo.length() != 10) {
            isSuccess = false;
            description = "Поле \"Ctn\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 10\n";
            return;
        }

        if (isChangeStatusToEns && (isEmpty(ban) || !isDigitSequence(ban) || ban.length() != 9)) {
            isSuccess = false;
            description = "Поле \"Ban\" не должно быть пустым, его значение должно быть последовательностью цифр, длинной 9\n";
            return;
        }

        if (!isChangeStatusToEns && !isChangeStatusToComverse)
        {
            isSuccess = false;
            description = "Должна быть выбрана хотя бы одна система для изменения статуса\n";
            return;
        }

        isSuccess = true;
    }

    public void validateCtnStock(Map<String, String> ctnStockList, int countCtn)
    {
        isSuccess = true;
        if (ctnStockList.size() == countCtn)
            isSuccess = true;
        else {
            isSuccess = false;
            description = "Запрос, удовлетворяющий заданным данным, вернул " + ctnStockList.size() + " значений вместо " + countCtn + '\n';
        }
    }

    public void validateSubscriber (List<SubscriberDo> subscriberDoList)
    {
        isSuccess = true;
        description = "";
        //Если система не выбрана для создания абонента, то флаг успеха создания абонента в ней - true
        for (SubscriberDo subscriberDo : subscriberDoList)
        {
            if (!subscriberDo.getCtn().equals("0"))
                description += subscriberDo.getCtn();
            if (subscriberDo.getBen() != 0)
                description += subscriberDo.getBen() + " ";
            if (subscriberDo.getBan() != 0)
                description += subscriberDo.getBan();

            description += " ";

            //Считаем, что создание абонента успешно уже тогда, когда он успешно создался в Ensemble
            isSuccess = subscriberDo.isCreateToEnsSuccess();

            if (!subscriberDo.isCreateToEnsSuccess())
                description += "Ошибка при создании абонента в Ensemble (" + subscriberDo.getResultCode() + ": " + subscriberDo.getResultDescription() + "). ";
            if (!subscriberDo.isRegToEnsSuccess())
                description += "Ошибка при регистрации абонента в Ensemble. ";
            if (!subscriberDo.isRegToAppSuccess())
                description += "Ошибка при регистрации абонента в USSS. ";
            if (!subscriberDo.isRegToComverseSuccess())
                description += "Ошибка при регистрации абонента в Comverse, возможно абонент был зарегистрирован там ранее. ";

            description += "\n";

        }
    }

    public void validatePricePlan (SubscriberPricePlanDo subscriberPricePlanDo)
    {
        isSuccess = true;
        if (!subscriberPricePlanDo.isSuccess())
            description = "Ошибка при смене тарифного плана в Ensemble (" + subscriberPricePlanDo.getResultCode() + ": " + subscriberPricePlanDo.getResultDescription() + ").";
        else description = "Смена тарифного плана в Ensemble произведена успешно.";

        description += "\n";
    }

    public void validateService (SubscriberServiceDo subscriberServiceDo)
    {
        isSuccess = true;
        if (!subscriberServiceDo.isSuccess())
            description = (subscriberServiceDo.getInclusionType() == 'A') ? "Ошибка при подключении услуги в Ensemble (" + subscriberServiceDo.getResultCode() + ": " + subscriberServiceDo.getResultDescription() + ")." : "Ошибка при отключении услуги в Ensemble (" + subscriberServiceDo.getResultCode() + ": " + subscriberServiceDo.getResultDescription() + ").";
        else description = (subscriberServiceDo.getInclusionType() == 'A') ? "Подключение услуги в Ensemble произведено успешно." : "Отключение услуги в Ensemble произведено успешно.";

        description += "\n";
    }

    public void validateStatus (SubscriberStatusDo subscriberStatusDo)
    {
        isSuccess = true;
        description = "";
        //Если система не выбрана для изменения статуса, то флаг успеха изменения статуса в ней - true
        if (!subscriberStatusDo.isChangeToEnsSuccess())
            description += "Ошибка при изменении статуса в Ensemble (" + subscriberStatusDo.getResultCode() + ": " + subscriberStatusDo.getResultDescription() + "). ";
        if (!subscriberStatusDo.isChangeToComverseSuccess())
            description += "Ошибка при изменении статуса в Comverse. ";
        if (!subscriberStatusDo.isChangeToEnsSuccess() && !subscriberStatusDo.isChangeToComverseSuccess())
            isSuccess = false;
        if (subscriberStatusDo.isChangeToEnsSuccess() && subscriberStatusDo.isChangeToComverseSuccess())
            description += "Изменение статуса произведено успешно. ";

        description += "\n";
    }

    private boolean isEmpty(String str)
    {
        return (str.equals("") || str.isEmpty());
    }

    private boolean isNaturalNumber(String str)
    {
        try {
            int number = Integer.parseInt(str);
            return (number > 0);
        } catch (NumberFormatException ignored) {}
        return false;
    }

    private boolean isNumber(String str)
    {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ignored) {}
        return false;
    }

    private boolean isDigitSequence(String str)
    {
        return str.matches("[0-9]+");
    }

    private boolean isCapitalLatinLetterSequence(String str)
    {
        return str.matches("[A-Z]+");
    }

    private boolean isCapitalLatinLetterAndDigitSequence(String str)
    {
        return str.matches("[A-Z,0-9,_]+");
    }
}
