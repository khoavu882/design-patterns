package com.malu.base.gift.consumer.web.rest;

import com.malu.base.gift.constant.ApplicationConstant;
import com.malu.base.gift.consumer.service.GiftConsumerService;
import com.malu.base.gift.consumer.web.rest.vm.GiftConsumerVM;
import com.malu.base.gift.consumer.web.rest.vm.GiftOwnerConsumerVM;
import com.malu.base.gift.domain.Gift;
import com.malu.base.gift.service.dto.GiftDTO;
import java.util.List;
import java.util.Optional;
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
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;

/**
 * REST controller for managing {@link Gift}.
 */
@RestController
@RequestMapping("/api/v1/consumer")
public class GiftConsumerResource {

    private final Logger log = LoggerFactory.getLogger(GiftConsumerResource.class);

    private static final String ENTITY_NAME = "giftGift";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftConsumerService giftConsumerService;

    public GiftConsumerResource(@Qualifier(ApplicationConstant.CONSUMER) GiftConsumerService giftConsumerService) {
        this.giftConsumerService = giftConsumerService;
    }

    /**
     * {@code GET  } : get all the gifts.
     *
     * @param keyword the keyword for search.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping("/gifts")
    public ResponseEntity<List<GiftConsumerVM>> getAllGifts(@RequestParam(value = "keyword", required = false) String keyword,
                                                            @PageableDefault(size = 20,page = 0)
                                                            @SortDefault.SortDefaults({
                                                                @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC)
                                                            }) Pageable pageable) {
        log.debug("REST request to get a page of Gifts");
        Page<GiftConsumerVM> page = giftConsumerService.findAllWithFilterByConsumer(keyword, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  } : get all the gifts.
     *
     * @param keyword the keyword for search.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping("/owner/gifts")
    public ResponseEntity<List<GiftOwnerConsumerVM>> getAllGiftsByOwner(@RequestParam(value = "keyword", required = false) String keyword,
                                                                        @PageableDefault(size = 20,page = 0)
                                                            @SortDefault.SortDefaults({
                                                                @SortDefault(sort = "lastModifiedDate", direction = Sort.Direction.DESC)
                                                            }) Pageable pageable) {
        log.debug("REST request to get a page of Gifts");
        Page<GiftOwnerConsumerVM> page = giftConsumerService.findAllWithFilterByOwner(keyword, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gifts/details} : get the "id" gift.
     *
     * @param listHashCode the id of the giftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gifts/details")
    public ResponseEntity<List<GiftConsumerVM>> getGiftsByListHashCode(@RequestBody @Valid List<String> listHashCode) {
        log.debug("REST request to get Gifts by list Hash Code : {}", listHashCode);
        List<GiftConsumerVM> giftConsumerVM = giftConsumerService.findByListHashCodeByConsumer(listHashCode);
        return ResponseEntity.ok().body(giftConsumerVM);
    }

    /**
     * {@code GET  /gifts/details/:hashCode} : get the "id" gift.
     *
     * @param hashCode the id of the giftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gifts/details/{hashCode}")
    public ResponseEntity<GiftConsumerVM> getGift(@PathVariable String hashCode) {
        log.debug("REST request to get Gift : {}", hashCode);
        GiftConsumerVM giftOwnerConsumerVM = giftConsumerService.findOneByHashCodeByConsumer(hashCode);
        return ResponseEntity.ok().body(giftOwnerConsumerVM);
    }

    /**
     * {@code GET  /owner/gifts/details/:hashCode} : get the "id" gift.
     *
     * @param hashCode the id of the giftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the giftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/owner/gifts/details/{hashCode}")
    public ResponseEntity<GiftOwnerConsumerVM> getGiftByOwner(@PathVariable String hashCode) {
        log.debug("REST request to get Gift : {}", hashCode);
        GiftOwnerConsumerVM giftOwnerConsumerVM = giftConsumerService.findOneByHashCodeByOwner(hashCode);
        return ResponseEntity.ok().body(giftOwnerConsumerVM);
    }
}
