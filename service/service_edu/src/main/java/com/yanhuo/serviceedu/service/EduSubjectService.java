package com.yanhuo.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.serviceedu.entity.EduSubject;
import com.yanhuo.serviceedu.entity.vo.SubjectNestedVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectNestedVO> getAll();
}
