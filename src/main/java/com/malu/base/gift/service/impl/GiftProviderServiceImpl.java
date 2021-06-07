package com.malu.base.gift.service.impl;

import com.malu.base.gift.domain.GiftProvider;
import com.malu.base.gift.repository.GiftProviderRepository;
import com.malu.base.gift.service.GiftProviderService;
import com.malu.base.gift.service.dto.GiftProviderDTO;
import com.malu.base.gift.service.mapper.GiftProviderMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GiftProvider}.
 */
@Service
@Transactional
@Primary
public class GiftProviderServiceImpl implements GiftProviderService {

    private final Logger log = LoggerFactory.getLogger(GiftProviderServiceImpl.class);

    private final GiftProviderRepository giftProviderRepository;

    private final GiftProviderMapper giftProviderMapper;

    public GiftProviderServiceImpl(GiftProviderRepository giftProviderRepository, GiftProviderMapper giftProviderMapper) {
        this.giftProviderRepository = giftProviderRepository;
        this.giftProviderMapper = giftProviderMapper;
    }

    @Override
    public GiftProviderDTO save(GiftProviderDTO giftProviderDTO) {
        log.debug("Request to save GiftProvider : {}", giftProviderDTO);
        GiftProvider giftProvider = giftProviderMapper.toEntity(giftProviderDTO);
        giftProvider = giftProviderRepository.save(giftProvider);
        return giftProviderMapper.toDto(giftProvider);
    }

    @Override
    public Optional<GiftProviderDTO> partialUpdate(GiftProviderDTO giftProviderDTO) {
        log.debug("Request to partially update GiftProvider : {}", giftProviderDTO);

        return giftProviderRepository
            .findById(giftProviderDTO.getId())
            .map(
                existingGiftProvider -> {
                    giftProviderMapper.partialUpdate(existingGiftProvider, giftProviderDTO);
                    return existingGiftProvider;
                }
            )
            .map(giftProviderRepository::save)
            .map(giftProviderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GiftProviderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GiftProviders");
        return giftProviderRepository.findAll(pageable).map(giftProviderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GiftProviderDTO> findOne(Long id) {
        log.debug("Request to get GiftProvider : {}", id);
        return giftProviderRepository.findById(id).map(giftProviderMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GiftProvider : {}", id);
        giftProviderRepository.deleteById(id);
    }
}
