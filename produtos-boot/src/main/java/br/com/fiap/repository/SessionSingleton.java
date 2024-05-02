package br.com.fiap.repository;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class SessionSingleton {
	
	private static SessionSingleton instance;
	private Session session;
	
	private SessionSingleton() {
		Configuration cfg = new Configuration().configure();
		session = cfg.buildSessionFactory().openSession();
	}
	
	public static SessionSingleton getInstance() {
		if(instance == null) {
			instance = new SessionSingleton();
		}
		return instance;
	}
	
	public Session getSession() {
		return this.session;
	}
	
}
