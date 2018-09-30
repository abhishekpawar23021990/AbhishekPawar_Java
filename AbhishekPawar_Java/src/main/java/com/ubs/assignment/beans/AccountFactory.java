package com.ubs.assignment.beans;

import java.util.HashMap;
import java.util.Map;

public class AccountFactory {

	Map<String , Class<? extends Account>> accountTypeMap;

	public AccountFactory()
	{
		accountTypeMap = new HashMap<>();
		accountTypeMap.put("E", EAccount.class);
		accountTypeMap.put("I", IAccount.class);		
	}
	
	public Account getAccount(String accountType)
	{
		try {
			return accountTypeMap.get(accountType).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
				
	}
	
	public static AccountFactory getInstance()
	{
		return new AccountFactory();
	}
}
