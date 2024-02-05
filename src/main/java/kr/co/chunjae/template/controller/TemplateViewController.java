package kr.co.chunjae.template.controller;

import kr.co.chunjae.template.dto.TemplateDTO;
import kr.co.chunjae.template.dto.TemplateThumbnailDTO;
import kr.co.chunjae.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/template")
@RequiredArgsConstructor
public class TemplateViewController {
    private final TemplateService templateService;
    @GetMapping("/write")
    public String writeForm() {
        return "template/write";
    }

    @GetMapping("/detail/{idx}")
    public String templateList(@PathVariable Integer idx,Model model){
        List<TemplateThumbnailDTO> templateThumbnailDTOList = templateService.getThumbnailList(idx);
        log.info(templateThumbnailDTOList.toString());
        TemplateDTO templateDTO = templateService.getTemplateByIdx(idx);
        String[] keywordList = templateDTO.getKeyword().split(",");

        model.addAttribute("templateList", templateThumbnailDTOList);
        model.addAttribute("templateData",templateDTO);
        model.addAttribute("keywordList", keywordList);
        return "template/detail";
    }

    @GetMapping("/update/{idx}")
    public String updateForm(@PathVariable Integer idx,Model model){
        TemplateDTO templateDTO = templateService.getTemplateByIdx(idx);
        String[] keywordList = templateDTO.getKeyword().split(",");

        model.addAttribute("templateData",templateDTO);
        model.addAttribute("keywordList", keywordList);

        return "template/update";
    }
}
