package com.example.demo.web;

import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscTopic;

public interface TopicRestController {
	public TsscTopic createTopic(TsscTopic topic) throws TopicServiceException ;
	public TsscTopic updateTopic(TsscTopic topic) throws TopicServiceException;
	public void deleteTopic(long id) throws TopicServiceException;
	public TsscTopic getTopic(long id) throws TopicServiceException;
	public Iterable<TsscTopic> findAll();
}
