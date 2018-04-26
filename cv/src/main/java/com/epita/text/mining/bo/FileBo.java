package com.epita.text.mining.bo;

import com.epita.text.mining.model.File;

public interface FileBo {

	void save(File file);

	void update(File file);

	void delete(File file);

	File findById(Long fileId);

}
