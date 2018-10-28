package biz.iwag.blog.web.rest;

import biz.iwag.blog.domain.Player;
import biz.iwag.blog.repository.PlayerRepository;
import biz.iwag.blog.service.VersionService;
import biz.iwag.blog.service.dto.VersionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    public LoginResource(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * GET  /version/getApi : get the "id" version.
     *
     */
    @RequestMapping(path = "/login", method=RequestMethod.POST)
    public ResponseEntity<Player> getApi(@RequestParam("deviceId") String deviceId ){
        Player example = new Player();
        example.setDeviceId(deviceId);
        Optional<Player> player = playerRepository.findOne(Example.of(example));
        return ResponseEntity.ok(player.get());
    }
}
