package com.xhdn.weather.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WeatherService {
	public JSONObject getFiveDayWeather(String searchCity) {

		WeatherWS server = new WeatherWS();
		WeatherWSSoap soap = server.getWeatherWSSoap();
		ArrayOfString aa = soap.getWeather("南昌", null);
		List<String> list = aa.getString();
		if(list==null||list.size()<=1) {
			return null;
		}
		String provinceCity = list.get(0);
		String city = list.get(1);
		String year = list.get(2);
		String date = list.get(3);
		String todayInfo = list.get(4);
		String todayDetail = list.get(5);
		String tips = list.get(6);
		JSONArray arr = new JSONArray();
		arr.add(getDayObject(list.get(7), list.get(8), list.get(9), list.get(10), list.get(11)));
		arr.add(getDayObject(list.get(12), list.get(13), list.get(14), list.get(15), list.get(16)));
		arr.add(getDayObject(list.get(17), list.get(18), list.get(19), list.get(20), list.get(21)));
		arr.add(getDayObject(list.get(22), list.get(23), list.get(24), list.get(25), list.get(26)));
		arr.add(getDayObject(list.get(27), list.get(28), list.get(29), list.get(30), list.get(31)));
		JSONObject result = new JSONObject();
		result.put("provinceCity", provinceCity);
		result.put("city", city);
		result.put("year", year);
		result.put("date", date);
		result.put("todayInfo", todayInfo);
		result.put("todayDetail", todayDetail);
		result.put("tips", tips);
		result.put("weathers", arr);
		return result;
	}

	public static JSONObject getDayObject(String day, String celsius, String wind, String pic1, String pic2) {
		JSONObject obj = new JSONObject();
		obj.put("date", day.split(" ")[0]);
		obj.put("weather", day.split(" ")[1]);
		obj.put("celsius", celsius);
		obj.put("wind", wind);
		obj.put("pic1", pic1);
		obj.put("pic2", pic1);
		return obj;
	}
}
