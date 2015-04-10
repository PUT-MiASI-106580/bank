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
	
	private Konta konta;

	//TODO: implement
	
	public Bank(String prefix){
		Id = prefix;
		konta = new Konta( prefix );
	}

	public String getId() {
		return this.Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}
	
	public boolean CreateKonto(Wlasciciel wlasciciel){
		
		if( konta == null )
		{
			konta = new Konta( this.Id );
		}
		return konta.createKonto(wlasciciel);
	}
	
	public boolean RemoveKonto(Konto account, String pin){
		return RemoveKonto(account, pin, null);
	}
	
	public boolean RemoveKonto(Konto account, Wlasciciel owner){
		return RemoveKonto(account, null, owner);
	}
	
	private boolean RemoveKonto(Konto account, String pin, Wlasciciel owner){
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
	public boolean Authorization(Konto account, String pin) {
		if( konta != null ){
			return konta.Authorization(account, pin);
		}
		return false;
	}

	@Override
	public boolean Authorization(Konto account, Wlasciciel owner) {
		if( konta != null ){
			return konta.Authorization(account, owner);
		}
		return false;
	}

	@Override
	public boolean Wyplata(double outCash, Konto account, Wlasciciel owner) {
		if( konta != null ){
			if( konta.Authorization(account, owner) == true){
				return account.Wyplata(outCash);
			}
		}
		return false;
	}

	@Override
	public boolean Wyplata(double outCash, Konto account, String pin) {
		if( konta != null ){
			if( konta.Authorization(account, pin) == true){
				return account.Wyplata(outCash);
			}
		}
		return false;
	}

	@Override
	public boolean Wplata(double inCash, Konto account, Wlasciciel owner) {
		if( konta != null ){
			if( konta.Authorization(account, owner) == true){
				return account.Wplata(inCash);
			}
		}
		return false;
	}

	@Override
	public boolean Wplata(double inCash, Konto account, String pin) {
		if( konta != null ){
			if( konta.Authorization(account, pin) == true){
				return account.Wplata(inCash);
			}
		}
		return false;
	}
	
}