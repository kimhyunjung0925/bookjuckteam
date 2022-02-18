package com.project.bookjuck.cscenter;

import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.cscenter.model.ComplaintEntity;
import com.project.bookjuck.cscenter.model.FaqEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsCenterService {
    @Autowired
    private CsCenterMapper mapper;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    public List<FaqEntity> selFaqList(){
        return mapper.selFaqList();
    }

    public FaqEntity selFaqDetail(FaqEntity entity){
        return mapper.selFaqDetail(entity);

    }

    public int inscomplaint(ComplaintEntity entity){
        entity.setIuser(authenticationFacade.getLoginUserPk());
        return mapper.inscomplaint(entity);
    }
}
