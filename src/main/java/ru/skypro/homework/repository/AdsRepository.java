package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Integer> {
    List<Ads> findAdsByAuthorOrderByPk(User author);

    @Query(value = "SELECT * FROM ads WHERE title ilike '%' || ?1 || '%' ", nativeQuery = true)
    List<Ads> findLikeTitle(String title);
}
