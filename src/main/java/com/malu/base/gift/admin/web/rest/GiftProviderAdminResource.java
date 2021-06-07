package com.malu.base.gift.admin.web.rest;

import com.malu.base.gift.admin.service.GiftProviderAdminService;
import com.malu.base.gift.admin.web.rest.vm.GiftProviderAdminVM;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

/**
 * REST controller for managing {@link com.malu.base.gift.domain.GiftProvider}.
 */
@RestController
@RequestMapping("/api/v1/admin")
public class GiftProviderAdminResource {

    private final Logger log = LoggerFactory.getLogger(GiftProviderAdminResource.class);

    private static final String ENTITY_NAME = "giftGiftProvider";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftProviderAdminService giftProviderAdminService;

    public GiftProviderAdminResource(@Qualifier(ApplicationConstant.ADMIN) GiftProviderAdminService giftProviderAdminService) {
        this.giftProviderAdminService = giftProviderAdminService;
    }

    /**
     * {@code POST  /gift-providers} : Create a new giftProvider.
     *
     * @param giftProviderExtDTO the giftProviderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftProviderDTO, or with status {@code 400 (Bad Request)} if the giftProvider has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gift-providers")
    public ResponseEntity<GiftProviderAdminVM> createGiftProvider(@Valid @RequestBody GiftProviderExtDTO giftProviderExtDTO)
        throws URISyntaxException {
        log.debug("REST request to save GiftProvider : {}", giftProviderExtDTO);
        GiftProviderAdminVM giftProviderAdminVM = giftProviderAdminService.createByAdmin(giftProviderExtDTO);
        return ResponseEntity
            .created(new URI("/api/gift-providers/" + giftProviderAdminVM.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, giftProviderAdminVM.getId().toString()))
            .body(giftProviderAdminVM);
    }

    /**
     * {@code PUT  /gift-providers/:id} : Updates an existing giftProvider.
     *
     * @param giftProviderExtDTO the giftProviderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftProviderDTO,
     * or with status {@code 400 (Bad Request)} if the giftProviderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftProviderDTO couldn't be updated.
     */
    @PutMapping("/gift-providers")
    public ResponseEntity<GiftProviderAdminVM> updateGiftProvider(@Valid @RequestBody GiftProviderExtDTO giftProviderExtDTO) {
        log.debug("REST request to update GiftProvider : {}", giftProviderExtDTO);
        GiftProviderAdminVM giftProviderAdminVM = giftProviderAdminService.updateByAdmin(giftProviderExtDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftProviderAdminVM.getId().toString()))
            .body(giftProviderAdminVM);
    }

    /**
     * {@code GET  /gift-providers} : get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftProviders in body.
     */
    @GetMapping("/gift-providers")
    public ResponseEntity<List<GiftProviderAdminVM>> getAllGiftProviders(@RequestParam(value = "keyword", required = false) String keyword,
                                                                         @RequestParam(value = "status", required = false) ActionStatus status,
                                                                         @PageableDefault(size = 20, page = 0) @SortDefault.SortDefaults({
                                                                             @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC)}) Pageable pageable) {
        log.debug("REST request to get a page of GiftProviders");
        Page<GiftProviderAdminVM> page = giftProviderAdminService.findAllWithFilterByAdmin(keyword, status, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-providers/details/:id} : get the "id" giftProvider.
     *
     * @param id the id of the giftProviderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftProviderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gift-providers/details/{id}")
    public ResponseEntity<GiftProviderAdminVM> getGiftProvider(@PathVariable Long id) {
        log.debug("REST request to get GiftProvider : {}", id);
        GiftProviderAdminVM giftProviderAdminVM = giftProviderAdminService.findOneByAdmin(id);
        return ResponseEntity.ok().body(giftProviderAdminVM);
    }

    /**
     * {@code PUT  /gift-providers/activate/:id} : activate the "id" giftProvider.
     *
     * @param id the id of the giftProviderDTO to activate.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PutMapping("/gift-providers/activate/{id}")
    public ResponseEntity<Void> activateGiftProvider(@PathVariable Long id) {
        log.debug("REST request to activate GiftProvider : {}", id);
        giftProviderAdminService.activateByAdmin(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code DELETE  /gift-providers/deactivate/:id} : deactivate the "id" giftProvider.
     *
     * @param id the id of the giftProviderDTO to deactivate.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PutMapping("/gift-providers/deactivate/{id}")
    public ResponseEntity<Void> deactivateGiftProvider(@PathVariable Long id) {
        log.debug("REST request to deactivate GiftProvider : {}", id);
        giftProviderAdminService.deactivateByAdmin(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
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
        giftProviderAdminService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
