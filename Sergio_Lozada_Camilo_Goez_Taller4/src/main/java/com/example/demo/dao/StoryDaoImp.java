package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscStory;

@Repository
@Transactional
public class StoryDaoImp implements StoryDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscStory save(TsscStory entity) {
		entityManager.persist(entity); 
		return entity;
		
	}

	@Override
	public TsscStory update(TsscStory entity) {
		entityManager.merge(entity);
		
		return entity;
		
	}

	@Override
	public TsscStory delete(TsscStory entity) {
		entityManager.remove(entity);
		return entity;
		
	}

	@Override
	public TsscStory findById(long codigo) {
		return entityManager.find(TsscStory.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscStory> findAll() {
		String jpql = "Select a from TsscStory a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
