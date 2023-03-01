package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPk(Ads pk);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM comments WHERE ads_id = ?1 ", nativeQuery = true)
    void deleteByAdsId(int id);
//    void deleteCommentsByPk_Pk(int id);
}
