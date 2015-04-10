package pl.put.miasi.bank;

public interface IWyplata {
	public boolean Wyplata(double outCash, Konto account, Wlasciciel owner);

	public boolean Wyplata(double outCash, Konto account, String pin);
}
