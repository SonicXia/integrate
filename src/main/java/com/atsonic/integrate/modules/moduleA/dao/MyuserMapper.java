package com.atsonic.integrate.modules.moduleA.dao;

import com.atsonic.integrate.modules.moduleA.entity.Myuser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sonic
 * @since 2019-02-26
 */
public interface MyuserMapper extends BaseMapper<Myuser> {

    @Select("SELECT * FROM myuser WHERE id = #{id}")
    public Myuser getUserById(Integer id);

    public String getUsername();


}
