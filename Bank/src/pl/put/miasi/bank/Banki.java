package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ryszard Wojtkowiak
 * 
 */

public class Banki {
	
	private ArrayList<Bank> banki;
	
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
			for( int i = 0; i < banki.size(); i++ ){
				if( banki.get(i).getId().equals(tmp) ){
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

	public Banki() {
		this.prefix = new String();
		prefixGenerator = 0;
	}

	public Banki(String prefix) {
		this.prefix = prefix;
		prefixGenerator = 0;
	}

	public Banki(ArrayList<Bank> banki) {
		this.banki = banki;
		prefixGenerator = 0;
	}

	public Banki(String prefix, ArrayList<Bank> banki) {
		this.prefix = prefix;
		this.banki = banki;
		prefixGenerator = 0;
	}
	
	public boolean addBank( Bank bank )
	{
		return banki.add(bank);
	}

	public int getPrefixLength() {
		return prefixLength;
	}

	public void setPrefixLength(int prefixLength) {
		this.prefixLength = prefixLength;
	}

	public List<Bank> getBanki() {
		return banki;
	}
	
	
}
