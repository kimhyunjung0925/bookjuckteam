package com.project.bookjuck.cscenter;

import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.cscenter.model.ComplaintEntity;
import com.project.bookjuck.cscenter.model.FaqEntity;
import com.project.bookjuck.cscenter.model.NoticeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class CsCenterService {
    @Value("${spring.servlet.multipart.location}")
    String uploadFilePath;

    @Autowired
    private CsCenterMapper mapper;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    public List<FaqEntity> selFaqList(){

        return mapper.selFaqList();
    }

    public List<NoticeEntity> selNotice(){

        return mapper.selNotice();
    }

    public NoticeEntity selItemNotice(NoticeEntity entity){
        return mapper.selItemNotice(entity);
    }


    public int inscomplaint(ComplaintEntity entity, MultipartFile file) throws Exception{
        //String projectPath= System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        String targetPath = uploadFilePath + "/cs";
        UUID uuid = UUID.randomUUID();

        String fileNm = file.getOriginalFilename();
        String ext = fileNm.substring(fileNm.lastIndexOf("."));
        String fileName = uuid + ext;
        File targetFolder = new File(targetPath);
        if(!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        File saveFile = new File(targetPath, fileName);

        file.transferTo(saveFile);

        entity.setFilename(fileName);
        entity.setFilepath("/files/" + fileName);

        entity.setIuser(authenticationFacade.getLoginUserPk());
        return mapper.inscomplaint(entity);
    }




}
