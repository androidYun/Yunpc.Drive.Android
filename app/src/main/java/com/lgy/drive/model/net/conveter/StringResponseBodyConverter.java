package com.lgy.drive.model.net.conveter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by ${lgy} on 2017/11/2409:32
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}
