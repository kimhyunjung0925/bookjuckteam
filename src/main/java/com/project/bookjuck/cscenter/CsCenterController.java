package com.project.bookjuck.cscenter;

import com.project.bookjuck.Const;
import com.project.bookjuck.cscenter.model.ComplaintEntity;
import com.project.bookjuck.cscenter.model.FaqEntity;
import com.project.bookjuck.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.project.bookjuck.Const.ERR_5;

@Controller
@RequestMapping("/cscenter")
public class CsCenterController {
    @Autowired
    private CsCenterService service;

    @GetMapping
    public String CsCenter() {
        return "cscenter/cscenter";
    }

    @GetMapping("/notice")
    public String CsNotice() {
        return "cscenter/notice";
    }

    @GetMapping("/complaint")
    public String comPlaint(@ModelAttribute("complaintentity") ComplaintEntity complaintEntity) {
        return "cscenter/complaint";
    }

    @GetMapping("/faq")
    public String FAQ(FaqEntity entity, Model model) {
        model.addAttribute("Faqdata", service.selFaqDetail(entity));
        return "cscenter/faqdetail";
    }

    @PostMapping("/complaint")
    public String comPlaintProc(ComplaintEntity complaintEntity, RedirectAttributes attr){
        int result = service.inscomplaint(complaintEntity);
        switch (result){
            case 0 :
                attr.addFlashAttribute(Const.MSG, ERR_5);
                return "redirect:/cscenter/complaint";
            default:
                return "redirect:/cscenter";
        }
    }
}
