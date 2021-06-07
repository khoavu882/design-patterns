package com.malu.base.gift.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.service.GiftSeasonExtService;
import com.malu.base.gift.service.dto.GiftSeasonDTO;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
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
 * REST controller for managing {@link com.malu.base.gift.domain.GiftSeason}.
 */
@RestController
@RequestMapping("/api/v1/gift-seasons")
public class GiftSeasonExtResource {

    private final Logger log = LoggerFactory.getLogger(GiftSeasonExtResource.class);

    private static final String ENTITY_NAME = "giftGiftSeason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftSeasonExtService giftSeasonExtService;

    public GiftSeasonExtResource(@Qualifier(ApplicationConstant.EXTEND) GiftSeasonExtService giftSeasonExtService) {
        this.giftSeasonExtService = giftSeasonExtService;
    }

    /**
     * {@code POST  } : Create a new giftSeason.
     *
     * @param giftSeasonExtDTO the giftSeasonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftSeasonDTO, or with status {@code 400 (Bad Request)} if the giftSeason has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping()
    public ResponseEntity<GiftSeason> createGiftSeason(@Valid @RequestBody GiftSeasonExtDTO giftSeasonExtDTO) throws URISyntaxException {
        log.debug("REST request to save GiftSeason : {}", giftSeasonExtDTO);
        GiftSeason result = giftSeasonExtService.create(giftSeasonExtDTO);
        return ResponseEntity
            .created(new URI("/api/gift-seasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gift-seasons/:id} : Updates an existing giftSeason.
     *
     * @param giftSeasonExtDTO the giftSeasonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftSeasonDTO,
     * or with status {@code 400 (Bad Request)} if the giftSeasonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftSeasonDTO couldn't be updated.
     */
    @PutMapping()
    public ResponseEntity<GiftSeason> updateGiftSeason(
        @Valid @RequestBody GiftSeasonExtDTO giftSeasonExtDTO
    ) {
        log.debug("REST request to update GiftSeason : {}", giftSeasonExtDTO);
        GiftSeason result = giftSeasonExtService.update(giftSeasonExtDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftSeasonExtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  } : get all the giftSeasons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftSeasons in body.
     */
    @GetMapping()
    public ResponseEntity<List<GiftSeasonDTO>> getAllGiftSeasons(Pageable pageable) {
        log.debug("REST request to get a page of GiftSeasons");
        Page<GiftSeasonDTO> page = giftSeasonExtService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-seasons/:id} : get the "id" giftSeason.
     *
     * @param id the id of the giftSeasonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftSeasonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<GiftSeasonDTO> getGiftSeason(@PathVariable Long id) {
        log.debug("REST request to get GiftSeason : {}", id);
        Optional<GiftSeasonDTO> giftSeasonDTO = giftSeasonExtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftSeasonDTO);
    }

    /**
     * {@code DELETE  /gift-seasons/:id} : delete the "id" giftSeason.
     *
     * @param id the id of the giftSeasonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiftSeason(@PathVariable Long id) {
        log.debug("REST request to delete GiftSeason : {}", id);
        giftSeasonExtService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
