package pl.put.miasi.bank.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.put.miasi.bank.accountdata.Accounts;
import pl.put.miasi.bank.commondata.Owner;
import pl.put.miasi.bank.manage.Banks;
import pl.put.miasi.bank.manage.KIR;

public class UtilsTest {			

	@Test
	public void testParseIds() {
		String id = KIR.getKir().createBank().CreateAccount(new Owner());
		System.out.println(id);
		String bank = null, account = null;
		
		String[] res = Utils.parseIds(id);
		StringBuilder b = new StringBuilder();
		StringBuilder a = new StringBuilder();
		int i = 0;
		for (Character c : id.toCharArray()) {
			if (i < Banks.PREFIX_LENGTH) {
				b.append(c);
			} else if (i < Banks.PREFIX_LENGTH + Accounts.PREFIX_LENGTH) {
				a.append(c);
			}
			i++;
		}
		System.out.println(b.toString());
		System.out.println(a.toString());
		assertEquals(b.toString(), res[Utils.BANK]);
		assertEquals(a.toString(), res[Utils.ACCOUNT]);
		
	}

}
