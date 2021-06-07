package com.malu.base.gift.service.impl;

import com.malu.base.gift.domain.GiftSeason;
import com.malu.base.gift.repository.GiftSeasonRepository;
import com.malu.base.gift.service.GiftSeasonService;
import com.malu.base.gift.service.dto.GiftSeasonDTO;
import com.malu.base.gift.service.mapper.GiftSeasonMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GiftSeason}.
 */
@Service
@Transactional
@Primary
public class GiftSeasonServiceImpl implements GiftSeasonService {

    private final Logger log = LoggerFactory.getLogger(GiftSeasonServiceImpl.class);

    private final GiftSeasonRepository giftSeasonRepository;

    private final GiftSeasonMapper giftSeasonMapper;

    public GiftSeasonServiceImpl(GiftSeasonRepository giftSeasonRepository, GiftSeasonMapper giftSeasonMapper) {
        this.giftSeasonRepository = giftSeasonRepository;
        this.giftSeasonMapper = giftSeasonMapper;
    }

    @Override
    public GiftSeasonDTO save(GiftSeasonDTO giftSeasonDTO) {
        log.debug("Request to save GiftSeason : {}", giftSeasonDTO);
        GiftSeason giftSeason = giftSeasonMapper.toEntity(giftSeasonDTO);
        giftSeason = giftSeasonRepository.save(giftSeason);
        return giftSeasonMapper.toDto(giftSeason);
    }

    @Override
    public Optional<GiftSeasonDTO> partialUpdate(GiftSeasonDTO giftSeasonDTO) {
        log.debug("Request to partially update GiftSeason : {}", giftSeasonDTO);

        return giftSeasonRepository
            .findById(giftSeasonDTO.getId())
            .map(
                existingGiftSeason -> {
                    giftSeasonMapper.partialUpdate(existingGiftSeason, giftSeasonDTO);
                    return existingGiftSeason;
                }
            )
            .map(giftSeasonRepository::save)
            .map(giftSeasonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GiftSeasonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GiftSeasons");
        return giftSeasonRepository.findAll(pageable).map(giftSeasonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GiftSeasonDTO> findOne(Long id) {
        log.debug("Request to get GiftSeason : {}", id);
        return giftSeasonRepository.findById(id).map(giftSeasonMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GiftSeason : {}", id);
        giftSeasonRepository.deleteById(id);
    }
}
