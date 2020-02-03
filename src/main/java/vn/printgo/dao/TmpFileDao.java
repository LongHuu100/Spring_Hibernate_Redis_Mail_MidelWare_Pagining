
package vn.printgo.dao;

import java.util.List;

import vn.printgo.model.TmpFile;

public interface TmpFileDao {
    TmpFile findByName(String name);
    void save(TmpFile tmpFile);
    void deleteByName(String name);
    List<TmpFile> findLikeName(String name);
    TmpFile findLikeNameMd5(String name);
}