package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public class OddzialBanku implements IAuthorization, IBank {

	private double cashAmount;
	
	private Bank bank;
	
	public OddzialBanku(double cashAmount, Bank bank){
		this.cashAmount = cashAmount;		
		this.bank = bank;
	}

	public boolean Authorization(Konto account, String pin) {
		
		return false;
	}

	public boolean Authorization(Konto account, Wlasciciel owner) {
		return bank.Authorization(account, owner);
	}

	@Override
	public boolean RemoveKonto(Konto account, Wlasciciel owner) {
		return RemoveKonto(account, owner);
	}

	@Override
	public String getId() {
		return bank.getId();
	}

	@Override
	public boolean CreateKonto(Wlasciciel wlasciciel) {
		return bank.CreateKonto(wlasciciel);
	}

	@Override
	public boolean Wyplata(double outCash, Konto account, Wlasciciel owner) {
		return bank.Wplata(outCash, account, owner);
	}

	@Override
	public boolean Wyplata(double outCash, Konto account, String pin) {
		return false;
	}

	@Override
	public boolean Wplata(double inCash, Konto account, Wlasciciel owner) {
		return bank.Wplata(inCash, account, owner);
	}

	@Override
	public boolean Wplata(double inCash, Konto account, String pin) {
		return false;
	}	
}
