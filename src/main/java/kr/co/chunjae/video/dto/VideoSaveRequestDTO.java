package kr.co.chunjae.video.dto;

import lombok.Data;

import java.util.List;

@Data
public class VideoSaveRequestDTO {

    private Integer type;
    private Integer subCategory;
    private Integer display;
    private String name;
    private String source;
    private String explanation;
    private String keyword;
    private VideoFileSaveRequestDTO fileSaveRequestDTO;
    private List<VideoThumbnailsSaveRequestDTO> thumbnailsSaveRequestList;


}
