package com.malu.base.gift.web.rest;

import com.malu.base.gift.repository.GiftLangRepository;
import com.malu.base.gift.service.GiftLangService;
import com.malu.base.gift.service.dto.GiftLangDTO;
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
 * REST controller for managing {@link com.malu.base.gift.domain.GiftLang}.
 */
@RestController
@RequestMapping("/api")
public class GiftLangResource {

    private final Logger log = LoggerFactory.getLogger(GiftLangResource.class);

    private static final String ENTITY_NAME = "giftGiftLang";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftLangService giftLangService;

    private final GiftLangRepository giftLangRepository;

    public GiftLangResource(GiftLangService giftLangService, GiftLangRepository giftLangRepository) {
        this.giftLangService = giftLangService;
        this.giftLangRepository = giftLangRepository;
    }

    /**
     * {@code POST  /gift-langs} : Create a new giftLang.
     *
     * @param giftLangDTO the giftLangDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftLangDTO, or with status {@code 400 (Bad Request)} if the giftLang has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gift-langs")
    public ResponseEntity<GiftLangDTO> createGiftLang(@Valid @RequestBody GiftLangDTO giftLangDTO) throws URISyntaxException {
        log.debug("REST request to save GiftLang : {}", giftLangDTO);
        if (giftLangDTO.getId() != null) {
            throw new BadRequestAlertException("A new giftLang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GiftLangDTO result = giftLangService.save(giftLangDTO);
        return ResponseEntity
            .created(new URI("/api/gift-langs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gift-langs/:id} : Updates an existing giftLang.
     *
     * @param id the id of the giftLangDTO to save.
     * @param giftLangDTO the giftLangDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftLangDTO,
     * or with status {@code 400 (Bad Request)} if the giftLangDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftLangDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gift-langs/{id}")
    public ResponseEntity<GiftLangDTO> updateGiftLang(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GiftLangDTO giftLangDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GiftLang : {}, {}", id, giftLangDTO);
        if (giftLangDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giftLangDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftLangRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GiftLangDTO result = giftLangService.save(giftLangDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftLangDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /gift-langs/:id} : Partial updates given fields of an existing giftLang, field will ignore if it is null
     *
     * @param id the id of the giftLangDTO to save.
     * @param giftLangDTO the giftLangDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftLangDTO,
     * or with status {@code 400 (Bad Request)} if the giftLangDTO is not valid,
     * or with status {@code 404 (Not Found)} if the giftLangDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the giftLangDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/gift-langs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<GiftLangDTO> partialUpdateGiftLang(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GiftLangDTO giftLangDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GiftLang partially : {}, {}", id, giftLangDTO);
        if (giftLangDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, giftLangDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftLangRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GiftLangDTO> result = giftLangService.partialUpdate(giftLangDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftLangDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /gift-langs} : get all the giftLangs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftLangs in body.
     */
    @GetMapping("/gift-langs")
    public ResponseEntity<List<GiftLangDTO>> getAllGiftLangs(Pageable pageable) {
        log.debug("REST request to get a page of GiftLangs");
        Page<GiftLangDTO> page = giftLangService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-langs/:id} : get the "id" giftLang.
     *
     * @param id the id of the giftLangDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftLangDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gift-langs/{id}")
    public ResponseEntity<GiftLangDTO> getGiftLang(@PathVariable Long id) {
        log.debug("REST request to get GiftLang : {}", id);
        Optional<GiftLangDTO> giftLangDTO = giftLangService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftLangDTO);
    }

    /**
     * {@code DELETE  /gift-langs/:id} : delete the "id" giftLang.
     *
     * @param id the id of the giftLangDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gift-langs/{id}")
    public ResponseEntity<Void> deleteGiftLang(@PathVariable Long id) {
        log.debug("REST request to delete GiftLang : {}", id);
        giftLangService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
