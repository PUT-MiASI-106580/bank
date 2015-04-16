package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public class CashDepositMachine implements IDeposit {

	private double cashAmount;
	
	private Bank bank;
	
	public CashDepositMachine(double cashAmount, Bank bank){
		this.cashAmount = cashAmount;		
		this.bank = bank;
	}

	@Override
	public boolean Deposit(double inCash, Account account, Owner owner) {
		return false;
	}

	@Override
	public boolean Deposit(double inCash, Account account, String pin) {
		return bank.Deposit(inCash, account, pin);
	}

}
