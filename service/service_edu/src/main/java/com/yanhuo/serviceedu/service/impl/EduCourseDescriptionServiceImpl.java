package com.yanhuo.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.serviceedu.entity.EduCourseDescription;
import com.yanhuo.serviceedu.mapper.EduCourseDescriptionMapper;
import com.yanhuo.serviceedu.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public boolean removeDesCourseId(String courseId) {
        QueryWrapper<EduCourseDescription> DescriptionWrapper = new QueryWrapper<>();
        DescriptionWrapper.eq("id", courseId);
        return baseMapper.delete(DescriptionWrapper) != 0;
    }
}
