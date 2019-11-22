package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsFilterConfig {

    //拦截器中存在跨域有问题，还要再设置 response.setHeader("Access-Control-Allow-Origin", "*");
//    //跨域配置
//    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            //重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry registry) {
                //添加映射路径
                registry.addMapping("/**")
                        //放行哪些原始域
                        .allowedOrigins("*")
                        //是否发送Cookie信息
                        .allowCredentials(true)
                        //放行哪些原始域(请求方式)
                        .allowedMethods("GET", "POST", "PUT", "DELETE"," PATCH")
                        //放行哪些原始域(头部信息)
                        .allowedHeaders("*")
                        .maxAge(3600)
                        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                        .exposedHeaders("Header1", "token","REDIRECT");
            }
        };
    }




   // 解决原理：一个http请求，先走filter，到达servlet后才进行拦截器的处理，所以我们可以把cors放在filter里，就可以优先于权限拦截器执行。
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）,不然前端获取不到头部信息
        config.addExposedHeader("token");
        config.addExposedHeader("RedirectUrl");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }



}
