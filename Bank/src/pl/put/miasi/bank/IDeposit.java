package pl.put.miasi.bank;

public interface IDeposit{
	public boolean Deposit(double inCash, IAccount account, Owner owner);
	public boolean Deposit(double inCash, IAccount account, String pin);
}
