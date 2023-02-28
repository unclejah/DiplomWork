package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long pk;
    private Long idAds;
    private Integer fileSize;
    private String mediaType;
}