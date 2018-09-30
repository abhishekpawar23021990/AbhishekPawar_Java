package com.ubs.assignment.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ubs.assignment.beans.Account;
import com.ubs.assignment.beans.EAccount;
import com.ubs.assignment.beans.IAccount;
import com.ubs.assignment.beans.Position;
import com.ubs.assignment.beans.Trade;
import com.ubs.assignment.filehandler.JsonFileReader;
import com.ubs.assignment.filehandler.TextFileReader;
import com.ubs.assignment.service.TradeService;
import com.ubs.assignment.service.TradeServiceImpl;
import com.ubs.assignment.strategy.DecreaseQuantityStrategy;
import com.ubs.assignment.strategy.IncreaseQuantityStrategy;

public class TradeServiceImplTest {
	List<Position> positionList = null;
	List<Trade> listOfTrades = null;
	TradeService service = null;
	
	@Before
	public void readStartOfDayPositions()
	{
		
		try {
			positionList = TextFileReader.readFile("src/test/resources/Input_StartOfDay_Positions_test.txt", true);
			listOfTrades = new JsonFileReader().readFile("src/test/resources/1537277231233_Input_Transactions_test.txt");
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoadStartPositions()
	{
		service = new TradeServiceImpl();
		positionList.forEach(position -> service.loadStartPositions(position));
		Map<String, Position> positions= service.getPositions();
		Position position = positions.get("IBM");
		
		Position p = new Position();
		p.setInstrumentName("IBM");
		Account eaccount = new EAccount(101,100000);
		p.addAccount(eaccount);
		Account iaccount = new IAccount(201,-100000);
		p.addAccount(iaccount);
		
		assertEquals(position, p);
	}
	
	@Test
	public void testApplyTrades()
	{
		service = new TradeServiceImpl();
		positionList.forEach(position -> service.loadStartPositions(position));
		Map<String, Position> before = service.getPositions();
		Position expected = before.get("IBM");
		expected.getAccount().forEach(a-> {
			if(a instanceof IAccount)
			{
				a.setQuantityUpdateStrategy(new DecreaseQuantityStrategy());
				a.updateQuantity(1000);
				a.addToDelta(1000);
			}
			else if(a instanceof IAccount)
			{
				a.setQuantityUpdateStrategy(new IncreaseQuantityStrategy());
				a.updateQuantity(1000);
				a.addToDelta(1000);
			}
		});
		
		listOfTrades.forEach(t-> service.applyTrades(t));
		Map<String, Position> after= service.getPositions();
		Position actual = after.get("IBM");
		
		assertEquals(actual, expected);
	}
}
