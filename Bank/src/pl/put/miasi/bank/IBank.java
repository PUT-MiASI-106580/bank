package pl.put.miasi.bank;

public interface IBank extends IAuthorization, IWplata, IWyplata {
	public boolean RemoveKonto(Konto account, Wlasciciel owner);

	public String getId();
	public boolean CreateKonto(Wlasciciel wlasciciel);
}
