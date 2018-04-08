package com.promotion.winaball.persistence.mapper;

import com.promotion.winaball.persistence.entity.RedemptionHistoryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RedemptionHistoryMapper {
    @Select("select exists(select 1 from redemption_history where coupon_code = #{couponCode})")
    boolean isCodeRedeemed(@Param("couponCode") String couponCode);

    @Insert("insert into redemption_history (customer_id, coupon_code, territory, is_winner)"
            + " values (#{customerId}, #{couponCode}, #{territory}, #{isWinner})")
    void insertRedeemedCode(RedemptionHistoryEntity redemptionHistoryEntity);
}
