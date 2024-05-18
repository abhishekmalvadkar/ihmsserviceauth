package com.amalvadkar.ihms.auth.utils;

public class AppConstants {

    public static final String ERROR_MSG_INVALID_AUTH_PROVIDER = "Invalid auth provider";
    public static final String AUTH_PROVIDER_GOOGLE = "google";
    public static final String ERROR_MSG_INVALID_TOKEN = "Invalid token";
    public static final String ERROR_MSG_EMAIL_ID_MISSING_IN_SYSTEM = "Your email id is not in the system, please contact to admin";
    public static final String LOGGED_IN_SUCCESSFULLY_RESPONSE_MSG = "LoggedIn Successfully";
    public static final String RESPONSE_HEADER_AUTHORIZATION = "Authorization";

    public static final String SOMETHING_WENT_WRONG_ERROR_MESSAGE = "Something went wrong, please contact to admin..";
    public static final String REQUEST_HEADER_AUTH_PROVIDER = "Auth-Provider";
    public static final String REQUEST_HEADER_TOKEN = "Token";
    public static final String REQUEST_HEADER_DEVICE = "Device";

    private AppConstants(){
        throw new IllegalStateException("You can't create object for AppConstants utility class");
    }
}
