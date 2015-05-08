package pl.put.miasi.bank.raports;


public interface IRaport<T> {
	T accept(Visitor v);
}
