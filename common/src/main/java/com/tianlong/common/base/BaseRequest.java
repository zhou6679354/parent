package com.tianlong.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhouwenheng
 * @version 1.0
 * @date 2020年06月15日 14:41
 */
public class BaseRequest implements Serializable {
    @Setter
    @Getter
    String method;
    @Setter
    @Getter
    String format;
    @Setter
    @Getter
    String app_key;
    @Setter
    @Getter
    String version;
    @Setter
    @Getter
    String sign;
    @Setter
    @Getter
    String sign_method;
    @Setter
    @Getter
    String timestamp;

    public String getUrl() {
        return "http://hadata-service-" + this.version + "/" + this.method;
    }


}
