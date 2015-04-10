package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public class Wplatomat implements IWplata {

	private double cashAmount;
	
	private Bank bank;
	
	public Wplatomat(double cashAmount, Bank bank){
		this.cashAmount = cashAmount;		
		this.bank = bank;
	}

	@Override
	public boolean Wplata(double inCash, Konto account, Wlasciciel owner) {
		return false;
	}

	@Override
	public boolean Wplata(double inCash, Konto account, String pin) {
		return bank.Wplata(inCash, account, pin);
	}

}
