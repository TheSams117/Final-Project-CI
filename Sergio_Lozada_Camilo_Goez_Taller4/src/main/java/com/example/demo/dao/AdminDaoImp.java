package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscAdmin;

@Repository
public class AdminDaoImp implements AdminDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public TsscAdmin save(TsscAdmin entity) {
		entityManager.persist(entity); 
		return entity;
		
	}

	@Override
	public TsscAdmin update(TsscAdmin entity) {
		entityManager.persist(entity);
		
		return entity;
		
	}

	@Override
	public TsscAdmin delete(TsscAdmin entity) {
		entityManager.remove(entity);
		return entity;
		
	}

	@Override
	public TsscAdmin findById(long codigo) {
		return entityManager.find(TsscAdmin.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscAdmin> findAll() {
		String jpql = "Select a from TsscAdmin a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public TsscAdmin findByUser(String user) {
		String jpql = "Select a from TsscAdmin a Where a.user = '"+user+"'";
		TsscAdmin admin = (TsscAdmin) entityManager.createQuery(jpql).getSingleResult();
		return admin;
	}

}
