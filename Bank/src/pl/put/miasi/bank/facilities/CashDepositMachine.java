package pl.put.miasi.bank.facilities;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.accountdata.operations.IDeposit;
import pl.put.miasi.bank.commondata.Owner;
import pl.put.miasi.bank.manage.Bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public class CashDepositMachine implements IDeposit {

	private double cashAmount;
	
	private Bank bank;
	
	public CashDepositMachine(double cashAmount, Bank bank){
		this.cashAmount = cashAmount;		
		this.bank = bank;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, Owner owner) {
		return false;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, String pin) {
		return bank.Deposit(inCash, account, pin, "Deposit in CashDepositMachine");
	}

}
