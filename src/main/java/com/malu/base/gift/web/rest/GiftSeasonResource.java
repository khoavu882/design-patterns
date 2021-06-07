package com.malu.base.gift.web.rest;

import com.malu.base.gift.repository.GiftSeasonRepository;
import com.malu.base.gift.service.GiftSeasonService;
import com.malu.base.gift.service.dto.GiftSeasonDTO;
import com.malu.base.gift.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.malu.base.gift.domain.GiftSeason}.
 */
@RestController
@RequestMapping("/api")
public class GiftSeasonResource {

    private final Logger log = LoggerFactory.getLogger(GiftSeasonResource.class);

    private static final String ENTITY_NAME = "giftGiftSeason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftSeasonService giftSeasonService;

    private final GiftSeasonRepository giftSeasonRepository;

    public GiftSeasonResource(GiftSeasonService giftSeasonService, GiftSeasonRepository giftSeasonRepository) {
        this.giftSeasonService = giftSeasonService;
        this.giftSeasonRepository = giftSeasonRepository;
    }

    /**
     * {@code POST  /gift-seasons} : Create a new giftSeason.
     *
     * @param giftSeasonDTO the giftSeasonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftSeasonDTO, or with status {@code 400 (Bad Request)} if the giftSeason has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gift-seasons")
    public ResponseEntity<GiftSeasonDTO> createGiftSeason(@Valid @RequestBody GiftSeasonDTO giftSeasonDTO) throws URISyntaxException {
        log.debug("REST request to save GiftSeason : {}", giftSeasonDTO);
        if (giftSeasonDTO.getId() != null) {
            throw new BadRequestAlertException("A new giftSeason cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GiftSeasonDTO result = giftSeasonService.save(giftSeasonDTO);
        return ResponseEntity
            .created(new URI("/api/gift-seasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gift-seasons/:id} : Updates an existing giftSeason.
     *
     * @param id the id of the giftSeasonDTO to save.
     * @param giftSeasonDTO the giftSeasonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftSeasonDTO,
     * or with status {@code 400 (Bad Request)} if the giftSeasonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftSeasonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gift-seasons/{id}")
    public ResponseEntity<GiftSeasonDTO> updateGiftSeason(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GiftSeasonDTO giftSeasonDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GiftSeason : {}, {}", id, giftSeasonDTO);
        if (giftSeasonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giftSeasonDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftSeasonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GiftSeasonDTO result = giftSeasonService.save(giftSeasonDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftSeasonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /gift-seasons/:id} : Partial updates given fields of an existing giftSeason, field will ignore if it is null
     *
     * @param id the id of the giftSeasonDTO to save.
     * @param giftSeasonDTO the giftSeasonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftSeasonDTO,
     * or with status {@code 400 (Bad Request)} if the giftSeasonDTO is not valid,
     * or with status {@code 404 (Not Found)} if the giftSeasonDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the giftSeasonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/gift-seasons/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<GiftSeasonDTO> partialUpdateGiftSeason(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GiftSeasonDTO giftSeasonDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GiftSeason partially : {}, {}", id, giftSeasonDTO);
        if (giftSeasonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giftSeasonDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftSeasonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GiftSeasonDTO> result = giftSeasonService.partialUpdate(giftSeasonDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftSeasonDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /gift-seasons} : get all the giftSeasons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftSeasons in body.
     */
    @GetMapping("/gift-seasons")
    public ResponseEntity<List<GiftSeasonDTO>> getAllGiftSeasons(Pageable pageable) {
        log.debug("REST request to get a page of GiftSeasons");
        Page<GiftSeasonDTO> page = giftSeasonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-seasons/:id} : get the "id" giftSeason.
     *
     * @param id the id of the giftSeasonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftSeasonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gift-seasons/{id}")
    public ResponseEntity<GiftSeasonDTO> getGiftSeason(@PathVariable Long id) {
        log.debug("REST request to get GiftSeason : {}", id);
        Optional<GiftSeasonDTO> giftSeasonDTO = giftSeasonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftSeasonDTO);
    }

    /**
     * {@code DELETE  /gift-seasons/:id} : delete the "id" giftSeason.
     *
     * @param id the id of the giftSeasonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gift-seasons/{id}")
    public ResponseEntity<Void> deleteGiftSeason(@PathVariable Long id) {
        log.debug("REST request to delete GiftSeason : {}", id);
        giftSeasonService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
