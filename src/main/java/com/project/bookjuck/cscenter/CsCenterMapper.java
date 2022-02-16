package com.project.bookjuck.cscenter;

import com.project.bookjuck.cscenter.model.FaqEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsCenterMapper {
    List<FaqEntity> selFaqList();
}
