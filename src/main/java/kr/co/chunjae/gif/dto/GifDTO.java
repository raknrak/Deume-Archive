package kr.co.chunjae.gif.dto;

import kr.co.chunjae.common.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@SuperBuilder
public class GifDTO extends BaseDTO {
  private Integer maincateIdx;
  private List<GifThumbnailDTO> gifThumbnailList;
}
