package pl.put.miasi.bank.facilities;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.commondata.Owner;
import pl.put.miasi.bank.manage.Bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public class BankOffice implements IAuthorization, IBankOffice {

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
		return bank.Deposit(outCash, account, owner,"Withdraw in bank Office");
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, String pin) {
		return false;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, Owner owner) {
		return bank.Deposit(inCash, account, owner, "Deposit in bank Office");
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, String pin) {
		return false;
	}

	@Override
	public void addBasicDebitTo(IAccount account, Owner owner) {		
		bank.addBasicDebitTo(account, owner);
	}
	
	public boolean makeTransfer(IAccount fromAccount, Owner owner, Bank to, IAccount toAccount,
			double amount, String title)
	{
		/*zwraca tylko czy autoryzacja sie powiodla, reszta zalezy od KIR*/
		return bank.makeTransfer( fromAccount, owner, to, toAccount, amount, title );
	}

}
