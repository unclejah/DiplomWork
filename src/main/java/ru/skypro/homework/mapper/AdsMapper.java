package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Mapper
public interface AdsMapper {
    @Mapping(source = "author.id", target = "author")
    AdsDto adsToAdsDto(Ads ads);

    List<AdsDto> adsToAdsDto(List<Ads> ads);

    @Mapping(source = "author", target = "author.id")
    @Mapping(target = "description", ignore = true)
    Ads adsDtoToAds(AdsDto adsDto);

    List<Ads> adsDtoToAds(List<AdsDto> adsDto);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "pk", ignore = true)
    Ads createAdsToAds(CreateAdsDto createAds);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "pk.pk", target = "pk")
    CommentDto adsCommentToAdsCommentDto(Comment adsComment);

    List<CommentDto> adsCommentToAdsCommentDto(List<Comment> adsComment);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "pk.pk")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment adsCommentDtoToAdsComment(CommentDto adsCommentDto);

    List<Comment> adsCommentDtoToAdsComment(List<CommentDto> adsCommentDto);
}
