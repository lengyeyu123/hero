package com.han.hero.project.mapper;

import com.han.hero.project.domain.DictType;
import com.han.hero.project.domain.DictTypeData;
import com.han.hero.project.vo.req.DataAddReqVo;
import com.han.hero.project.vo.req.DataListReqVo;
import com.han.hero.project.vo.req.TypeListReqVo;

import java.util.List;

public interface DictMapper {

    List<DictType> typeList(TypeListReqVo vo);

    void typeAdd(DictType dictType);

    List<DictTypeData> dataList(DataListReqVo vo);

    void dataUpdate(DictTypeData dictTypeData);

    void typeUpdate(DictType dictType);

    DictType selectTypeById(Integer dictId);

    void typeDel(Integer dictId);

    void dataAdd(DataAddReqVo vo);

    void dataDel(Integer dictCode);
}
