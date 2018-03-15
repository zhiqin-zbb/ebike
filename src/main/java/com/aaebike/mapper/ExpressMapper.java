package com.aaebike.mapper;

import org.springframework.stereotype.Repository;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.entity.table.Express;

@Repository
public interface ExpressMapper extends MyMapper<Express> {
    /**
     * 设置该用户的所有地址为非默认
     *
     * @param userId 用户ID
     */
    int cancelDefaultByUserId(Integer userId);

    /**
     * 设置该地址为非默认
     *
     * @param expressId 地址ID
     * @return
     */
    int cancelDefaultByExpressId(Integer expressId);
}
