package pl.put.miasi.bank.accountdata;

import java.util.ArrayList;
import java.util.List;

import pl.put.miasi.bank.accountdata.operations.IAuthorization;
import pl.put.miasi.bank.commondata.Owner;

/**
 * 
 * @author Ryszard Wojtkowiak
 * 
 */
public class Accounts implements IAuthorization {

	private ArrayList<IAccount> accounts;

	private String prefix;
	
	private static final int PREFIX_LENGTH = 8;

	private int IDGenerator;

	private String getId() {
		IDGenerator++;
		
		String fill = prefix;
		int diff = Integer.toString(IDGenerator).length() - PREFIX_LENGTH;
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
			diff = Integer.toString(IDGenerator).length() - PREFIX_LENGTH;
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
		assert false : "Unreachable code getId";
		return null;
	}
	
	public String createAccount(Owner owner){
		Account account = new Account(owner);
		String accountId = null;
		try {
			accountId = this.getId();
			account.setId(accountId);
			accounts.add(account);
		} catch (AssertionError e) {
			accountId = null;
			account = null; //mark object as possible to remove by gc
			e.printStackTrace();			
		}
		return accountId;
	}

	public String getPrefix() throws Exception {
		if (prefix == null) {
			throw new Exception("prefix is null");
		}
		else if( !prefix.matches("[0-9]+")){
			throw new Exception("prefix contains not only numbers");
		}
		else if( prefix.length() != PREFIX_LENGTH )
		{
			throw new Exception("prefix is too long or too short");
		}
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<IAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<IAccount>accounts) {
		this.accounts = accounts;
	}

	public Accounts() {
		this.prefix = new String();
		this.accounts = new ArrayList<IAccount>();
		IDGenerator = 0;
	}

	public Accounts(String prefix) {
		this.prefix = prefix;
		IDGenerator = 0;
	}

	public Accounts(ArrayList<IAccount> accounts) {
		this.accounts = accounts;
		IDGenerator = 0;
	}

	public Accounts(String prefix, ArrayList<IAccount> accounts) {
		this.prefix = prefix;
		this.accounts = accounts;
		IDGenerator = 0;
	}

	public int getPrefixLength() {
		return PREFIX_LENGTH;
	}

	@Deprecated
	public boolean accountExists(IAccount account ){
		if( accounts != null ){
			return accounts.contains(account);
		}
		return false;
	}
	
	public boolean accountExists(String id) {
		if (id == null) {
			return false;
		}
		IAccount account = getAccountFromNumber(id);
		return accounts.contains(account);
	}

	public IAccount getAccountFromNumber(String number) {
		IAccount account = null;
		for (IAccount a : accounts) {
			if (number.equals(a.getId())) {
				account = a;
			}
		}
		return account;
	}

	@Override
	public boolean Authorization(IAccount account, String pin) {
		if(accounts != null ){
			if(accounts.contains(account) == true){
				return account.getPin().equals(pin);
			}
		}
		return false;
	}

	@Override
	public boolean Authorization(IAccount account, Owner owner) {
		if( accounts != null ){
			if(accounts.contains(account) == true){
				return account.getOwner().contains(owner);
			}
		}
		return false;
	}

	public int getAccountIndex(IAccount account) {
		return accounts.indexOf(account);
	}

	public void changeAccountType(int index, AbstractAccountDecorator debitAccount) {
		accounts.remove(index);
		accounts.add(index, debitAccount);		
	}
	
}
