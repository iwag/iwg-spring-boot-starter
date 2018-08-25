package biz.iwag.blog.web.rest;

import biz.iwag.blog.service.dto.VersionDTO;
import com.codahale.metrics.annotation.Timed;
import biz.iwag.blog.domain.Version;
import biz.iwag.blog.service.VersionService;
import biz.iwag.blog.web.rest.errors.BadRequestAlertException;
import biz.iwag.blog.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

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
    @Timed
    public ResponseEntity<VersionDTO> getApi(@RequestParam("deviceId") String deviceId,
                                         @RequestParam("platform") String platform,
                                         @RequestParam("build_version") String buildVersion) {
        VersionDTO info = versionService.getVersionInfo(platform, buildVersion);
        return ResponseEntity.ok(info);
    }
}
