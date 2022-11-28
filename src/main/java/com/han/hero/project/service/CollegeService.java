package com.han.hero.project.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.hero.common.enums.DelFlag;
import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.College;
import com.han.hero.project.domain.User;
import com.han.hero.project.mapper.CollegeMapper;
import com.han.hero.project.mapper.UserMapper;
import com.han.hero.project.vo.req.CollegeReqVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private UserMapper userMapper;

    public PageInfo<College> pageList(CollegeReqVo vo) {
        return PageHelper.startPage(vo).doSelectPageInfo(() -> collegeMapper.list(vo));
    }

    public void add(CollegeReqVo vo) {
        // 参数不能为空
        if (StringUtils.isBlank(vo.getCode()) || StringUtils.isBlank(vo.getName()) || vo.getAdminUserId() == null) {
            throw new ServiceException(ResultStatus.COMMON_PARAMS_NULL);
        }
        // code name adminUserId 不能重复
        validateUnique(vo);
        vo.setCreateBy(SecurityUtil.getUserId());
        collegeMapper.add(vo);
    }

    /**
     * 验证数据是否唯一
     */
    private void validateUnique(CollegeReqVo vo) {
        if (StringUtils.isNotBlank(vo.getCode())) {
            CollegeReqVo collegeReqVo = new CollegeReqVo();
            collegeReqVo.setCode(vo.getCode());
            List<College> list = collegeMapper.list(collegeReqVo);
            if (!list.isEmpty()) {
                throw new ServiceException(ResultStatus.COMMON_DATA_NOT_UNIQUE, "已存在code为" + vo.getCode() + "的院系，请勿重复填加");
            }
        }
        if (StringUtils.isNotBlank(vo.getName())) {
            CollegeReqVo collegeReqVo = new CollegeReqVo();
            collegeReqVo.setName(vo.getName());
            List<College> list = collegeMapper.list(collegeReqVo);
            if (!list.isEmpty()) {
                throw new ServiceException(ResultStatus.COMMON_DATA_NOT_UNIQUE, "已存在name为" + vo.getName() + "的院系，请勿重复填加");
            }
        }
        if (vo.getAdminUserId() != null) {
            CollegeReqVo collegeReqVo = new CollegeReqVo();
            collegeReqVo.setAdminUserId(vo.getAdminUserId());
            List<College> list = collegeMapper.list(collegeReqVo);
            if (!list.isEmpty()) {
                User user = userMapper.selectById(vo.getAdminUserId());
                if (user == null) {
                    throw new ServiceException(ResultStatus.COMMON_SERVER_ERROR);
                }
                throw new ServiceException(ResultStatus.COMMON_DATA_NOT_UNIQUE, "工号为" + user.getUserName() + "的管理员已被分配给其他院系，请勿重复分配");
            }
        }
    }

    public void update(CollegeReqVo vo) {
        if (vo.getId() == null) {
            throw new ServiceException(ResultStatus.COMMON_PARAMS_NULL);
        }
        vo.setUpdateBy(SecurityUtil.getUserId());
        validateUnique(vo);
        collegeMapper.update(vo);
    }

    public void del(CollegeReqVo vo) {
        if (vo.getId() == null) {
            throw new ServiceException(ResultStatus.COMMON_PARAMS_NULL);
        }
        vo.setDelFLag(DelFlag.Del);
        update(vo);
    }
}
