package com.malu.base.gift.web.rest;

import com.malu.base.gift.repository.GiftProviderRepository;
import com.malu.base.gift.service.GiftProviderService;
import com.malu.base.gift.service.dto.GiftProviderDTO;
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
 * REST controller for managing {@link com.malu.base.gift.domain.GiftProvider}.
 */
@RestController
@RequestMapping("/api")
public class GiftProviderResource {

    private final Logger log = LoggerFactory.getLogger(GiftProviderResource.class);

    private static final String ENTITY_NAME = "giftGiftProvider";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftProviderService giftProviderService;

    private final GiftProviderRepository giftProviderRepository;

    public GiftProviderResource(GiftProviderService giftProviderService, GiftProviderRepository giftProviderRepository) {
        this.giftProviderService = giftProviderService;
        this.giftProviderRepository = giftProviderRepository;
    }

    /**
     * {@code POST  /gift-providers} : Create a new giftProvider.
     *
     * @param giftProviderDTO the giftProviderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftProviderDTO, or with status {@code 400 (Bad Request)} if the giftProvider has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gift-providers")
    public ResponseEntity<GiftProviderDTO> createGiftProvider(@Valid @RequestBody GiftProviderDTO giftProviderDTO)
        throws URISyntaxException {
        log.debug("REST request to save GiftProvider : {}", giftProviderDTO);
        if (giftProviderDTO.getId() != null) {
            throw new BadRequestAlertException("A new giftProvider cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GiftProviderDTO result = giftProviderService.save(giftProviderDTO);
        return ResponseEntity
            .created(new URI("/api/gift-providers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gift-providers/:id} : Updates an existing giftProvider.
     *
     * @param id the id of the giftProviderDTO to save.
     * @param giftProviderDTO the giftProviderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftProviderDTO,
     * or with status {@code 400 (Bad Request)} if the giftProviderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftProviderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gift-providers/{id}")
    public ResponseEntity<GiftProviderDTO> updateGiftProvider(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GiftProviderDTO giftProviderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GiftProvider : {}, {}", id, giftProviderDTO);
        if (giftProviderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giftProviderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftProviderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GiftProviderDTO result = giftProviderService.save(giftProviderDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftProviderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /gift-providers/:id} : Partial updates given fields of an existing giftProvider, field will ignore if it is null
     *
     * @param id the id of the giftProviderDTO to save.
     * @param giftProviderDTO the giftProviderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftProviderDTO,
     * or with status {@code 400 (Bad Request)} if the giftProviderDTO is not valid,
     * or with status {@code 404 (Not Found)} if the giftProviderDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the giftProviderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/gift-providers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<GiftProviderDTO> partialUpdateGiftProvider(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GiftProviderDTO giftProviderDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GiftProvider partially : {}, {}", id, giftProviderDTO);
        if (giftProviderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giftProviderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftProviderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GiftProviderDTO> result = giftProviderService.partialUpdate(giftProviderDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftProviderDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /gift-providers} : get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftProviders in body.
     */
    @GetMapping("/gift-providers")
    public ResponseEntity<List<GiftProviderDTO>> getAllGiftProviders(Pageable pageable) {
        log.debug("REST request to get a page of GiftProviders");
        Page<GiftProviderDTO> page = giftProviderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-providers/:id} : get the "id" giftProvider.
     *
     * @param id the id of the giftProviderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftProviderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gift-providers/{id}")
    public ResponseEntity<GiftProviderDTO> getGiftProvider(@PathVariable Long id) {
        log.debug("REST request to get GiftProvider : {}", id);
        Optional<GiftProviderDTO> giftProviderDTO = giftProviderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftProviderDTO);
    }

    /**
     * {@code DELETE  /gift-providers/:id} : delete the "id" giftProvider.
     *
     * @param id the id of the giftProviderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gift-providers/{id}")
    public ResponseEntity<Void> deleteGiftProvider(@PathVariable Long id) {
        log.debug("REST request to delete GiftProvider : {}", id);
        giftProviderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
