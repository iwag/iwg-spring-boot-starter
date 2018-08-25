package biz.iwag.blog.repository;

import biz.iwag.blog.domain.GameSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GameSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GameSettingsRepository extends JpaRepository<GameSettings, Long> {

}
