package pl.put.miasi.bank.utils;

import pl.put.miasi.bank.accountdata.Accounts;
import pl.put.miasi.bank.manage.Banks;

public class Utils {

	public static final double BASIC_DEBIT = 1000;
	public static final int BANK = 0;
	public static final int ACCOUNT = 1;
	
	private static boolean validateId(String inputId) {
		boolean valid = inputId.length() == Banks.PREFIX_LENGTH + Accounts.PREFIX_LENGTH;
		
		//do your validation;
		
		return valid;
		
	}

	public static String[] parseIds(String inputId) {
		if (!validateId(inputId)) {
			return null;
		}
		return new String[] { getBankId(inputId), getAccountId(inputId) };
	}
	
	public static String getBankId(String inputId) {
		if (!validateId(inputId)) {
			return null;
		}
		return inputId.substring(0, Banks.PREFIX_LENGTH);
	}
		
	public static String getAccountId(String inputId) {
		if (!validateId(inputId)) {
			return null;
		}
		String res = inputId.substring(Banks.PREFIX_LENGTH, Banks.PREFIX_LENGTH + Accounts.PREFIX_LENGTH); 
		return res;
	}
	
}
