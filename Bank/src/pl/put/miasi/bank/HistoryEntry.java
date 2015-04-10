package pl.put.miasi.bank;

import java.util.Date;

import pl.put.miasi.bank.Konto.Wpis;

public class HistoryEntry {
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
	
	
}
