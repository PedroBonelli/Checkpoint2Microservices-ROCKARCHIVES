package br.com.fiap.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.AlbumModel;

@Repository
public class AlbumRepository {

	private Session session;
	private Transaction tx;
	
	private Session sessionUpdate;
	private Transaction txUpdate;
	
	public AlbumRepository() {
		
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
	
	public List<AlbumModel> findAll(){
		List<AlbumModel> retorno = null;
		
		try {
			startOperation();
			Query<AlbumModel> query = session.createQuery("FROM AlbumModel");
			retorno = query.list();
			tx.commit(); 
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>> Erro no findAll - Albums" + e);
		}
		
		return retorno;
	}
	
	public AlbumModel findById(Long id) {
		AlbumModel retorno = null;
		
		try {
			startOperation();
			Query<AlbumModel> query = session.createQuery("FROM AlbumModel WHERE ID = '" +id+"'");
			retorno = query.getSingleResult();
			tx.commit(); 
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>> Erro no findById - Albums" + e);
		}
		return retorno;
	}
	
	public void saveAlbum(AlbumModel album) {
		try {
			startOperation();
			session.save(album);
			tx.commit(); 
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>> Erro no save - Albums"+e);
		}
	}
	
	public void updateAlbum(AlbumModel album) {
		try {
			startOperationUpdate();
			sessionUpdate.update(album);
			txUpdate.commit();
			System.out.println("\n >>>>>>>>>>>>>>>>>>>> Session de update : "+sessionUpdate.toString()+"\n");
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>>>>>>>> Erro no update - Albums");
		}
	}
	
	public void deleteAlbum(AlbumModel album) {
		try {
			startOperation();
			session.delete(album);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("\n >>>>>>>>>>>>>>>>>> Erro no delete - Albums");
		}
	}
}
