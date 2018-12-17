package com.kh.dc.info.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {
	@RequestMapping("info/weather/list.do")
	public String weatherList() {
		return "info/weather/list";
	}
}
