package br.com.fiap.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.BandaModel;

@Repository
public class BandaRepository {
	
	private Session session;
	private Transaction tx;
	
	private Session sessionUpdate;
	private Transaction txUpdate;
	
	public BandaRepository() {
		
	}
	
	public void startOperation() {
		SessionSingleton sessionSingleton = SessionSingleton.getInstance();
		this.session = sessionSingleton.getSession();
		System.out.println("\n Session sendo usada no startOperation => "+session.toString()+"\n");
		this.tx = session.beginTransaction();
	}
	
	public void startOperationUpdate() {
		Configuration cfg = new Configuration().configure();
		sessionUpdate = cfg.buildSessionFactory().openSession();
		this.txUpdate = sessionUpdate.beginTransaction();
	}
	
	public List<BandaModel> findAll() {
		List<BandaModel> retorno = null;
		
		try {
			startOperation();
			Query<BandaModel> query = session.createQuery("FROM BandaModel");
			retorno = query.list(); 
			tx.commit();
			
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>> Erro no findAll - Bandas" + e);
		}
		
		return retorno;
	}
	
	public BandaModel findById(Long id) {
		BandaModel retorno = null;
		try {
			startOperation();
			Query<BandaModel> query = session.createQuery("FROM BandaModel WHERE ID = '" +id+"'");
			retorno = query.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>> Erro no findById - Bandas" + e);
		}
		return retorno;
	}
	
	public void saveBanda(BandaModel banda) {
		try {
			startOperation();
			session.save(banda);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>>> Erro no save - Bandas");
		}
	}
	
	public void updateBanda(BandaModel banda) {
		try {
			startOperationUpdate();
			sessionUpdate.update(banda);
			txUpdate.commit();
			System.out.println("\n >>>>>>>>>>>>>>>>>>>> Session de update : "+sessionUpdate.toString()+"\n");
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>>>> Erro no update - Bandas" + e);
		}
	}
	
	public void deleteBanda(BandaModel banda) {
		try {
			startOperation();
			session.delete(banda);
			tx.commit(); 
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>>>> Erro no delete - Bandas");
		}
	}
	
	
	
}
