package com.w08e.data.pub.config;


import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 路由器功能配置
 *
 * @author yxz
 * @date 2023/02/27
 */
//@Slf4j
//@Configuration
//@AllArgsConstructor
public class RouterFunctionConfiguration {

//    /**
//     * 这里为支持的请求头，如果有自定义的header字段请自己添加
//     */
//    private static final String ALLOWED_HEADERS = "*";
//    private static final String ALLOWED_METHODS = "GET,POST,PUT,DELETE,OPTIONS,HEAD";
//    private static final String ALLOWED_ORIGIN = "*";
//    private static final String ALLOWED_EXPOSE = "*";
//    private static final String MAX_AGE = "18000L";
//
//    /**
//     * 跨域配置
//     */
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            //if (CorsUtils.isCorsRequest(request)) {
//            ServerHttpResponse response = ctx.getResponse();
//            HttpHeaders headers = response.getHeaders();
//            headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
//            headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
//            headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
//            headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
//            headers.add("Access-Control-Max-Age", MAX_AGE);
//            headers.add("Access-Control-Allow-Credentials", "true");
//            if (request.getMethod() == HttpMethod.OPTIONS) {
//                response.setStatusCode(HttpStatus.OK);
//                return Mono.empty();
//            }
//            //}
//            return chain.filter(ctx);
//        };
//    }
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
//        corsConfiguration.addAllowedHeader("*"); // 允许任何头
//        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
//        return new CorsFilter(source);
//    }




    @Bean
    public CorsFilter corsFilter() {
        //1. 添加 CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        //config.addAllowedOriginPattern("*");
        //config.addAllowedOrigin("*");
        //是否发送 Cookie
        config.setAllowCredentials(true);
        //放行哪些请求方式
        config.addAllowedMethod("*");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //暴露哪些头部信息
        config.addExposedHeader("*");
        //2. 添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",config);
        //3. 返回新的CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }
}

//@Configuration
//public class CorsConfig {
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
//        corsConfiguration.addAllowedHeader("*"); // 允许任何头
//        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
//        return new CorsFilter(source);
//    }
//}
