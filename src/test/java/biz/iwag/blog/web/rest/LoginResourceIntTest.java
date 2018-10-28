package biz.iwag.blog.web.rest;

import biz.iwag.blog.BlogApp;
import biz.iwag.blog.domain.Player;
import biz.iwag.blog.repository.PlayerDataRepository;
import biz.iwag.blog.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static biz.iwag.blog.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PlayerResource REST controller.
 *
 * @see LoginResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApp.class)
public class LoginResourceIntTest {

    private static final Integer DEFAULT_EXPERIENCE = 1;
    private static final Integer UPDATED_EXPERIENCE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FBUSERID = "AAAAAAAAAA";
    private static final String UPDATED_FBUSERID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LASTTIMEBROKEFIXABLESCENERYITEM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LASTTIMEBROKEFIXABLESCENERYITEM = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_SESSIONING = 1F;
    private static final Float UPDATED_SESSIONING = 2F;

    private static final LocalDate DEFAULT_SESSIONENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SESSIONENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_POTLIMIT = 1;
    private static final Integer UPDATED_POTLIMIT = 2;

    private static final Integer DEFAULT_ACTUALTUTORIAL = 1;
    private static final Integer UPDATED_ACTUALTUTORIAL = 2;

    private static final String DEFAULT_COMPLETEDTUTORIALS = "AAAAAAAAAA";
    private static final String UPDATED_COMPLETEDTUTORIALS = "BBBBBBBBBB";

    private static final Integer DEFAULT_CURRENTTUTORIALSTEP = 1;
    private static final Integer UPDATED_CURRENTTUTORIALSTEP = 2;

    private static final Boolean DEFAULT_ENABLEFEEDBACK = false;
    private static final Boolean UPDATED_ENABLEFEEDBACK = true;

    private static final String DEFAULT_QUESTSDEQUEUE = "AAAAAAAAAA";
    private static final String UPDATED_QUESTSDEQUEUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATIONDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATIONDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_GCUSERID = "AAAAAAAAAA";
    private static final String UPDATED_GCUSERID = "BBBBBBBBBB";

    private static final String DEFAULT_GPUSERID = "AAAAAAAAAA";
    private static final String UPDATED_GPUSERID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISABLED = false;
    private static final Boolean UPDATED_DISABLED = true;

    private static final String DEFAULT_LASTSAVEDEVICEMODEL = "AAAAAAAAAA";
    private static final String UPDATED_LASTSAVEDEVICEMODEL = "BBBBBBBBBB";

    private static final String DEFAULT_LASTSAVEDEVICEOS = "AAAAAAAAAA";
    private static final String UPDATED_LASTSAVEDEVICEOS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LASTSAVEPLAYERDATATIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LASTSAVEPLAYERDATATIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TIERSPECIALPACKAGEBOUGHT = 1;
    private static final Integer UPDATED_TIERSPECIALPACKAGEBOUGHT = 2;

    private static final String DEFAULT_LASTSAVEDEVICEID = "AAAAAAAAAA";
    private static final String UPDATED_LASTSAVEDEVICEID = "BBBBBBBBBB";

    private static final Long DEFAULT_LASTSAVEGAMECLIENT = 1L;
    private static final Long UPDATED_LASTSAVEGAMECLIENT = 2L;

    private static final Long DEFAULT_LASTSAVEGAMESERVER = 1L;
    private static final Long UPDATED_LASTSAVEGAMESERVER = 2L;

    private static final String DEFAULT_LASTSAVEAPI = "AAAAAAAAAA";
    private static final String UPDATED_LASTSAVEAPI = "BBBBBBBBBB";

    private static final String DEFAULT_AB = "AAAAAAAAAA";
    private static final String UPDATED_AB = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ABSCHEMACHANGED = false;
    private static final Boolean UPDATED_ABSCHEMACHANGED = true;

    private static final Integer DEFAULT_IAPARCHETYPE = 1;
    private static final Integer UPDATED_IAPARCHETYPE = 2;

    private static final Integer DEFAULT_BIGGESTIAPARCHETYPE = 1;
    private static final Integer UPDATED_BIGGESTIAPARCHETYPE = 2;

    private static final LocalDate DEFAULT_IAPPURCHASELASTDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IAPPURCHASELASTDATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_IAPCYCLEWEEKSTARTDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IAPCYCLEWEEKSTARTDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_IAPCYCLECURRENTWEEK = 1;
    private static final Integer UPDATED_IAPCYCLECURRENTWEEK = 2;

    private static final LocalDate DEFAULT_IAPPURCHASESPECIALPACKAGELASTDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IAPPURCHASESPECIALPACKAGELASTDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FBACCESSTOKEN = "AAAAAAAAAA";
    private static final String UPDATED_FBACCESSTOKEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HACKER = false;
    private static final Boolean UPDATED_HACKER = true;

    private static final Long DEFAULT_VIPENDTIME = 1L;
    private static final Long UPDATED_VIPENDTIME = 2L;

    @Autowired
    private PlayerRepository playerRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restPlayerMockMvc;

    private Player player;

    @Autowired
    private PlayerDataRepository playerDataRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoginResource playerResource = new LoginResource(playerRepository, playerDataRepository);
        this.restPlayerMockMvc = MockMvcBuilders.standaloneSetup(playerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Player createEntity(EntityManager em) {
        Player player = new Player()
            .experience(DEFAULT_EXPERIENCE)
            .name(DEFAULT_NAME)
            .deviceId(DEFAULT_DEVICE_ID)
            .fbuserid(DEFAULT_FBUSERID)
            .enablefeedback(DEFAULT_ENABLEFEEDBACK)
            .creationdate(DEFAULT_CREATIONDATE)
            .disabled(DEFAULT_DISABLED)
            .ab(DEFAULT_AB)
            .hacker(DEFAULT_HACKER);
        return player;
    }

    @Before
    public void initTest() {
        player = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playerRepository.findAll().size();

        // Create the Player with an existing ID
        player.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayerMockMvc.perform(post("/api/login")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(player)))
            .andExpect(status().isBadRequest());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDeviceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = playerRepository.findAll().size();
        // set the field null
        player.setDeviceId(null);

        // Create the Player, which fails.

        restPlayerMockMvc.perform(post("/api/login")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(player)))
            .andExpect(status().isBadRequest());

        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeTest);
    }

    // disabled for now
    // @Test
    @Transactional
    public void getPlayer() throws Exception {
        // Initialize the database
        playerRepository.saveAndFlush(player);

        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", player.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(player.getId().intValue()))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.deviceId").value(DEFAULT_DEVICE_ID.toString()))
            .andExpect(jsonPath("$.fbuserid").value(DEFAULT_FBUSERID.toString()))
            .andExpect(jsonPath("$.lasttimebrokefixablesceneryitem").value(DEFAULT_LASTTIMEBROKEFIXABLESCENERYITEM.toString()))
            .andExpect(jsonPath("$.sessioning").value(DEFAULT_SESSIONING.doubleValue()))
            .andExpect(jsonPath("$.sessionendtime").value(DEFAULT_SESSIONENDTIME.toString()))
            .andExpect(jsonPath("$.potlimit").value(DEFAULT_POTLIMIT))
            .andExpect(jsonPath("$.actualtutorial").value(DEFAULT_ACTUALTUTORIAL))
            .andExpect(jsonPath("$.completedtutorials").value(DEFAULT_COMPLETEDTUTORIALS.toString()))
            .andExpect(jsonPath("$.currenttutorialstep").value(DEFAULT_CURRENTTUTORIALSTEP))
            .andExpect(jsonPath("$.enablefeedback").value(DEFAULT_ENABLEFEEDBACK.booleanValue()))
            .andExpect(jsonPath("$.questsdequeue").value(DEFAULT_QUESTSDEQUEUE.toString()))
            .andExpect(jsonPath("$.creationdate").value(DEFAULT_CREATIONDATE.toString()))
            .andExpect(jsonPath("$.gcuserid").value(DEFAULT_GCUSERID.toString()))
            .andExpect(jsonPath("$.gpuserid").value(DEFAULT_GPUSERID.toString()))
            .andExpect(jsonPath("$.disabled").value(DEFAULT_DISABLED.booleanValue()))
            .andExpect(jsonPath("$.lastsavedevicemodel").value(DEFAULT_LASTSAVEDEVICEMODEL.toString()))
            .andExpect(jsonPath("$.lastsavedeviceos").value(DEFAULT_LASTSAVEDEVICEOS.toString()))
            .andExpect(jsonPath("$.lastsaveplayerdatatime").value(DEFAULT_LASTSAVEPLAYERDATATIME.toString()))
            .andExpect(jsonPath("$.tierspecialpackagebought").value(DEFAULT_TIERSPECIALPACKAGEBOUGHT))
            .andExpect(jsonPath("$.lastsavedeviceid").value(DEFAULT_LASTSAVEDEVICEID.toString()))
            .andExpect(jsonPath("$.lastsavegameclient").value(DEFAULT_LASTSAVEGAMECLIENT.intValue()))
            .andExpect(jsonPath("$.lastsavegameserver").value(DEFAULT_LASTSAVEGAMESERVER.intValue()))
            .andExpect(jsonPath("$.lastsaveapi").value(DEFAULT_LASTSAVEAPI.toString()))
            .andExpect(jsonPath("$.ab").value(DEFAULT_AB.toString()))
            .andExpect(jsonPath("$.abschemachanged").value(DEFAULT_ABSCHEMACHANGED.booleanValue()))
            .andExpect(jsonPath("$.iaparchetype").value(DEFAULT_IAPARCHETYPE))
            .andExpect(jsonPath("$.biggestiaparchetype").value(DEFAULT_BIGGESTIAPARCHETYPE))
            .andExpect(jsonPath("$.iappurchaselastdate").value(DEFAULT_IAPPURCHASELASTDATE.toString()))
            .andExpect(jsonPath("$.iapcycleweekstartdate").value(DEFAULT_IAPCYCLEWEEKSTARTDATE.toString()))
            .andExpect(jsonPath("$.iapcyclecurrentweek").value(DEFAULT_IAPCYCLECURRENTWEEK))
            .andExpect(jsonPath("$.iappurchasespecialpackagelastdate").value(DEFAULT_IAPPURCHASESPECIALPACKAGELASTDATE.toString()))
            .andExpect(jsonPath("$.fbaccesstoken").value(DEFAULT_FBACCESSTOKEN.toString()))
            .andExpect(jsonPath("$.hacker").value(DEFAULT_HACKER.booleanValue()))
            .andExpect(jsonPath("$.vipendtime").value(DEFAULT_VIPENDTIME.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPlayer() throws Exception {
        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Player.class);
        Player player1 = new Player();
        player1.setId(1L);
        Player player2 = new Player();
        player2.setId(player1.getId());
        assertThat(player1).isEqualTo(player2);
        player2.setId(2L);
        assertThat(player1).isNotEqualTo(player2);
        player1.setId(null);
        assertThat(player1).isNotEqualTo(player2);
    }
}
