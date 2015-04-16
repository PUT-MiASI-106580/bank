package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ryszard Wojtkowiak
 * 
 */

public class Banks {
	
	private ArrayList<Bank> banks;
	
	private int prefixLength = 8;

	private int prefixGenerator;
	
	private String prefix;

	private String getId() throws Exception
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
	
	public void createBank(){
		try {
			Bank bank = new Bank(this.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Banks() {
		this.prefix = new String();
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
	
	
}
