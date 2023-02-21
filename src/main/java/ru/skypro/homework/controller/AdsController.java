package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.service.AdsService;

import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final Logger logger = LoggerFactory.getLogger(AdsController.class);
    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }
      /**
     * Получить все существующие объявления GET <a href="http://localhost:3000/ads">...</a>
     **/

    @GetMapping()
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        ResponseWrapperAdsDto allAds = adsService.getAllAds();
        if (allAds.getCount() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allAds);
    }

    /**
     * POST <a href="http://localhost:3000/ads">...</a>
     * Добавление объявления.
     */

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestPart("properties") CreateAdsDto createAds,
                                         @RequestPart("image") MultipartFile file) {
        if (createAds == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(adsService.createAds(createAds, file));
//        return ResponseEntity.ok().build();
    }

    /**
     * Получить все комментарии(отзывы) к объявлению GET <a href="http://localhost:3000/ads/">...</a>{ad_pk}/comment
     **/

    @GetMapping(value = "/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable("ad_pk") String adPk) {
        int pk = Integer.parseInt(adPk);
        ResponseWrapperCommentDto adsComment = adsService.getAdsComments(pk);
        if (adsComment.getCount() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adsComment);
    }
    /**
     * POST <a href="http://localhost:3000/ads">...</a>{ad_pk}/comment
     * Добавление комментария к объявлению.
     */

    @PostMapping("/{ad_pk}/comments")
        public ResponseEntity<CommentDto> addComments(@PathVariable("ad_pk")  String adPk,
                                                      @RequestBody CommentDto comment) {
        int pk = Integer.parseInt(adPk);
        CommentDto adsComment = adsService.addAdsComment(pk, comment);
        if (adsComment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsComment);
        }
    /**
     * Получить объявление по его идентификатору, то-есть по id
     * GET <a href="http://localhost:3000/ads/">...</a>{id}
     **/

    @GetMapping("/{id}")
        public ResponseEntity<FullAdsDto> getFullAd(@PathVariable int id) {
        FullAdsDto fullAds = adsService.getAds(id);
        if (fullAds == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fullAds);
        }
    /**
     * Удалить объявление по его идентификатору, то-есть по id.
     * DELETE <a href="http://localhost:3000/ads/{">...</a>id}
     **/

    @DeleteMapping("/{id}")
        public ResponseEntity<AdsDto> removeAds(@PathVariable int id) {
        AdsDto adsDto = adsService.removeAds(id);
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
        }
    /** Редактировать объявление по его идентификатору,
     * PUT <a href="http://localhost:3000/ads/">...</a>{id}
     **/

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable int id, @RequestBody CreateAdsDto ads) {
        AdsDto adsDto = adsService.updateAds(id, ads);
        if (ads == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsDto);
        }
    /**
     * POST <a href="http://localhost:3000/ads">...</a>{ad_pk}/comment
     * Получение комментария к объявлению.
     */

    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> getAdsComment(@PathVariable("ad_pk") String adPk,
                                                    @PathVariable int id) {
        int pk = Integer.parseInt(adPk);
        CommentDto adsCommentDto = adsService.getAdsComment(pk, id);
        if (adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsCommentDto);
        }
    /**
     * DELETE <a href="http://localhost:3000/ads">...</a>{ad_pk}/comment/{id}
     * Удаление комментария к объявлению.
     */

    @DeleteMapping("/{ad_pk}/comments/{id}")
        public ResponseEntity<Void> deleteComment(@PathVariable("ad_pk") String adPk,
        @PathVariable int id) {
        int pk = Integer.parseInt(adPk);
        CommentDto adsCommentDto = adsService.deleteAdsComment(pk, id);
        if (adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
        }
    /**
     * POST <a href="http://localhost:3000/ads/">...</a>{ad_pk}/comment
     * Обновление отзыва(комментария) к объявлению. Объявление должно существовать.
     */

    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> updateAdsComment(@PathVariable("ad_pk") String adPk,
                                                       @PathVariable int id,
                                                       @RequestBody CommentDto comment) {
        int pk = Integer.parseInt(adPk);
        CommentDto adsComment = adsService.updateAdsComment(pk, id, comment);
        if (adsComment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsComment);
        }
    /**
     * Получить все объявления автора GET <a href="http://localhost:3000/ads">...</a>
     **/

        @GetMapping("/me")
        public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(Principal principal) {
            ResponseWrapperAdsDto Ads = adsService.getAdsMe(principal);
            return ResponseEntity.ok(Ads);
        }
}
