package biz.iwag.blog.web.rest;

import biz.iwag.blog.BlogApp;

import biz.iwag.blog.config.EmbededRedisTestConfiguration;
import biz.iwag.blog.repository.HelperRedisRepository;
import biz.iwag.blog.service.VersionService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VersionResource REST controller.
 *
 * @see VersionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApp.class)
@Import(EmbededRedisTestConfiguration.class)
public class VersionResourceIntTest {

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private VersionService versionService;

    @Autowired
    HelperRedisRepository helperRedisRepository;

    private MockMvc restVersionMockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VersionResource versionResource = new VersionResource(versionService);
        this.restVersionMockMvc = MockMvcBuilders.standaloneSetup(versionResource).build();
    }

    @Before
    public void initTest() {
        helperRedisRepository.addValueList("settings:sr_debug_devices", "test2");

    }

    @Test
    @Transactional
    public void getVersion() throws Exception {
        // Get the version
        restVersionMockMvc.perform(post("/api/version/getApi")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("deviceId","test1")
            .param("build_version","240")
            .param("platform","Android")
            .param("device_model","iphone7,1")
            .param("screen_height","700")
            .param("max_texture_size","100"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.api_version").value(37))
            .andExpect(jsonPath("$.enable_debug").value(false));
    }

    @Test
    @Transactional
    public void getVersionWithDebugDevice() throws Exception {
        // Get the version
        restVersionMockMvc.perform(post("/api/version/getApi")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("deviceId","test2")
            .param("build_version","240")
            .param("platform","Android")
            .param("device_model","iphone7,1")
            .param("screen_height","700")
            .param("max_texture_size","100"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.api_version").value(37))
            .andExpect(jsonPath("$.enable_debug").value(true));
    }

}
