package com.itech.hrms.dao;



import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.itech.hrms.model.Employee;


public class EmployeeeDaoImpl implements EmployeeDao{

	Session session = null;
	@Autowired
	SessionFactory sessionFactory ;
	Transaction txn = null;

	public Employee employeeRegisterService(Employee employee) {

		session = sessionFactory.openSession();
		System.out.println("Session opened::"+session);
		txn = session.beginTransaction();
		System.out.println("txn began..");
		session.saveOrUpdate(employee);
		System.out.println("method runs..");
		txn.commit();
		session.close();
		return employee;
	}

	public boolean authLoginService(int id, String password) {
		try{
			System.out.println("request id :"+id);
			System.out.println("request password :"+password);

			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			System.out.println("session created..");


			Query query=  session.createQuery("from Employee where id=:emp_id and password=:emp_pwd");

			query.setParameter("emp_id",id);
			query.setParameter("emp_pwd",password);
			//System.out.println("Query ::"+query.getFetchSize());
			List l =query.list();
			System.out.println("Total Number Of Records : "+l.size());
			if(l.size()>0){
				System.out.println(" Login Success");
				return true;
			}else{
				System.out.println(" Login Failure");
				return false;
			} 
		}
		catch(Exception e){
			System.out.println("Exception ::"+e);
		}
		txn.commit();
		session.close();
		return false;
	}

	public boolean setPasswordService(int id, String password, String old_password) {
		try{
			System.out.println("request id :"+id);
			System.out.println("request password :"+password);
			System.out.println("request password :"+old_password);

			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			System.out.println("session created..");

			Query query1=  session.createQuery("from Employee where id="+id+" and password="+old_password+"");
			List l =query1.list();
			System.out.println("Total Number Of Records : "+l.size());
			if(l.size()>0){
				Query query = session.createQuery("UPDATE Employee SET password = "+password+" WHERE id = "+id+" ");
				System.out.println(query);
				int rowsAffected = query.executeUpdate();

				if(rowsAffected>0){
					System.out.println("changed...");
					txn.commit();
					session.close();
					return true;
				}else{System.out.println("Not Changed");}}
			}catch(Exception e){
					System.out.println("Exception ::"+e);
				}
	return false;
}
}
