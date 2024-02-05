package kr.co.chunjae.contents.dto;

import kr.co.chunjae.common.dto.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@SuperBuilder
public class ContentsDTO extends BaseDTO {
    // 여기에 필요한 필드 추가
    private Integer maincateIdx;
    private List<ContentsThumbnailDTO> contentsThumbnailList;
}
