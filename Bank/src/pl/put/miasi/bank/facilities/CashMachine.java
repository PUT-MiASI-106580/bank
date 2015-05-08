package pl.put.miasi.bank.facilities;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.commondata.Owner;
import pl.put.miasi.bank.manage.Bank;

/**
 * @author Miko�aj Ignaszak
 * 
 */
public class CashMachine implements IWithdraw {

	private double cashAmount;

	private Bank bank;

	public CashMachine(double cashAmount, Bank bank) {
		this.cashAmount = cashAmount;
		this.bank = bank;
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, Owner owner) {
		//Ignore
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, String pin) {
		if (cashAmount - outCash >= 0) {
			return bank.Withdraw(outCash, account, pin,"Withdraw in CashMachine");
		} else {
			throw new IllegalStateException("Not enough money");
		}
	}

}
