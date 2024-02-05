package kr.co.chunjae.template.service;

import kr.co.chunjae.template.dto.TemplateDTO;
import kr.co.chunjae.template.dto.TemplateSaveDTO;
import kr.co.chunjae.template.dto.TemplateThumbnailDTO;
import kr.co.chunjae.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TemplateService {
    private final TemplateRepository templateRepository;

    public void insertTemplate(TemplateSaveDTO requestDTO) {
        TemplateDTO templateDTO = TemplateDTO
                .builder()
                .typeIdx(requestDTO.getType())
                .display(requestDTO.getDisplay())
                .name(requestDTO.getName())
                .keyword(requestDTO.getKeyword())
                .explanation(requestDTO.getExplanation())
                .source(requestDTO.getSource())
                .subcateIdx(requestDTO.getSubCategory())
                .original(requestDTO.getTemplateMainFileDTO().getOriginal())
                .saved(requestDTO.getTemplateMainFileDTO().getSaved())
                .build();
        Integer insertTemplateResult = templateRepository.insertTemplate(templateDTO);
        if (insertTemplateResult != 1) {
            throw new RuntimeException("템플릿 정보를 저장하는 중 오류 발생");
        }

        Map<String, Object> templateThumbnailInsertMap = new HashMap<>();
        templateThumbnailInsertMap.put("templateIdx", templateDTO.getIdx());
        templateThumbnailInsertMap.put("thumbnailList", requestDTO.getTemplateThumbnailList());

        Integer thumbnailInsertResult = templateRepository.insertTemplateThumbnailList(templateThumbnailInsertMap);
        if (requestDTO.getTemplateThumbnailList().size() != thumbnailInsertResult) {
            throw new RuntimeException("템플릿 썸네일 정보를 저장하는 중 오류 발생");
        }
    }

    public List<TemplateThumbnailDTO> getThumbnailList(Integer idx) {
        return templateRepository.getThumbnailList(idx);
    }

    public TemplateDTO getTemplateByIdx(Integer idx) {
        return templateRepository.getTemplateByIdx(idx);
    }

    public void updateTemplate(TemplateSaveDTO requestDTO) {
        templateRepository.deleteTemplateThumbnailByIdx(requestDTO.getIdx());
        TemplateDTO templateUpdateDTO = TemplateDTO
                .builder()
                .idx(requestDTO.getIdx())
                .typeIdx(requestDTO.getType())
                .display(requestDTO.getDisplay())
                .name(requestDTO.getName())
                .keyword(requestDTO.getKeyword())
                .explanation(requestDTO.getExplanation())
                .source(requestDTO.getSource())
                .subcateIdx(requestDTO.getSubCategory())
                .original(requestDTO.getTemplateMainFileDTO().getOriginal())
                .saved(requestDTO.getTemplateMainFileDTO().getSaved())
                .build();

        templateRepository.updateTemplate(templateUpdateDTO);

        Map<String, Object> templateThumbnailInsertMap = new HashMap<>();
        templateThumbnailInsertMap.put("templateIdx", templateUpdateDTO.getIdx());
        templateThumbnailInsertMap.put("thumbnailList", requestDTO.getTemplateThumbnailList());

        Integer thumbnailInsertResult = templateRepository.insertTemplateThumbnailList(templateThumbnailInsertMap);
        if (requestDTO.getTemplateThumbnailList().size() != thumbnailInsertResult) {
            throw new RuntimeException("템플릿 썸네일 정보를 저장하는 중 오류 발생");
        }
    }
}
