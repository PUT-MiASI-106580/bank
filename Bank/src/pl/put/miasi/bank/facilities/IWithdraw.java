package pl.put.miasi.bank.facilities;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.commondata.Owner;

public interface IWithdraw {
	public boolean Withdraw(double outCash, IAccount account, Owner owner);

	public boolean Withdraw(double outCash, IAccount account, String pin);
}
