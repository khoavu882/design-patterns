package com.malu.base.gift.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.GiftExtService;
import com.malu.base.gift.service.dto.GiftDTO;
import com.malu.base.gift.service.dto.GiftExtDTO;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.malu.base.gift.domain.Gift}.
 */
@RestController
@RequestMapping("/api/v1/gifts")
public class GiftExtResource {

    private final Logger log = LoggerFactory.getLogger(GiftExtResource.class);

    private static final String ENTITY_NAME = "giftGift";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftExtService giftExtService;

    public GiftExtResource(@Qualifier(ApplicationConstant.EXTEND) GiftExtService giftExtService) {
        this.giftExtService = giftExtService;
    }

    /**
     * {@code POST  } : Create a new gift.
     *
     * @param giftExtDTO the GiftExtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftDTO, or with status {@code 400 (Bad Request)} if the gift has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping()
    public ResponseEntity<Gift> createGift(@Valid @RequestBody GiftExtDTO giftExtDTO) throws URISyntaxException {
        log.debug("REST request to save Gift : {}", giftExtDTO);
        Gift result = giftExtService.create(giftExtDTO);
        return ResponseEntity
            .created(new URI("/api/gifts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  } : Updates an existing gift.
     *
     * @param giftExtDTO the giftDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftDTO,
     * or with status {@code 400 (Bad Request)} if the giftDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftDTO couldn't be updated.
     */
    @PutMapping()
    public ResponseEntity<Gift> updateGift(
        @Valid @RequestBody GiftExtDTO giftExtDTO
    ) {
        log.debug("REST request to update Gift : {}", giftExtDTO);
        Gift result = giftExtService.update(giftExtDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftExtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  } : get all the gifts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping()
    public ResponseEntity<List<GiftDTO>> getAllGifts(Pageable pageable) {
        log.debug("REST request to get a page of Gifts");
        Page<GiftDTO> page = giftExtService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /details/:id} : get the "id" gift.
     *
     * @param id the id of the giftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<GiftDTO> getGift(@PathVariable Long id) {
        log.debug("REST request to get Gift : {}", id);
        Optional<GiftDTO> giftDTO = giftExtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftDTO);
    }

    /**
     * {@code DELETE  /:id} : delete the "id" gift.
     *
     * @param id the id of the giftDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
        log.debug("REST request to delete Gift : {}", id);
        giftExtService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
