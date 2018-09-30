package com.ubs.assignment;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ubs.assignment.beans.EAccount;
import com.ubs.assignment.beans.IAccount;
import com.ubs.assignment.beans.Position;
import com.ubs.assignment.beans.Trade;
import com.ubs.assignment.filehandler.EodCsvFileWriter;
import com.ubs.assignment.filehandler.JsonFileReader;
import com.ubs.assignment.filehandler.TextFileReader;
import com.ubs.assignment.service.TradeService;
import com.ubs.assignment.service.TradeServiceImpl;

public class Main {
	
	private static TradeService service = new TradeServiceImpl();
	private final static String START_OF_DAY_FILE = "src/main/resources/Input_StartOfDay_Positions.txt";
	private final static String INPUT_TRANSACTIONS_FILE = "src/main/resources/1537277231233_Input_Transactions.txt";
	private final static String OUTPUT_EOD_FILE = "src/main/resources/Expected_EndOfDay_Positions.txt";
	
	public static void main(String[] args) {
		try {
			List<Position> positionList = TextFileReader.readFile(START_OF_DAY_FILE, true);
			positionList.forEach(position -> service.loadStartPositions(position));
			List<Trade> listOfTrades = new JsonFileReader().readFile(INPUT_TRANSACTIONS_FILE);
			listOfTrades.forEach(trade -> service.applyTrades(trade));
			
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		Map<String, Position> positions = service.getPositions();
		writeEodFile(positions.values().stream().collect(Collectors.toList()));
	}


    public static void writeEodFile(List<Position> position)
    {
        FileWriter writer;
		try {
			writer = new FileWriter(OUTPUT_EOD_FILE);
			//for header
			EodCsvFileWriter.writeLine(writer, Arrays.asList("Instrument", "Account", "AccountType", "Quantity", "Delta"));
			
			for (Position p : position) {
				
				List<String> list = new ArrayList<>();
				p.getAccount().forEach( a -> {
					list.add(p.getInstrumentName());
					list.add(String.valueOf(a.getAccountNumber()));
					
					if(a instanceof IAccount)
					{
						list.add("I");
					}
					else if(a instanceof EAccount)
					{
						list.add("E");
					}
					list.add(String.valueOf(a.getQuantity()));
					list.add(String.valueOf(a.getDelta()));
					try {
						EodCsvFileWriter.writeLine(writer, list);
						list.clear();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

   

    }

}


