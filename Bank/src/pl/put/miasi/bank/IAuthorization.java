package pl.put.miasi.bank;

/**
 * @author Miko�aj Ignaszak
 *
 */
public interface IAuthorization {

	abstract boolean Authorization(IAccount account, String pin);
	
	abstract boolean Authorization(IAccount account, Owner owner);
}
