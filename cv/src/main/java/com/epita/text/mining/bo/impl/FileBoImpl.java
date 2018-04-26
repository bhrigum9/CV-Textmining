package com.epita.text.mining.bo.impl;

import com.epita.text.mining.bo.FileBo;
import com.epita.text.mining.dao.FileDao;
import com.epita.text.mining.model.File;

public class FileBoImpl implements FileBo {
	FileDao fileDao;

	/**
	 * @param fileDao
	 *            the fileDao to set
	 */
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public void save(File file) {
		fileDao.save(file);
	}

	@Override
	public void update(File file) {
		fileDao.update(file);
	}

	@Override
	public void delete(File file) {
		fileDao.delete(file);

	}

	@Override
	public File findById(Long fileId) {
		fileDao.findByStockCode(fileId);
		return null;
	}

}
