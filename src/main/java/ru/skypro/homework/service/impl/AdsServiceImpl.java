package ru.skypro.homework.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.AdsCommentNotFoundException;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.List;
@Service
public class AdsServiceImpl  implements AdsService {
    private final AdsRepository adsRepository;
    private final AdsMapper mapper = Mappers.getMapper(AdsMapper.class);
    private final CommentRepository adsCommentRepository;
    private final UserRepository userRepository;
    public AdsServiceImpl(AdsRepository adsRepository, CommentRepository adsCommentRepository, UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.userRepository = userRepository;
    }
    @Override
    public ResponseWrapperAdsDto getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        return getResponseWrapperAds(adsList);
    }
    private ResponseWrapperAdsDto getResponseWrapperAds(List<Ads> adsList) {
        List<AdsDto> adsDtoList = mapper.adsToAdsDto(adsList);
        ResponseWrapperAdsDto responseWrapperAds = new ResponseWrapperAdsDto();
        if (!adsDtoList.isEmpty()) {
            responseWrapperAds.setCount(adsDtoList.size());
            responseWrapperAds.setResults(adsDtoList);
        }
        return responseWrapperAds;
    }

    @Override
    public AdsDto createAds(CreateAdsDto createAds, MultipartFile file) {
        Ads ads = mapper.createAdsToAds(createAds);
        adsRepository.save(ads);
        return mapper.adsToAdsDto(ads);
    }

    @Override
    public ResponseWrapperCommentDto getAdsComments(int pk) {
        Ads ads = adsRepository.findById(pk).orElseThrow(AdsNotFoundException::new);
        List<Comment> adsCommentList = adsCommentRepository.findByPk(ads);
        List<CommentDto> adsCommentDtoList = mapper.adsCommentToAdsCommentDto(adsCommentList);
        ResponseWrapperCommentDto responseWrapperAdsComment = new ResponseWrapperCommentDto();
        if (!adsCommentDtoList.isEmpty()) {
            responseWrapperAdsComment.setCount(adsCommentDtoList.size());
            responseWrapperAdsComment.setResults(adsCommentDtoList);
        }
        return responseWrapperAdsComment;
    }

    @Override
    public CommentDto addAdsComment(int pk, CommentDto adsCommentDto) {
        Comment adsComment = new Comment();
        adsComment.setAuthor(userRepository.findById(1).orElseThrow(UserNotFoundException::new));
        adsComment.setPk(adsRepository.findById(pk).orElseThrow(AdsNotFoundException::new));
        adsComment.setCreatedAt(OffsetDateTime.now());
        adsComment.setText(adsCommentDto.getText());
        adsCommentRepository.save(adsComment);
        return adsCommentDto;
    }
    @Override
    public FullAdsDto getAds(int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        User user = userRepository.findById(ads.getAuthor().getId()).orElseThrow(UserNotFoundException::new);
        FullAdsDto fullAds = new FullAdsDto();
        fullAds.setAuthorFirstName(user.getFirstName());
        fullAds.setAuthorLastName(user.getLastName());
        fullAds.setDescription(ads.getDescription());
        fullAds.setImage(ads.getImage());
        fullAds.setEmail(user.getEmail());
        fullAds.setPhone(user.getPhone());
        fullAds.setPk(ads.getPk());
        fullAds.setPrice(ads.getPrice());
        fullAds.setTitle(ads.getTitle());
        return fullAds;
    }
    @Override
    public AdsDto removeAds(int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
            adsRepository.deleteById(id);
            return mapper.adsToAdsDto(ads);
    }
    @Override
    public AdsDto updateAds(int id, CreateAdsDto adsDto) {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
            ads.setTitle(adsDto.getTitle());
            ads.setPrice(adsDto.getPrice());
            ads.setDescription(adsDto.getDescription());
        AdsDto adsDtoRen = mapper.adsToAdsDto(ads);
            return adsDtoRen;
    }
    @Override
    public CommentDto getAdsComment(int pk, int id) {
        adsRepository.findById(pk).orElseThrow(AdsNotFoundException::new);
        Comment adsComment = adsCommentRepository.findById(id).orElseThrow(AdsCommentNotFoundException::new);
        return mapper.adsCommentToAdsCommentDto(adsComment);
    }
    @Override
    public CommentDto deleteAdsComment(int pk, int id) {
        Comment adsComment = adsCommentRepository.findById(id).orElseThrow(AdsCommentNotFoundException::new);
            adsCommentRepository.deleteById(id);
            return mapper.adsCommentToAdsCommentDto(adsComment);

    }
    @Override
    public CommentDto updateAdsComment(int pk, int id, CommentDto adsCommentDto) {
        Comment adsComment = adsCommentRepository.findById(id).orElseThrow(AdsCommentNotFoundException::new);
        Ads ads = adsRepository.findById(pk).orElseThrow(AdsNotFoundException::new);
            adsComment.setAuthor(userRepository.findById(adsCommentDto.getAuthor()).orElseThrow(UserNotFoundException::new));
            adsComment.setPk(ads);
            adsComment.setText(adsCommentDto.getText());
            adsComment.setCreatedAt(OffsetDateTime.now());
            adsCommentRepository.save(adsComment);
            return adsCommentDto;

    }
    @Override
    public ResponseWrapperAdsDto getAdsMe(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Ads> adsList = adsRepository.findAdsByAuthorOrderByPk(user);
        return getResponseWrapperAds(adsList);
    }
}
