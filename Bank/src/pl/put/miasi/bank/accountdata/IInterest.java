package pl.put.miasi.bank.accountdata;


public interface IInterest {
	/**Oprocentowanie zwraca to co konto zarobilo ( NIE stan konta + to co zarobilo ) */
	public double interest( IAccount account ); 
}
