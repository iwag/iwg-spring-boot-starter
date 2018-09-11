package biz.iwag.blog.service;

import biz.iwag.blog.BlogApp;
import biz.iwag.blog.config.Constants;
import biz.iwag.blog.domain.User;
import biz.iwag.blog.repository.GameDataRedisRepository;
import biz.iwag.blog.repository.HelperRedisRepository;
import biz.iwag.blog.repository.UserRepository;
import biz.iwag.blog.service.dto.UserDTO;
import biz.iwag.blog.service.dto.VersionDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Test class for the UserResource REST controller.
 *
 * @see VersionService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApp.class)
@Transactional
public class VersionServiceIntTest {

    @Autowired
    GameDataRedisRepository gameDataRedisRepository;

    @Autowired
    HelperRedisRepository helperRedisRepository;

    @Autowired
    private VersionService versionService;

    @Autowired
    private AuditingHandler auditingHandler;

    @Mock
    DateTimeProvider dateTimeProvider;


    @Before
    public void init() {


        when(dateTimeProvider.getNow()).thenReturn(Optional.of(LocalDateTime.now()));
        auditingHandler.setDateTimeProvider(dateTimeProvider);
    }

    @Test
    @Transactional
    public void assertThatAnonymousUserIsNotGet() {
        String platform = "Android";
        String buildVersion = "240";
        String deviceId = "test";
        String deviceModel = "model";
        Integer screenHeight = 1280;
        Integer maxTextureSize = 1280;

        final VersionDTO versionDto = versionService.getVersionInfo(platform, buildVersion, deviceId, deviceModel, screenHeight, maxTextureSize);

        assertThat(versionDto).isNotNull();
    }

}
