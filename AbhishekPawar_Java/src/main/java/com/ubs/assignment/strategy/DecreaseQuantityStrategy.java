package com.ubs.assignment.strategy;

public class DecreaseQuantityStrategy implements UpdateQuantityStrategy {

	public long updateQuantity(long quantity, long transactionQuantity) {
		return quantity - (transactionQuantity);
	}

}
