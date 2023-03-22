package com.example.networkinterface.controller;

import com.example.networkinterface.model.AppInfo;
import com.example.networkinterface.service.NetworkInterfaceService;
import com.example.networkinterface.service.SpringBootContainerDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.NamingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
public class NetworkInterfaceController {

	@Autowired
	private NetworkInterfaceService networkInterfaceService;

	@GetMapping("/")
	public String index(Model model) {
		List<AppInfo> apps = networkInterfaceService.getAppInfo();
		String networkName = networkInterfaceService.getNetworkName();
		model.addAttribute("apps", apps);
		model.addAttribute("networkName", networkName);
		return "index";
	}

	@GetMapping("/apps/{appName}")
	public String appDetails(@PathVariable String appName, Model model) {
		AppInfo appInfo = networkInterfaceService.getAppInfo(appName);
		model.addAttribute("app", appInfo);
		return "app";
	}

	@GetMapping("/test")
	public String test(Model model) throws UnknownHostException, NamingException {
		SpringBootContainerDiscovery springBootContainerDiscovery = new SpringBootContainerDiscovery();
		List<InetAddress>  inetAddresses = springBootContainerDiscovery.discoverSpringBootContainers();
		List<String> inetAddressStrings = inetAddresses.stream()
				.map(InetAddress::toString)
				.toList();
		model.addAttribute("inetAddressStrings", inetAddressStrings);
		return "test";
	}

}
