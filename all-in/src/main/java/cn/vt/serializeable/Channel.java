package cn.vt.serializeable;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 接入渠道表
 * </p>
 *
 * @author zhaotao
 * @since 2021-07-28
 */
@Data
@Accessors(chain = true)
public class Channel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 接入方app_id
     */
    private String appId;

    /**
     * 接入方密钥
     */
    private String appSecret;

    /**
     * 备注
     */
    private String remark;

}
