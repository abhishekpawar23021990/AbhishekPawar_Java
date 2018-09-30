package com.ubs.assignment.filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.ubs.assignment.beans.Account;
import com.ubs.assignment.beans.AccountFactory;
import com.ubs.assignment.beans.Position;

public class TextFileReader {

	public static List<Position> readFile(String pathname, boolean ignoreFirstLine)
			throws FileNotFoundException, InstantiationException, IllegalAccessException {
		List<Position> positionList = new ArrayList<>();
		Scanner sc = new Scanner(new FileInputStream(new File(pathname)));
		// to skip firstLine
		if (ignoreFirstLine && sc.hasNextLine()) {
			sc.nextLine();
		}

		while (sc.hasNextLine()) {
			String nextLine = sc.nextLine();
			String[] split = nextLine.split(",");
			Position p = new Position();
			p.setInstrumentName(split[0]);
			long accountNumber = new Long(split[1]).longValue();
			String accountType = split[2];
			AccountFactory accountFactory = AccountFactory.getInstance();
			Account account = accountFactory.getAccount(accountType);
			account.setAccountNumber(accountNumber);
			long quantity = new Long(split[3]).longValue();
			account.setQuantity(quantity);
			p.addAccount(account);
			positionList.add(p);

		}
		sc.close();
		return positionList;
	}
}
