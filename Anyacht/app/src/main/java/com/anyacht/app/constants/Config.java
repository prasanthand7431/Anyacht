package com.anyacht.app.constants;

import static com.anyacht.app.utils.CarwashApplication.isLive;

public class Config {

    public static final String LIVE_URL = "http://anyacht.aravindinfotech.com/";

    public static final String LOCAL_URL = "https://192.168.1.100/carwash/";

    public static final String API_PATH = "mobile/";

    public static final String BASE_URL = (isLive) ? LIVE_URL + API_PATH : API_PATH;

    public static final String LOGIN_URL = "login.php";

    public static final String SIGNUP_URL = "signup.php";

    public static final String FORGOT_PASS = "forgetpassword.php";

    public static final String CHANGED_PASS = "changepassword.php";

    public static final String DESTINATION_URL = "destination.php";

    public static final String SERACHYACHT_URL = "searchyacht.php";

    public static final String BOOKING_REG_URL = "booking.php";

    public static final String BOOKING_HISTORY_URL = "bookinghistory.php";


}
