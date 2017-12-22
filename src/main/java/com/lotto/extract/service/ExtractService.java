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
	
	public LottoNumberBaseVo getWinningNumberOneByCnt(int cnt) {
		
		LottoNumberBaseVo result = dao.getWinningNumberOneByCnt(cnt);
		return result;
	}

	public List<LottoNumberBaseVo> getAllWinningNumber() {
		List<LottoNumberBaseVo> result = dao.getAllWinningNumber();
		return result;
	}
	
	public void insertWinningNumber(LottoNumberBaseVo param) {
		dao.insertWinningNumber(param);
	}

	public List<LottoNumberBaseVo> expectationNumberExtract() {
		List<LottoNumberBaseVo> winningNumberList = dao.getAllWinningNumber();

		
		return null;
	}
}
