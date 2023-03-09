package com.yanhuo.serviceedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanhuo.serviceedu.entity.EduCourse;
import com.yanhuo.serviceedu.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String id);

}
