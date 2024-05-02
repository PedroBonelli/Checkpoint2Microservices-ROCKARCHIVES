package br.com.fiap.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.IntegranteModel;

@Repository
public class IntegranteRepository {

	private Session session;
	private Transaction tx;
	
	private Session sessionUpdate;
	private Transaction txUpdate;
	
	public IntegranteRepository() {
		
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
	
	//removi o finally session.close da função
	
	public List<IntegranteModel> findAll(){
		List<IntegranteModel> retorno = null;
		
		try {
			startOperation();
			Query<IntegranteModel> query = session.createQuery("FROM IntegranteModel");
			retorno = query.list();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>> Erro no findAll - Integrantes" + e);
		}
		
		return retorno;
	}
	
	public IntegranteModel findById(Long id) {
		IntegranteModel retorno = null;
		
		try {
			startOperation();
			Query<IntegranteModel> query = session.createQuery("FROM IntegranteModel WHERE ID = '"+id+"'");
			retorno = query.getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>> Erro no findById - Integrantes");
		} 
		
		return retorno;
	}
	
	public void saveIntegrante(IntegranteModel integrante) {
		try {
			startOperation();
			session.save(integrante);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>> Erro no save - Integrantes");
		} 
	}
	
	public void updateIntegrante(IntegranteModel integrante) {
		try {
			startOperationUpdate();
			sessionUpdate.update(integrante);
			txUpdate.commit();
			System.out.println("\n >>>>>>>>>>>>>>>>>>>> Session de update : "+sessionUpdate.toString()+"\n");
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>>>> Erro no update - Integrantes");
		} finally {
			sessionUpdate.close();
		}
	}
	
	public void deleteIntegrante(IntegranteModel integrante) {
		try {
			startOperation();
			session.delete(integrante);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>> Erro no delete - Integrantes");
		} 
	}
	
}
