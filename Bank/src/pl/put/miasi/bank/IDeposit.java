package pl.put.miasi.bank;

public interface IDeposit{
	public boolean Deposit(double inCash, Account account, Owner owner);
	public boolean Deposit(double inCash, Account account, String pin);
}
