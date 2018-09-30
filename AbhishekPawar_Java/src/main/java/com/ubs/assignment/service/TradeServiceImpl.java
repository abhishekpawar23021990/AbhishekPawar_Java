package com.ubs.assignment.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.ubs.assignment.beans.Account;
import com.ubs.assignment.beans.Position;
import com.ubs.assignment.beans.Trade;

public class TradeServiceImpl implements TradeService {

	private Map<String, Position> positionMap;
	
	public TradeServiceImpl()
	{
		positionMap= new LinkedHashMap<>();
	}
	public void applyTrades(Trade trade) {
		Position position = positionMap.get(trade.getInstrumentName());
		Set<Account> setOfAccount = position.getAccount();
		setOfAccount.forEach( account -> {
											account.setQuantityUpdateStrategy(trade.getUpdateStrategy(account)); // as strategy pattern is used to compute quantity , set object of the applicable strategy
											account.updateQuantity(trade.getTransactionQty()); // update quantity
											account.addToDelta(trade.getTransactionQty());
										});
	}

	public Map<String, Position> loadStartPositions(Position position) {
		String key = position.getInstrumentName();
		Position p = null;
		if(positionMap.containsKey(key))
		{
			p = positionMap.get(key);
			p.getAccount().addAll(position.getAccount());
		}
		else
		{
			p= position;
		}
		positionMap.put(key, p);
		return positionMap;
	}

	@Override
	public Map<String, Position> getPositions() {
		return positionMap;
	}

}
