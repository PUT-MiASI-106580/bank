package pl.put.miasi.bank.accountdata.operations;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.manage.Bank;

/**
 * 
 * @author Mikolaj Szychowiak
 *
 */
public class Transfer {

	private final Bank from;
	private final Bank to;
	private final IAccount fromAccount;
	private final IAccount toAccount;
	private final double amount;
	private final String title;
	
	public Transfer(Bank from, Bank to, IAccount fromAccount, IAccount toAccount,
			double amount) {
		this(from, to, fromAccount, toAccount, amount, null);
	}
	
	public Transfer(Bank from, Bank to, IAccount fromAccount, IAccount toAccount,
			double amount, String title) {
		this.from = from;
		this.to = to;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.title = title;
	}

	public Bank getFrom() {
		return from;
	}

	public Bank getTo() {
		return to;
	}

	public IAccount getFromAccount() {
		return fromAccount;
	}

	public IAccount getToAccount() {
		return toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public String getTitle() {
		return title;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Transfer)) {
			return false;
		}
		Transfer oth = (Transfer) obj;
		if (oth.amount != this.amount) {
			return false;
		}
		if (!(oth.from.getId().equals(this.from.getId()))) {
			return false;
		}
		if (!(oth.to.getId().equals(this.to.getId()))) {
			return false;
		} 
		return true;
	}

}
