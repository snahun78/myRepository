package com.lotto.extract.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotto.extract.dao.ExtractDao;
import com.lotto.extract.vo.LottoNumberBaseVo;
import com.lotto.extract.vo.StatisticsVo;

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
		
		StatisticsVo statisticsResult = statisticsNumber(winningNumberList);
		List<LottoNumberBaseVo> expectResult = expectNumber(statisticsResult);
		return winningNumberList;
	}
	
	private StatisticsVo statisticsNumber(List<LottoNumberBaseVo> numberList) {
		
		int allCount = 0;
		StatisticsVo statistic = new StatisticsVo();
		Map<String, Integer> numberWinningCountMap = new HashMap<String, Integer>();
		
		for (LottoNumberBaseVo item : numberList) {
			int number1 = item.getNumber1();
			int number2 = item.getNumber2();
			int number3 = item.getNumber3();
			int number4 = item.getNumber4();
			int number5 = item.getNumber5();
			int number6 = item.getNumber6();
			int bnusNo = item.getBnusNo();
			
			String key = String.valueOf(number1);
			int count1 = 0;
			if(numberWinningCountMap.containsKey(key)) {
				count1 = numberWinningCountMap.get(key);				
			}
			numberWinningCountMap.put(key, ++count1);
			
			key = String.valueOf(number2);
			if(numberWinningCountMap.containsKey(key)) {
				count1 = numberWinningCountMap.get(key);
			}
			else {
				count1 = 0;
			}
			numberWinningCountMap.put(key, ++count1);
			
			key = String.valueOf(number3);
			if(numberWinningCountMap.containsKey(key)) {
				count1 = numberWinningCountMap.get(key);
			}
			else {
				count1 = 0;
			}
			numberWinningCountMap.put(key, ++count1);
			
			key = String.valueOf(number4);
			if(numberWinningCountMap.containsKey(key)) {
				count1 = numberWinningCountMap.get(key);
			}
			else {
				count1 = 0;
			}
			numberWinningCountMap.put(key, ++count1);
			
			key = String.valueOf(number5);
			if(numberWinningCountMap.containsKey(key)) {
				count1 = numberWinningCountMap.get(key);
			}
			else {
				count1 = 0;
			}
			numberWinningCountMap.put(key, ++count1);
			
			key = String.valueOf(number6);
			if(numberWinningCountMap.containsKey(key)) {
				count1 = numberWinningCountMap.get(key);
			}
			else {
				count1 = 0;
			}
			numberWinningCountMap.put(key, ++count1);
			
			key = String.valueOf(bnusNo);
			if(numberWinningCountMap.containsKey(key)) {
				count1 = numberWinningCountMap.get(key);
			}
			else {
				count1 = 0;
			}
			numberWinningCountMap.put(key, ++count1);
			
			allCount++;
		}
		
		statistic.setAllCount(allCount);
		statistic.setNumberWinningCount(numberWinningCountMap);
		
		Set<String> keySet = numberWinningCountMap.keySet();
		int keySize = keySet.size();
		Iterator<String> iter = keySet.iterator();
		
		HashMap<String, Double> setNumberWinningProbability = new HashMap<String, Double>();
		DecimalFormat dformat = new DecimalFormat("####.#####");
		double probabilitySum = 0;
		while(iter.hasNext()) {
			String key = iter.next();
			int value = numberWinningCountMap.get(key);
			double  probability = (double)value / (double)allCount;
			probabilitySum = Double.sum(probabilitySum, probability);
			setNumberWinningProbability.put(key, probability);
		}
		
		double standardProbability = probabilitySum / keySize;
		statistic.setNumberWinningProbability(setNumberWinningProbability);
		statistic.setStandardProbability(standardProbability);
		
		System.out.println("statistic = " + standardProbability);
		return statistic;
	}
	
	private List<LottoNumberBaseVo> expectNumber(StatisticsVo param) {
		List<Integer> expectNumber = new ArrayList<>();
		
		double standardProbability = param.getStandardProbability();
		Map<String, Double> proMap = param.getNumberWinningProbability();
		Iterator<String> iter = proMap.keySet().iterator();
		
		while(iter.hasNext()) {
			String key = iter.next();
			double value = proMap.get(key);
			
			if(value < standardProbability) {
				expectNumber.add(Integer.parseInt(key));
			}
		}
		
		return null;
	}
}
