package biz.iwag.blog.web.rest;

import biz.iwag.blog.domain.Player;
import biz.iwag.blog.domain.PlayerData;
import biz.iwag.blog.repository.PlayerDataRepository;
import biz.iwag.blog.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Version.
 */
@RestController
@RequestMapping("/api")
public class LoginResource {

    private final Logger log = LoggerFactory.getLogger(LoginResource.class);

    private static final String ENTITY_NAME = "version";

    private final PlayerRepository playerRepository;

    private final PlayerDataRepository playerDataRepository;

    public LoginResource(PlayerRepository playerRepository, PlayerDataRepository playerDataRepository) {
        this.playerRepository = playerRepository;
        this.playerDataRepository = playerDataRepository;
    }

    /**
     * GET  /version/getApi : get the "id" version.
     *
     */
    @RequestMapping(path = "/login", method=RequestMethod.POST)
    public ResponseEntity<Player> getApi(@RequestParam("deviceId") String deviceId ){
        Player example = new Player();
        example.setDeviceId(deviceId);
        Optional<Player> playerOpt = playerRepository.findOne(Example.of(example));

        playerOpt.ifPresent(p -> {
            List<PlayerData> list = playerDataRepository.findAllByPlayerId(p.getId());
        });

        return ResponseEntity.ok(playerOpt.get());
    }
}
