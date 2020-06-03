package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TopicDao;
import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

@Service
public class TopicServiceImp implements TopicService  {
	@Autowired
	private TopicDao topicDao;
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTopic createTopic(TsscTopic topic) throws TopicServiceException {
		if(topic == null) {
			throw new TopicServiceException("CreateTopic: The new topic can be null");
		}else if(topic.getDefaultGroups() <= 0) {
			throw new TopicServiceException("CreateTopic: Number of groups equal or less to 0");
		}else if(topic.getDefaultSprints() <= 0) {
			throw new TopicServiceException("CreateTopic: Number of sptrints equal or less to 0");
		}
		topic.setTsscGames(new ArrayList<TsscGame>());
		return topicDao.save(topic);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTopic updateTopic(TsscTopic topic) throws TopicServiceException {
		if(topic == null) {
			throw new TopicServiceException("UpdateTopic: The topic to update can be null");
		}else if(topic.getDefaultGroups() <= 0) {
			throw new TopicServiceException("UpdateTopic: Number of groups equal or less to 0");
		}else if(topic.getDefaultSprints() <= 0) {
			throw new TopicServiceException("UpdateTopic: Number of sptrints equal or less to 0");
		}else if(topicDao.findById(topic.getId())==null) {
			throw new TopicServiceException("UpdateTopic: The topic to update dosen't exist.");
		}
		
		return topicDao.update(topic);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTopic(TsscTopic topic) throws TopicServiceException {
		List<TsscGame> list = topic.getTsscGames();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setTsscTopic(null);
		}
		topic.setTsscGames(null);
		
		
		topicDao.delete(topic);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTopic getTopic(long id) throws TopicServiceException {
		return topicDao.findById(id);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<TsscTopic> findAll() {
		
		return topicDao.findAll();
	}

}
