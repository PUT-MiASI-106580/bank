package pl.put.miasi.bank;

public interface Visitor {
	HistoryEntry visit(HistoryEntry entry);
}
