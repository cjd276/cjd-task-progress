package com.cjd.rescue.product.service;

import com.cjd.rescue.api.common.KvTypeApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.KvTypeMapper;
import com.cjd.rescue.entity.common.DeleteAndAddKvTypeParams;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.KvType;
import com.cjd.rescue.entity.common.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class KvTypeService implements KvTypeApi {


    @Autowired
    private KvTypeMapper kvTypeMapper;

    @SysLog
    @JDBCException
    @Override
    public ReturnT list() {
        Example example = new Example(KvType.class);
        example.setOrderByClause("kv_sort desc");
        List<KvType> list = kvTypeMapper.selectByExample(example);
        return ReturnT.result(Err.SUCCESS).dataMap("list",list);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT deleteAndAdd(DeleteAndAddKvTypeParams kvTypes) {
        List<KvType> list = kvTypes.getKvTypeList();

        if(null != list && list.size() > 0){
            Example example = new Example(KvType.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andNotEqualTo("id","sys_1");
            kvTypeMapper.deleteByExample(example);
            for (KvType kvType:list){
                if(!"sys_1".equals(kvType.getId())){
                    kvType.setId(IdUtil.generateID());
                    kvTypeMapper.insertSelective(kvType);
                }

            }
        }
        return ReturnT.result(Err.SUCCESS);
    }

}
