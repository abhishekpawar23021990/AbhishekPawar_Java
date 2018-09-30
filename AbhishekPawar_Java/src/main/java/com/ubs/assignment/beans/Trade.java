package com.ubs.assignment.beans;

import com.ubs.assignment.strategy.UpdateQuantityStrategy;

public interface Trade {
	String getInstrumentName();
	long getTransactionQty();
	UpdateQuantityStrategy getUpdateStrategy(Account account);

}
