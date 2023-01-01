package com.WooJoo09.repository;

import com.WooJoo09.entity.Chat;
import com.WooJoo09.entity.Member;
import com.WooJoo09.entity.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(
            value = "select count(*) countUnreadChat from chat " +
                    "where (partner_num in (select partner_num from partner p, trade t " +
                    "where (p.trade_num = t.trade_num and host = :memberNum) or part_mem_num = :memberNum)) " +
                    "and sender != :memberNum and is_read = 'UNREAD'",
            nativeQuery = true
    )
    String chatReadCheck(@Param("memberNum") int memberNum);

    List<Chat> findByPartnerNum(Partner partnerNum);

    @Query(
            value = "select ANY_VALUE(m.nickname) nickname, ANY_VALUE(pi2.img_url) img_url, max(c.chat_time) as chat_time,\n" +
                    "ANY_VALUE(c.chat_content) chat_content, ANY_VALUE(t.category) category_num, \n" +
                    "ANY_VALUE(c.is_read) is_read, ANY_VALUE(p.partner_num) partner_num,ANY_VALUE(p.accept_trade) accept_trade,\n" +
                    "ANY_VALUE(t.done_trade) done_trade,ANY_VALUE(t.product) product, ANY_VALUE(t.price) price, ANY_VALUE(t.host) host, \n" +
                    "ANY_VALUE(t.trade_num) trade_num, ANY_VALUE(p.part_mem_num) part_mem_num, ANY_VALUE(c.sender) sender,\n" +
                    "(select count(case when is_read = 'UNREAD' then 1 end) from chat\n" +
                    "where (partner_num in (select partner_num from partner p, trade t \n" +
                    "where (p.trade_num = t.trade_num and host = :memberNum) or part_mem_num = :memberNum)) \n" +
                    "and sender != :memberNum and partner_num = p.partner_num) as countUnreadChat\n" +
                    "from\n" +
                    "(select * from chat\n" +
                    "where(partner_num, chat_time) in (select partner_num, max(chat_time) as chat_time\n" +
                    "from chat group by partner_num)\n" +
                    "order by chat_time desc) c,\n" +
                    "partner p, product_img pi2, member m, trade t \n" +
                    "where p.partner_num = c.partner_num  \n" +
                    "and case p.part_mem_num when :memberNum then m.member_num = t.host \n" +
                    "else p.part_mem_num = m.member_num end\n" +
                    "and p.trade_num = pi2.trade_num\n" +
                    "and case p.part_mem_num when :memberNum then p.trade_num = t.trade_num\n" +
                    "else (p.trade_num = t.trade_num and t.host = :memberNum) end\n" +
                    "and pi2.is_represent = 'REPRESENT'\n" +
                    "and accept_trade != 'DELETE' \n" +
                    "and done_trade != 'DELETE'\n" +
                    "group by p.partner_num\n" +
                    "union \n" +
                    "select ANY_VALUE(m.nickname) nickname, null img_url, max(c.chat_time) as chat_time,\n" +
                    "ANY_VALUE(c.chat_content) chat_content, ANY_VALUE(t.category) category_num, \n" +
                    "ANY_VALUE(c.is_read) is_read, ANY_VALUE(p.partner_num) partner_num,ANY_VALUE(p.accept_trade) accept_trade,\n" +
                    "ANY_VALUE(t.done_trade) done_trade,ANY_VALUE(t.product) product, ANY_VALUE(t.price) price, ANY_VALUE(t.host) host, \n" +
                    "ANY_VALUE(t.trade_num) trade_num, ANY_VALUE(p.part_mem_num) part_mem_num, ANY_VALUE(c.sender) sender,\n" +
                    "(select count(case when is_read = 'UNREAD' then 1 end) from chat\n" +
                    "where (partner_num in (select partner_num from partner p, trade t \n" +
                    "where (p.trade_num = t.trade_num and host = :memberNum) or part_mem_num = :memberNum)) \n" +
                    "and sender != :memberNum and partner_num = p.partner_num) as countUnreadChat\n" +
                    "from\n" +
                    "(select * from chat\n" +
                    "where(partner_num, chat_time) in (select partner_num, max(chat_time) as chat_time\n" +
                    "from chat group by partner_num)\n" +
                    "order by chat_time desc) c,\n" +
                    "partner p, member m, trade t \n" +
                    "where p.partner_num = c.partner_num  \n" +
                    "and case p.part_mem_num when :memberNum then m.member_num = t.host \n" +
                    "else p.part_mem_num = m.member_num end\n" +
                    "and case p.part_mem_num when :memberNum then p.trade_num = t.trade_num\n" +
                    "else (p.trade_num = t.trade_num and t.host = :memberNum) end\n" +
                    "and accept_trade != 'DELETE' \n" +
                    "and done_trade != 'DELETE' and t.trade_num not in (select trade_num from product_img)\n" +
                    "group by p.partner_num\n" +
                    "order by chat_time desc",
            nativeQuery = true
    )
    List<Map<?,?>> chatList(@Param("memberNum") int memberNum);

    @Query(
            value = "select chat_content, chat_time, sender, msg_type from chat where partner_num = :partner_num " ,
            nativeQuery = true
    )
    List<Map<?,?>> chatContent (@Param("partner_num") int partnerNum);

    List<Chat> findByPartnerNumAndSender(Partner partnerNum, Member memberNum);

    List<Chat> findBySenderNotAndPartnerNum(Member memberNum, Partner partnerNum);

    @Query(
            value = "select p.partner_num partnerNum, product, p.accept_trade acceptTrade, nickname partner, " +
                    "(select count(*) from chat c where c.partner_num = p.partner_num) countChat, " +
                    "(select nickname from member m2 where t.host = m2.member_num) host " +
                    "from partner p, member m, trade t " +
                    "where part_mem_num = m.member_num and p.trade_num = t.trade_num " +
                    "order by partner_num",
            nativeQuery = true
    )
    List<Map<?,?>> adminChatSelect();

    @Query(
            value = "select p.partner_num partnerNum, product, p.accept_trade acceptTrade, nickname partner, " +
                    "(select count(*) from chat c where c.partner_num = p.partner_num) countChat, " +
                    "(select nickname from member m2 where t.host = m2.member_num) host " +
                    "from partner p, member m, trade t " +
                    "where part_mem_num = m.member_num and p.trade_num = t.trade_num " +
                    "and (product like :target or nickname like :target or " +
                    "(select nickname from member m2 where t.host = m2.member_num) like :target) " +
                    "order by partner_num",
            nativeQuery = true
    )
    List<Map<?,?>> adminChatSearch(@Param("target") String target);

}
