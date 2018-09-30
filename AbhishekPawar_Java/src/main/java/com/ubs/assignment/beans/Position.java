package com.ubs.assignment.beans;

import java.util.HashSet;
import java.util.Set;

public class Position implements Comparable<Position> {
	private String instrumentName;
	private Set<Account> account;
	
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	
	public Set<Account> getAccount() {
		return account;
	}
	public void setAccount(Set<Account> account) {
		this.account = account;
	}
	
	public boolean addAccount(Account accountType)
	{
		if(null == account)
		{
			account=new HashSet<>();
		}
			return account.add(accountType);
	}
	@Override
	public String toString() {
		return "Position [instrumentName=" + instrumentName + ", account=" + account+" "+ "]";
	}
	@Override
	public int compareTo(Position o) {
		if(this.equals(o))
		return 0;
		else
		return -1;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((instrumentName == null) ? 0 : instrumentName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (instrumentName == null) {
			if (other.instrumentName != null)
				return false;
		} else if (!instrumentName.equals(other.instrumentName))
			return false;
		return true;
	}
	
	
	
	

}
