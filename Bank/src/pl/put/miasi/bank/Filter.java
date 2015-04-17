package pl.put.miasi.bank;

public interface Filter<T> {
	T executeFilter(T toFilter);
}
