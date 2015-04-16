package pl.put.miasi.bank;

public interface IBank extends IAuthorization, IDeposit, IWithdraw {
	public boolean RemoveAccount(IAccount account, Owner owner);

	public String getId();
	public boolean CreateAccount(Owner owner);
	
	void addBasicDebitTo(IAccount account, Owner owner);
}
