package com.malu.base.gift.consumer.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftSeasonConsumerService;
import com.malu.base.gift.consumer.service.dto.GiftSeasonConsumerDTO;
import com.malu.base.gift.consumer.web.rest.vm.GiftSeasonConsumerVM;
import com.malu.base.gift.domain.GiftSeason;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;

/**
 * REST controller for managing {@link GiftSeason}.
 */
@RestController
@RequestMapping("/api/v1/consumer")
public class GiftSeasonConsumerResource {

    private final Logger log = LoggerFactory.getLogger(GiftSeasonConsumerResource.class);

    private static final String ENTITY_NAME = "giftGiftSeason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftSeasonConsumerService giftSeasonConsumerService;

    public GiftSeasonConsumerResource(@Qualifier(ApplicationConstant.CONSUMER) GiftSeasonConsumerService giftSeasonConsumerService) {
        this.giftSeasonConsumerService = giftSeasonConsumerService;
    }

    /**
     * {@code POST  /gift-seasons} : Create a new giftSeason.
     *
     * @param giftSeasonConsumerDTO the giftSeasonConsumerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftSeasonConsumerDTO, or with status {@code 400 (Bad Request)} if the giftSeason has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gift-seasons")
    public ResponseEntity<GiftSeasonConsumerVM> createGiftSeason(@Valid @RequestBody GiftSeasonConsumerDTO giftSeasonConsumerDTO)
        throws URISyntaxException {
        log.debug("REST request to save GiftSeason : {}", giftSeasonConsumerDTO);
        GiftSeasonConsumerVM result = giftSeasonConsumerService.createByConsumer(giftSeasonConsumerDTO);
        return ResponseEntity
            .created(new URI("/api/gift-seasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gift-seasons} : Updates an existing giftSeason.
     *
     * @param giftSeasonConsumerDTO the giftSeasonConsumerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftSeasonConsumerDTO,
     * or with status {@code 400 (Bad Request)} if the giftSeasonConsumerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftSeasonConsumerDTO couldn't be updated.
     */
    @PutMapping("/gift-seasons")
    public ResponseEntity<GiftSeasonConsumerVM> updateGiftSeason(@Valid @RequestBody GiftSeasonConsumerDTO giftSeasonConsumerDTO) {
        log.debug("REST request to update GiftSeason : {}", giftSeasonConsumerDTO);
        GiftSeasonConsumerVM result = giftSeasonConsumerService.updateByConsumer(giftSeasonConsumerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftSeasonConsumerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  } : get all the giftSeasons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftSeasons in body.
     */
    @GetMapping("/gift-seasons")
    public ResponseEntity<List<GiftSeasonConsumerVM>> getAllGiftSeasons(
        @RequestParam(value = "keyword", required = false) String keyword,
        @PageableDefault(size = 20, page = 0) @SortDefault.SortDefaults(
            { @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC) }
        ) Pageable pageable
    ) {
        log.debug("REST request to get a page of GiftSeasons");
        Page<GiftSeasonConsumerVM> page = giftSeasonConsumerService.findAllWithFilterByConsumer(keyword, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-seasons/:id} : get the "id" giftSeason.
     *
     * @param id the id of the giftSeasonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftSeasonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gift-seasons/details/{id}")
    public ResponseEntity<GiftSeasonConsumerVM> getGiftSeason(@PathVariable Long id) {
        log.debug("REST request to get GiftSeason : {}", id);
        GiftSeasonConsumerVM giftSeasonConsumerVM = giftSeasonConsumerService.findOneByConsumer(id);
        return ResponseEntity.ok().body(giftSeasonConsumerVM);
    }
}
