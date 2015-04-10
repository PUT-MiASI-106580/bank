package pl.put.miasi.bank;

public interface IOprocentowanie {
	/**Oprocentowanie zwraca to co konto zarobilo ( NIE stan konta + to co zarobilo ) */
	public double oprocentownie( Konto account ); 
}
