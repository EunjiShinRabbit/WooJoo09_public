package com.WooJoo09.repository;

import com.WooJoo09.constant.IsActive;
import com.WooJoo09.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    Banner findByBannerNum(Long bannerNum);

    List<Banner> findByIsActive(IsActive isActive);

}
