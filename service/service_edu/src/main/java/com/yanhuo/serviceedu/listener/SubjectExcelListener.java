package com.yanhuo.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanhuo.servicebase.exceptionhandler.GuliException;
import com.yanhuo.serviceedu.entity.EduSubject;
import com.yanhuo.serviceedu.entity.excel.SubjectData;
import com.yanhuo.serviceedu.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "添加失败");
        }
        EduSubject existOneSubject = this.existOneSubject(subjectData.getOneSubjectName(), subjectService);
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), subjectService, pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }


    }

    private EduSubject existOneSubject(String name, EduSubjectService subjectService) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }

    private EduSubject existTwoSubject(String name, EduSubjectService subjectService, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
