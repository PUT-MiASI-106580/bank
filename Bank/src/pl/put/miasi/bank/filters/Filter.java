package pl.put.miasi.bank.filters;

public interface Filter<T> {
	T executeFilter(T toFilter);
}
