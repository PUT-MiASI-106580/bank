package pl.put.miasi.bank.filters;

import java.util.List;

import pl.put.miasi.bank.accountdata.operations.Transfer;

public class AbstractAntiFraudingFilter implements Filter<List<Transfer>> {
	private List<Filter<List<Transfer>>> filters;
	
	private void addFilter(Filter<List<Transfer>> filter) {
		if (!filters.contains(filter)) {
			filters.add(filter);
		}
	}
	
	protected AbstractAntiFraudingFilter() {
		//empty
	}
	
	@Override
	public List<Transfer> executeFilter(List<Transfer> toFilter) {
		List<Transfer> result = toFilter;
		for (Filter<List<Transfer>> f : filters) {
			result = f.executeFilter(result);
		}
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {		
		return obj.getClass().equals(this.getClass());
	}

	public static AbstractAntiFraudingFilter createFilters() {
		AbstractAntiFraudingFilter filter = new AbstractAntiFraudingFilter();
		filter.addFilter(new AntiFraudingFilter());
		filter.addFilter(new AntiFraudingManyTransactionalFilter());
		return filter;
	}
}
