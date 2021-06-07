package com.malu.base.gift.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.malu.base.gift.IntegrationTest;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.repository.GiftSeasonRepository;
import com.malu.base.gift.service.dto.GiftSeasonDTO;
import com.malu.base.gift.service.mapper.GiftSeasonMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link GiftSeasonResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class GiftSeasonResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/gift-seasons";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GiftSeasonRepository giftSeasonRepository;

    @Autowired
    private GiftSeasonMapper giftSeasonMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGiftSeasonMockMvc;

    private GiftSeason giftSeason;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiftSeason createEntity(EntityManager em) {
        GiftSeason giftSeason = new GiftSeason().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).icon(DEFAULT_ICON);
        return giftSeason;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiftSeason createUpdatedEntity(EntityManager em) {
        GiftSeason giftSeason = new GiftSeason().name(UPDATED_NAME).description(UPDATED_DESCRIPTION).icon(UPDATED_ICON);
        return giftSeason;
    }

    @BeforeEach
    public void initTest() {
        giftSeason = createEntity(em);
    }

    @Test
    @Transactional
    void createGiftSeason() throws Exception {
        int databaseSizeBeforeCreate = giftSeasonRepository.findAll().size();
        // Create the GiftSeason
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);
        restGiftSeasonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO)))
            .andExpect(status().isCreated());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeCreate + 1);
        GiftSeason testGiftSeason = giftSeasonList.get(giftSeasonList.size() - 1);
        assertThat(testGiftSeason.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGiftSeason.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGiftSeason.getIcon()).isEqualTo(DEFAULT_ICON);
    }

    @Test
    @Transactional
    void createGiftSeasonWithExistingId() throws Exception {
        // Create the GiftSeason with an existing ID
        giftSeason.setId(1L);
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        int databaseSizeBeforeCreate = giftSeasonRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftSeasonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftSeasonRepository.findAll().size();
        // set the field null
        giftSeason.setName(null);

        // Create the GiftSeason, which fails.
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        restGiftSeasonMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO)))
            .andExpect(status().isBadRequest());

        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGiftSeasons() throws Exception {
        // Initialize the database
        giftSeasonRepository.saveAndFlush(giftSeason);

        // Get all the giftSeasonList
        restGiftSeasonMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giftSeason.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)));
    }

    @Test
    @Transactional
    void getGiftSeason() throws Exception {
        // Initialize the database
        giftSeasonRepository.saveAndFlush(giftSeason);

        // Get the giftSeason
        restGiftSeasonMockMvc
            .perform(get(ENTITY_API_URL_ID, giftSeason.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(giftSeason.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON));
    }

    @Test
    @Transactional
    void getNonExistingGiftSeason() throws Exception {
        // Get the giftSeason
        restGiftSeasonMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGiftSeason() throws Exception {
        // Initialize the database
        giftSeasonRepository.saveAndFlush(giftSeason);

        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();

        // Update the giftSeason
        GiftSeason updatedGiftSeason = giftSeasonRepository.findById(giftSeason.getId()).get();
        // Disconnect from session so that the updates on updatedGiftSeason are not directly saved in db
        em.detach(updatedGiftSeason);
        updatedGiftSeason.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).icon(UPDATED_ICON);
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(updatedGiftSeason);

        restGiftSeasonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftSeasonDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO))
            )
            .andExpect(status().isOk());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
        GiftSeason testGiftSeason = giftSeasonList.get(giftSeasonList.size() - 1);
        assertThat(testGiftSeason.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftSeason.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGiftSeason.getIcon()).isEqualTo(UPDATED_ICON);
    }

    @Test
    @Transactional
    void putNonExistingGiftSeason() throws Exception {
        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();
        giftSeason.setId(count.incrementAndGet());

        // Create the GiftSeason
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftSeasonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftSeasonDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGiftSeason() throws Exception {
        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();
        giftSeason.setId(count.incrementAndGet());

        // Create the GiftSeason
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftSeasonMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGiftSeason() throws Exception {
        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();
        giftSeason.setId(count.incrementAndGet());

        // Create the GiftSeason
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftSeasonMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGiftSeasonWithPatch() throws Exception {
        // Initialize the database
        giftSeasonRepository.saveAndFlush(giftSeason);

        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();

        // Update the giftSeason using partial update
        GiftSeason partialUpdatedGiftSeason = new GiftSeason();
        partialUpdatedGiftSeason.setId(giftSeason.getId());

        restGiftSeasonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiftSeason.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiftSeason))
            )
            .andExpect(status().isOk());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
        GiftSeason testGiftSeason = giftSeasonList.get(giftSeasonList.size() - 1);
        assertThat(testGiftSeason.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGiftSeason.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGiftSeason.getIcon()).isEqualTo(DEFAULT_ICON);
    }

    @Test
    @Transactional
    void fullUpdateGiftSeasonWithPatch() throws Exception {
        // Initialize the database
        giftSeasonRepository.saveAndFlush(giftSeason);

        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();

        // Update the giftSeason using partial update
        GiftSeason partialUpdatedGiftSeason = new GiftSeason();
        partialUpdatedGiftSeason.setId(giftSeason.getId());

        partialUpdatedGiftSeason.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).icon(UPDATED_ICON);

        restGiftSeasonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiftSeason.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiftSeason))
            )
            .andExpect(status().isOk());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
        GiftSeason testGiftSeason = giftSeasonList.get(giftSeasonList.size() - 1);
        assertThat(testGiftSeason.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftSeason.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGiftSeason.getIcon()).isEqualTo(UPDATED_ICON);
    }

    @Test
    @Transactional
    void patchNonExistingGiftSeason() throws Exception {
        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();
        giftSeason.setId(count.incrementAndGet());

        // Create the GiftSeason
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftSeasonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, giftSeasonDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGiftSeason() throws Exception {
        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();
        giftSeason.setId(count.incrementAndGet());

        // Create the GiftSeason
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftSeasonMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGiftSeason() throws Exception {
        int databaseSizeBeforeUpdate = giftSeasonRepository.findAll().size();
        giftSeason.setId(count.incrementAndGet());

        // Create the GiftSeason
        GiftSeasonDTO giftSeasonDTO = giftSeasonMapper.toDto(giftSeason);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftSeasonMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(giftSeasonDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiftSeason in the database
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGiftSeason() throws Exception {
        // Initialize the database
        giftSeasonRepository.saveAndFlush(giftSeason);

        int databaseSizeBeforeDelete = giftSeasonRepository.findAll().size();

        // Delete the giftSeason
        restGiftSeasonMockMvc
            .perform(delete(ENTITY_API_URL_ID, giftSeason.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GiftSeason> giftSeasonList = giftSeasonRepository.findAll();
        assertThat(giftSeasonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
