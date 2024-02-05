package kr.co.chunjae.video.dto;

import kr.co.chunjae.common.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter @Setter @ToString
@NoArgsConstructor
@SuperBuilder
public class VideoDTO extends BaseDTO {
    // 여기에다가 필요한 필드 추가
    private String test;
}
