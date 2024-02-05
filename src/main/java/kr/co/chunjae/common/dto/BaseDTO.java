package kr.co.chunjae.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
@SuperBuilder
public abstract class BaseDTO {
    private Integer idx;
    private Integer subcateIdx;
    private Integer typeIdx;
    private String name;
    private String source;
    private String explanation;
    private Integer display;
    private String original;
    private String saved;
    private String uploadPath;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String keyword;
}
