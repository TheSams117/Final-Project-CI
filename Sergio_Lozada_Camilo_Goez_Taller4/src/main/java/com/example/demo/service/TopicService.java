package com.example.demo.service;
 
import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscTopic;

public interface TopicService {
	public TsscTopic createTopic(TsscTopic topic) throws TopicServiceException ;
	public TsscTopic updateTopic(TsscTopic topic) throws TopicServiceException;
	public void deleteTopic(TsscTopic topic) throws TopicServiceException;
	public TsscTopic getTopic(long id) throws TopicServiceException;
	public Iterable<TsscTopic> findAll();
}
