package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 * 
 */
public class CashMachine implements IWithdraw {

	private double cashAmount;

	private Bank bank;

	public CashMachine(double cashAmount, Bank bank) {
		this.cashAmount = cashAmount;
		this.bank = bank;
	}

	@Override
	public boolean Withdraw(double outCash, Account account, Owner owner) {
		//Ignore
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, Account account, String pin) {
		if (cashAmount - outCash >= 0) {
			return bank.Withdraw(outCash, account, pin);
		} else {
			throw new IllegalStateException("Not enough money");
		}
	}

}