package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Ryszard Wojtkowiak
 * 
 */

public class Banks {
	
	static class BankFilter implements Filter<List<Transfer>> {

		private List<Transfer> toThisBank;
		private final String id;
		
		public BankFilter(String id) {
			this.id = id;
			toThisBank = new ArrayList<>();
		}
		
		@Override
		public List<Transfer> executeFilter(List<Transfer> toFilter) {
			List<Transfer> result = new ArrayList<>();
			for (Transfer t : toFilter) {
				if (id.startsWith(t.getTo().getId())) {
					result.add(t);
				}
			}
			for (Transfer t : result) {
				toFilter.remove(t);
			}
			toThisBank.addAll(result);
			return toFilter;
		}
		
		public List<Transfer> getFilteredPackage() {
			List<Transfer> result = new ArrayList<>();
			Collections.copy(result, toThisBank);
			return result;
		}
		
		public String getId() {
			return id;
		}
	}
	
	private ArrayList<Bank> banks;
	
	private int prefixLength = 8;

	private int prefixGenerator;
	
	private String prefix;

	private String generateId() throws Exception
	{
		prefixGenerator++;
		
		String fill = prefix;
		int diff = Integer.toString(prefixGenerator).length() - prefixLength;
		if(diff < 0)
		{
			while( diff < 0 )
			{
				diff++;
				fill = fill + '0';
			}
		}

		boolean finishLoop = false;
		while( !finishLoop )
		{
			String tmp = fill + Integer.toString(prefixGenerator);
			finishLoop = true;
			for( int i = 0; i < banks.size(); i++ ){
				if( banks.get(i).getId().equals(tmp) ){
					finishLoop = false;
				}
			}
			
			if(finishLoop == true)
			{
				return tmp;
			}
			
			prefixGenerator++;
			
			fill = prefix;
			diff = Integer.toString(prefixGenerator).length() - prefixLength;
			if(diff < 0)
			{
				while( diff < 0 )
				{
					diff++;
					fill = fill + '0';
				}
			}
			tmp = fill + Integer.toString(prefixGenerator);
			
			tmp = prefix + Integer.toString(prefixGenerator);
		}
		throw new Exception("Unreachable code getId");
	}
	
	@Deprecated
	public void createBank(){
		try {
			@SuppressWarnings("unused")
			Bank bank = new Bank(this.generateId(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public BankFilter createBank(KIR kir){
		try {
			Bank bank = new Bank(this.generateId(), kir);
			banks.add(bank);
			return new BankFilter(bank.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Banks() {
		this.prefix = new String();
		this.banks = new ArrayList<>();
		prefixGenerator = 0;
	}

	public Banks(String prefix) {
		this.prefix = prefix;
		prefixGenerator = 0;
	}

	public Banks(ArrayList<Bank> banks) {
		this.banks = banks;
		prefixGenerator = 0;
	}

	public Banks(String prefix, ArrayList<Bank> banks) {
		this.prefix = prefix;
		this.banks = banks;
		prefixGenerator = 0;
	}
	
	public boolean addBank( Bank bank )
	{
		return banks.add(bank);
	}

	public int getPrefixLength() {
		return prefixLength;
	}

	public void setPrefixLength(int prefixLength) {
		this.prefixLength = prefixLength;
	}

	public List<Bank> getBanks() {
		return banks;
	}
	
	public Bank getBank( String Id )
	{
		for( int i = 0; i < banks.size(); i++ ){
			if( banks.get(i).getId().equals(Id) ){
				return banks.get(i);
			}
		}
		return null;
	}

	public void notifyAboutSessionBegin() {
		for( int i = 0; i < banks.size(); i++ ){
			banks.get(i).notifyAboutSessionBegin();
		}
	}
	
	
}
