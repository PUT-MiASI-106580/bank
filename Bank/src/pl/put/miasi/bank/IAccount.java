package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public interface IAccount {
	void deposit(double amount);
	
	void withdraw(double amount);

	String getPin();

	ArrayList<Owner> getOwner();

	String getId();
	
	double getBalance();

	public abstract Map<Date, Registry> getHistory();
}
