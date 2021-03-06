package com.project.picktoon.service.impl;

import com.project.picktoon.domain.WebtoonImage;
import com.project.picktoon.repository.WebtoonImageRepository;
import com.project.picktoon.repository.WebtoonRepository;
import com.project.picktoon.service.WebtoonImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WebtoonImageServiceImpl implements WebtoonImageService {
    public final WebtoonImageRepository webtoonImageRepository;

    @Override
    @Transactional
    public WebtoonImage addWebtoonImage(WebtoonImage webtoonImage) {
        return webtoonImageRepository.save(webtoonImage);
    }

    @Override
    @Transactional(readOnly = true)
    public WebtoonImage getWebtoonImage(Long webtoonImageId) {
        return webtoonImageRepository.findById(webtoonImageId).get();
    }
}
