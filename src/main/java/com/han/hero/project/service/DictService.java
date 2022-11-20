package com.han.hero.project.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.DictData;
import com.han.hero.project.domain.DictType;
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

    public PageInfo<?> typePageList(DictTypeListReqVo vo) {
        return PageHelper.startPage(vo).doSelectPageInfo(() -> dictMapper.typeList(vo));
    }

    public void typeAdd(DictTypeAddReqVo vo) {
        DictType dictType = new DictType();
        dictType.setName(vo.getName());
        dictType.setType(vo.getType());
        dictType.setRemark(vo.getRemark());
        dictType.setDelFlag(vo.getDelFlag());
        dictType.setCreateBy(SecurityUtil.getUserId());
        dictMapper.typeAdd(dictType);
    }

    public void typeUpdate(DictTypeUpdateReqVo vo) {
        Integer userId = SecurityUtil.getUserId();
        String dictType = vo.getType();
        if (StringUtils.isNotBlank(dictType)) {
            DictDataListReqVo dictDataListReqVo = new DictDataListReqVo();
            dictDataListReqVo.setType(dictType);
            List<DictData> list = dictMapper.dictDataList(dictDataListReqVo);
            if (!list.isEmpty()) {
                for (DictData dictData : list) {
                    dictData.setUpdateBy(userId);
                    dictData.setType(dictType);
                    dictMapper.dictDataUpdate(dictData);
                }
            }
        }
        DictType update = new DictType();
        update.setId(vo.getId());
        update.setName(vo.getName());
        update.setType(vo.getType());
        update.setDelFlag(vo.getDelFlag());
        update.setUpdateBy(userId);
        update.setRemark(vo.getRemark());
        dictMapper.typeUpdate(update);
    }

    public void typeDel(Integer dictId) {
        DictType dictType = dictMapper.selectTypeById(dictId);
        if (dictType == null) {
            throw new ServiceException(ResultStatus.COMMON_DATA_NOT_EXIST);
        }
        DictDataListReqVo vo = new DictDataListReqVo();
        vo.setType(dictType.getType());
        List<DictData> list = dictMapper.dictDataList(vo);
        if (list.isEmpty()) {
            dictMapper.typeDel(dictId);
        } else {
            throw new ServiceException(ResultStatus.COMMON_DATA_HAS_USED);
        }
    }

    public PageInfo<?> dataPageList(DictDataListReqVo vo) {
        return PageHelper.startPage(vo)
                .setOrderBy("dictType")
                .setOrderBy("orderNum")
                .doSelectPageInfo(() -> dictMapper.dictDataList(vo));
    }

    public void dataAdd(DictDataAddReqVo vo) {
        vo.setCreateBy(SecurityUtil.getUserId());
        dictMapper.dictDataAdd(vo);
    }

    public void dataDel(Integer dictCode) {
        dictMapper.dictDataDel(dictCode);
    }


    public void dataUpdate(DictDataUpdateReqVo vo) {
        DictData dictData = new DictData();
        dictData.setId(vo.getId());
        dictData.setOrderNum(vo.getOrderNum());
        dictData.setLabel(vo.getLabel());
        dictData.setVal(vo.getVal());
        dictData.setType(vo.getType());
        dictData.setDefaultState(vo.getDefaultState());
        dictData.setDelFlag(vo.getState());
        dictData.setRemark(vo.getRemark());
        dictData.setUpdateBy(SecurityUtil.getUserId());
        dictMapper.dictDataUpdate(dictData);
    }
}
