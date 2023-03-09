package com.yanhuo.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanhuo.commonutils.R;
import com.yanhuo.serviceedu.entity.EduCourse;
import com.yanhuo.serviceedu.entity.EduTeacher;
import com.yanhuo.serviceedu.service.EduCourseService;
import com.yanhuo.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("service_edu/index")
@CrossOrigin
public class IndexController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @Cacheable(value = "index", key = "'selectIndexList'")
    @GetMapping("OrAll")
    public R index(){
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(courseQueryWrapper);

        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherQueryWrapper);
        return R.ok().data("course",courseList).data("teacher",teacherList);
    }
}
