package com.promotion.winaball.persistence.mapper;

import com.promotion.winaball.persistence.entity.TerritoryEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TerritoryMapper {
    @Select("select * from territory where name = #{name}")
    TerritoryEntity getTerritory(@Param("name") String name);
}
