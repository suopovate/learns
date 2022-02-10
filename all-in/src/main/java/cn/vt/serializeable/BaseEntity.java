package cn.vt.serializeable;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseEntity {

    protected Long id;
    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
