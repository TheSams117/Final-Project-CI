package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscTimecontrol;

@Repository
@Transactional
public class TimecontrolDaoImp implements TimecontrolDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscTimecontrol save(TsscTimecontrol entity) {
		entityManager.persist(entity); 
		return entity;
		
	}

	@Override
	public TsscTimecontrol update(TsscTimecontrol entity) {
		entityManager.merge(entity);
		
		return entity;
		
	}

	@Override
	public TsscTimecontrol delete(TsscTimecontrol entity) {
		entityManager.remove(entity);
		return entity;
		
		
	}

	@Override
	public TsscTimecontrol findById(long codigo) {
		return entityManager.find(TsscTimecontrol.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscTimecontrol> findAll() {
		String jpql = "Select a from TsscTimecontrol a";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	

}
