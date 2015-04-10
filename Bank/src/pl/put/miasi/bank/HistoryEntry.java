package pl.put.miasi.bank;

import java.util.Date;

import pl.put.miasi.bank.Konto.Wpis;

public class HistoryEntry implements ReportElement<HistoryEntry>{
	private final Wpis wpis;
	private final Date date;
	public HistoryEntry(Wpis wpis, Date date) {
		super();
		this.wpis = wpis;
		this.date = date;
	}
	public Wpis getWpis() {
		return wpis;
	}
	public Date getDate() {
		return date;
	}
	
	public String doReport() {
		StringBuilder b = new StringBuilder();
		b.append(RaportUtils.ACCOUNT_TAG_BEGIN).append(wpis.getOperacja()).
		return null;		
	}
	@Override
	public HistoryEntry accept(Report report) {		
		return null;
	}
	
}
