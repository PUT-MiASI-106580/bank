package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public interface IAuthorization {

	abstract boolean Authorization(Account account, String pin);
	
	abstract boolean Authorization(Account account, Owner owner);
}
