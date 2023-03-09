package com.yanhuo.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.serviceedu.entity.EduCourseDescription;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    boolean removeDesCourseId(String courseId);
}
