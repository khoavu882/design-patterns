package com.malu.base.gift.consumer.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftProviderConsumerService;
import com.malu.base.gift.consumer.web.rest.vm.GiftProviderConsumerVM;
import com.malu.base.gift.domain.enumeration.ActionStatus;
import com.malu.base.gift.service.dto.GiftProviderExtDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
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

/**
 * REST controller for managing {@link com.malu.base.gift.domain.GiftProvider}.
 */
@RestController
@RequestMapping("/api/v1/consumer")
public class GiftProviderConsumerResource {

    private final Logger log = LoggerFactory.getLogger(GiftProviderConsumerResource.class);

    private static final String ENTITY_NAME = "giftGiftProvider";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftProviderConsumerService giftProviderConsumerService;

    public GiftProviderConsumerResource(@Qualifier(ApplicationConstant.CONSUMER) GiftProviderConsumerService giftProviderConsumerService) {
        this.giftProviderConsumerService = giftProviderConsumerService;
    }

    /**
     * {@code POST  /gift-providers} : Create a new giftProvider.
     *
     * @param giftProviderExtDTO the giftProviderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new giftProviderDTO, or with status {@code 400 (Bad Request)} if the giftProvider has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gift-providers")
    public ResponseEntity<GiftProviderConsumerVM> createGiftProvider(@Valid @RequestBody GiftProviderExtDTO giftProviderExtDTO)
        throws URISyntaxException {
        log.debug("REST request to save GiftProvider : {}", giftProviderExtDTO);
        GiftProviderConsumerVM giftProviderConsumerVM = giftProviderConsumerService.createByConsumer(giftProviderExtDTO);
        return ResponseEntity
            .created(new URI("/api/gift-providers/" + giftProviderConsumerVM.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, giftProviderConsumerVM.getId().toString()))
            .body(giftProviderConsumerVM);
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
    public ResponseEntity<GiftProviderConsumerVM> updateGiftProvider(@Valid @RequestBody GiftProviderExtDTO giftProviderExtDTO) {
        log.debug("REST request to update GiftProvider : {}", giftProviderExtDTO);
        GiftProviderConsumerVM giftProviderConsumerVM = giftProviderConsumerService.updateByConsumer(giftProviderExtDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, giftProviderConsumerVM.getId().toString()))
            .body(giftProviderConsumerVM);
    }

    /**
     * {@code GET  /gift-providers} : get all the giftProviders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of giftProviders in body.
     */
    @GetMapping("/gift-providers")
    public ResponseEntity<List<GiftProviderConsumerVM>> getAllGiftProviders(
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "status", required = false) ActionStatus status,
        @PageableDefault(size = 20, page = 0) @SortDefault.SortDefaults(
            { @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC) }
        ) Pageable pageable
    ) {
        log.debug("REST request to get a page of GiftProviders");
        Page<GiftProviderConsumerVM> page = giftProviderConsumerService.findAllWithFilterByConsumer(keyword, status, pageable);
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
    public ResponseEntity<GiftProviderConsumerVM> getGiftProvider(@PathVariable Long id) {
        log.debug("REST request to get GiftProvider : {}", id);
        GiftProviderConsumerVM giftProviderConsumerVM = giftProviderConsumerService.findOneByConsumer(id);
        return ResponseEntity.ok().body(giftProviderConsumerVM);
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
        giftProviderConsumerService.activateByConsumer(id);
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
        giftProviderConsumerService.deactivateByConsumer(id);
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
        giftProviderConsumerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
