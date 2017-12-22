package com.lotto.extract.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lotto.extract.service.ExtractService;
import com.lotto.extract.vo.LottoNumberBaseVo;

@Controller
@RequestMapping("/extract")
public class ExtractController {

	@Autowired
	private ExtractService service;
	
	@RequestMapping("/getWinningNumberOneByCnt.ax")
	public void getWinningNumberOneByCnt(@RequestParam int param) {
		LottoNumberBaseVo result = service.getWinningNumberOneByCnt(param);
		System.out.println("result = " + result.toString());
	}
	
	@RequestMapping(value="/getAllWinningNumber.ax", method=RequestMethod.POST)
	public @ResponseBody List<LottoNumberBaseVo> getAllWinningNumber() {
		List<LottoNumberBaseVo> result = service.getAllWinningNumber();
		return result;
	}
	
	@RequestMapping(value="/insertWinningNumber.ax", method=RequestMethod.POST)
	public @ResponseBody String insertWinningNumber(@RequestBody LottoNumberBaseVo param) {
		try {
			service.insertWinningNumber(param);
			return "success";
		}catch (Exception e) {
			return "fail";
		}
	}
	
	@RequestMapping("/expectationNumberExtract.ax")
	public List<LottoNumberBaseVo> expectationNumberExtract(){
		List<LottoNumberBaseVo> result = new ArrayList<>();
		
		service.expectationNumberExtract();
		
		return result;
	}
	
}
