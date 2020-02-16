package com.majiang.community.mapper;

import com.majiang.community.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modify)" +
            " values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modify})")
    void insert(User user);
}
