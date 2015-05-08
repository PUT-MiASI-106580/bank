package pl.put.miasi.bank.accountdata.operations;

import pl.put.miasi.bank.accountdata.IAccount;
import pl.put.miasi.bank.commondata.Owner;

/**
 * @author Miko�aj Ignaszak
 *
 */
public interface IAuthorization {

	abstract boolean Authorization(IAccount account, String pin);
	
	abstract boolean Authorization(IAccount account, Owner owner);
}
