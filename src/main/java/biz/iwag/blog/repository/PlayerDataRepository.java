package biz.iwag.blog.repository;

import biz.iwag.blog.domain.Player;
import biz.iwag.blog.domain.PlayerData;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Player entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerDataRepository extends JpaRepository<PlayerData, Long> {
    public default List<PlayerData> findAllByPlayerId(Long playerId) {
        PlayerData example = new PlayerData();
        example.setPlayerId(playerId);
        return this.findAll(Example.of(example));
    }
}
