package com.xhdn.weather.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xhdn.weather.service.WeatherService;

import net.sf.json.JSONObject;

@Controller
public class WeatherController {
	@Resource WeatherService service;
	
	@RequestMapping(value = "/getFiveDayWeather", produces = {"text/html;charset=UTF-8;"})
	public void getFiveDayWeather(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		JSONObject  result = service.getFiveDayWeather(request.getParameter("city"));
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result.toString());
	}
}
