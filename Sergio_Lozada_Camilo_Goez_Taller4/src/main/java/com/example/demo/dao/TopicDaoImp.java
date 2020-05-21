package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscTopic;

@Repository
@Transactional
public class TopicDaoImp implements TopicDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscTopic save(TsscTopic entity) {
		entityManager.persist(entity); 
		return entity;
	}

	@Override
	public TsscTopic update(TsscTopic entity) {
		entityManager.merge(entity);
		
		return entity;
	}

	@Override
	public TsscTopic delete(TsscTopic entity) {
		entityManager.remove(entity);
		return entity;
	}

	@Override
	public TsscTopic findById(long codigo) {
		return entityManager.find(TsscTopic.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select a from TsscTopic a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public TsscTopic findByName(String name) {
		String jpql = "Select a from TsscTopic a WHERE a.name = '"+name+"'";
		Query q = entityManager.createQuery(jpql);
		return (TsscTopic) q.getSingleResult();
	}

	@Override
	public TsscTopic findByDescription(String Description) {
		String jpql = "Select a from TsscTopic a WHERE a.description = '"+Description+"'";
		Query q = entityManager.createQuery(jpql);
		return (TsscTopic) q.getSingleResult();
	}

}
