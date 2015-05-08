package pl.put.miasi.bank.accountdata.operations;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.commondata.Owner;

public interface IDeposit{
	public boolean Deposit(double inCash, IAccount account, Owner owner);
	public boolean Deposit(double inCash, IAccount account, String pin);
}
