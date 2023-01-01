package com.WooJoo09.service;

import com.WooJoo09.constant.IsActive;
import com.WooJoo09.entity.Banner;
import com.WooJoo09.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;

    public Map<String, Object> bannerSelect(){
        Map<String,Object> map = new HashMap<>();
        if(bannerRepository.findAll().isEmpty()){
            map.put("bannerSelect", "notData");
        }else{
            List<Banner> bannerList = bannerRepository.findByIsActive(IsActive.ACTIVE);
            map.put("bannerSelect", "OK");
            map.put("banner", bannerList);
        }
        return map;
    }

    public Map<String, Object> adminBannerSelect(){
        Map<String,Object> map = new HashMap<>();
        if(bannerRepository.findAll().isEmpty()){
            map.put("bannerSelect", "notData");
        }else{
            List<Banner> bannerList = bannerRepository.findAll();
            map.put("bannerSelect", "OK");
            map.put("banner", bannerList);
        }
        return map;
    }
    public Map<String, String> bannerInsert(String bannerName, String imgUrl, String directUrl){
        Map<String,String> map = new HashMap<>();
        Banner banner = new Banner();
        banner.setBannerName(bannerName);
        banner.setImgUrl(imgUrl);
        banner.setDirectUrl(directUrl);
        banner.setIsActive(IsActive.ACTIVE);
        Banner savedBanner = bannerRepository.save(banner);
        log.info(savedBanner.toString());
        map.put("bannerInsert", "OK");
        return map;
    }
    public Map<String, String> bannerUpdate(Long bannerNum, String bannerName, String imgUrl, String directUrl, String isActive){
        Map<String,String> map = new HashMap<>();
        Banner banner = bannerRepository.findByBannerNum(bannerNum);
        banner.setBannerName(bannerName);
        banner.setImgUrl(imgUrl);
        banner.setDirectUrl(directUrl);
        if(isActive.equals("active")) banner.setIsActive(IsActive.ACTIVE);
        else banner.setIsActive(IsActive.INACTIVE);
        Banner savedBanner = bannerRepository.save(banner);
        log.info(savedBanner.toString());
        map.put("bannerUpdate", "OK");
        return map;
    }
    public Map<String, String> bannerDelete(List<Integer> bannerNums){
        // 삭제가 아닌 비활성화 상태로 만들기
        Map<String,String> map = new HashMap<>();
        for(Integer e : bannerNums){
            Long bannerNum = Long.valueOf(e);
            Banner banner = bannerRepository.findByBannerNum(bannerNum);
            banner.setIsActive(IsActive.INACTIVE);
            Banner savedBanner = bannerRepository.save(banner);
            log.info(savedBanner.toString());
        }
        map.put("bannerDelete", "OK");
        return map;
    }

//    public Map<String, String> bannerDelete(Long bannerNum){
//        // 삭제가 아닌 비활성화 상태로 만들기
//        Map<String,String> map = new HashMap<>();
//        Banner banner = bannerRepository.findByBannerNum(bannerNum);
//        banner.setIsActive(IsActive.INACTIVE);
//        Banner savedBanner = bannerRepository.save(banner);
//        log.info(savedBanner.toString());
//        map.put("bannerDelete", "OK");
//        return map;
//    }
}
