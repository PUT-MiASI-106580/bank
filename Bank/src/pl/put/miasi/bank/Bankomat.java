package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 * 
 */
public class Bankomat implements IWyplata {

	private double cashAmount;

	private Bank bank;

	public Bankomat(double cashAmount, Bank bank) {
		this.cashAmount = cashAmount;
		this.bank = bank;
	}

	@Override
	public boolean Wyplata(double outCash, Konto account, Wlasciciel owner) {
		//Ignore
		return false;
	}

	@Override
	public boolean Wyplata(double outCash, Konto account, String pin) {
		if (cashAmount - outCash >= 0) {
			return bank.Wyplata(outCash, account, pin);
		} else {
			throw new IllegalStateException("Not enough money");
		}
	}

}
