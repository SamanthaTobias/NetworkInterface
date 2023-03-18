package com.example.networkinterface.controller;

import com.example.networkinterface.model.AppInfo;
import com.example.networkinterface.service.NetworkInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
