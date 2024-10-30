package com.JAVA.DAO;

import java.util.List;

import com.JAVA.Beans.PersonBean;

public interface PersonDAO {
	 void create( PersonBean person ) throws DAOException;

	 PersonBean find( int id ) throws DAOException;

	List<PersonBean> findAll() throws DAOException;

	void delete(int id);
	 void update(PersonBean person);
	}