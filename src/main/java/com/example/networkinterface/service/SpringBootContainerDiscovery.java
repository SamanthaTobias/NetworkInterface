package com.example.networkinterface.service;

import org.springframework.jndi.JndiTemplate;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class SpringBootContainerDiscovery { // todo make proper service?

	private final JndiTemplate jndiTemplate; // todo use @Autowired?

	public SpringBootContainerDiscovery() {
		this.jndiTemplate = new JndiTemplate();
	}

	public List<InetAddress> discoverSpringBootContainers() throws NamingException, UnknownHostException {
		List<InetAddress> addresses = new ArrayList<>();
		Context context = jndiTemplate.getContext();
		NamingEnumeration<NameClassPair> namingEnum = context.list("java:comp/env/docker/hosts");
		while (namingEnum.hasMoreElements()) {
			NameClassPair pair = namingEnum.next();
			String name = pair.getName();
			if (name.startsWith("my-spring-boot-")) {
				String address = (String) jndiTemplate.lookup("docker/hosts/" + name);
				addresses.add(InetAddress.getByName(address));
			}
		}
		return addresses;
	}

}
