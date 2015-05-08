package pl.put.miasi.bank.accountdata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.put.miasi.bank.accountdata.Account;
import pl.put.miasi.bank.accountdata.operations.Transfer;
import pl.put.miasi.bank.commondata.Owner;
import pl.put.miasi.bank.manage.Bank;
import pl.put.miasi.bank.manage.KIR;

public class TransferTest {

	Transfer t;
	@Before
	public void setUp() throws Exception {
		t = new Transfer(new Bank("bank1", KIR.getKir()), new Bank("bank2", KIR.getKir()), new Account(new Owner()), new Account(new Owner()), 250);
	}

	@Test
	public void testEqualsObject() {
		Transfer t2 = new Transfer(new Bank("bank1", KIR.getKir()), new Bank("bank2", KIR.getKir()), new Account(new Owner()), new Account(new Owner()), 250);
		Transfer t3 = new Transfer(new Bank("bank1", KIR.getKir()), new Bank("bank3", KIR.getKir()), new Account(new Owner()), new Account(new Owner()), 250);
		Transfer t4 = new Transfer(new Bank("bank3", KIR.getKir()), new Bank("bank2", KIR.getKir()), new Account(new Owner()), new Account(new Owner()), 250);
		Transfer t5 = new Transfer(new Bank("bank3", KIR.getKir()), new Bank("bank2", KIR.getKir()), new Account(new Owner()), new Account(new Owner()), 300);
		
		assertTrue(t.equals(t2));
		assertTrue(t2.equals(t));
		assertFalse(t.equals(t3));
		assertFalse(t.equals(t4));
		assertFalse(t.equals(t5));
		
	}

}
