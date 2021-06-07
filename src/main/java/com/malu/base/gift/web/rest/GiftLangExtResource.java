package com.malu.base.gift.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.service.GiftLangExtService;
import com.malu.base.gift.service.dto.GiftLangDTO;
import com.malu.base.gift.service.dto.GiftLangExtDTO;
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
 * REST controller for managing {@link com.malu.base.gift.domain.GiftLang}.
 */
@RestController
@RequestMapping("/api/v1/gift-languages")
public class GiftLangExtResource {

    private final Logger log = LoggerFactory.getLogger(GiftLangExtResource.class);

    private static final String ENTITY_NAME = "giftGiftLang";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftLangExtService giftLangExtService;

    public GiftLangExtResource(@Qualifier(ApplicationConstant.EXTEND) GiftLangExtService giftLangExtService) {
        this.giftLangExtService = giftLangExtService;
    }

    /**
     * {@code POST  } : Create a new giftLang.
     *
     * @param giftLangExtDTO the giftLangExtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftLangExtDTO, or with status {@code 400 (Bad Request)} if the giftLang has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping()
    public ResponseEntity<GiftLang> createGiftLang(@Valid @RequestBody GiftLangExtDTO giftLangExtDTO) throws URISyntaxException {
        log.debug("REST request to save GiftLang : {}", giftLangExtDTO);
        GiftLang result = giftLangExtService.create(giftLangExtDTO);
        return ResponseEntity
            .created(new URI("/api/gift-languages/details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  } : Updates an existing giftLang.
     *
     * @param giftLangExtDTO the giftLangDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftLangDTO,
     * or with status {@code 400 (Bad Request)} if the giftLangDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftLangDTO couldn't be updated.
     */
    @PutMapping()
    public ResponseEntity<GiftLang> updateGiftLang(@Valid @RequestBody GiftLangExtDTO giftLangExtDTO) {
        log.debug("REST request to update GiftLang : {}", giftLangExtDTO);
        GiftLang result = giftLangExtService.update(giftLangExtDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftLangExtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  } : get all the giftLangs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftLangs in body.
     */
    @GetMapping()
    public ResponseEntity<List<GiftLangDTO>> getAllGiftLangs(Pageable pageable) {
        log.debug("REST request to get a page of GiftLangs");
        Page<GiftLangDTO> page = giftLangExtService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /details/:id} : get the "id" giftLang.
     *
     * @param id the id of the giftLangDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftLangDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<GiftLangDTO> getGiftLang(@PathVariable Long id) {
        log.debug("REST request to get GiftLang : {}", id);
        Optional<GiftLangDTO> giftLangDTO = giftLangExtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftLangDTO);
    }

    /**
     * {@code DELETE  /:id} : delete the "id" giftLang.
     *
     * @param id the id of the giftLangDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiftLang(@PathVariable Long id) {
        log.debug("REST request to delete GiftLang : {}", id);
        giftLangExtService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
