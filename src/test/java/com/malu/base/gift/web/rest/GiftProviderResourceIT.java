package com.malu.base.gift.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.malu.base.gift.IntegrationTest;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.repository.GiftProviderRepository;
import com.malu.base.gift.service.dto.GiftProviderDTO;
import com.malu.base.gift.service.mapper.GiftProviderMapper;
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
 * Integration tests for the {@link GiftProviderResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class GiftProviderResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final ActionStatus DEFAULT_STATUS = ActionStatus.ACTIVATED;
    private static final ActionStatus UPDATED_STATUS = ActionStatus.DEACTIVATED;

    private static final String ENTITY_API_URL = "/api/gift-providers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GiftProviderRepository giftProviderRepository;

    @Autowired
    private GiftProviderMapper giftProviderMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGiftProviderMockMvc;

    private GiftProvider giftProvider;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiftProvider createEntity(EntityManager em) {
        GiftProvider giftProvider = new GiftProvider()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .website(DEFAULT_WEBSITE)
            .icon(DEFAULT_ICON)
            .status(DEFAULT_STATUS);
        return giftProvider;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiftProvider createUpdatedEntity(EntityManager em) {
        GiftProvider giftProvider = new GiftProvider()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .website(UPDATED_WEBSITE)
            .icon(UPDATED_ICON)
            .status(UPDATED_STATUS);
        return giftProvider;
    }

    @BeforeEach
    public void initTest() {
        giftProvider = createEntity(em);
    }

    @Test
    @Transactional
    void createGiftProvider() throws Exception {
        int databaseSizeBeforeCreate = giftProviderRepository.findAll().size();
        // Create the GiftProvider
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);
        restGiftProviderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isCreated());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeCreate + 1);
        GiftProvider testGiftProvider = giftProviderList.get(giftProviderList.size() - 1);
        assertThat(testGiftProvider.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testGiftProvider.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGiftProvider.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testGiftProvider.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testGiftProvider.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createGiftProviderWithExistingId() throws Exception {
        // Create the GiftProvider with an existing ID
        giftProvider.setId(1L);
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);

        int databaseSizeBeforeCreate = giftProviderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftProviderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGiftProviders() throws Exception {
        // Initialize the database
        giftProviderRepository.saveAndFlush(giftProvider);

        // Get all the giftProviderList
        restGiftProviderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giftProvider.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getGiftProvider() throws Exception {
        // Initialize the database
        giftProviderRepository.saveAndFlush(giftProvider);

        // Get the giftProvider
        restGiftProviderMockMvc
            .perform(get(ENTITY_API_URL_ID, giftProvider.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(giftProvider.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGiftProvider() throws Exception {
        // Get the giftProvider
        restGiftProviderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGiftProvider() throws Exception {
        // Initialize the database
        giftProviderRepository.saveAndFlush(giftProvider);

        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();

        // Update the giftProvider
        GiftProvider updatedGiftProvider = giftProviderRepository.findById(giftProvider.getId()).get();
        // Disconnect from session so that the updates on updatedGiftProvider are not directly saved in db
        em.detach(updatedGiftProvider);
        updatedGiftProvider.code(UPDATED_CODE).name(UPDATED_NAME).website(UPDATED_WEBSITE).icon(UPDATED_ICON).status(UPDATED_STATUS);
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(updatedGiftProvider);

        restGiftProviderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftProviderDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isOk());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
        GiftProvider testGiftProvider = giftProviderList.get(giftProviderList.size() - 1);
        assertThat(testGiftProvider.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGiftProvider.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftProvider.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testGiftProvider.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testGiftProvider.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingGiftProvider() throws Exception {
        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();
        giftProvider.setId(count.incrementAndGet());

        // Create the GiftProvider
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftProviderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftProviderDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGiftProvider() throws Exception {
        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();
        giftProvider.setId(count.incrementAndGet());

        // Create the GiftProvider
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftProviderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGiftProvider() throws Exception {
        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();
        giftProvider.setId(count.incrementAndGet());

        // Create the GiftProvider
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftProviderMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGiftProviderWithPatch() throws Exception {
        // Initialize the database
        giftProviderRepository.saveAndFlush(giftProvider);

        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();

        // Update the giftProvider using partial update
        GiftProvider partialUpdatedGiftProvider = new GiftProvider();
        partialUpdatedGiftProvider.setId(giftProvider.getId());

        partialUpdatedGiftProvider.code(UPDATED_CODE).icon(UPDATED_ICON);

        restGiftProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiftProvider.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiftProvider))
            )
            .andExpect(status().isOk());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
        GiftProvider testGiftProvider = giftProviderList.get(giftProviderList.size() - 1);
        assertThat(testGiftProvider.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGiftProvider.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGiftProvider.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testGiftProvider.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testGiftProvider.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateGiftProviderWithPatch() throws Exception {
        // Initialize the database
        giftProviderRepository.saveAndFlush(giftProvider);

        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();

        // Update the giftProvider using partial update
        GiftProvider partialUpdatedGiftProvider = new GiftProvider();
        partialUpdatedGiftProvider.setId(giftProvider.getId());

        partialUpdatedGiftProvider.code(UPDATED_CODE).name(UPDATED_NAME).website(UPDATED_WEBSITE).icon(UPDATED_ICON).status(UPDATED_STATUS);

        restGiftProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiftProvider.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiftProvider))
            )
            .andExpect(status().isOk());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
        GiftProvider testGiftProvider = giftProviderList.get(giftProviderList.size() - 1);
        assertThat(testGiftProvider.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGiftProvider.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftProvider.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testGiftProvider.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testGiftProvider.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingGiftProvider() throws Exception {
        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();
        giftProvider.setId(count.incrementAndGet());

        // Create the GiftProvider
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, giftProviderDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGiftProvider() throws Exception {
        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();
        giftProvider.setId(count.incrementAndGet());

        // Create the GiftProvider
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGiftProvider() throws Exception {
        int databaseSizeBeforeUpdate = giftProviderRepository.findAll().size();
        giftProvider.setId(count.incrementAndGet());

        // Create the GiftProvider
        GiftProviderDTO giftProviderDTO = giftProviderMapper.toDto(giftProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftProviderMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftProviderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiftProvider in the database
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGiftProvider() throws Exception {
        // Initialize the database
        giftProviderRepository.saveAndFlush(giftProvider);

        int databaseSizeBeforeDelete = giftProviderRepository.findAll().size();

        // Delete the giftProvider
        restGiftProviderMockMvc
            .perform(delete(ENTITY_API_URL_ID, giftProvider.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GiftProvider> giftProviderList = giftProviderRepository.findAll();
        assertThat(giftProviderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
