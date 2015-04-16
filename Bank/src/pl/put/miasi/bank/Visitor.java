package pl.put.miasi.bank;

public interface Visitor {

	FullHistory accept(FullHistory fullHistory);
	
}
