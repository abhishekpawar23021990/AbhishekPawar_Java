package com.ubs.assignment.beans;

import com.ubs.assignment.strategy.UpdateQuantityStrategy;

public interface Account {

	public void setQuantityUpdateStrategy(UpdateQuantityStrategy strategy);
	public void updateQuantity (long transactionQuantity);
	public void setAccountNumber(long accountNumber);
	public void setQuantity(long quantity);
	public long getAccountNumber();
	public void addToDelta(long transactionQuantity);
	public long getQuantity();
	public long getDelta();
}
