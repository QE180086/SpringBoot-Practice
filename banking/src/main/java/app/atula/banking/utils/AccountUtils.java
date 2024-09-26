package app.atula.banking.utils;

import java.time.Year;

public class AccountUtils {
    public static String ACCOUNT_EXIST_CODE="001";
    public static String ACCOUNT_EXIST_MESSAGE="This is an account CREATED, please create NEW ACCOUNT";
    public static String ACCOUNT_CREATION_SUCCESS="002";
    public static String ACCOUNT_CREATION_MESSAGE="ACCOUNT has been successful";
    public static String ACCOUNT_FINDACCOUNT_SUCCESS="003";
    public static String ACCOUNT_FINDACCOUNT_MESSAGE="FIND ACCOUNT SUCCESSFULL";
    public static String ACCOUNT_FINDACCOUNT_FAIL="004";
    public static String ACCOUNT_FINDACCOUNT_MESSAGEFAIL="FIND ACCOUNT FAIL";
    public static String ACCOUNT_TRANSFER_FAIL="005";
    public static String ACCOUNT_TRANSFER_MESSAGEFAIL="TRANSFER FAIL";
    public static String ACCOUNT_TRANSFER_SUCCESS="006";
    public static String ACCOUNT_TRANSFER_MESSAGESUCCESS="TRANSFER SUCCESS";
    public static String ACCOUNTNUMBER_NOTFOUND_MESSAGEFAIL="NOT FOUND ACCOUNTNUMBER";
    public static String ACCOUNTNUMBER_NOTFOUND_FAIL="007";
    public static String WITHDRAW_MESSAGESUCCESSL="WITHDRAW SUCCESS";
    public static String WITHDRAW_SUCCESSL="008";
    public static String generateAccountNumber(){
        Year currentYear = Year.now();
        String year = String.valueOf(currentYear);
        int min = 10000000;
        int max = 99999999;

        int randNumber = (int) Math.floor(Math.random()*(max - min +1) +min);
        String number = String.valueOf(randNumber);

        return year+number;
    }
}
