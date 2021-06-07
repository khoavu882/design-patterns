package com.malu.base.gift.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.service.GiftProviderExtService;
import com.malu.base.gift.service.dto.GiftProviderDTO;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
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
 * REST controller for managing {@link com.malu.base.gift.domain.GiftProvider}.
 */
@RestController
@RequestMapping("/api/v1/gift-providers")
public class GiftProviderExtResource {

    private final Logger log = LoggerFactory.getLogger(GiftProviderExtResource.class);

    private static final String ENTITY_NAME = "giftGiftProvider";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftProviderExtService giftProviderExtService;

    public GiftProviderExtResource(@Qualifier(ApplicationConstant.EXTEND) GiftProviderExtService giftProviderExtService) {
        this.giftProviderExtService = giftProviderExtService;
    }

    /**
     * {@code POST  } : Create a new giftProvider.
     *
     * @param giftProviderExtDTO the GiftProviderExtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new GiftProviderExtDTO, or with status {@code 400 (Bad Request)} if the giftProvider has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping()
    public ResponseEntity<GiftProvider> createGiftProvider(@Valid @RequestBody GiftProviderExtDTO giftProviderExtDTO)
        throws URISyntaxException {
        log.debug("REST request to save GiftProvider : {}", giftProviderExtDTO);
        GiftProvider result = giftProviderExtService.create(giftProviderExtDTO);
        return ResponseEntity
            .created(new URI("/api/gift-providers/details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  } : Updates an existing giftProvider.
     *
     * @param giftProviderExtDTO the GiftProviderExtDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated GiftProviderExtDTO,
     * or with status {@code 400 (Bad Request)} if the GiftProviderExtDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the GiftProviderExtDTO couldn't be updated.
     */
    @PutMapping()
    public ResponseEntity<GiftProvider> updateGiftProvider(
        @Valid @RequestBody GiftProviderExtDTO giftProviderExtDTO
    ) {
        log.debug("REST request to update GiftProvider : {}", giftProviderExtDTO);
        GiftProvider result = giftProviderExtService.update(giftProviderExtDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftProviderExtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gift-providers} : get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftProviders in body.
     */
    @GetMapping()
    public ResponseEntity<List<GiftProviderDTO>> getAllGiftProviders(Pageable pageable) {
        log.debug("REST request to get a page of GiftProviders");
        Page<GiftProviderDTO> page = giftProviderExtService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-providers/:id} : get the "id" giftProvider.
     *
     * @param id the id of the GiftProviderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the GiftProviderExtDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<GiftProviderDTO> getGiftProvider(@PathVariable Long id) {
        log.debug("REST request to get GiftProvider : {}", id);
        Optional<GiftProviderDTO> giftProviderDTO = giftProviderExtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(giftProviderDTO);
    }

    /**
     * {@code DELETE  /gift-providers/:id} : delete the "id" giftProvider.
     *
     * @param id the id of the GiftProviderExtDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiftProvider(@PathVariable Long id) {
        log.debug("REST request to delete GiftProvider : {}", id);
        giftProviderExtService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
