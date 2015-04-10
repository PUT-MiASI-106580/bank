package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public interface IAuthorization {

	abstract boolean Authorization(Konto account, String pin);
	
	abstract boolean Authorization(Konto account, Wlasciciel owner);
}
