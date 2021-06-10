package com.malu.base.gift.consumer.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftConsumerService;
import com.malu.base.gift.consumer.service.dto.GiftConsumerDTO;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.dto.GiftDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link Gift}.
 */
@RestController
@RequestMapping("/api/v1/consumer")
public class GiftConsumerResource {

    private final Logger log = LoggerFactory.getLogger(GiftConsumerResource.class);

    private static final String ENTITY_NAME = "giftGift";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftConsumerService giftConsumerService;

    public GiftConsumerResource(@Qualifier(ApplicationConstant.CONSUMER) GiftConsumerService giftConsumerService) {
        this.giftConsumerService = giftConsumerService;
    }

    /**
     * {@code POST  /gifts} : Create a new gift.
     *
     * @param giftConsumerDTO the GiftExtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftDTO, or with status {@code 400 (Bad Request)} if the gift has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gifts")
    public ResponseEntity<GiftConsumerVM> createGift(@Valid @RequestBody GiftConsumerDTO giftConsumerDTO) throws URISyntaxException {
        log.debug("REST request to save Gift : {}", giftConsumerDTO);
        GiftConsumerVM giftConsumerVM = giftConsumerService.createByConsumer(giftConsumerDTO);
        return ResponseEntity
            .created(new URI("/api/gifts/" + giftConsumerVM.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, giftConsumerVM.getId().toString()))
            .body(giftConsumerVM);
    }

    /**
     * {@code PUT  /gifts} : Updates an existing gift.
     *
     * @param giftConsumerDTO the giftConsumerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftConsumerDTO,
     * or with status {@code 400 (Bad Request)} if the giftConsumerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftConsumerDTO couldn't be updated.
     */
    @PutMapping("/gifts")
    public ResponseEntity<GiftConsumerVM> updateGift(@Valid @RequestBody GiftConsumerDTO giftConsumerDTO) {
        log.debug("REST request to update Gift : {}", giftConsumerDTO);
        GiftConsumerVM giftConsumerVM = giftConsumerService.updateByConsumer(giftConsumerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftConsumerVM.getId().toString()))
            .body(giftConsumerVM);
    }

    /**
     * {@code GET  } : get all the gifts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping("/gifts")
    public ResponseEntity<List<GiftDTO>> getAllGifts(Pageable pageable) {
        log.debug("REST request to get a page of Gifts");
        Page<GiftDTO> page = giftConsumerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gifts/details/:id} : get the "id" gift.
     *
     * @param id the id of the giftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gifts/details/{id}")
    public ResponseEntity<GiftDTO> getGift(@PathVariable Long id) {
        log.debug("REST request to get Gift : {}", id);
        Optional<GiftDTO> giftDTO = giftConsumerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftDTO);
    }

    /**
     * {@code DELETE  /gifts/:id} : delete the "id" gift.
     *
     * @param id the id of the giftDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gifts/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
        log.debug("REST request to delete Gift : {}", id);
        giftConsumerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
