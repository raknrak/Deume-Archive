package kr.co.chunjae.template.repository;

import kr.co.chunjae.template.dto.TemplateDTO;
import kr.co.chunjae.template.dto.TemplateThumbnailDTO;
import kr.co.chunjae.template.mapper.TemplateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TemplateRepository {
    private final TemplateMapper templateMapper;

    public Integer insertTemplate(TemplateDTO templateDTO) {
        return templateMapper.insertTemplate(templateDTO);
    }

    public Integer insertTemplateThumbnailList(Map<String, Object> templateThumbnailInsertMap) {
        return templateMapper.insertTemplateThumbnailList(templateThumbnailInsertMap);
    }

    public List<TemplateThumbnailDTO> getThumbnailList(Integer idx) {
        return templateMapper.getThumbnailList(idx);
    }

    public TemplateDTO getTemplateByIdx(Integer idx) {
        return templateMapper.getTemplateByIdx(idx);
    }

    public void deleteTemplateThumbnailByIdx(Integer idx) {
        templateMapper.deleteTemplateThumbnailByIdx(idx);
    }

    public void updateTemplate(TemplateDTO templateUpdateDTO) {
        templateMapper.updateTemplate(templateUpdateDTO);
    }
}
