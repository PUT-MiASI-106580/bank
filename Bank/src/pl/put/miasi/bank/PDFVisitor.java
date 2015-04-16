package pl.put.miasi.bank;

import java.util.Date;
import java.util.Map;

public class PDFVisitor implements Visitor {

	@Override
	public FullHistory accept(FullHistory fullHistory) {
		Map<Date, Registry> history = fullHistory.account.getHistory();
		
		fullHistory.history = "Fully Generated History....";
		fullHistory.object = fullHistory.history;/*Implements change to PDF*/
		
		return fullHistory;
	}
	
}
