package pl.put.miasi.bank;

/**
 * 
 * @author Mikolaj Szychowiak
 *
 */
public class Transfer {

	private final Bank from;
	private final Bank to;
	private final Account fromAccount;
	private final Account toAccount;
	private final double amount;
	private final String title;
	
	public Transfer(Bank from, Bank to, Account fromAccount, Account toAccount,
			double amount) {
		this(from, to, fromAccount, toAccount, amount, null);
	}
	
	public Transfer(Bank from, Bank to, Account fromAccount, Account toAccount,
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

	public Account getFromAccount() {
		return fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public String getTitle() {
		return title;
	}

}
