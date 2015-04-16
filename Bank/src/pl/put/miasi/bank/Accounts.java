package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ryszard Wojtkowiak
 * 
 */
public class Accounts implements IAuthorization {

	private ArrayList<Account> accounts;

	private String prefix;
	
	private int prefixLength = 8;

	private int IDGenerator;

	private String getId() throws Exception {
		IDGenerator++;
		
		String fill = prefix;
		int diff = Integer.toString(IDGenerator).length() - prefixLength;
		if(diff < 0)
		{
			while( diff < 0 )
			{
				diff++;
				fill = fill + '0';
			}
		}

		boolean finishLoop = false;
		
		IDGenerator = 0;
		
		while( !finishLoop )
		{
			String tmp = fill + Integer.toString(IDGenerator);
			finishLoop = true;
			for( int i = 0; i < accounts.size(); i++ ){
				if( accounts.get(i).getId().equals(tmp) ){
					finishLoop = false;
				}
			}
			
			if(finishLoop == true)
			{
				return tmp;
			}
			
			IDGenerator++;
			
			fill = prefix;
			diff = Integer.toString(IDGenerator).length() - prefixLength;
			if(diff < 0)
			{
				while( diff < 0 )
				{
					diff++;
					fill = fill + '0';
				}
			}
			tmp = fill + Integer.toString(IDGenerator);
			
			tmp = prefix + Integer.toString(IDGenerator);
		}
		throw new Exception("Unreachable code getId");
	}
	
	public boolean createAccount(Owner owner){
		Account account = new Account(owner);
		try {
			account.setId(this.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getPrefix() throws Exception {
		if (prefix == null) {
			throw new Exception("prefix is null");
		}
		else if( !prefix.matches("[0-9]+")){
			throw new Exception("prefix contains not only numbers");
		}
		else if( prefix.length() != prefixLength )
		{
			throw new Exception("prefix is too long or too short");
		}
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account>accounts) {
		this.accounts = accounts;
	}

	public Accounts() {
		this.prefix = new String();
		this.accounts = new ArrayList<Account>();
		IDGenerator = 0;
	}

	public Accounts(String prefix) {
		this.prefix = prefix;
		IDGenerator = 0;
	}

	public Accounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
		IDGenerator = 0;
	}

	public Accounts(String prefix, ArrayList<Account> accounts) {
		this.prefix = prefix;
		this.accounts = accounts;
		IDGenerator = 0;
	}

	public int getPrefixLength() {
		return prefixLength;
	}

	public void setPrefixLength(int prefixLength) {
		this.prefixLength = prefixLength;
	}
	
	public boolean accountExists( Account account ){
		if( accounts != null ){
			return accounts.contains(account);
		}
		return false;
	}

	@Override
	public boolean Authorization(Account account, String pin) {
		if( accounts != null ){
			if(accounts.contains(account) == true){
				return account.getPin().equals(pin);
			}
		}
		return false;
	}

	@Override
	public boolean Authorization(Account account, Owner owner) {
		if( accounts != null ){
			if(accounts.contains(account) == true){
				return account.getOwner().contains(owner);
			}
		}
		return false;
	}
	
}
