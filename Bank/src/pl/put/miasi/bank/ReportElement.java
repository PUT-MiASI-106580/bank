package pl.put.miasi.bank;

public interface ReportElement<T> {
	T accept(Report report)
}
