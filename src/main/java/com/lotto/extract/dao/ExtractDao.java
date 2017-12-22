package com.lotto.extract.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lotto.extract.vo.LottoNumberBaseVo;

@Repository
@Transactional
public class ExtractDao{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	final String DB_COLLECTION_NAME = "winningNumber";
	
	public LottoNumberBaseVo getWinningNumberOneByCnt(int cnt) {
		
		Query query = new Query(Criteria.where("cnt").is(cnt));
		LottoNumberBaseVo result = mongoTemplate.findOne(query, LottoNumberBaseVo.class, DB_COLLECTION_NAME);
		return result;
	}
	
	public List<LottoNumberBaseVo> getAllWinningNumber(){
		List<LottoNumberBaseVo> result = mongoTemplate.findAll(LottoNumberBaseVo.class, DB_COLLECTION_NAME);
		return result;
	}
	
	public void insertWinningNumber(LottoNumberBaseVo param) {
		mongoTemplate.insert(param, DB_COLLECTION_NAME);
	}
}
