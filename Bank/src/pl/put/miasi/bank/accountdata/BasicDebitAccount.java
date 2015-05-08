package pl.put.miasi.bank.accountdata;

public class BasicDebitAccount extends AbstractAccountDecorator {

	private final double maxDebit;
	private double currentDebit;

	public BasicDebitAccount(IAccount account, double debit) {
		super(account);
		this.maxDebit = debit;
	}

	@Override
	public void deposit(double amount, String title) {
		if (currentDebit > 0) {
			if (amount < currentDebit) {
				currentDebit -= amount;
				return;
			} else {
				amount -= currentDebit;
				currentDebit = 0;
			}
		}
		account.deposit(amount, title);
	}

	@Override
	public void withdraw(double amount, String title) {
		double ballance = account.getBalance();
		if (amount > ballance) {
			
			double diff = amount - ballance;
			currentDebit += diff;
			amount = maxDebit >= currentDebit ? ballance : ballance
					+ (currentDebit - maxDebit); 
		}
		account.withdraw(amount, title);

	}

}
