package com.anyacht.app.constants;

import com.anyacht.app.fragment.BookingHistory;
import com.anyacht.app.reponse.BasicResponse;
import com.anyacht.app.reponse.Booking;
import com.anyacht.app.reponse.Destination;
import com.anyacht.app.reponse.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.anyacht.app.constants.Config.BOOKING_HISTORY_URL;
import static com.anyacht.app.constants.Config.BOOKING_REG_URL;
import static com.anyacht.app.constants.Config.CHANGED_PASS;
import static com.anyacht.app.constants.Config.DESTINATION_URL;
import static com.anyacht.app.constants.Config.FORGOT_PASS;
import static com.anyacht.app.constants.Config.LOGIN_URL;
import static com.anyacht.app.constants.Config.SERACHYACHT_URL;
import static com.anyacht.app.constants.Config.SIGNUP_URL;

public interface Api_Interface {

    @FormUrlEncoded
    @POST(LOGIN_URL)
    Call<User> Login(
            @Field("username")String get_username,
            @Field("password")String get_pass);

    @FormUrlEncoded
    @POST(SIGNUP_URL)
    Call<BasicResponse> SignUp(
            @Field("name") String get_userName,
            @Field("email") String get_emailId,
            @Field("phone") String get_mobile,
            @Field("password") String get_userpass,
            @Field("usertype") String get_user_type);

    @FormUrlEncoded
    @POST(FORGOT_PASS)
    Call<BasicResponse> ForgotPass(
            @Field("email")String get_emailID);


    @FormUrlEncoded
    @POST(CHANGED_PASS)
    Call<BasicResponse> ChangePassWord(
            @Field("uid") String mUserid,
            @Field("oldpassword") String get_pass2,
            @Field("newpassword")  String mPass);

    @FormUrlEncoded
    @POST(DESTINATION_URL)
    Call<Destination> getDestination(
            @Field("uid")String mUserid);

    @FormUrlEncoded
    @POST(SERACHYACHT_URL)
    Call<Booking> getBooking(
            @Field("dest_id")String get_desId,
            @Field("type")String get_status,
            @Field("checkin_date")String get_checkin,
            @Field("hours")String get_hours);

    @FormUrlEncoded
    @POST(SERACHYACHT_URL)
    Call<Booking> getOvernightBooking(
            @Field("dest_id")String get_desId,
            @Field("type")String get_status,
            @Field("checkin_date")String get_checkin,
            @Field("checkout_date")String get_checkout);

    @FormUrlEncoded
    @POST(BOOKING_REG_URL)
    Call<Booking> getBookingRegister(
            @Field("id_room") String get_id,
            @Field("name") String mUsername,
            @Field("email")  String mEmailId,
            @Field("mobile")  String mMobile,
            @Field("type") String get_status,
            @Field("checkin") String get_checkin,
            @Field("hours ") String get_hours);

    @FormUrlEncoded
    @POST(BOOKING_HISTORY_URL)
    Call<BookingHistory> getBookingHistory(
            @Field("u_id")String mUserid);
    @FormUrlEncoded
    @POST(BOOKING_REG_URL)
    Call<Booking> getBookingRegister1(
            @Field("id_room") String get_id,
            @Field("name") String mUsername,
            @Field("email") String mEmailId,
            @Field("mobile") String mMobile,
            @Field("type") String get_status,
            @Field("checkin")String get_checkin,
            @Field("checkout")String get_checkout);
}
