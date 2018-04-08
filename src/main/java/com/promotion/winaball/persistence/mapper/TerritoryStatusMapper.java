package com.promotion.winaball.persistence.mapper;

import com.promotion.winaball.persistence.entity.TerritoryStatusEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TerritoryStatusMapper {

    @Select("select * from territory_status where territory_id = #{territoryId}")
    TerritoryStatusEntity getStatus(@Param("territoryId") long territoryId);

    @Update("update territory_status set"
            + " redeems_after_win=#{redeemsAfterWin}, wins_today=#{winsToday}, wins_total=#{winsTotal}")
    void updateStatus(TerritoryStatusEntity territoryStatusEntity);
}
