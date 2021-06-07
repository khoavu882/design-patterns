package com.malu.base.gift.web.rest;

import static com.malu.base.gift.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.malu.base.gift.IntegrationTest;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.enumeration.EnumGiftStatus;
import com.malu.base.gift.repository.GiftRepository;
import com.malu.base.gift.service.dto.GiftDTO;
import com.malu.base.gift.service.mapper.GiftMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link GiftResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class GiftResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIA_PATH = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA_PATH = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ORIGINAL_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORIGINAL_PRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Instant DEFAULT_PUBLISH_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLISH_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_EXPIRE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_USE_GUIDE = "AAAAAAAAAA";
    private static final String UPDATED_USE_GUIDE = "BBBBBBBBBB";

    private static final String DEFAULT_TERMS = "AAAAAAAAAA";
    private static final String UPDATED_TERMS = "BBBBBBBBBB";

    private static final String DEFAULT_TAGS = "AAAAAAAAAA";
    private static final String UPDATED_TAGS = "BBBBBBBBBB";

    private static final EnumGiftStatus DEFAULT_STATUS = EnumGiftStatus.ACTIVATED;
    private static final EnumGiftStatus UPDATED_STATUS = EnumGiftStatus.DEACTIVATED;

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/gifts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private GiftMapper giftMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGiftMockMvc;

    private Gift gift;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gift createEntity(EntityManager em) {
        Gift gift = new Gift()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .icon(DEFAULT_ICON)
            .mediaPath(DEFAULT_MEDIA_PATH)
            .originalPrice(DEFAULT_ORIGINAL_PRICE)
            .price(DEFAULT_PRICE)
            .publishDate(DEFAULT_PUBLISH_DATE)
            .startDate(DEFAULT_START_DATE)
            .expireDate(DEFAULT_EXPIRE_DATE)
            .useGuide(DEFAULT_USE_GUIDE)
            .terms(DEFAULT_TERMS)
            .tags(DEFAULT_TAGS)
            .status(DEFAULT_STATUS)
            .userId(DEFAULT_USER_ID);
        return gift;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gift createUpdatedEntity(EntityManager em) {
        Gift gift = new Gift()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .icon(UPDATED_ICON)
            .mediaPath(UPDATED_MEDIA_PATH)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .price(UPDATED_PRICE)
            .publishDate(UPDATED_PUBLISH_DATE)
            .startDate(UPDATED_START_DATE)
            .expireDate(UPDATED_EXPIRE_DATE)
            .useGuide(UPDATED_USE_GUIDE)
            .terms(UPDATED_TERMS)
            .tags(UPDATED_TAGS)
            .status(UPDATED_STATUS)
            .userId(UPDATED_USER_ID);
        return gift;
    }

    @BeforeEach
    public void initTest() {
        gift = createEntity(em);
    }

    @Test
    @Transactional
    void createGift() throws Exception {
        int databaseSizeBeforeCreate = giftRepository.findAll().size();
        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);
        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isCreated());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeCreate + 1);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testGift.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGift.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGift.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testGift.getMediaPath()).isEqualTo(DEFAULT_MEDIA_PATH);
        assertThat(testGift.getOriginalPrice()).isEqualByComparingTo(DEFAULT_ORIGINAL_PRICE);
        assertThat(testGift.getPrice()).isEqualByComparingTo(DEFAULT_PRICE);
        assertThat(testGift.getPublishDate()).isEqualTo(DEFAULT_PUBLISH_DATE);
        assertThat(testGift.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testGift.getExpireDate()).isEqualTo(DEFAULT_EXPIRE_DATE);
        assertThat(testGift.getUseGuide()).isEqualTo(DEFAULT_USE_GUIDE);
        assertThat(testGift.getTerms()).isEqualTo(DEFAULT_TERMS);
        assertThat(testGift.getTags()).isEqualTo(DEFAULT_TAGS);
        assertThat(testGift.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGift.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    void createGiftWithExistingId() throws Exception {
        // Create the Gift with an existing ID
        gift.setId(1L);
        GiftDTO giftDTO = giftMapper.toDto(gift);

        int databaseSizeBeforeCreate = giftRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkOriginalPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftRepository.findAll().size();
        // set the field null
        gift.setOriginalPrice(null);

        // Create the Gift, which fails.
        GiftDTO giftDTO = giftMapper.toDto(gift);

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isBadRequest());

        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftRepository.findAll().size();
        // set the field null
        gift.setPrice(null);

        // Create the Gift, which fails.
        GiftDTO giftDTO = giftMapper.toDto(gift);

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isBadRequest());

        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGifts() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get all the giftList
        restGiftMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gift.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].mediaPath").value(hasItem(DEFAULT_MEDIA_PATH)))
            .andExpect(jsonPath("$.[*].originalPrice").value(hasItem(sameNumber(DEFAULT_ORIGINAL_PRICE))))
            .andExpect(jsonPath("$.[*].price").value(hasItem(sameNumber(DEFAULT_PRICE))))
            .andExpect(jsonPath("$.[*].publishDate").value(hasItem(DEFAULT_PUBLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].expireDate").value(hasItem(DEFAULT_EXPIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].useGuide").value(hasItem(DEFAULT_USE_GUIDE)))
            .andExpect(jsonPath("$.[*].terms").value(hasItem(DEFAULT_TERMS)))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }

    @Test
    @Transactional
    void getGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get the gift
        restGiftMockMvc
            .perform(get(ENTITY_API_URL_ID, gift.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gift.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.mediaPath").value(DEFAULT_MEDIA_PATH))
            .andExpect(jsonPath("$.originalPrice").value(sameNumber(DEFAULT_ORIGINAL_PRICE)))
            .andExpect(jsonPath("$.price").value(sameNumber(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.publishDate").value(DEFAULT_PUBLISH_DATE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.expireDate").value(DEFAULT_EXPIRE_DATE.toString()))
            .andExpect(jsonPath("$.useGuide").value(DEFAULT_USE_GUIDE))
            .andExpect(jsonPath("$.terms").value(DEFAULT_TERMS))
            .andExpect(jsonPath("$.tags").value(DEFAULT_TAGS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }

    @Test
    @Transactional
    void getNonExistingGift() throws Exception {
        // Get the gift
        restGiftMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift
        Gift updatedGift = giftRepository.findById(gift.getId()).get();
        // Disconnect from session so that the updates on updatedGift are not directly saved in db
        em.detach(updatedGift);
        updatedGift
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .icon(UPDATED_ICON)
            .mediaPath(UPDATED_MEDIA_PATH)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .price(UPDATED_PRICE)
            .publishDate(UPDATED_PUBLISH_DATE)
            .startDate(UPDATED_START_DATE)
            .expireDate(UPDATED_EXPIRE_DATE)
            .useGuide(UPDATED_USE_GUIDE)
            .terms(UPDATED_TERMS)
            .tags(UPDATED_TAGS)
            .status(UPDATED_STATUS)
            .userId(UPDATED_USER_ID);
        GiftDTO giftDTO = giftMapper.toDto(updatedGift);

        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftDTO))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGift.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGift.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGift.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testGift.getMediaPath()).isEqualTo(UPDATED_MEDIA_PATH);
        assertThat(testGift.getOriginalPrice()).isEqualTo(UPDATED_ORIGINAL_PRICE);
        assertThat(testGift.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testGift.getPublishDate()).isEqualTo(UPDATED_PUBLISH_DATE);
        assertThat(testGift.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGift.getExpireDate()).isEqualTo(UPDATED_EXPIRE_DATE);
        assertThat(testGift.getUseGuide()).isEqualTo(UPDATED_USE_GUIDE);
        assertThat(testGift.getTerms()).isEqualTo(UPDATED_TERMS);
        assertThat(testGift.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testGift.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGift.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void putNonExistingGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, giftDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(giftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGiftWithPatch() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift using partial update
        Gift partialUpdatedGift = new Gift();
        partialUpdatedGift.setId(gift.getId());

        partialUpdatedGift
            .description(UPDATED_DESCRIPTION)
            .publishDate(UPDATED_PUBLISH_DATE)
            .startDate(UPDATED_START_DATE)
            .tags(UPDATED_TAGS);

        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGift.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testGift.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGift.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGift.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testGift.getMediaPath()).isEqualTo(DEFAULT_MEDIA_PATH);
        assertThat(testGift.getOriginalPrice()).isEqualByComparingTo(DEFAULT_ORIGINAL_PRICE);
        assertThat(testGift.getPrice()).isEqualByComparingTo(DEFAULT_PRICE);
        assertThat(testGift.getPublishDate()).isEqualTo(UPDATED_PUBLISH_DATE);
        assertThat(testGift.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGift.getExpireDate()).isEqualTo(DEFAULT_EXPIRE_DATE);
        assertThat(testGift.getUseGuide()).isEqualTo(DEFAULT_USE_GUIDE);
        assertThat(testGift.getTerms()).isEqualTo(DEFAULT_TERMS);
        assertThat(testGift.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testGift.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGift.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    void fullUpdateGiftWithPatch() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift using partial update
        Gift partialUpdatedGift = new Gift();
        partialUpdatedGift.setId(gift.getId());

        partialUpdatedGift
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .icon(UPDATED_ICON)
            .mediaPath(UPDATED_MEDIA_PATH)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .price(UPDATED_PRICE)
            .publishDate(UPDATED_PUBLISH_DATE)
            .startDate(UPDATED_START_DATE)
            .expireDate(UPDATED_EXPIRE_DATE)
            .useGuide(UPDATED_USE_GUIDE)
            .terms(UPDATED_TERMS)
            .tags(UPDATED_TAGS)
            .status(UPDATED_STATUS)
            .userId(UPDATED_USER_ID);

        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGift.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGift.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGift.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGift.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testGift.getMediaPath()).isEqualTo(UPDATED_MEDIA_PATH);
        assertThat(testGift.getOriginalPrice()).isEqualByComparingTo(UPDATED_ORIGINAL_PRICE);
        assertThat(testGift.getPrice()).isEqualByComparingTo(UPDATED_PRICE);
        assertThat(testGift.getPublishDate()).isEqualTo(UPDATED_PUBLISH_DATE);
        assertThat(testGift.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testGift.getExpireDate()).isEqualTo(UPDATED_EXPIRE_DATE);
        assertThat(testGift.getUseGuide()).isEqualTo(UPDATED_USE_GUIDE);
        assertThat(testGift.getTerms()).isEqualTo(UPDATED_TERMS);
        assertThat(testGift.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testGift.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGift.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void patchNonExistingGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, giftDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(giftDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // Create the Gift
        GiftDTO giftDTO = giftMapper.toDto(gift);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(giftDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeDelete = giftRepository.findAll().size();

        // Delete the gift
        restGiftMockMvc
            .perform(delete(ENTITY_API_URL_ID, gift.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
