package com.globits.da.common;

public class Constant {
    public final class EMPLOYEE{
        public final static String REGEX_CODE="^\\w{6,10}$";
        public final static String REGEX_EMAIL="^\\w+[@](gmail)[.](com)$";
        public final static String REGEX_PHONE_NUMBER="^\\d{11}$";
    }
    public final class EXCUTION_ERROR{
        public final static String SUCCESS = "0";
        public final static String ERROR = "1";
    }
}
