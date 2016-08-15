package org.suree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suree.domain.TaiKyokuTable;
import org.suree.mappers.TaiKyokuTableMapper;

import javax.annotation.Resource;

/**
 * Created by Sure on 8/15/16.
 */

@Service
public class TaiKyokuDao {

    @Resource
    private TaiKyokuTableMapper taiKyokuTableMapper;


    public TaiKyokuTable selectById(Integer id) {
        return taiKyokuTableMapper.selectByPrimaryKey(1);
    }


}
