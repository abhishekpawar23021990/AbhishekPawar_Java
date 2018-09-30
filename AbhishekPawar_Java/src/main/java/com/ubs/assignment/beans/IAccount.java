package com.ubs.assignment.beans;

import com.ubs.assignment.strategy.UpdateQuantityStrategy;

public class IAccount implements Account,Comparable<Account> {
	
	private UpdateQuantityStrategy strategy ;
	private long accountNumber;
	private long quantity;
	private long delta;
	
		
	public IAccount(long accountNumber, long quantity) {
		super();
		this.accountNumber = accountNumber;
		this.quantity = quantity;
	}
	public IAccount()
	{
		
	}
	public void setQuantityUpdateStrategy(UpdateQuantityStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void updateQuantity (long transactionQuantity)
	{
		quantity = strategy.updateQuantity(quantity, transactionQuantity);
	}

	public UpdateQuantityStrategy getStrategy() {
		return strategy;
	}

	public long getDelta() {
		return delta;
	}

	public void addToDelta(long delta) {
		this.delta = strategy.updateQuantity(this.delta, delta);
	}

	public void setStrategy(UpdateQuantityStrategy strategy) {
		this.strategy = strategy;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + (int) (delta ^ (delta >>> 32));
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
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
		IAccount other = (IAccount) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (delta != other.delta)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	@Override
	public int compareTo(Account o) {
		if(accountNumber == o.getAccountNumber())
			return 0;
		else
			return -1;
	}
	@Override
	public String toString() {
		return "IAccount [strategy=" + strategy + ", accountNumber=" + accountNumber + ", quantity=" + quantity
				+ ", delta=" + delta + "]";
	}
	
	
}
