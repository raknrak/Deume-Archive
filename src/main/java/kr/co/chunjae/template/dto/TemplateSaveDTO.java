package kr.co.chunjae.template.dto;

import kr.co.chunjae.contents.validation.ValidList;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TemplateSaveDTO {
    @NotNull(message = "콘텐츠 유형은 필수 입력값입니다")
    private Integer type;

    @NotNull(message = "노출 여부는 필수 입력값입니다")
    private Integer display;

    @NotBlank(message = "제목은 필수 입력값입니다")
    private String name;

    @NotNull(message = "키워드는 필수 입력값입니다")
    private String keyword;

    private String explanation = "";
    private String source = "";

    @NotNull(message = "서브 카테고리는 필수 입력값입니다")
    private Integer subCategory;

    @ValidList(message = "템플릿 썸네일 사진은 최소 한 개 이상이여야 합니다")
    @Valid
    private List<TemplateThumbnailDTO> templateThumbnailList;

    @NotNull(message = "템플릿 파일은 필수 입력값입니다")
    @Valid
    private TemplateMainFileDTO templateMainFileDTO;

    private Integer idx;
}
