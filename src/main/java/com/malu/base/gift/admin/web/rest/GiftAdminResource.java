package com.malu.base.gift.admin.web.rest;

import com.malu.base.gift.admin.service.GiftAdminService;
import com.malu.base.gift.admin.service.dto.GiftAdminDTO;
import com.malu.base.gift.admin.web.rest.vm.GiftAdminVM;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.GiftExtService;
import com.malu.base.gift.service.dto.GiftDTO;
import com.malu.base.gift.service.dto.GiftExtDTO;
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
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Gift}.
 */
@RestController
@RequestMapping("/api/v1/admin")
public class GiftAdminResource {

    private final Logger log = LoggerFactory.getLogger(GiftAdminResource.class);

    private static final String ENTITY_NAME = "giftGift";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftAdminService giftAdminService;

    public GiftAdminResource(@Qualifier(ApplicationConstant.ADMIN) GiftAdminService giftAdminService) {
        this.giftAdminService = giftAdminService;
    }

    /**
     * {@code POST  /gifts} : Create a new gift.
     *
     * @param giftAdminDTO the GiftExtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftDTO, or with status {@code 400 (Bad Request)} if the gift has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gifts")
    public ResponseEntity<GiftAdminVM> createGift(@Valid @RequestBody GiftAdminDTO giftAdminDTO) throws URISyntaxException {
        log.debug("REST request to save Gift : {}", giftAdminDTO);
        GiftAdminVM giftAdminVM = giftAdminService.createByAdmin(giftAdminDTO);
        return ResponseEntity
            .created(new URI("/api/gifts/" + giftAdminVM.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, giftAdminVM.getId().toString()))
            .body(giftAdminVM);
    }

    /**
     * {@code PUT  /gifts} : Updates an existing gift.
     *
     * @param giftAdminDTO the giftAdminDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftAdminDTO,
     * or with status {@code 400 (Bad Request)} if the giftAdminDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftAdminDTO couldn't be updated.
     */
    @PutMapping("/gifts")
    public ResponseEntity<GiftAdminVM> updateGift(
        @Valid @RequestBody GiftAdminDTO giftAdminDTO
    ) {
        log.debug("REST request to update Gift : {}", giftAdminDTO);
        GiftAdminVM giftAdminVM = giftAdminService.updateByAdmin(giftAdminDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftAdminVM.getId().toString()))
            .body(giftAdminVM);
    }

    /**
     * {@code GET  } : get all the gifts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping("/gifts")
    public ResponseEntity<List<GiftAdminVM>> getAllGifts(@RequestParam(value = "keyword", required = false) String keyword,
                                                         @RequestParam(value = "status", required = false) ActionStatus status,
                                                         @PageableDefault(size = 20,page = 0)
                                                             @SortDefault.SortDefaults({
                                                                 @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC)
                                                             }) Pageable pageable) {
        log.debug("REST request to get a page of Gifts");
        Page<GiftAdminVM> page = giftAdminService.findAllWithFilterByAdmin(keyword, status, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gifts/details/:hashCode} : get the "hashCode" gift.
     *
     * @param hashCode the id of the GiftAdminVM to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the GiftAdminVM, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gifts/details/{hashCode}")
    public ResponseEntity<GiftAdminVM> getGiftByHashCode(@PathVariable String hashCode) {
        log.debug("REST request to get Gift : {}", hashCode);
        GiftAdminVM giftAdminVM = giftAdminService.adminFindOneByHashCode(hashCode);
        return ResponseEntity.ok().body(giftAdminVM);
    }

    /**
     * {@code GET  /gifts/:id} : get the "id" gift.
     *
     * @param id the id of the GiftAdminVM to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the GiftAdminVM, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gifts/{id}")
    public ResponseEntity<GiftAdminVM> getGiftById(@PathVariable Long id) {
        log.debug("REST request to get Gift : {}", id);
        GiftAdminVM giftAdminVM = giftAdminService.adminFindOneById(id);
        return ResponseEntity.ok().body(giftAdminVM);
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
        giftAdminService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
