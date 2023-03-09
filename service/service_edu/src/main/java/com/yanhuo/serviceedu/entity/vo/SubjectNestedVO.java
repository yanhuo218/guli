package com.yanhuo.serviceedu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectNestedVO {
    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}
