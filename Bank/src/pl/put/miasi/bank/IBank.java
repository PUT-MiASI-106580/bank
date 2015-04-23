package pl.put.miasi.bank;

public interface IBank extends IAuthorization {
	public boolean RemoveAccount(IAccount account, Owner owner);

	public String getId();
	public boolean CreateAccount(Owner owner);
	
	void addBasicDebitTo(IAccount account, Owner owner);
	
	public boolean Withdraw(double outCash, IAccount account, Owner owner, String title);
	public boolean Withdraw(double outCash, IAccount account, String pin, String title);
	
	public boolean Deposit(double inCash, IAccount account, Owner owner, String title);
	public boolean Deposit(double inCash, IAccount account, String pin, String title);
}
