/**
 * 
 */
package pl.put.miasi.bank.accountdata;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.put.miasi.bank.accountdata.Account;
import pl.put.miasi.bank.accountdata.operations.Operation;
import pl.put.miasi.bank.commondata.Owner;
import pl.put.miasi.bank.commondata.Registry;

/**
 * @author inf106580
 *
 */
public class AccountTest {

	private Account account;
	private static Random rand;
	
	@BeforeClass
	public static void setUpClass() {
		rand = new Random(System.currentTimeMillis());
	}
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		account = new Account(new Owner());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link pl.put.miasi.bank.accountdata.Account#deposit(double)}.
	 */
	@Test
	public void testWplata() throws IllegalArgumentException{
		assertEquals(0, account.getBalance(), 0.01);
		double deposit = rand.nextInt()*rand.nextDouble();
		if (deposit < 0) {
			deposit *= -1;
		}		
		account.deposit(deposit,"SomeDeposit");
		assertEquals(deposit, account.getBalance(), 0.01);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWplataException() {
		assertEquals(0, account.getBalance(), 0.01);
		double deposit = rand.nextInt()*rand.nextDouble();
		if (deposit >= 0) {
			deposit *= -1;
			deposit -= rand.nextInt(12);
		}		
		account.deposit(deposit,"SomeDeposit");
		fail("Exception expected");
	}

	/**
	 * Test method for {@link pl.put.miasi.bank.accountdata.Account#withdraw(double)}.
	 */
	@Test
	public void testWyplata() {
		assertEquals(0, account.getBalance(), 0.01);
		double in = rand.nextInt(3000) + 1000;
		account.deposit(in,"SomeDeposit");
		double initialSaldo = account.getBalance();
		double withdraw = rand.nextInt(3000)*rand.nextDouble();
		if (withdraw < 0) {
			withdraw *= -1;
		}		
		account.withdraw(withdraw,"SomeWithDraw");
		assertEquals(initialSaldo - withdraw, account.getBalance(), 0.01);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWyplataArgumentException() {
		assertEquals(0, account.getBalance(), 0.01);
		double in = rand.nextInt(3000) + 1000;
		account.deposit(in,"SomeDeposit");
		double withdraw = rand.nextInt(3000)*rand.nextDouble();
		if (withdraw >= 0) {
			withdraw *= -1;
			withdraw -= rand.nextInt(12);
		}		
		account.withdraw(withdraw,"SomeWithDraw");
		fail("Exception expected");
	}
	
	@Test(expected=IllegalStateException.class)
	public void testWyplataNotEnoughMoney() {
		assertEquals(0, account.getBalance(), 0.01);
		double in = rand.nextInt(3000) + 1000;
		account.deposit(in,"SomeDeposit");
		double withdraw = rand.nextInt(2000)*rand.nextDouble() + 4000;
		if (withdraw < 0) {
			withdraw *= -1;
		}		
		account.withdraw(withdraw,"SomeWithDraw");
		fail("Exception expected");
	}
	
	//test do not work without decorator implemented
	public void testWyplataWithDebit() {
		assertEquals(0, account.getBalance(), 0.01);
		double in = rand.nextInt(3000) + 2000;		
		account.deposit(in,"SomeDeposit");
		double initialSaldo = account.getBalance();
		double withdraw = rand.nextInt(2000)*rand.nextDouble() + 4000;
		if (withdraw < 0) {
			withdraw *= -1;
		}		
		account.withdraw(withdraw,"SomeWithDraw");
		assertEquals(initialSaldo - withdraw, account.getBalance(), 0.01);
	}
	
	@Test
	public void checkHistory() throws InterruptedException {
		double in = rand.nextInt(3000) + 1000;
		account.deposit(in,"SomeDeposit");		
		assertEquals(1, account.getHistory().size());
		Date d1 = (Date) account.getHistory().keySet().iterator().next();
		Registry w = account.getHistory().get(d1);
		assertEquals(w.getOperation(), Operation.DEPOSIT);
		assertEquals(in, (Double) w.getParams().get(0), 0.01);
		double withdraw = rand.nextInt(300)*rand.nextDouble();
		if (withdraw < 0) {
			withdraw *= -1;
		}				
		Thread.sleep(1000);
		account.withdraw(withdraw,"SomeWithDraw");		
		assertEquals(2, account.getHistory().size());		
		Set<Date> temp = account.getHistory().keySet();
		temp.remove(d1);
		d1 = (Date) temp.iterator().next();
		w = account.getHistory().get(d1);
		assertEquals(w.getOperation(), Operation.WITHDRAW);
		assertEquals(withdraw, (Double) w.getParams().get(0), 0.01);
	}

}
