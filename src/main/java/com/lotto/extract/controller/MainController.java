package com.lotto.extract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lotto.extract.service.ExtractService;
import com.lotto.extract.vo.LottoNumberBaseVo;

@Controller
public class MainController {

	@Autowired
	private ExtractService service;
	
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
}
