package pl.put.miasi.bank;

public interface IInterest {
	/**Oprocentowanie zwraca to co konto zarobilo ( NIE stan konta + to co zarobilo ) */
	public double interest( Account account ); 
}
