package kr.co.chunjae.template.mapper;

import kr.co.chunjae.template.dto.TemplateDTO;
import kr.co.chunjae.template.dto.TemplateThumbnailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TemplateMapper {
    Integer insertTemplate(TemplateDTO templateDTO);

    Integer insertTemplateThumbnailList(Map<String, Object> templateThumbnailInsertMap);

    List<TemplateThumbnailDTO> getThumbnailList(Integer idx);

    TemplateDTO getTemplateByIdx(Integer idx);

    void deleteTemplateThumbnailByIdx(Integer idx);

    void updateTemplate(TemplateDTO templateUpdateDTO);
}
