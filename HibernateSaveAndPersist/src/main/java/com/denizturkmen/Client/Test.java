package com.denizturkmen.Client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.denizturkmen.Entity.Employee;
import com.denizturkmen.Util.HibernateUtil;

public class Test {
	public static void main(String[] args) {
		
		//opensession kullandığım için transaction begin end commit edecez ama getcurrentsession otomatik yapıyor
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			session.beginTransaction();
			session.persist(getEmployee1());
			
			Integer id = (Integer) session.save(getEmployee2());
			System.out.println("Employee is created with id --"+id);
			
			session.merge(getEmployee4());
			session.evict(getEmployee1());
			
			session.saveOrUpdate(getEmployee3());
			
			//veritabanı yansıtması için
			session.getTransaction().commit();
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		
		
	}
	

	private static Employee getEmployee1() {
		Employee employee = new Employee();
		employee.setEmployeeName("Deniz Türkmen");
		employee.setDoj(new Date());
		employee.setEmail("turkmen_deniz@hotmail.com");
		employee.setSalary(15000.0);
		
		return employee;
	}
	
	private static Employee getEmployee2() {
		Employee employee = new Employee();
		employee.setEmployeeName("Besra Karaarslan");
		employee.setEmail("besra@hotmail.com");
		employee.setSalary(522000.0);
		employee.setDoj(new Date());
		
		return employee;
	}
	
	private static Employee getEmployee3() {
		Employee employee = new Employee();
		employee.setEmployeeName("Eren");
		employee.setEmail("Eren@hotmail.com");
		employee.setSalary(1.0);
		employee.setDoj(new Date());
		
		return employee;
	}
	
	private static Employee getEmployee4() {
		Employee employee = new Employee();
		employee.setEmployeeName("Merve");
		employee.setEmail("Merve@hotmail.com");
		employee.setSalary(5623.0);
		employee.setDoj(new Date());
		
		return employee;
	}
}
