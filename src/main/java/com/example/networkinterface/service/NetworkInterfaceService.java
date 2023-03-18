package com.example.networkinterface.service;

import com.example.networkinterface.model.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkInterfaceService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${network.name}")
	private String networkName;

	@Value("${basicapp.url}")
	private String basicAppUrl;

	@Value("${weatherapp.url}")
	private String weatherAppUrl;

	@Value("${middleman.url}")
	private String middlemanAppUrl;

	public List<AppInfo> getAppInfo() {
		List<AppInfo> appInfoList = new ArrayList<>();
		appInfoList.add(new AppInfo("BasicApp", "A basic Java app", List.of(basicAppUrl + "/hello")));
		appInfoList.add(new AppInfo("WeatherApp", "Get the weather", List.of(weatherAppUrl + "/weather")));
		appInfoList.add(new AppInfo("MiddleMan", "Make requests to other apps", List.of(middlemanAppUrl + "/hello", middlemanAppUrl + "/reverse")));
		return appInfoList;
	}

	public AppInfo getAppInfo(String appName) {
		for (AppInfo appInfo : getAppInfo()) {
			if (appInfo.name().equals(appName)) {
				return appInfo;
			}
		}
		throw new IllegalArgumentException("Illegal app name: " + appName);
	}

	public String getNetworkName() {
		return networkName;
	}

}
