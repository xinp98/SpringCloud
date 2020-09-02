package cn.tedu.sp11.fallback;

import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author XinP
 * @Date 2020/9/1 11:34
 */
@Component
public class ItemFallback implements FallbackProvider {
    /**
     * 返回 service id
     * 针对哪个服务,应用当前降级类
     * 如果返回的是*或null 表示对所有服务都执行当前降级类
     * @return
     */
    @Override
    public String getRoute() {
        return "item-service";
    }

    /**
     *
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String json  = JsonResult.err().msg("调用商品服务失败").toString();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(json.getBytes("utf-8"));
                return byteArrayInputStream;
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders h = new HttpHeaders();
                h.setContentType(MediaType.APPLICATION_JSON);
                return h;
            }
        };
    }
}
