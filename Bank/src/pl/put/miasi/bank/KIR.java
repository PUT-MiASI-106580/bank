package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikołaj Szychowiak
 *
 */
public final class KIR {

	private static KIR kir;
	
	private final Banki bankList;
	
	private Map<Bank, List<Przelew>> packages;		
	
	private KIR() {
		bankList = new Banki();	
		createNewPackages();
	}
	
	private void createNewPackages() {
		packages = new HashMap<>();
		for (Bank b : bankList.getBanki()) {
			packages.put(b, new ArrayList<Przelew>());
		}
		
	}

	public static KIR getKir() {
		if (kir == null) {
			kir = new KIR();
		}
		return kir;
	}
	
	public void addPrzelew(Przelew p) throws IllegalArgumentException{
		Bank to = p.getTo();
		if (!packages.containsKey(to)) {
			throw new IllegalArgumentException("Nie prawidlowy bank");
		}
		
		packages.get(to).add(p);
	}
	
	public List<Przelew> getPrzelewyToBank(Bank to) {
		if (!packages.containsKey(to)) {
			throw new IllegalArgumentException("Nie prawidlowy bank");
		}
		List<Przelew> result = packages.get(to);
		resetPrzelewy(to);
		return result;
	}

	private void resetPrzelewy(Bank to) {
		packages.remove(to);
		packages.put(to, new ArrayList<Przelew>());
		
	}
	
}
