package com.epita.text.mining.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.epita.text.mining.dao.FileDao;
import com.epita.text.mining.model.File;

public class FileDaoImpl extends HibernateDaoSupport implements FileDao {

	@Override
	public void save(File file) {
		getHibernateTemplate().save(file);
	}

	@Override
	public void update(File file) {
		getHibernateTemplate().update(file);
	}

	@Override
	public void delete(File file) {
		getHibernateTemplate().delete(file);
	}

	@SuppressWarnings("deprecation")
	@Override
	public File findByStockCode(Long fileId) {
		List list = getHibernateTemplate().find("from FILE where fileId=?", fileId);
		return (File) list.get(0);

	}

}
