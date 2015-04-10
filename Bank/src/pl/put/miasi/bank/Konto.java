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
public class Konto {

	protected static class Wpis {
		private final Operacja operacja;
		private final List<Object> params;		
		public Wpis(Operacja operacja, Object... params) {
			this.operacja = operacja;
			this.params = new ArrayList<Object>(Arrays.asList(params));
		}
		public Operacja getOperacja() {
			return operacja;
		}
		public List<Object> getParams() {
			return params;
		}
		
	}
	
	private double saldo;
	
	private String Id;
	
	private final ArrayList<Wlasciciel> wlasciciel;
	
	private final Map<Date, Wpis> historia;//co zamiast inta?

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
	
	public Konto(Wlasciciel wlasciciel) {
		this(wlasciciel, 0);
	}
	
	public Konto(Wlasciciel wlasciciel, double saldo) {
		this.pin = generatePin();
		this.wlasciciel = new ArrayList<Wlasciciel>();			
		this.wlasciciel.add(wlasciciel);
		this.saldo = Math.max(saldo, 0);
		historia = new HashMap<Date, Wpis>();		
	}

	public Konto(ArrayList<Wlasciciel> wlasciciele, double saldo) {
		this.pin = generatePin();
		this.wlasciciel = wlasciciele;			
		this.saldo = Math.max(saldo, 0);
		historia = new HashMap<Date, Wpis>();		
	}	
	
	public void wplata(double kwota) throws IllegalArgumentException {
		if (kwota >=0) {
			historia.put(new Date(System.currentTimeMillis()), new Wpis(Operacja.WPLATA, kwota));
			saldo += kwota;
		} else {
			throw new IllegalArgumentException("Wpłata nie może być ujemna");
		}
	}
	
	public void wyplata(double kwota) {
		if (kwota >=0 && saldo - kwota >= -debet) {
			historia.put(new Date(System.currentTimeMillis()), new Wpis(Operacja.WYPLATA, kwota));
			saldo -= kwota;
		} else if (kwota >=0) {
			throw new IllegalStateException("Brak wystarczających środków na koncie");
		} else {
			throw new IllegalArgumentException("Wypłata nie może być ujemna");
		}
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Wlasciciel> getWlasciciel() {
		return wlasciciel;
	}

	public Map<Date, Wpis> getHistoria() {
		return historia;
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
	
	public boolean Wplata(double Cash){
		this.saldo += Cash;
		return true;
	}
	public boolean Wyplata(double Cash){
		if( this.saldo - Cash > - this.debet )
		{
			saldo -= Cash;
			return true;
		}
		return false;
	}
}
