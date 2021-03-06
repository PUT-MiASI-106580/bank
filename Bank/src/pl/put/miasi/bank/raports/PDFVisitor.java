package pl.put.miasi.bank.raports;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pl.put.miasi.bank.accountdata.operations.Operation;
import pl.put.miasi.bank.commondata.Registry;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class PDFVisitor implements Visitor {


	@Override
	public FullHistory visit(FullHistory fullHistory) {
		Map<Date, Registry> history = fullHistory.account.getHistory();
		
		fullHistory.history = history.toString();
		fullHistory.object = fullHistory.history;/*Implements change to PDF*/
		
		return fullHistory;
	}

	@Override
	public WithdrawRaport visit(WithdrawRaport withdrawReport) {
		Map<Date, Registry> tmp = new HashMap<Date, Registry>();
		
		for(java.util.Map.Entry<Date, Registry> entry : withdrawReport.account.getHistory().entrySet())
		{
			if( entry.getValue().getOperation().equals(Operation.WITHDRAW) ){
				tmp.put(entry.getKey(), entry.getValue());
			}
		}
		
		withdrawReport.history = tmp.toString();
		withdrawReport.object = withdrawReport.history;/*Implements change to PDF*/
		
		return withdrawReport;
	}

	@Override
	public DepositRaport visit(DepositRaport depositReport) {
		Map<Date, Registry> tmp = new HashMap<Date, Registry>();
		
		for(java.util.Map.Entry<Date, Registry> entry : depositReport.account.getHistory().entrySet())
		{
			if( entry.getValue().getOperation().equals(Operation.DEPOSIT) ){
				tmp.put(entry.getKey(), entry.getValue());
			}
		}
		
		depositReport.history = tmp.toString();
		depositReport.object = depositReport.history;/*Implements change to PDF*/
		
		return depositReport;
	}
	
}
