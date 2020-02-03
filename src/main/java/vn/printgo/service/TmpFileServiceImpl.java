
package vn.printgo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.printgo.dao.TmpFileDao;
import vn.printgo.entities.RProduct;
import vn.printgo.entities.RTmpFile;
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
	public List<RTmpFile> findLikeName(String name) {
		
		List<TmpFile> lFile = dao.findLikeName(name);
		List<RTmpFile>  result = lFile.stream().map(tmp -> {
			RTmpFile _rTF = new RTmpFile();
			_rTF.setName(tmp.getName());
			_rTF.setNameMd(tmp.getNameMd());
            return _rTF;
        }).collect(Collectors.toList());
			
		return result;
	}
	
	public TmpFile findLikeNameMd5(String fileName) {
		return dao.findLikeNameMd5(fileName);
	}
}
