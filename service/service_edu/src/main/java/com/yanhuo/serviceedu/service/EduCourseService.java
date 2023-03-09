package com.yanhuo.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.serviceedu.entity.EduCourse;
import com.yanhuo.serviceedu.entity.vo.CourseInfoVo;
import com.yanhuo.serviceedu.entity.vo.CoursePublishVo;
import com.yanhuo.serviceedu.entity.vo.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void getPageListQuery(Page<EduCourse> courses, CourseQuery courseQuery);

    boolean deleteCourse(String id);
}
