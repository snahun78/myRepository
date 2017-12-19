package com.lotto.extract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotto.extract.dao.ExtractDao;
import com.lotto.extract.vo.LottoNumberBaseVo;

@Service
public class ExtractService {

	@Autowired
	private ExtractDao dao;
	
	public LottoNumberBaseVo getWinnigNuber(int cnt) {
		
		LottoNumberBaseVo result = dao.getWinningNumber(1);
		return result;
	}

	public List<LottoNumberBaseVo> getWinningNumberAll() {
		List<LottoNumberBaseVo> result = dao.getWinningNumberAll();
		return result;
	}
}
