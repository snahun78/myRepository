package com.lotto.extract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lotto.extract.service.ExtractService;
import com.lotto.extract.vo.LottoNumberBaseVo;

@Controller
@RequestMapping("/extract")
public class ExtractController {

	@Autowired
	private ExtractService service;
	
	@RequestMapping("/getNumber")
	public void extractNumber() {
		LottoNumberBaseVo result = service.getWinnigNuber(1);
		System.out.println("result = " + result.toString());
	}
	
	@RequestMapping("/getAllNumber")
	public void extractAllNumber() {
		List<LottoNumberBaseVo> result = service.getWinningNumberAll();
		System.out.println("result = " + result.toString());
	}
}
