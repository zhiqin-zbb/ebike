package com.aaebike.model;

import com.aaebike.common.constants.ErrorConstants;
import lombok.Data;

@Data
public class ResponseVo {
    private Boolean success;

    private Integer errorCode;

    private String errorMsg;

    private Object data;

    public static ResponseVo valueOf(Boolean success, Object object, ErrorConstants errorConstants) {
        ResponseVo responseVo = new ResponseVo();
        if (success) {
            responseVo.setSuccess(true);
            responseVo.setData(object);
        } else {
            responseVo.setSuccess(false);
            responseVo.setErrorCode(errorConstants.getErrorCode());
            responseVo.setErrorMsg(errorConstants.getErrorMsg());
        }
        return responseVo;
    }
}
