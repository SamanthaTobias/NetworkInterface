package com.example;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.ResolveResult;
import java.util.Hashtable;

public class MyCustomContext extends javax.naming.InitialContext {

	public MyCustomContext(Hashtable<?, ?> environment) throws NamingException {
		super(environment);
	}

//	@Override
	protected ResolveResult getRootURLContext(String name, Hashtable<?, ?> env) throws NamingException {
		return new ResolveResult(new MyCustomContext(env), name);
	}

	@Override
	public Object lookup(Name name) throws NamingException {
		return lookup(name.toString());
	}

	@Override
	public Object lookup(String name) throws NamingException {
//		if (!name.startsWith("docker/hosts")) {
			return "127.0.0.1";
//		}
//		return super.lookup(name);
	}

}
