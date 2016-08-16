package org.suree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suree.domain.TaiKyokuTable;
import org.suree.domain.TaiKyokuTableExample;
import org.suree.mappers.TaiKyokuTableMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sure on 8/15/16.
 */

@Service
public class TaiKyokuDao {

    @Resource
    private TaiKyokuTableMapper taiKyokuTableMapper;


    public TaiKyokuTable selectById(Integer id) {
        return taiKyokuTableMapper.selectByPrimaryKey(id);
    }

    public List<TaiKyokuTable> getByLogs(List<String> logs) {
        TaiKyokuTableExample taiKyokuTableExample = new TaiKyokuTableExample();
        taiKyokuTableExample.createCriteria().andLogcodeIn(logs);
        return taiKyokuTableMapper.selectByExampleWithBLOBs(taiKyokuTableExample);
    }

    public void insert(TaiKyokuTable taiKyokuTable) {
        taiKyokuTableMapper.insert(taiKyokuTable);
    }


}
