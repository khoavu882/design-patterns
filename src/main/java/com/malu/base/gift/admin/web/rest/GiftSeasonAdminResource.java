package com.malu.base.gift.admin.web.rest;

import com.malu.base.gift.admin.service.GiftSeasonAdminService;
import com.malu.base.gift.admin.service.dto.GiftSeasonAdminDTO;
import com.malu.base.gift.admin.web.rest.vm.GiftSeasonAdminVM;
import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.GiftSeasonExtService;
import com.malu.base.gift.service.dto.GiftSeasonDTO;
import com.malu.base.gift.service.dto.GiftSeasonExtDTO;
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
 * REST controller for managing {@link GiftSeason}.
 */
@RestController
@RequestMapping("/api/v1/admin")
public class GiftSeasonAdminResource {

    private final Logger log = LoggerFactory.getLogger(GiftSeasonAdminResource.class);

    private static final String ENTITY_NAME = "giftGiftSeason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftSeasonAdminService giftSeasonAdminService;

    public GiftSeasonAdminResource(@Qualifier(ApplicationConstant.ADMIN) GiftSeasonAdminService giftSeasonAdminService) {
        this.giftSeasonAdminService = giftSeasonAdminService;
    }

    /**
     * {@code POST  /gift-seasons} : Create a new giftSeason.
     *
     * @param giftSeasonAdminDTO the giftSeasonAdminDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftSeasonAdminDTO, or with status {@code 400 (Bad Request)} if the giftSeason has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gift-seasons")
    public ResponseEntity<GiftSeasonAdminVM> createGiftSeason(@Valid @RequestBody GiftSeasonAdminDTO giftSeasonAdminDTO) throws URISyntaxException {
        log.debug("REST request to save GiftSeason : {}", giftSeasonAdminDTO);
        GiftSeasonAdminVM result = giftSeasonAdminService.createByAdmin(giftSeasonAdminDTO);
        return ResponseEntity
            .created(new URI("/api/gift-seasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gift-seasons} : Updates an existing giftSeason.
     *
     * @param giftSeasonAdminDTO the giftSeasonAdminDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated giftSeasonAdminDTO,
     * or with status {@code 400 (Bad Request)} if the giftSeasonAdminDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the giftSeasonAdminDTO couldn't be updated.
     */
    @PutMapping("/gift-seasons")
    public ResponseEntity<GiftSeasonAdminVM> updateGiftSeason(
        @Valid @RequestBody GiftSeasonAdminDTO giftSeasonAdminDTO
    ) {
        log.debug("REST request to update GiftSeason : {}", giftSeasonAdminDTO);
        GiftSeasonAdminVM result = giftSeasonAdminService.updateByAdmin(giftSeasonAdminDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftSeasonAdminDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  } : get all the giftSeasons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftSeasons in body.
     */
    @GetMapping("/gift-seasons")
    public ResponseEntity<List<GiftSeasonAdminVM>> getAllGiftSeasons(@RequestParam(value = "keyword", required = false) String keyword,
                                                                     @PageableDefault(size = 20, page = 0) @SortDefault.SortDefaults({
                                                                         @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC)}) Pageable pageable) {
        log.debug("REST request to get a page of GiftSeasons");
        Page<GiftSeasonAdminVM> page = giftSeasonAdminService.findAllWithFilterByAdmin(keyword, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gift-seasons/:id} : get the "id" giftSeason.
     *
     * @param id the id of the giftSeasonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftSeasonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gift-seasons/details/{id}")
    public ResponseEntity<GiftSeasonAdminVM> getGiftSeason(@PathVariable Long id) {
        log.debug("REST request to get GiftSeason : {}", id);
        GiftSeasonAdminVM giftSeasonAdminVM = giftSeasonAdminService.findOneByAdmin(id);
        return ResponseEntity.ok().body(giftSeasonAdminVM);
    }
}
