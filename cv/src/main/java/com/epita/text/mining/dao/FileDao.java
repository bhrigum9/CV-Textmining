package com.epita.text.mining.dao;

import com.epita.text.mining.model.File;

public interface FileDao {

	public void save(File file);

	public void update(File file);

	public void delete(File file);

	File findByStockCode(Long fileId);
}
