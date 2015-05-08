package pl.put.miasi.bank.commondata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.put.miasi.bank.accountdata.Operation;

public class Registry {
	private final Operation operation;
	private final List<Object> params;		
	public Registry(Operation operation, Object... params) {
		this.operation = operation;
		this.params = new ArrayList<Object>(Arrays.asList(params));
	}
	public Operation getOperation() {
		return operation;
	}
	public List<Object> getParams() {
		return params;
	}
	
}