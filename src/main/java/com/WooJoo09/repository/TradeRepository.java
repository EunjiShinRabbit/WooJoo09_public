package com.WooJoo09.repository;

import com.WooJoo09.constant.DoneTrade;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TradeRepository  extends JpaRepository<Trade, Long> {
    @Query(
             value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                     "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                     "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                     "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                     "(select count(*) from star s where s.trade_num = t.trade_num) countStar " +
                     "from category c, trade t where t.category = c.category_num and product like :target and done_trade != 'DELETE' " +
                     "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                     "order by countStar desc, tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeByProduct(@Param("target") String target, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar," +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar " +
                    "from category c, trade t where t.category = c.category_num and product like :target and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "order by countStar desc, tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeByProductLogin(@Param("target") String target, @Param("memberNum") int memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOption(@Param("option") String option, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionLogin(@Param("option") String option, @Param("memberNum") int memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and city = :city " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionCity(@Param("option") String option, @Param("city") String city, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and city = :city " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionCityLogin(@Param("option") String option, @Param("city") String city, @Param("memberNum") int memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and town = :town " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionTown(@Param("option") String option, @Param("town") String town, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and town = :town " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionTownLogin(@Param("option") String option, @Param("town") String town, @Param("memberNum") int memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and category_name = :category " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionCategory(@Param("category") String category, @Param("option") String option, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and category_name = :category " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionLoginCategory(@Param("category") String category, @Param("option") String option, @Param("memberNum") int memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and city = :city and category_name = :category " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionCityCategory(@Param("category") String category, @Param("option") String option, @Param("city") String city, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and city = :city and category_name = :category " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionCityLoginCategory(@Param("category") String category, @Param("option") String option, @Param("city") String city, @Param("memberNum") int memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and town = :town and category_name = :category " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionTownCategory(@Param("category") String category, @Param("option") String option, @Param("town") String town, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, city, town, due_date dueDate, " +
                    "trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, done_trade doneTrade, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) countStar, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar " +
                    "from category c, trade t where t.category = c.category_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and town = :town and category_name = :category " +
                    "order by case when :option = 'recommend' then countStar end desc, " +
                    "case when :option = 'recent' then writeDate end desc, " +
                    "case when :option = 'dateLimit' then dueDate end asc, " +
                    "case when :option = 'lowPrice' then price end asc, " +
                    "case when :option = 'highPrice' then price end desc, " +
                    "tradeNum desc",
            countQuery = "select count(*) from trade where host not in " +
                    "(select member_num from member where is_active = 'INACTIVE') and done_trade != 'DELETE'",
            nativeQuery = true
    )
    Page<Map<?,?>> findTradeOptionTownLoginCategory(@Param("category") String category, @Param("option") String option, @Param("town") String town, @Param("memberNum") int memberNum, Pageable pageable);


    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, " +
                    "city, town, due_date dueDate, trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, " +
                    "done_trade doneTrade, trade_place tradePlace, product_detail productDetail, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) as countStar " +
                    "from category c, trade t where t.category = c.category_num " +
                    "and trade_num = :tradeNum",
            nativeQuery = true
    )
    Map<?,?> tradeDetailSelect(@Param("tradeNum") int tradeNum);
    // "and done_trade != 'DELETE' "
    //  "and host not in (select member_num from member where is_active = 'INACTIVE') " +
    //  를 주석처리함 -> 어드민에서 삭제한 게시글, 비활성 멤버 게시글 볼 수 있도록

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, price, " +
                    "city, town, due_date dueDate, trade_method tradeMethod, write_date writeDate, limit_partner limitPartner, " +
                    "done_trade doneTrade, trade_place tradePlace, product_detail productDetail, " +
                    "(select count(*) from partner p where p.trade_num = t.trade_num and accept_trade = 'ACCEPT') acceptPartner, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num) as countStar, " +
                    "(select count(*) from star s where s.trade_num = t.trade_num and s.member_num = :memberNum) as myStar," +
                    "case host when :memberNum then 'Y' else 'N' end as isMyWRite " +
                    "from category c, trade t where t.category = c.category_num " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num = :tradeNum",
            nativeQuery = true
    )
    Map<?,?> tradeDetailSelectLogin(@Param("tradeNum") int tradeNum, @Param("memberNum") int memberNum);

    @Query(
            value = "select member_num memberNum, grade, introduce, pf_img pfImg, nickname, is_active isActive, " +
                    "(select count(*) from trade t where host = member_num and done_trade = 'DONE') countDoneTrade, " +
                    "(select count(*) from partner p where part_mem_num = member_num and accept_trade = 'ACCEPT') countPartTrade " +
                    "from mem_grade mg, member m " +
                    "where member_num = (select host from trade where trade_num = :tradeNum) and " +
                    "((select count(*) from good g, trade t where good_trade_num = trade_num and host = member_num) - " +
                    "(select count(*) from dislike d, trade t where dis_trade_num = trade_num and host = member_num)) " +
                    "between low_good and high_good",
            nativeQuery = true
    )
    Map<?,?> tradeDetailMemberSelect(@Param("tradeNum") int tradeNum);

    @Query(
            value = "select img_url imgUrl from product_img pi where trade_num = :tradeNum order by is_represent desc",
            nativeQuery = true
    )
    List<Map<?,?>> tradeDetailImgSelect(@Param("tradeNum") int tradeNum);

    Trade findByTradeNum(Long tradeNum);
    Long countByHost(Member host);
    Optional<Trade> findByTradeNumAndDoneTrade(Long tradeNum, DoneTrade doneTrade);

    List<Trade> findByHostOrderByWriteDate(Member host);

    @Query(
            value = "select trade_num tradeNum, product, price, due_date dueDate, done_trade doneTrade, nickname, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg " +
                    "from category c, trade t, member m where t.category = c.category_num " +
                    "and host = m.member_num " +
                    "and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and host = :memberNum " +
                    "order by write_date desc",
            countQuery = "select count(*) from trade t, member m where host = m.member_num and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and host = :memberNum",
            nativeQuery = true
    )
    Page<Map<?,?>> hostTradeSelect(@Param("memberNum") Long memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, product, price, due_date dueDate, done_trade doneTrade, nickname, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg " +
                    "from category c, trade t, member m where t.category = c.category_num " +
                    "and host = m.member_num " +
                    "and done_trade = 'ONGOING' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from partner where part_mem_num = :memberNum and accept_trade = 'REJECT') " +
                    "order by write_date desc",
            countQuery = "select count(*) " +
                    "from trade t, member m where " +
                    "host = m.member_num " +
                    "and done_trade = 'ONGOING' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from partner where part_mem_num = :memberNum and accept_trade = 'REJECT')",
            nativeQuery = true
    )
    Page<Map<?,?>> partnerTradeSelectReject(@Param("memberNum") Long memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, product, price, due_date dueDate, done_trade doneTrade, nickname," +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg " +
                    "from category c, trade t, member m where t.category = c.category_num " +
                    "and host = m.member_num " +
                    "and done_trade = 'ONGOING' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from partner where part_mem_num = :memberNum and accept_trade = 'ACCEPT') " +
                    "order by write_date desc",
            countQuery = "select count(*) " +
                    "from trade t, member m where " +
                    "host = m.member_num " +
                    "and done_trade = 'ONGOING' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from partner where part_mem_num = :memberNum and accept_trade = 'ACCEPT')",
            nativeQuery = true
    )
    Page<Map<?,?>> partnerTradeSelectOngoing(@Param("memberNum") Long memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, product, price, due_date dueDate, done_trade doneTrade, nickname, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg, " +
                    "(select count(*) from good where good_trade_num = trade_num and good_mem_num = :memberNum) mygood, " +
                    "(select count(*) from dislike where dis_trade_num = trade_num and dis_mem_num = :memberNum) mydislike " +
                    "from category c, trade t, member m where t.category = c.category_num " +
                    "and host = m.member_num " +
                    "and done_trade = 'DONE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from partner where part_mem_num = :memberNum and accept_trade = 'ACCEPT') " +
                    "order by write_date desc",
            countQuery = "select count(*) " +
                    "from trade t, member m where host = m.member_num " +
                    "and done_trade = 'DONE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from partner where part_mem_num = :memberNum and accept_trade = 'ACCEPT')",
            nativeQuery = true
    )
    Page<Map<?,?>> partnerTradeSelectDone(@Param("memberNum") Long memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, product, price, due_date dueDate, done_trade doneTrade, nickname, " +
                    "(select img_url from product_img pi where pi.trade_num = t.trade_num and is_represent = 'REPRESENT') representImg " +
                    "from category c, trade t, member m where t.category = c.category_num " +
                    "and host = m.member_num " +
                    "and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from star where member_num = :memberNum) " +
                    "order by write_date desc",
            countQuery = "select count(*) " +
                    "from trade t, member m where host = m.member_num " +
                    "and done_trade != 'DELETE' " +
                    "and host not in (select member_num from member where is_active = 'INACTIVE') " +
                    "and trade_num in (select trade_num from star where member_num = :memberNum)",
            nativeQuery = true
    )
    Page<Map<?,?>> starTradeSelect(@Param("memberNum") Long memberNum, Pageable pageable);

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, done_trade doneTrade, nickname, city, town, write_date writeDate, " +
                    "(select count(*) from complain where complain_trade = trade_num) countComplain " +
                    "from trade t, member m, category c where c.category_num = t.category and " +
                    "m.member_num = t.host order by tradeNum",
            nativeQuery = true
    )
    List<Map<?,?>> adminWriteSelect();

    @Query(
            value = "select trade_num tradeNum, category_name categoryName, product, done_trade doneTrade, nickname, city, town, " +
                    "(select count(*) from complain where complain_trade = trade_num) countComplain " +
                    "from trade t, member m, category c where c.category_num = t.category and m.member_num = t.host " +
                    "and (product like :target or nickname like :target) " +
                    "order by tradeNum",
            nativeQuery = true
    )
    List<Map<?,?>> adminWriteSearch(@Param("target") String target);
}
