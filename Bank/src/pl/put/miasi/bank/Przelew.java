package pl.put.miasi.bank;

/**
 * 
 * @author Mikolaj Szychowiak
 *
 */
public class Przelew {

	private final Bank from;
	private final Bank to;
	private final Konto fromAccount;
	private final Konto toAccount;
	private final double amount;
	private final String title;
	
	public Przelew(Bank from, Bank to, Konto fromAccount, Konto toAccount,
			double amount) {
		this(from, to, fromAccount, toAccount, amount, null);
	}
	
	public Przelew(Bank from, Bank to, Konto fromAccount, Konto toAccount,
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

	public Konto getFromAccount() {
		return fromAccount;
	}

	public Konto getToAccount() {
		return toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public String getTitle() {
		return title;
	}

}
