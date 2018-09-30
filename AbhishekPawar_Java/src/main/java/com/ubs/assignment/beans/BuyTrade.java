package com.ubs.assignment.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubs.assignment.strategy.DecreaseQuantityStrategy;
import com.ubs.assignment.strategy.IncreaseQuantityStrategy;
import com.ubs.assignment.strategy.UpdateQuantityStrategy;

public class BuyTrade implements Trade {
	
	@JsonProperty("TransactionId")
	private long transactionId;
	@JsonProperty("Instrument")
	String instrumentName;
	@JsonProperty("TransactionType")
	String transactionType;
	@JsonProperty("TransactionQuantity")
	long transactionQty;
	
	
	
	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public BuyTrade(String instrumentName, long transactionQty) {
		super();
		this.instrumentName = instrumentName;
		this.transactionQty = transactionQty;
	}
	
	public BuyTrade()
	{
		
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public long getTransactionQty() {
		return transactionQty;
	}

	public void setTransactionQty(long transactionQty) {
		this.transactionQty = transactionQty;
	}

	
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public UpdateQuantityStrategy getUpdateStrategy(Account account) {
		if(account instanceof EAccount)
		{
			return new IncreaseQuantityStrategy();
		}
		else if (account instanceof IAccount)
		{
			return new DecreaseQuantityStrategy();
		}
		else
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instrumentName == null) ? 0 : instrumentName.hashCode());
		result = prime * result + (int) (transactionId ^ (transactionId >>> 32));
		result = prime * result + (int) (transactionQty ^ (transactionQty >>> 32));
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		BuyTrade other = (BuyTrade) obj;
		if (instrumentName == null) {
			if (other.instrumentName != null)
				return false;
		} else if (!instrumentName.equals(other.instrumentName))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionQty != other.transactionQty)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BuyTrade [transactionId=" + transactionId + ", instrumentName=" + instrumentName + ", transactionType="
				+ transactionType + ", transactionQty=" + transactionQty + "]";
	}

	
	
	

}
