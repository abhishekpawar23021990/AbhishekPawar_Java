package com.ubs.assignment.service;

import java.util.Map;

import com.ubs.assignment.beans.Position;
import com.ubs.assignment.beans.Trade;

public interface TradeService {
	public void applyTrades(Trade t);
	public Map<String, Position> loadStartPositions(Position position);
	public Map<String, Position> getPositions();
	

}
