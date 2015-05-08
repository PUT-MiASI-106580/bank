package pl.put.miasi.bank.accountdata;

import pl.put.miasi.bank.utils.CurrenciesExchangeRates;

public class OperationsInForeignCurrency extends AbstractAccountDecorator {

	private double rate;
	private final String currency;

	public OperationsInForeignCurrency(IAccount account, String curr) {
		super(account);
		this.currency = curr;
		if(CurrenciesExchangeRates.rates.containsKey(this.currency))
			this.rate = CurrenciesExchangeRates.rates.get(this.currency);
		else
			this.rate = 1;
	}

	public String getCurrency()
	{
		return this.currency;
	}
	
	@Override
	public void deposit(double amount, String title) {
		double properAmount = amount * this.rate;
		account.deposit(properAmount, title);

	}

	@Override
	public void withdraw(double amount, String title) {
		double properAmount = amount / this.rate;
		account.withdraw(properAmount, title);
	}

}
