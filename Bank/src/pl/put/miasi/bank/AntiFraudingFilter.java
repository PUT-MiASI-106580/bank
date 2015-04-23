package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.List;

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
