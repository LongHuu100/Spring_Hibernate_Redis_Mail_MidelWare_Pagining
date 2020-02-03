
package vn.printgo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.printgo.model.TmpFile;

@Repository("tmpFileDao")
public class TmpFileDaoImpl extends AbstractDao<Integer, TmpFile> implements TmpFileDao {

    static final Logger logger = LoggerFactory.getLogger(TmpFileDaoImpl.class);
	@Override
	public TmpFile findByName(String name) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        TmpFile tmpFile = (TmpFile) crit.uniqueResult();
        return tmpFile;
	}

	@Override
	public void save(TmpFile tmpFile) {
		persist(tmpFile);
	}

	@Override
	public void deleteByName(String name) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        TmpFile tmpFile = (TmpFile) crit.uniqueResult();
        if(tmpFile != null)
        	delete(tmpFile);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<TmpFile> findLikeName(String name) {
		String QUERY = "FROM tmp_file as f WHERE f.name LIKE :fName";
		Session session = getSession();
		List<TmpFile> lTmp = session.createQuery(QUERY).setString("fName", name + "%").list();
		return lTmp;
	}
	
	public TmpFile findLikeNameMd5(String fileName) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("nameMd", fileName));
        TmpFile tmpFile = (TmpFile) crit.uniqueResult();
        logger.info("tmpFile: {}", tmpFile);
		return tmpFile;
	}
}
