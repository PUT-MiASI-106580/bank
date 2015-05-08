package pl.put.miasi.bank.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.put.miasi.bank.accountdata.Transfer;
import pl.put.miasi.bank.exceptions.FraudingException;

public class AntiFraudingManyTransactionalFilter extends AbstractAntiFraudingFilter {		

	@Override
	public List<Transfer> executeFilter(List<Transfer> toFilter) {
		Map<Transfer, Integer> history = new HashMap<>();
		List<Transfer> result = new ArrayList<>();
		for (Transfer t : toFilter) {
			int i = 0;
			if (history.containsKey(t)) {
				i = history.get(t);
				i++;
			} 
			history.put(t, i);			
		}
		for (Transfer t : toFilter) {
			if (history.containsKey(t) && history.get(t) == 0) {
				result.add(t);
			} else {
				try {
					throw new FraudingException("Too many similar transfers");
				} catch (FraudingException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
