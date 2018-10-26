package biz.iwag.blog.web.rest;

import biz.iwag.blog.service.dto.VersionDTO;
import biz.iwag.blog.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Version.
 */
@RestController
@RequestMapping("/api")
public class VersionResource {

    private final Logger log = LoggerFactory.getLogger(VersionResource.class);

    private static final String ENTITY_NAME = "version";

    private final VersionService versionService;

    public VersionResource(VersionService versionService) {
        this.versionService = versionService;
    }

    /**
     * GET  /version/getApi : get the "id" version.
     *
     */
    @RequestMapping(path = "/version/getApi", method=RequestMethod.POST)
    public ResponseEntity<VersionDTO> getApi(@RequestParam("deviceId") String deviceId,
                                             @RequestParam("platform") String platform,
                                             @RequestParam("build_version") String buildVersion,
                                             @RequestParam("device_model") String deviceModel,
                                             @RequestParam("screen_height") Integer screenHeight,
                                             @RequestParam("max_texture_size")  Integer maxTextureSize) {
        VersionDTO info = versionService.getVersionInfo(platform, buildVersion, deviceId, deviceModel, screenHeight, maxTextureSize);
        return ResponseEntity.ok(info);
    }
}
