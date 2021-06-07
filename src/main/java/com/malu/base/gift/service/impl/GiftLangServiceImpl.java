package com.malu.base.gift.service.impl;

import com.malu.base.gift.domain.GiftLang;
import com.malu.base.gift.repository.GiftLangRepository;
import com.malu.base.gift.service.GiftLangService;
import com.malu.base.gift.service.dto.GiftLangDTO;
import com.malu.base.gift.service.mapper.GiftLangMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GiftLang}.
 */
@Service
@Transactional
@Primary
public class GiftLangServiceImpl implements GiftLangService {

    private final Logger log = LoggerFactory.getLogger(GiftLangServiceImpl.class);

    private final GiftLangRepository giftLangRepository;

    private final GiftLangMapper giftLangMapper;

    public GiftLangServiceImpl(GiftLangRepository giftLangRepository, GiftLangMapper giftLangMapper) {
        this.giftLangRepository = giftLangRepository;
        this.giftLangMapper = giftLangMapper;
    }

    @Override
    public GiftLangDTO save(GiftLangDTO giftLangDTO) {
        log.debug("Request to save GiftLang : {}", giftLangDTO);
        GiftLang giftLang = giftLangMapper.toEntity(giftLangDTO);
        giftLang = giftLangRepository.save(giftLang);
        return giftLangMapper.toDto(giftLang);
    }

    @Override
    public Optional<GiftLangDTO> partialUpdate(GiftLangDTO giftLangDTO) {
        log.debug("Request to partially update GiftLang : {}", giftLangDTO);

        return giftLangRepository
            .findById(giftLangDTO.getId())
            .map(
                existingGiftLang -> {
                    giftLangMapper.partialUpdate(existingGiftLang, giftLangDTO);
                    return existingGiftLang;
                }
            )
            .map(giftLangRepository::save)
            .map(giftLangMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GiftLangDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GiftLangs");
        return giftLangRepository.findAll(pageable).map(giftLangMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GiftLangDTO> findOne(Long id) {
        log.debug("Request to get GiftLang : {}", id);
        return giftLangRepository.findById(id).map(giftLangMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GiftLang : {}", id);
        giftLangRepository.deleteById(id);
    }
}
