package com.WooJoo09.repository;

import com.WooJoo09.constant.ReceiveAd;
import com.WooJoo09.dto.MemberDTO;
import com.WooJoo09.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberNum(Long MemberNum);
    Optional<Member> findById(String id);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByPhone(String phone);
    Member findByIdAndPwd(String id, String pwd);
    //    List<Member> findByRealNameAndEmail(String realName, String email);
    Optional<Member> findByRealNameAndEmail(String realName, String email);
    Optional<Member> findByIdAndEmail(String id, String email);
    List<Member> findByEmail(String email);
    Optional<Member> findByMemberNumAndId(Long memberNum, String id);
    List<Member> findByReceiveAd(ReceiveAd receiveAd);

    @Query(
            value = "select grade from mem_grade mg, member m \n" +
                    "where ((select count(*) from good g, trade t where good_trade_num = trade_num and host = member_num) -\n" +
                    "(select count(*) from dislike d, trade t where dis_trade_num = trade_num and host = member_num))\n" +
                    "between low_good and high_good and member_num = :memberNum" ,
            nativeQuery = true
    )
    Map<String,String> memberGrade (@Param("memberNum") Long memberNum);

    @Query(
            value = "select member_num memberNum, id, is_active isActive, grade, pf_img pfImg, receive_ad receiveAd, nickname, " +
                    "real_name realName, email, phone, birth_date birthDate, reg_date regDate, introduce, " +
                    "(select count(*) from trade t where host = member_num and done_trade = 'DONE') countTrade, " +
                    "(select count(*) from partner p where part_mem_num = member_num and accept_trade = 'ACCEPT') countPartner, " +
                    "(select count(*) from complain c where c.complain_trade in (select trade_num from trade where host = member_num)) countComplain " +
                    "from member m, mem_grade mg where ((select count(*) from good g, trade t where good_trade_num = trade_num and host = member_num) - " +
                    "(select count(*) from dislike d, trade t where dis_trade_num = trade_num and host = member_num)) " +
                    "between low_good and high_good and member_num != 1 order by member_num",
            nativeQuery = true
    )
    List<Map<?,?>> adminMemberSelect();

    @Query(
            value = "select member_num memberNum, id, is_active isActive, grade, pf_img pfImg, receive_ad receiveAd, nickname, " +
                    "real_name realName, email, phone, birth_date birthDate, reg_date regDate, introduce, " +
                    "(select count(*) from trade t where host = member_num and done_trade = 'DONE') countTrade, " +
                    "(select count(*) from partner p where part_mem_num = member_num and accept_trade = 'ACCEPT') countPartner, " +
                    "(select count(*) from complain c where c.complain_trade in (select trade_num from trade where host = member_num)) countComplain " +
                    "from member m, mem_grade mg where ((select count(*) from good g, trade t where good_trade_num = trade_num and host = member_num) - " +
                    "(select count(*) from dislike d, trade t where dis_trade_num = trade_num and host = member_num)) " +
                    "between low_good and high_good and member_num != 1 and " +
                    "(id like :target or nickname like :target or grade like :target or real_name like :target) " +
                    "order by member_num",
            nativeQuery = true
    )
    List<Map<?,?>> adminMemberSearch(@Param("target") String target);


}
