package com.project.bookjuck.cscenter;

import com.project.bookjuck.cscenter.model.FaqEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsCenterService {
    @Autowired
    private CsCenterMapper mapper;

    public List<FaqEntity> selFaqList(){
        return mapper.selFaqList();
    }
}
