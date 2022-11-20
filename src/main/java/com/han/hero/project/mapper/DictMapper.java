package com.han.hero.project.mapper;

import com.han.hero.project.domain.DictData;
import com.han.hero.project.domain.DictType;
import com.han.hero.project.vo.req.DictDataAddReqVo;
import com.han.hero.project.vo.req.DictDataListReqVo;
import com.han.hero.project.vo.req.DictTypeListReqVo;

import java.util.List;

public interface DictMapper {

    List<DictType> typeList(DictTypeListReqVo vo);

    void typeAdd(DictType dictType);

    List<DictData> dictDataList(DictDataListReqVo vo);

    void dictDataUpdate(DictData dictData);

    void typeUpdate(DictType dictType);

    DictType selectTypeById(Integer dictId);

    void typeDel(Integer dictId);

    void dictDataAdd(DictDataAddReqVo vo);

    void dictDataDel(Integer dictCode);
}
