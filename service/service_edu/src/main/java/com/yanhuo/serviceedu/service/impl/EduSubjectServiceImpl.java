package com.yanhuo.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.serviceedu.entity.EduSubject;
import com.yanhuo.serviceedu.entity.excel.SubjectData;
import com.yanhuo.serviceedu.entity.vo.SubjectNestedVO;
import com.yanhuo.serviceedu.entity.vo.SubjectVo;
import com.yanhuo.serviceedu.listener.SubjectExcelListener;
import com.yanhuo.serviceedu.mapper.EduSubjectMapper;
import com.yanhuo.serviceedu.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SubjectNestedVO> getAll() {
        List<SubjectNestedVO> subjectNestedVOList = new ArrayList<>();


        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> OneSubjects = baseMapper.selectList(queryWrapper);

        QueryWrapper<EduSubject> Wrapper = new QueryWrapper<>();
        Wrapper.ne("parent_id", 0);
        Wrapper.orderByAsc("sort", "id");
        List<EduSubject> TwoSubjects = baseMapper.selectList(Wrapper);

        int count = OneSubjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = OneSubjects.get(i);
            SubjectNestedVO subjectNestedVO = new SubjectNestedVO();
            BeanUtils.copyProperties(subject, subjectNestedVO);
            subjectNestedVOList.add(subjectNestedVO);

            ArrayList<SubjectVo> subjectVos = new ArrayList<>();
            int count2 = TwoSubjects.size();
            for (int j = 0; j < count2; j++) {
                EduSubject subject1 = TwoSubjects.get(j);
                if (subject1.getParentId().equals(subject.getId())) {
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subject1, subjectVo);
                    subjectVos.add(subjectVo);
                }
            }
            subjectNestedVO.setChildren(subjectVos);
        }
        return subjectNestedVOList;
    }
}
