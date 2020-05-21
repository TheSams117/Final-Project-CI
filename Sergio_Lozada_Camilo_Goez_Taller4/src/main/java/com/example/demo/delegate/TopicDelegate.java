package com.example.demo.delegate;

import com.example.demo.model.TsscTopic;

public interface TopicDelegate {

	public TsscTopic createTopic(TsscTopic Topic);
	public void updateTopic(TsscTopic Topic);
	public void deleteTopic(long id);
	public TsscTopic getTopic(long id);
	public Iterable<TsscTopic> findAll();
}
