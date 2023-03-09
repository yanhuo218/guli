package com.yanhuo.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanhuo.commonutils.R;
import com.yanhuo.serviceedu.entity.EduCourse;
import com.yanhuo.serviceedu.entity.EduCourseDescription;
import com.yanhuo.serviceedu.entity.vo.CourseInfoVo;
import com.yanhuo.serviceedu.entity.vo.CoursePublishVo;
import com.yanhuo.serviceedu.entity.vo.CourseQuery;
import com.yanhuo.serviceedu.service.EduCourseDescriptionService;
import com.yanhuo.serviceedu.service.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/service_edu/course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduCourseDescriptionService descriptionService;

    @PostMapping("getPageListQuery/{page}/{limit}")
    public R getCourseList(@PathVariable Long limit, @PathVariable Long page, @RequestBody(required = false) CourseQuery courseQuery) {
        Page<EduCourse> courses = new Page<>(page, limit);
        courseService.getPageListQuery(courses, courseQuery);
        long total = courses.getTotal();
        List<EduCourse> records = courses.getRecords();
        return R.ok().data("list", records).data("total", total);
    }


    @PostMapping("addCourseInfo")
    public R AddCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return id != null ? R.ok().data("id", id) : R.error().message("失败");
    }

    @GetMapping("getCourse/{id}")
    public R GetDataCourse(@PathVariable String id) {
        EduCourse Course = courseService.getById(id);
        EduCourseDescription description = descriptionService.getById(id);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(Course, courseInfoVo);
        courseInfoVo.setDescription(description.getDescription());
        return R.ok().data("courseInfo", courseInfoVo);
    }

    @PutMapping("updateCourse")
    public R UpdateCourse(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("course", coursePublishVo);
    }

    @PutMapping("setCoursePublish/{id}")
    public R setCoursePublish(@PathVariable String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        boolean update = courseService.updateById(course);
        return update ? R.ok() : R.error();
    }

    @GetMapping("ifStatus/{id}")
    public R ifStatus(@PathVariable String id) {
        EduCourse byId = courseService.getById(id);
        String status = byId.getStatus();
        if (Objects.equals(status, "Draft")) {
            return R.ok();
        }
        return R.error();
    }

    @DeleteMapping("delete/{id}")
    public R deleteCourse(@PathVariable String id) {
        boolean remove = courseService.deleteCourse(id);
        return remove ? R.ok() : R.error();
    }
}

