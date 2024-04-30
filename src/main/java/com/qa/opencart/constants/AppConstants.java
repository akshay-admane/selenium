package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AppConstants {
	
	//Timeouts
	public static final int DEFAULT_SHORT_TIME_OUT= 5;
	public static final int DEFAULT_MEDIUM_TIME_OUT= 10;
	public static final int DEFAULT_LONG_TIME_OUT= 15;
	
	public static final String LOGIN_PAGE_TITLE_VALUE= "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE= "?route=account/login";
	
	public static final String ACCOUNTS_PAGE_TITLE_VALUE= "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION_VALUE= "oute=account/account";
	public static final int ACCOUNTS_PAGE_HEADER_COUNT = 4;
	public static final int ACCOUNTS_PAGE_OPTIONS_COUNT = 13;

	public static final List<String> EXPECTED_ACCOUNTS_PAGE_HEADER_LIST = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final List<String> EXPECTED_ACCOUNTS_PAGE_OPTIONS_LIST = Arrays.asList("Login, Register, Forgotten Password, My Account, Address Book, Wish List, Order History, Downloads, Recurring payments, Reward Points, Returns, Transactions, Newsletter");

	
	public static final int LOGIN_PAGE_RIGHT_COLUMN_OPTIONS_SIZE = 13;
	public static final List<String> EXPECTED_LOGIN_PAGE_RIGHT_COLUMN_OPTIONS_LIST = Arrays.asList("Login, Register, Forgotten Password, My Account, Address Book, Wish List, Order History, Downloads, Recurring payments, Reward Points, Returns, Transactions, Newsletter");
	
	
	public static final String USER_REG_SUCCESS_MESSAGE= "Your Account Has Been Created";
	
	//********************************************Sheet Names**************************************************//
	public static final String USER_REG_SHEET_NAME= "register";

	
	
	
	
}
