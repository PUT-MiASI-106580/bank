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
	
	@Override
	public void addBasicDebitTo(IAccount account, Owner owner) {		
		int index =  accounts.getAccountIndex(account);
		if (index < 0 || !Authorization(account, owner)) {
			return;
		}
		
	}
	
	public boolean RemoveAccount(IAccount account, String pin){
		return RemoveAccount(account, pin, null);
	}
	
	public boolean RemoveAccount(IAccount account, Owner owner){
		return RemoveAccount(account, null, owner);
	}
	
	private boolean RemoveAccount(IAccount account, String pin, Owner owner){
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
	public boolean Authorization(IAccount account, String pin) {
		if( accounts != null ){
			return accounts.Authorization(account, pin);
		}
		return false;
	}

	@Override
	public boolean Authorization(IAccount account, Owner owner) {
		if( accounts != null ){
			return accounts.Authorization(account, owner);
		}
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, Owner owner) {
		if( accounts != null ){
			if( accounts.Authorization(account, owner) == true){
				account.withdraw(outCash);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Withdraw(double outCash, IAccount account, String pin) {
		if( accounts != null ){
			if( accounts.Authorization(account, pin)){
				account.withdraw(outCash);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, Owner owner) {
		if( accounts != null ){
			if( accounts.Authorization(account, owner) == true){
				account.deposit(inCash);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Deposit(double inCash, IAccount account, String pin) {
		if( accounts != null ){
			if( accounts.Authorization(account, pin) == true){
				account.deposit(inCash);
				return true;
			}
		}
		return false;
	}
	
}