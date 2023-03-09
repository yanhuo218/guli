package com.yanhuo.serviceedu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.serviceedu.entity.EduTeacher;
import com.yanhuo.serviceedu.mapper.EduTeacherMapper;
import com.yanhuo.serviceedu.query.TeacherQuery;
import com.yanhuo.serviceedu.service.EduTeacherService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-09
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {

    }
}
