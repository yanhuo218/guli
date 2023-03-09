package com.yanhuo.serviceedu.controller;


import com.yanhuo.commonutils.R;
import com.yanhuo.serviceedu.entity.vo.SubjectNestedVO;
import com.yanhuo.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-15
 */
@CrossOrigin
@RestController
@RequestMapping("/service_edu/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<SubjectNestedVO> subjectServiceAll = subjectService.getAll();
        return R.ok().data("items", subjectServiceAll);
    }

}

