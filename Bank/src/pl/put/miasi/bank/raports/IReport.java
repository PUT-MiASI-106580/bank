package pl.put.miasi.bank.raports;


public interface IReport<T> {
	T accept(Visitor v);
}
