package pl.put.miasi.bank;

import java.util.Date;

public class HistoryEntry implements ReportElement<HistoryEntry>{
	private final Registry registry;
	private final Date date;
	public HistoryEntry(Registry registry, Date date) {
		super();
		this.registry = registry;
		this.date = date;
	}
	public Registry getWpis() {
		return registry;
	}
	public Date getDate() {
		return date;
	}
	
	public String doReport() {
		StringBuilder b = new StringBuilder();
		b.append(RaportUtils.ACCOUNT_TAG_BEGIN).append(registry.getOperation());
		return null;		
	}
	@Override
	public HistoryEntry accept(IReport report) {		
		return null;
	}
	
}
