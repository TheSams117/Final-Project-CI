package com.example.demo.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

@Repository
@Transactional
public class GameDaoImp implements GameDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TsscGame save(TsscGame entity) {
		entityManager.persist(entity); 
		return entity;
		
	}

	@Override
	public TsscGame update(TsscGame entity) {
		entityManager.merge(entity);
		
		return entity;
		
	}

	@Override
	public TsscGame delete(TsscGame entity) {
		entityManager.remove(entity);
		return entity;
		
	}

	@Override
	public TsscGame findById(long codigo) {
		return entityManager.find(TsscGame.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscGame> findAll() {
		String jpql = "Select a from TsscGame a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscGame> findByTopicName(String name) {
		
		String jpql = "Select a from TsscGame a Where a.tsscTopic.name = '"+name+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscGame> findByTopicDescription(String description) {
		String jpql = "Select a from TsscGame a Where a.tsscTopic.description = '"+description+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscGame> findByTopicId(long id) {
		String jpql = "Select a from TsscGame a Where a.tsscTopic.id = "+id+"";
		return entityManager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TsscGame> findByDateTime(LocalDate date, LocalTime time1, LocalTime time2) {
		String jpql = "Select a from TsscGame a Where :date = a.scheduledDate AND a.scheduledTime BETWEEN :time1 AND :time2 ";
		Query q = entityManager.createQuery(jpql);
		q.setParameter("date", date);
		q.setParameter("time1", time1);
		q.setParameter("time2", time2);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TsscGame> findByDate(LocalDate date1, LocalDate date2) {
		String jpql = "Select a from TsscGame a Where a.scheduledDate BETWEEN :date1 AND :date2 ";
		Query q = entityManager.createQuery(jpql);
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);
		return q.getResultList();
	}

	@Override
	public List<TsscTopic> Query2A(LocalDate date) {
		String q = "Select a.tsscTopic from TsscGame a where :date = a.scheduledDate ORDER BY a.scheduledTime ASC";
		
		TypedQuery<TsscTopic> query = entityManager.createQuery(q, TsscTopic.class).setParameter("date", date);

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TsscGame> Query2B(LocalDate date) {
		String query = "Select a from TsscGame a Where "+ "(a.scheduledDate =:date AND (("
				+ "(SELECT Count(b) FROM TsscTimecontrol b WHERE b.tsscGame.id = a.id) = 0) OR "+
		"(SELECT Count(c) FROM TsscStory c WHERE c.tsscGame.id = a.id ) < 10))";
		
		Query q = entityManager.createQuery(query, TsscGame.class).setParameter("date", date);
		
		return q.getResultList();
	}


}
