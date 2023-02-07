package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.security.Principal;
import java.util.Collection;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    private final Logger logger = LoggerFactory.getLogger(AdsController.class);

      /**
     * Получить все существующие объявления GET <a href="http://localhost:3000/ads">...</a>
     **/

    @GetMapping()
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok().build();
    }

    /**
     * POST <a href="http://localhost:3000/ads">...</a>
     * Добавление объявления.
     */

    @PostMapping
    public ResponseEntity<Ads> addAds(@RequestPart("properties") CreateAds createAds,
                                      @RequestPart("image") MultipartFile file) {
        return ResponseEntity.ok().build();
    }

    /**
     * Получить все комментарии(отзывы) к объявлению GET <a href="http://localhost:3000/ads/">...</a>{ad_pk}/comment
     **/

    @GetMapping(value = "/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("ad_pk") String adPk) {
        return ResponseEntity.ok().build();
    }
    /**
     * POST <a href="http://localhost:3000/ads">...</a>{ad_pk}/comment
     * Добавление комментария к объявлению.
     */

    @PostMapping("/{ad_pk}/comments")
        public ResponseEntity<Comment> addComments(@PathVariable("ad_pk")  String adPk,
                                                  @RequestBody Comment comment) {
            return ResponseEntity.ok().build();
        }
    /**
     * Получить объявление по его идентификатору, то-есть по id
     * GET <a href="http://localhost:3000/ads/">...</a>{id}
     **/

    @GetMapping("/{id}")
        public ResponseEntity<FullAds> getFullAd(@PathVariable int id) {
            return ResponseEntity.ok().build();
        }
    /**
     * Удалить объявление по его идентификатору, то-есть по id.
     * DELETE <a href="http://localhost:3000/ads/{">...</a>id}
     **/

    @DeleteMapping("/{id}")
        public ResponseEntity<Ads> removeAds(@PathVariable int id) {
            return ResponseEntity.ok().build();
        }
    /** Редактировать объявление по его идентификатору,
     * PUT <a href="http://localhost:3000/ads/">...</a>{id}
     **/

    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable int id, @RequestPart("properties")  CreateAds ads) {
            return ResponseEntity.ok().build();
        }
    /**
     * POST <a href="http://localhost:3000/ads">...</a>{ad_pk}/comment
     * Получение комментария к объявлению.
     */

    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> getAdsComment(@PathVariable("ad_pk") String adPk,
        @PathVariable int id) {
            return ResponseEntity.ok().build();
        }
    /**
     * DELETE <a href="http://localhost:3000/ads">...</a>{ad_pk}/comment/{id}
     * Удаление комментария к объявлению.
     */

    @DeleteMapping("/{ad_pk}/comments/{id}")
        public ResponseEntity<Void> deleteComment(@PathVariable("ad_pk") String adPk,
        @PathVariable int id) {
            return ResponseEntity.ok().build();
        }
    /**
     * POST <a href="http://localhost:3000/ads/">...</a>{ad_pk}/comment
     * Обновление отзыва(комментария) к объявлению. Объявление должно существовать.
     */

    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> updateAdsComment(@PathVariable("ad_pk") String adPk,
        @PathVariable int id,
        @RequestBody Comment comment) {
            return ResponseEntity.ok(comment);
        }
    /**
     * Получить все объявления автора GET <a href="http://localhost:3000/ads">...</a>
     **/

        @GetMapping("/me")
        public ResponseEntity<ResponseWrapperAds> getAdsMe(Principal principal) {
            return ResponseEntity.ok().build();
        }
}
