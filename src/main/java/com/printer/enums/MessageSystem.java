package com.printer.enums;

public enum MessageSystem {

	SERVER_ERROR, ACTION_CARRIED_OUT, REMOVE_REGISTER, ROLENOTFOUND, USER_CREATE_SUCCESS, LOGOUT_SYSTEM,
	NOT_REFLESH_TOKEN_DATABASE, USERNAME_EXISTS, EMAIL_EXISTS, UNAVAILABLE_PERIOD, ALTER_USER;

	public String getString() {
		switch (this) {
		case SERVER_ERROR:
			return "GLOBAL_WORD.WORD_MSG_SERVER_ERROR";
		case ACTION_CARRIED_OUT:
			return "GLOBAL_WORD.MSG_CREATE";
		case REMOVE_REGISTER:
			return "GLOBAL_WORD.MSG_REMOVE";
		case ROLENOTFOUND:
			return "Error: Role is not found.";
		case USER_CREATE_SUCCESS:
			return "GLOBAL_WORD.MSG_USER_CREATE_SUCCESS";
		case LOGOUT_SYSTEM:
			return "Log out successful!";
		case NOT_REFLESH_TOKEN_DATABASE:
			return "Refresh token is not in database!";
		case USERNAME_EXISTS:
			return "GLOBAL_WORD.MSG_USERNAME_ALREADY";
		case EMAIL_EXISTS:
			return "GLOBAL_WORD.MSG_EMAIL_ALREADY";
		case UNAVAILABLE_PERIOD:
			return "GLOBAL_WORD.UNAVAILABLE PERIOD";
		case ALTER_USER:
			return "GLOBAL_WORD.ALTER_USER";
		default:
			return "Value unavailable";
		}

	}

}