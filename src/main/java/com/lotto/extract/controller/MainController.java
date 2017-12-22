package com.lotto.extract.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.lotto.extract.service.ExtractService;
import com.lotto.extract.vo.DrwNoVo;
import com.lotto.extract.vo.LottoNumberBaseVo;

@Controller
public class MainController {

	@Autowired
	private ExtractService service;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		List<LottoNumberBaseVo> result = service.getAllWinningNumber();
		int count = result.size();
		
		if(count > 0) {
			LottoNumberBaseVo lastItem = result.get(count-1);
			mv.addObject("lastItem", lastItem);
		}
		
		mv.addObject("count", count);
		return mv;
	}
	
	@RequestMapping("/winningList")
	public ModelAndView getWinningList() {
		ModelAndView mv = new ModelAndView("winningListPage");
		List<LottoNumberBaseVo> result = service.getAllWinningNumber();
		int count = result.size();
		
		if(count > 0) {
			LottoNumberBaseVo lastItem = result.get(count-1);
			mv.addObject("lastItem", lastItem);
		}
		
		mv.addObject("count", count);
		mv.addObject("winningList", result);
		return mv;
	}
	
	@RequestMapping("/numberUploadByTextFile")
	public void numberUploadByTextFile() {
		
		Gson gson = new Gson();
		LottoNumberBaseVo convertData = new LottoNumberBaseVo();
		
		String url = "http://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=";
		RestTemplate restTemplate = new RestTemplate();
		
//		String apiUrl = url + "366";
//		String result = restTemplate.postForObject(apiUrl, null, String.class);
//		System.out.println(result);
//		785
		for(int i=601; i<=785; i++) {

			String drwNo = String.valueOf(i);
			String apiUrl = url + drwNo;
			String result = restTemplate.postForObject(apiUrl, null, String.class);
			DrwNoVo apiData = gson.fromJson(result, DrwNoVo.class);
			
			if(apiData.getReturnValue().equals("success")) {
				convertData.setDrwNo(apiData.getDrwNo());
				convertData.setWinningDate(apiData.getDrwNoDate());
				convertData.setNumber1(apiData.getDrwtNo1());
				convertData.setNumber2(apiData.getDrwtNo2());
				convertData.setNumber3(apiData.getDrwtNo3());
				convertData.setNumber4(apiData.getDrwtNo4());
				convertData.setNumber5(apiData.getDrwtNo5());
				convertData.setNumber6(apiData.getDrwtNo6());
				convertData.setBnusNo(apiData.getBnusNo());
				
//				service.insertWinningNumber(convertData);
			}
			
		}
	}
}
