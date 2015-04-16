package pl.put.miasi.bank;

public interface IReport<T> {
	T accept(Visitor v);
}
