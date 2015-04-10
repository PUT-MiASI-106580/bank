package pl.put.miasi.bank;

public interface IWplata {
	public boolean Wplata(double inCash, Konto account, Wlasciciel owner);
	public boolean Wplata(double inCash, Konto account, String pin);
}
