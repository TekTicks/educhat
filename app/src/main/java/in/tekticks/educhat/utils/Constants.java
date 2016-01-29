package in.tekticks.educhat.utils;

import java.util.ArrayList;

import in.tekticks.educhat.R;
import in.tekticks.educhat.activity.*;
import in.tekticks.educhat.adapters.*;
import in.tekticks.educhat.asynctask.*;
import in.tekticks.educhat.data.*;

public class Constants {

    public static final String APP_NAME="EduChat";
    public static final String LOG="EduChat";

    //Navigation Menu Items
    public static final String[] group_names = {"Organisations", "Settings", "Help", "About", "Log out"};
    public static final String[] sub_name = {"Contact List", "Chat", "Notification", "Connection Settings"};
    public static final String[] sub_name_text = {"Customize contact list appearance", "ABC", "Notification Settings", "Connection Settings"};
    public static final int[] images = {R.drawable.ic_account_balance_black_18dp, R.drawable.ic_settings_black_18dp, R.drawable.ic_help_black_18dp, R.drawable.ic_info_outline_black_18dp, R.drawable.ic_person_black_18dp};

    //API Links
    public static final String SignInLink="http://chat.educhatapp.com:9090/index.jsp";
    public static final String MobileVerificationStepOneLink="http://engine.dial2verify.in/Integ/API.dvf";
    public static final String MobileVerificationStepTwoLink="http://engine.dial2verify.in/Integ/UserLayer/DataFeed_APIV2.dvf";
    public static final String SignUpLink="http://chat.educhatapp.com:9090/plugins/restapi/v1/users";
    public static final String UpdateUserDetailsLink="http://chat.educhatapp.com:9090/plugins/restapi/v1/users";
    public static final String SearchUSerDetailsLink="http://chat.educhatapp.com:9090/plugins/restapi/v1/users";
    public static final String AddFBFriendsLink="http://api.educhatapp.com/index.php/FB/addfb";
    public static final String UploadFileLink="http://api.educhatapp.com/index.php/media";
    public static final String RetrieveFileLink="http://api.educhatapp.com/index.php/media/";
    public static final String AddingReviewLink="http://api.educhatapp.com/index.php/review";
    public static final String RetrivingReviewLink="http://api.educhatapp.com/index.php/review";
    public static final String GettingAllRoomsLink="http://api.educhatapp.com/index.php/rooms";

    //REST API Headers
    public static final String AUTHORIZATION_KEY="YKh211D4ivhwvsJd";
    public static final String CONTENT_TYPE="application/json";

    //REST API Parameters
    public static final String NOTIFY="http://engine.dial2verify.in/Integ/CatchAll.dvf";
    public static final String e_notify="tattle@geekshastra.com";
    public static final String passkey="RA$64905655-BE96-11E4-B16C-94DE80A5CEAB";

    //Log Tags for AsyncTask
    public static final String SignInAsyncTask="SIGN IN USER ASYNC TASK";

    //Response Code
    public static final int STATUS_201=201;

}
