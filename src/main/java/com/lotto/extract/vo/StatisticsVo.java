package com.lotto.extract.vo;

import java.util.Map;

public class StatisticsVo {

	private int tx;
	private String statisticDate;
	private int allCount;
	private Map<String, Integer> numberWinningCount;
	private Map<String, Integer> numberWinningProbability;
	
	public int getTx() {
		return tx;
	}
	public void setTx(int tx) {
		this.tx = tx;
	}
	public String getStatisticDate() {
		return statisticDate;
	}
	public void setStatisticDate(String statisticDate) {
		this.statisticDate = statisticDate;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public Map<String, Integer> getNumberWinningCount() {
		return numberWinningCount;
	}
	public void setNumberWinningCount(Map<String, Integer> numberWinningCount) {
		this.numberWinningCount = numberWinningCount;
	}
	public Map<String, Integer> getNumberWinningProbability() {
		return numberWinningProbability;
	}
	public void setNumberWinningProbability(Map<String, Integer> numberWinningProbability) {
		this.numberWinningProbability = numberWinningProbability;
	}
	
}
