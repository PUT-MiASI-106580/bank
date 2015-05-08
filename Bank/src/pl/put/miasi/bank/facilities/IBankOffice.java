package pl.put.miasi.bank.facilities;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.commondata.Owner;

public interface IBankOffice extends IAuthorization {
	
	public boolean RemoveAccount(IAccount account, Owner owner);

	public String getId();
	public boolean CreateAccount(Owner owner);
	
	void addBasicDebitTo(IAccount account, Owner owner);
	
	public boolean Withdraw(double outCash, IAccount account, Owner owner);
	public boolean Withdraw(double outCash, IAccount account, String pin);
	
	public boolean Deposit(double inCash, IAccount account, Owner owner);
	public boolean Deposit(double inCash, IAccount account, String pin);
}
