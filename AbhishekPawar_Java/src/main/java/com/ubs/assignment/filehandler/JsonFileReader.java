package com.ubs.assignment.filehandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubs.assignment.beans.BuyTrade;
import com.ubs.assignment.beans.SellTrade;
import com.ubs.assignment.beans.Trade;

public class JsonFileReader {

 public List<Trade> readFile(String path) throws JsonParseException, IOException
 {
	 
	 	JsonFactory jsonFactory = new JsonFactory();
		JsonParser jp = jsonFactory.createParser(new File(path));
		jp.setCodec(new ObjectMapper());
		JsonNode jsonNode = jp.readValueAsTree();
		List<JsonNode> buyList = new ArrayList<>();
		List<JsonNode> sellList = new ArrayList<>();
		jsonNode.forEach(node -> {if (node.get("TransactionType").asText().equals("B")) buyList.add(node);});
		jsonNode.forEach(node -> {if (node.get("TransactionType").asText().equals("S")) sellList.add(node);});
		List<Trade> tradeList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		buyList.forEach(n -> tradeList.add(mapper.convertValue(n, BuyTrade.class)));
		sellList.forEach(n -> tradeList.add(mapper.convertValue(n, SellTrade.class)));
		return tradeList;
	}
}	
