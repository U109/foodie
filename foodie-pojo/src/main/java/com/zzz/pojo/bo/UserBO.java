package com.zzz.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangzhongzhen wrote on 2024/1/30
 * @version 1.0
 * @description:
 */
@Data
@ApiModel(value = "用户对象 BO", description = "从客户端，由用户传入的数据封装在此")
public class UserBO {
    @ApiModelProperty(value = "用户名", name = "username", example = "例如  admin", required = true)
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("确认密码")
    private String confirmPassword;

}
