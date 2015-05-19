package pl.put.miasi.bank.manage;

import java.util.ArrayList;
import java.util.List;

import pl.put.miasi.bank.accountdata.Accounts;
import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.accountdata.operations.IAuthorization;
import pl.put.miasi.bank.accountdata.operations.Transfer;
import pl.put.miasi.bank.commondata.Owner;

/**
 * 
 * @author Mikolaj Szychowiak
 * 
 */
/*
 * TO DO: zmienic wszystkie operacje parametry wejsciowe konto na string
 * skladajacy sie z bank.ID+konto.ID aktualnie brak powiazan z KIR'em i
 * przelewami w calym projekcie nalezy przetlumaczyc plinglish na english
 */
public class Bank implements IAuthorization, IBank {
	private String Id;

	private Accounts accounts;

	private final KIR kir;

	ArrayList<Transfer> transfersPackage;

	public Bank(String prefix, KIR kir) {
		Id = prefix;
		accounts = new Accounts(prefix);
		this.kir = kir;
		this.transfersPackage = new ArrayList<Transfer>();
	}

	public String getId() {
		return this.Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String CreateAccount(Owner owner) {

		if (accounts == null) {
			accounts = new Accounts(this.Id);
		}
		return accounts.createAccount(owner);
	}

	@Override
	public void addBasicDebitTo(IAccount account, Owner owner) {
		int index = accounts.getAccountIndex(account);
		if (index < 0 || !Authorization(account, owner)) {
			return;
		}

	}

	public boolean RemoveAccount(IAccount account, String pin) {
		return RemoveAccount(account, pin, null);
	}

	public boolean RemoveAccount(IAccount account, Owner owner) {
		return RemoveAccount(account, null, owner);
	}

	private boolean RemoveAccount(IAccount account, String pin, Owner owner) {
		if (pin != null) {
			if (Authorization(account, pin) == false) {
				return false;
			}
		} else if (owner != null) {
			if (Authorization(account, owner) == false) {
				return false;
			}
		} else {
			return false;
		}

		return false;
	}

	@Override
	public boolean Authorization(IAccount account, String pin) {
		if (accounts != null) {
			return accounts.Authorization(account, pin);
		}
		return false;
	}

	@Override
	public boolean Authorization(IAccount account, Owner owner) {
		if (accounts != null) {
			return accounts.Authorization(account, owner);
		}
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, Owner owner, String title) {
		if (accounts != null) {
			if (accounts.Authorization(account, owner) == true) {
				account.withdraw(outCash, title);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, String pin, String title) {
		if (accounts != null) {
			if (accounts.Authorization(account, pin)) {
				account.withdraw(outCash, title);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, Owner owner, String title) {
		if (accounts != null) {
			if (accounts.Authorization(account, owner) == true) {
				account.deposit(inCash, title);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, String pin, String title) {
		if (accounts != null) {
			if (accounts.Authorization(account, pin) == true) {
				account.deposit(inCash, title);
				return true;
			}
		}
		return false;
	}

	public void notifyAboutSessionBegin() {

		kir.addTransfersPackage(transfersPackage);
		transfersPackage.clear();
	}
	
	public Accounts getAccounts(){
		return accounts;		
	}

	public boolean makeTransfer(IAccount fromAccount, Owner owner, Bank to,
			IAccount toAccount, double amount, String title) {
		if (accounts != null) {
			if (accounts.Authorization(fromAccount, owner) == false) {
				return false;
			}
			Transfer transfer = new Transfer(this,to,fromAccount, toAccount, amount, title);
			transfersPackage.add(transfer);
			fromAccount.withdraw(amount, title);
			return true;
		}
		return false;

	}

}