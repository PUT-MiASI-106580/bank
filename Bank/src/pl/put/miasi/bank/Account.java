package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author Mikolaj Szychowiak
 *
 */
public class Account {


	
	private double balance;
	
	private String Id;
	
	private final ArrayList<Owner> owner;
	
	private final Map<Date, Registry> history;//co zamiast inta?

	private double debet;
	
	private String pin;
	
	private String generatePin(){
		String tmp;
		Random generator = new Random();
		tmp = Integer.toString(generator.nextInt());
		if( tmp.length() > 4 ){
			tmp.substring(0, 4);
		}
		
		while( tmp.length() < 4 )
		{
			tmp.concat("0");
		}
		
		return tmp;
	}
	
	public Account(Owner owner) {
		this(owner, 0);
	}
	
	public Account(Owner owner, double balance) {
		this.pin = generatePin();
		this.owner = new ArrayList<Owner>();			
		this.owner.add(owner);
		this.balance = Math.max(balance, 0);
		history = new HashMap<Date, Registry>();		
	}

	public Account(ArrayList<Owner> owners, double balance) {
		this.pin = generatePin();
		this.owner = owners;			
		this.balance = Math.max(balance, 0);
		history = new HashMap<Date, Registry>();		
	}	
	
	public void deposit(double ammount) throws IllegalArgumentException {
		if (ammount >=0) {
			history.put(new Date(System.currentTimeMillis()), new Registry(Operation.DEPOSIT, ammount));
			balance += ammount;
		} else {
			throw new IllegalArgumentException("Wpłata nie może być ujemna");
		}
	}
	
	public void withdraw(double ammount) {
		if (ammount >=0 && balance - ammount >= -debet) {
			history.put(new Date(System.currentTimeMillis()), new Registry(Operation.WITHDRAW, ammount));
			balance -= ammount;
		} else if (ammount >=0) {
			throw new IllegalStateException("Brak wystarczających środków na koncie");
		} else {
			throw new IllegalArgumentException("Wypłata nie może być ujemna");
		}
	}

	public double getSaldo() {
		return balance;
	}

	public void setSaldo(double balance) {
		this.balance = balance;
	}

	public ArrayList<Owner> getOwner() {
		return owner;
	}

	public Map<Date, Registry> getHistory() {
		return history;
	}

	public double getDebet() {
		return debet;
	}

	public void setDebet(double debet) {
		this.debet = debet;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public boolean Deposit(double Cash){
		this.balance += Cash;
		return true;
	}
	public boolean Withdraw(double Cash){
		if( this.balance - Cash > - this.debet )
		{
			balance -= Cash;
			return true;
		}
		return false;
	}
	
	private List<HistoryEntry> convertHistoryToList() {
		List<HistoryEntry> entries = new ArrayList<>();
		for (Date d : history.keySet()) {
			entries.add(new HistoryEntry(history.get(d), d));
		}
		return entries;
	}
}
