package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.security.Principal;

public interface AdsService {
    ResponseWrapperAdsDto getAllAds();
    AdsDto createAds(CreateAdsDto createAds, MultipartFile file);
    ResponseWrapperCommentDto getAdsComments(int pk);
    CommentDto addAdsComment(int pk, CommentDto adsCommentDto);
    FullAdsDto getAds(int id);
    AdsDto removeAds(int id);
    AdsDto updateAds(int id, CreateAdsDto adsDto);
    CommentDto getAdsComment(int pk, int id);
    CommentDto deleteAdsComment(int pk, int id);
    CommentDto updateAdsComment(int pk, int id, CommentDto adsCommentDto);
    ResponseWrapperAdsDto getAdsMe(Principal principal);
}
