package com.yanhuo.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.servicebase.exceptionhandler.GuliException;
import com.yanhuo.serviceedu.entity.EduCourse;
import com.yanhuo.serviceedu.entity.EduCourseDescription;
import com.yanhuo.serviceedu.entity.vo.CourseInfoVo;
import com.yanhuo.serviceedu.entity.vo.CoursePublishVo;
import com.yanhuo.serviceedu.entity.vo.CourseQuery;
import com.yanhuo.serviceedu.mapper.EduCourseMapper;
import com.yanhuo.serviceedu.service.EduChapterService;
import com.yanhuo.serviceedu.service.EduCourseDescriptionService;
import com.yanhuo.serviceedu.service.EduCourseService;
import com.yanhuo.serviceedu.service.EduVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
@Slf4j
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescription;

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new GuliException(20001, "添加失败");
        }
        EduCourseDescription Description = new EduCourseDescription();
        String courseId = eduCourse.getId();
        Description.setId(courseId);
        Description.setDescription(courseInfoVo.getDescription());
        boolean save = courseDescription.save(Description);
        return save ? courseId : "0";
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        baseMapper.updateById(eduCourse);

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    public void getPageListQuery(Page<EduCourse> page, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (courseQuery == null) {
            baseMapper.selectPage(page, queryWrapper);
            return;
        }
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean deleteCourse(String courseId) {
        videoService.removeVideoCourseId(courseId);
        chapterService.removeChapterCourseId(courseId);
        courseDescription.removeDesCourseId(courseId);
        baseMapper.deleteById(courseId);
        return true;
    }
}
