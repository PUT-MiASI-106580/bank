package pl.put.miasi.bank;

public interface IWithdraw {
	public boolean Withdraw(double outCash, IAccount account, Owner owner);

	public boolean Withdraw(double outCash, IAccount account, String pin);
}
