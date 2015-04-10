/**
 * 
 */
package pl.put.miasi.bank;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.put.miasi.bank.Konto.Wpis;

/**
 * @author inf106580
 *
 */
public class KontoTest {

	private Konto konto;
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
		konto = new Konto(new Wlasciciel());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link pl.put.miasi.bank.Konto#wplata(double)}.
	 */
	@Test
	public void testWplata() throws IllegalArgumentException{
		assertEquals(0, konto.getSaldo(), 0.01);
		double wplata = rand.nextInt()*rand.nextDouble();
		if (wplata < 0) {
			wplata *= -1;
		}		
		konto.wplata(wplata);
		assertEquals(wplata, konto.getSaldo(), 0.01);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWplataException() {
		assertEquals(0, konto.getSaldo(), 0.01);
		double wplata = rand.nextInt()*rand.nextDouble();
		if (wplata >= 0) {
			wplata *= -1;
			wplata -= rand.nextInt(12);
		}		
		konto.wplata(wplata);
		fail("Exception expected");
	}

	/**
	 * Test method for {@link pl.put.miasi.bank.Konto#wyplata(double)}.
	 */
	@Test
	public void testWyplata() {
		assertEquals(0, konto.getSaldo(), 0.01);
		double in = rand.nextInt(3000) + 1000;
		konto.wplata(in);
		double initialSaldo = konto.getSaldo();
		double wyplata = rand.nextInt(3000)*rand.nextDouble();
		if (wyplata < 0) {
			wyplata *= -1;
		}		
		konto.wyplata(wyplata);
		assertEquals(initialSaldo - wyplata, konto.getSaldo(), 0.01);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWyplataArgumentException() {
		assertEquals(0, konto.getSaldo(), 0.01);
		double in = rand.nextInt(3000) + 1000;
		konto.wplata(in);
		double wyplata = rand.nextInt(3000)*rand.nextDouble();
		if (wyplata >= 0) {
			wyplata *= -1;
			wyplata -= rand.nextInt(12);
		}		
		konto.wyplata(wyplata);
		fail("Exception expected");
	}
	
	@Test(expected=IllegalStateException.class)
	public void testWyplataNotEnoughMoney() {
		assertEquals(0, konto.getSaldo(), 0.01);
		double in = rand.nextInt(3000) + 1000;
		konto.wplata(in);
		double wyplata = rand.nextInt(2000)*rand.nextDouble() + 4000;
		if (wyplata < 0) {
			wyplata *= -1;
		}		
		konto.wyplata(wyplata);
		fail("Exception expected");
	}
	
	@Test
	public void testWyplataWithDebit() {
		assertEquals(0, konto.getSaldo(), 0.01);
		konto.setDebet(3000);
		double in = rand.nextInt(3000) + 2000;		
		konto.wplata(in);
		double initialSaldo = konto.getSaldo();
		double wyplata = rand.nextInt(2000)*rand.nextDouble() + 4000;
		if (wyplata < 0) {
			wyplata *= -1;
		}		
		konto.wyplata(wyplata);
		assertEquals(initialSaldo - wyplata, konto.getSaldo(), 0.01);
	}
	
	@Test
	public void checkHistory() throws InterruptedException {
		double in = rand.nextInt(3000) + 1000;
		konto.wplata(in);		
		assertEquals(1, konto.getHistoria().size());
		Date d1 = (Date) konto.getHistoria().keySet().iterator().next();
		Wpis w = konto.getHistoria().get(d1);
		assertEquals(w.getOperacja(), Operacja.WPLATA);
		assertEquals(in, (Double) w.getParams().get(0), 0.01);
		double wyplata = rand.nextInt(300)*rand.nextDouble();
		if (wyplata < 0) {
			wyplata *= -1;
		}				
		Thread.sleep(1000);
		konto.wyplata(wyplata);		
		assertEquals(2, konto.getHistoria().size());		
		Set<Date> temp = konto.getHistoria().keySet();
		temp.remove(d1);
		d1 = (Date) temp.iterator().next();
		w = konto.getHistoria().get(d1);
		assertEquals(w.getOperacja(), Operacja.WYPLATA);
		assertEquals(wyplata, (Double) w.getParams().get(0), 0.01);
	}

}
