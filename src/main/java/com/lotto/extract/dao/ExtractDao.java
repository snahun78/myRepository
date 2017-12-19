package com.lotto.extract.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.lotto.extract.vo.LottoNumberBaseVo;

@Repository
public class ExtractDao{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	final String COLLECTION = "lottos";
	
	public LottoNumberBaseVo getWinningNumber(int cnt) {
		
		Query query = new Query(Criteria.where("_id").is(cnt));
		LottoNumberBaseVo result = mongoTemplate.findOne(query, LottoNumberBaseVo.class, COLLECTION);
		return result;
	}
	
	public List<LottoNumberBaseVo> getWinningNumberAll(){
		List<LottoNumberBaseVo> result = mongoTemplate.findAll(LottoNumberBaseVo.class);
		return result;
	}
}
