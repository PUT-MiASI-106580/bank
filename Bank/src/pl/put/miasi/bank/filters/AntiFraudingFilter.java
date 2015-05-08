package pl.put.miasi.bank.filters;

import java.util.ArrayList;
import java.util.List;

import pl.put.miasi.bank.accountdata.Transfer;
import pl.put.miasi.bank.exceptions.FraudingException;

public class AntiFraudingFilter extends AbstractAntiFraudingFilter {
	
	private static int LIMIT = 20000;

	@Override
	public List<Transfer> executeFilter(List<Transfer> toFilter) {
		List<Transfer>result = new ArrayList<>();
		for (Transfer t : toFilter) {
			try {
				if (t.getAmount() > LIMIT) {
					throw new FraudingException("Transfer value over 20000");
				}
				result.add(t);
			} catch (FraudingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
