package com.han.hero.project.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.DictType;
import com.han.hero.project.domain.DictTypeData;
import com.han.hero.project.mapper.DictMapper;
import com.han.hero.project.vo.req.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DictService {

    @Autowired
    private DictMapper dictMapper;

    public PageInfo<?> typePageList(TypeListReqVo vo) {
        return PageHelper.startPage(vo).doSelectPageInfo(() -> dictMapper.typeList(vo));
    }

    public void typeAdd(TypeAddReqVo vo) {
        DictType dictType = new DictType();
        dictType.setDictName(vo.getDictName());
        dictType.setDictType(vo.getDictType());
        dictType.setRemark(vo.getRemark());
        dictType.setDelFlag(vo.getState());
        dictType.setCreateBy(SecurityUtil.getUserId());
        dictMapper.typeAdd(dictType);
    }

    public void typeUpdate(TypeUpdateReqVo vo) {
        Integer userId = SecurityUtil.getUserId();
        String dictType = vo.getDictType();
        if (StringUtils.isNotBlank(dictType)) {
            DataListReqVo dataListReqVo = new DataListReqVo();
            dataListReqVo.setDictType(dictType);
            List<DictTypeData> list = dictMapper.dataList(dataListReqVo);
            if (!list.isEmpty()) {
                for (DictTypeData dictTypeData : list) {
                    dictTypeData.setUpdateBy(userId);
                    dictTypeData.setDictType(dictType);
                    dictMapper.dataUpdate(dictTypeData);
                }
            }
        }
        DictType update = new DictType();
        update.setDictId(vo.getDictId());
        update.setDictName(vo.getDictName());
        update.setDictType(vo.getDictType());
        update.setDelFlag(vo.getState());
        update.setUpdateBy(userId);
        update.setRemark(vo.getRemark());
        dictMapper.typeUpdate(update);
    }

    public void typeDel(Integer dictId) {
        DictType dictType = dictMapper.selectTypeById(dictId);
        if (dictType == null) {
            throw new ServiceException(ResultStatus.COMMON_DATA_NOT_EXIST);
        }
        DataListReqVo vo = new DataListReqVo();
        vo.setDictType(dictType.getDictType());
        List<DictTypeData> list = dictMapper.dataList(vo);
        if (list.isEmpty()) {
            dictMapper.typeDel(dictId);
        } else {
            throw new ServiceException(ResultStatus.COMMON_DATA_HAS_USED);
        }
    }

    public PageInfo<?> dataPageList(DataListReqVo vo) {
        return PageHelper.startPage(vo)
                .setOrderBy("dictType")
                .setOrderBy("orderNum")
                .doSelectPageInfo(() -> dictMapper.dataList(vo));
    }

    public void dataAdd(DataAddReqVo vo) {
        vo.setCreateBy(SecurityUtil.getUserId());
        dictMapper.dataAdd(vo);
    }

    public void dataDel(Integer dictCode) {
        dictMapper.dataDel(dictCode);
    }


    public void dataUpdate(DataUpdateReqVo vo) {
        DictTypeData dictTypeData = new DictTypeData();
        dictTypeData.setDictCode(vo.getDictCode());
        dictTypeData.setOrderNum(vo.getOrderNum());
        dictTypeData.setDictLabel(vo.getDictLabel());
        dictTypeData.setDictValue(vo.getDictValue());
        dictTypeData.setDictType(vo.getDictType());
        dictTypeData.setDefaultState(vo.getDefaultState());
        dictTypeData.setDelFlag(vo.getState());
        dictTypeData.setRemark(vo.getRemark());
        dictTypeData.setUpdateBy(SecurityUtil.getUserId());
        dictMapper.dataUpdate(dictTypeData);
    }
}
