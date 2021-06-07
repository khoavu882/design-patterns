package com.malu.base.gift.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.malu.base.gift.IntegrationTest;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.repository.GiftLangRepository;
import com.malu.base.gift.service.dto.GiftLangDTO;
import com.malu.base.gift.service.mapper.GiftLangMapper;
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
 * Integration tests for the {@link GiftLangResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class GiftLangResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_USE_GUIDE = "AAAAAAAAAA";
    private static final String UPDATED_USE_GUIDE = "BBBBBBBBBB";

    private static final String DEFAULT_TERMS = "AAAAAAAAAA";
    private static final String UPDATED_TERMS = "BBBBBBBBBB";

    private static final String DEFAULT_LANG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LANG_CODE = "BBBBBBBBBB";

    private static final ActionStatus DEFAULT_STATUS = ActionStatus.ACTIVATED;
    private static final ActionStatus UPDATED_STATUS = ActionStatus.DEACTIVATED;

    private static final String ENTITY_API_URL = "/api/gift-langs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GiftLangRepository giftLangRepository;

    @Autowired
    private GiftLangMapper giftLangMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGiftLangMockMvc;

    private GiftLang giftLang;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiftLang createEntity(EntityManager em) {
        GiftLang giftLang = new GiftLang()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .useGuide(DEFAULT_USE_GUIDE)
            .terms(DEFAULT_TERMS)
            .langCode(DEFAULT_LANG_CODE)
            .status(DEFAULT_STATUS);
        return giftLang;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiftLang createUpdatedEntity(EntityManager em) {
        GiftLang giftLang = new GiftLang()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .useGuide(UPDATED_USE_GUIDE)
            .terms(UPDATED_TERMS)
            .langCode(UPDATED_LANG_CODE)
            .status(UPDATED_STATUS);
        return giftLang;
    }

    @BeforeEach
    public void initTest() {
        giftLang = createEntity(em);
    }

    @Test
    @Transactional
    void createGiftLang() throws Exception {
        int databaseSizeBeforeCreate = giftLangRepository.findAll().size();
        // Create the GiftLang
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);
        restGiftLangMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftLangDTO)))
            .andExpect(status().isCreated());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeCreate + 1);
        GiftLang testGiftLang = giftLangList.get(giftLangList.size() - 1);
        assertThat(testGiftLang.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGiftLang.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGiftLang.getUseGuide()).isEqualTo(DEFAULT_USE_GUIDE);
        assertThat(testGiftLang.getTerms()).isEqualTo(DEFAULT_TERMS);
        assertThat(testGiftLang.getLangCode()).isEqualTo(DEFAULT_LANG_CODE);
        assertThat(testGiftLang.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createGiftLangWithExistingId() throws Exception {
        // Create the GiftLang with an existing ID
        giftLang.setId(1L);
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);

        int databaseSizeBeforeCreate = giftLangRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftLangMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftLangDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllGiftLangs() throws Exception {
        // Initialize the database
        giftLangRepository.saveAndFlush(giftLang);

        // Get all the giftLangList
        restGiftLangMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giftLang.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].useGuide").value(hasItem(DEFAULT_USE_GUIDE)))
            .andExpect(jsonPath("$.[*].terms").value(hasItem(DEFAULT_TERMS)))
            .andExpect(jsonPath("$.[*].langCode").value(hasItem(DEFAULT_LANG_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getGiftLang() throws Exception {
        // Initialize the database
        giftLangRepository.saveAndFlush(giftLang);

        // Get the giftLang
        restGiftLangMockMvc
            .perform(get(ENTITY_API_URL_ID, giftLang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(giftLang.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.useGuide").value(DEFAULT_USE_GUIDE))
            .andExpect(jsonPath("$.terms").value(DEFAULT_TERMS))
            .andExpect(jsonPath("$.langCode").value(DEFAULT_LANG_CODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGiftLang() throws Exception {
        // Get the giftLang
        restGiftLangMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGiftLang() throws Exception {
        // Initialize the database
        giftLangRepository.saveAndFlush(giftLang);

        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();

        // Update the giftLang
        GiftLang updatedGiftLang = giftLangRepository.findById(giftLang.getId()).get();
        // Disconnect from session so that the updates on updatedGiftLang are not directly saved in db
        em.detach(updatedGiftLang);
        updatedGiftLang
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .useGuide(UPDATED_USE_GUIDE)
            .terms(UPDATED_TERMS)
            .langCode(UPDATED_LANG_CODE)
            .status(UPDATED_STATUS);
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(updatedGiftLang);

        restGiftLangMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftLangDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftLangDTO))
            )
            .andExpect(status().isOk());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
        GiftLang testGiftLang = giftLangList.get(giftLangList.size() - 1);
        assertThat(testGiftLang.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftLang.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGiftLang.getUseGuide()).isEqualTo(UPDATED_USE_GUIDE);
        assertThat(testGiftLang.getTerms()).isEqualTo(UPDATED_TERMS);
        assertThat(testGiftLang.getLangCode()).isEqualTo(UPDATED_LANG_CODE);
        assertThat(testGiftLang.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingGiftLang() throws Exception {
        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();
        giftLang.setId(count.incrementAndGet());

        // Create the GiftLang
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftLangMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftLangDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftLangDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGiftLang() throws Exception {
        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();
        giftLang.setId(count.incrementAndGet());

        // Create the GiftLang
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftLangMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftLangDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGiftLang() throws Exception {
        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();
        giftLang.setId(count.incrementAndGet());

        // Create the GiftLang
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftLangMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftLangDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGiftLangWithPatch() throws Exception {
        // Initialize the database
        giftLangRepository.saveAndFlush(giftLang);

        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();

        // Update the giftLang using partial update
        GiftLang partialUpdatedGiftLang = new GiftLang();
        partialUpdatedGiftLang.setId(giftLang.getId());

        partialUpdatedGiftLang.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).langCode(UPDATED_LANG_CODE);

        restGiftLangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiftLang.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiftLang))
            )
            .andExpect(status().isOk());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
        GiftLang testGiftLang = giftLangList.get(giftLangList.size() - 1);
        assertThat(testGiftLang.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftLang.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGiftLang.getUseGuide()).isEqualTo(DEFAULT_USE_GUIDE);
        assertThat(testGiftLang.getTerms()).isEqualTo(DEFAULT_TERMS);
        assertThat(testGiftLang.getLangCode()).isEqualTo(UPDATED_LANG_CODE);
        assertThat(testGiftLang.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateGiftLangWithPatch() throws Exception {
        // Initialize the database
        giftLangRepository.saveAndFlush(giftLang);

        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();

        // Update the giftLang using partial update
        GiftLang partialUpdatedGiftLang = new GiftLang();
        partialUpdatedGiftLang.setId(giftLang.getId());

        partialUpdatedGiftLang
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .useGuide(UPDATED_USE_GUIDE)
            .terms(UPDATED_TERMS)
            .langCode(UPDATED_LANG_CODE)
            .status(UPDATED_STATUS);

        restGiftLangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGiftLang.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGiftLang))
            )
            .andExpect(status().isOk());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
        GiftLang testGiftLang = giftLangList.get(giftLangList.size() - 1);
        assertThat(testGiftLang.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGiftLang.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGiftLang.getUseGuide()).isEqualTo(UPDATED_USE_GUIDE);
        assertThat(testGiftLang.getTerms()).isEqualTo(UPDATED_TERMS);
        assertThat(testGiftLang.getLangCode()).isEqualTo(UPDATED_LANG_CODE);
        assertThat(testGiftLang.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingGiftLang() throws Exception {
        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();
        giftLang.setId(count.incrementAndGet());

        // Create the GiftLang
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftLangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, giftLangDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftLangDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGiftLang() throws Exception {
        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();
        giftLang.setId(count.incrementAndGet());

        // Create the GiftLang
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftLangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftLangDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGiftLang() throws Exception {
        int databaseSizeBeforeUpdate = giftLangRepository.findAll().size();
        giftLang.setId(count.incrementAndGet());

        // Create the GiftLang
        GiftLangDTO giftLangDTO = giftLangMapper.toDto(giftLang);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftLangMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(giftLangDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GiftLang in the database
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGiftLang() throws Exception {
        // Initialize the database
        giftLangRepository.saveAndFlush(giftLang);

        int databaseSizeBeforeDelete = giftLangRepository.findAll().size();

        // Delete the giftLang
        restGiftLangMockMvc
            .perform(delete(ENTITY_API_URL_ID, giftLang.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GiftLang> giftLangList = giftLangRepository.findAll();
        assertThat(giftLangList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
