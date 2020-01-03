
package vn.printgo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.printgo.dao.TmpFileDao;
import vn.printgo.model.TmpFile;

@Service("tmpFileService")
@Transactional
public class TmpFileServiceImpl implements TmpFileService {
	
	@Autowired
    private TmpFileDao dao;
	
	@Override
	public TmpFile findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public void save(TmpFile tmpFile) {
		dao.save(tmpFile);
	}

	@Override
	public void deleteByName(String name) {
		dao.deleteByName(name);
	}

	@Override
	public List<TmpFile> findLikeName(String name) {
		List<TmpFile> lFile = dao.findLikeName(name);
		return lFile;
	}
}
