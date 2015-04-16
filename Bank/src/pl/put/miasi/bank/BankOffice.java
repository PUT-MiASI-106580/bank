package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public class BankOffice implements IAuthorization, IBank {

	private double cashAmount;
	
	private Bank bank;
	
	public BankOffice(double cashAmount, Bank bank){
		this.cashAmount = cashAmount;		
		this.bank = bank;
	}

	public boolean Authorization(IAccount account, String pin) {
		
		return false;
	}

	public boolean Authorization(IAccount account, Owner owner) {
		return bank.Authorization(account, owner);
	}

	@Override
	public boolean RemoveAccount(IAccount account, Owner owner) {
		return RemoveAccount(account, owner);
	}

	@Override
	public String getId() {
		return bank.getId();
	}

	@Override
	public boolean CreateAccount(Owner owner) {
		return bank.CreateAccount(owner);
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, Owner owner) {
		return bank.Deposit(outCash, account, owner);
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, String pin) {
		return false;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, Owner owner) {
		return bank.Deposit(inCash, account, owner);
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, String pin) {
		return false;
	}

	@Override
	public void addBasicDebitTo(IAccount account, Owner owner) {		
		bank.addBasicDebitTo(account, owner);
	}

}
