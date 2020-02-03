/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.printgo.service;

import java.util.List;

import vn.printgo.entities.RTmpFile;
import vn.printgo.model.TmpFile;

public interface TmpFileService {
	TmpFile findByName(String name);
    void save(TmpFile tmpFile);
    void deleteByName(String name);
    List<RTmpFile> findLikeName(String name);
    TmpFile findLikeNameMd5(String name);
}
