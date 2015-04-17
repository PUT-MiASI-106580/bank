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
	
	private final Banks bankList;
	
	private Map<Bank, List<Transfer>> packages;		
	
	private KIR() {
		bankList = new Banks();	
		createNewPackages();
	}
	
	private void createNewPackages() {
		packages = new HashMap<>();
		for (Bank b : bankList.getBanks()) {
			packages.put(b, new ArrayList<Transfer>());
		}
		
	}

	public static KIR getKir() {
		if (kir == null) {
			kir = new KIR();
		}
		return kir;
	}
	
	public void addTransfer(Transfer p) throws IllegalArgumentException{
		Bank to = p.getTo();
		if (!packages.containsKey(to)) {
			throw new IllegalArgumentException("Nie prawidlowy bank");
		}
		
		packages.get(to).add(p);
	}
	
	public List<Transfer> getTransferyToBank(Bank to) {
		if (!packages.containsKey(to)) {
			throw new IllegalArgumentException("Nie prawidlowy bank");
		}
		List<Transfer> result = packages.get(to);
		resetTransfery(to);
		return result;
	}

	private void resetTransfery(Bank to) {
		packages.remove(to);
		packages.put(to, new ArrayList<Transfer>());
		
	}
	
	public Bank createBank() {
		bankList.createBank(this);
		List<Bank> banks = bankList.getBanks(); 
		return banks.get(banks.size());
	}
	
}
