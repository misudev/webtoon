package com.project.picktoon.repository;

import com.project.picktoon.domain.Keyword;
import com.project.picktoon.domain.Webtoon;
import com.project.picktoon.dto.SearchKeyword;
import com.project.picktoon.util.SeeAge;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class WebtoonRepositoryTest {
    @Autowired
    WebtoonRepository webtoonRepository;
    @Autowired
    PlatformRepository platformRepository;
    @Autowired
    KeywordRepository keywordRepository;

    @Before
    public void initTest(){


    }

    @Test
    public void getAlls(){
        List<Webtoon> webtoons = webtoonRepository.findAll();
        for(Webtoon w : webtoons)
            System.out.println(w);

    }

    @Test
    public void addWebtoon(){
        Webtoon newWebtoon = new Webtoon();
        newWebtoon.setTitle("테스트 웹툰");
        newWebtoon.setDescription("테스트 웹툰 입니다.");
        newWebtoon.setLink("www.naver.com");
        newWebtoon.setPlatform(platformRepository.getOne(1));
        newWebtoon.setSeeAge(SeeAge.SEEAGE_ALL);
        newWebtoon.setState("연재중");

        List<Keyword> keywords = new ArrayList<>();
        keywords.add(keywordRepository.getOne(1L));
        newWebtoon.setKeywords(keywords);
        System.out.println(newWebtoon);
        Webtoon webtoon = webtoonRepository.save(newWebtoon);

        Webtoon checkWebtoon = webtoonRepository.getOne(webtoon.getId());
        assertEquals(checkWebtoon, webtoon);

    }

    @Test
    public void testSaveWebtoon(){
        Webtoon webtoon = new Webtoon();
        webtoon.setId(1L);
        webtoon.setTitle("테스트 웹툰");
        webtoon.setDescription("테스트 웹툰 입니다.");
        webtoon.setLink("www.naver.com");
        webtoon.setPlatform(platformRepository.getOne(1));
        webtoon.setSeeAge(SeeAge.SEEAGE_ALL);
        webtoon.setState("연재중");

        System.out.println(webtoonRepository.getOne(1L));
        webtoonRepository.save(webtoon);
        System.out.println(webtoonRepository.getOne(1L));
    }

    @Test
    public void testGetWebtoonByTitle(){
        Webtoon webtoon = webtoonRepository.getWebtoonByTitle("마음의 소리");
        System.out.println(webtoon);
    }

    @Test
    public void testGetBestWebtoon(){
        List<Webtoon> webtoons = webtoonRepository.getBestWebtoons();
        for(Webtoon w : webtoons)
            System.out.println("best webtoon : "+ w);
    }

    @Test
    public void testSearchWebtoon(){

        SearchKeyword searchKeyword1 = new SearchKeyword();
        SearchKeyword searchKeyword2 = new SearchKeyword();
        searchKeyword1.setKeywordType(1);
        searchKeyword1.setKeywordValue("월");
        searchKeyword2.setKeywordType(2);
        searchKeyword2.setKeywordValue("일상");
        List<SearchKeyword> keywords = new ArrayList<>();
        keywords.add(searchKeyword1);
        keywords.add(searchKeyword2);

       // List<Webtoon> webtoons  = webtoonRepository.getWebtoons(keywords, null);
        List<Webtoon> webtoons  = webtoonRepository.getWebtoons(keywords, "마음의 소리");
        for(Webtoon w : webtoons)
            System.out.println(w);
    }

}