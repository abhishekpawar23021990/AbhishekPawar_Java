package com.ubs.assignment.test;


import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.ubs.assignment.beans.Account;
import com.ubs.assignment.beans.BuyTrade;
import com.ubs.assignment.beans.EAccount;
import com.ubs.assignment.beans.IAccount;
import com.ubs.assignment.beans.Position;
import com.ubs.assignment.beans.Trade;
import com.ubs.assignment.filehandler.JsonFileReader;
import com.ubs.assignment.filehandler.TextFileReader;

public class FileHandlerTest {
	
	@Test
	public void testStartOfDayPositionsFileReader()
	{
		try {
			List<Position> positionList = TextFileReader.readFile("src/test/resources/Input_StartOfDay_Positions_test.txt", true);
			Position pEaccount = new Position();
			pEaccount.setInstrumentName("IBM");
			Account eaccount = new EAccount(101,100000);
			pEaccount.addAccount(eaccount);
			Position pIaccount = new Position();
			pIaccount.setInstrumentName("IBM");
			Account iaccount = new IAccount(201,-100000);
			pIaccount.addAccount(iaccount);
			List<Position> expectedList = new ArrayList<>();
			expectedList.add(pEaccount);
			expectedList.add(pEaccount);
		
			assertTrue(positionList.containsAll(expectedList));
		} catch (FileNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
				
	}

	@Test
	public void testInputTransactionsFileReader()
	{
		
			try {
				List<Trade> listOfTrades = new JsonFileReader().readFile("src/test/resources/1537277231233_Input_Transactions_test.txt");
				BuyTrade t = new BuyTrade();
				t.setInstrumentName("IBM");
				t.setTransactionId(1);
				t.setTransactionQty(1000);
				t.setTransactionType("B");
				assertThat(listOfTrades, CoreMatchers.hasItem(t));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
