package pl.put.miasi.bank;

public interface IWithdraw {
	public boolean Withdraw(double outCash, Account account, Owner owner);

	public boolean Withdraw(double outCash, Account account, String pin);
}