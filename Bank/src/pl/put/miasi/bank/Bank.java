package pl.put.miasi.bank;
/**
 * 
 * @author Mikolaj Szychowiak
 *
 */
/*
TO DO:
zmienic wszystkie operacje parametry wejsciowe konto na string skladajacy sie z bank.ID+konto.ID
aktualnie brak powiazan z KIR'em i przelewami
w calym projekcie nalezy przetlumaczyc plinglish na english
 * */
public class Bank implements IAuthorization, IBank{
	private String Id;
	
	private Accounts accounts;

	//TODO: implement
	
	public Bank(String prefix){
		Id = prefix;
		accounts = new Accounts( prefix );
	}

	public String getId() {
		return this.Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}
	
	public boolean CreateAccount(Owner owner){
		
		if( accounts == null )
		{
			accounts = new Accounts( this.Id );
		}
		return accounts.createAccount(owner);
	}
	
	public boolean RemoveAccount(Account account, String pin){
		return RemoveAccount(account, pin, null);
	}
	
	public boolean RemoveAccount(Account account, Owner owner){
		return RemoveAccount(account, null, owner);
	}
	
	private boolean RemoveAccount(Account account, String pin, Owner owner){
		if(pin != null)
		{
			if( Authorization(account,pin) == false ){
				return false;
			}
		}
		else if(owner != null)
		{
			if( Authorization(account,owner) == false ){
				return false;
			}
		}
		else{
			return false;
		}

		return false;
	}

	@Override
	public boolean Authorization(Account account, String pin) {
		if( accounts != null ){
			return accounts.Authorization(account, pin);
		}
		return false;
	}

	@Override
	public boolean Authorization(Account account, Owner owner) {
		if( accounts != null ){
			return accounts.Authorization(account, owner);
		}
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, Account account, Owner owner) {
		if( accounts != null ){
			if( accounts.Authorization(account, owner) == true){
				account.withdraw(outCash);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, Account account, String pin) {
		if( accounts != null ){
			if( accounts.Authorization(account, pin)){
				account.withdraw(outCash);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Deposit(double inCash, Account account, Owner owner) {
		if( accounts != null ){
			if( accounts.Authorization(account, owner) == true){
				account.deposit(inCash);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Deposit(double inCash, Account account, String pin) {
		if( accounts != null ){
			if( accounts.Authorization(account, pin) == true){
				account.deposit(inCash);
				return true;
			}
		}
		return false;
	}
	
}