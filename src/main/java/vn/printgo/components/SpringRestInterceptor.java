/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.printgo.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SpringRestInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(SpringRestInterceptor.class);
    @Autowired
    Environment environment;
    
    // called just before the controller 
    @Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler) throws Exception {
        /*
        String x = request.getMethod();
        logger.info(x + " intercepted");
        if("GET".equals(x))
        throw new CustomGenericException("500", "Not Permit Request");
        */
        // Chỉ lấy HandlerMethod để loại bỏ các file tĩnh như css, js, image..

        if( handler instanceof HandlerMethod ) {
            // there are cases where this handler isn't an instance of HandlerMethod, so the cast fails.
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //controllerName = handlerMethod.getBean().getClass().getSimpleName().replace("Controller", "");
            String controllerName  = handlerMethod.getBeanType().getSimpleName().replace("Controller", "");
            String actionName = handlerMethod.getMethod().getName();
            logger.info(request.getHeader("Origin"));
            // setAttribute đẩy data xuống view, ở bất kỳ view nào cũng lấy được.
            request.setAttribute("special", controllerName + actionName);
        }
        return true;
    }
    /*
    // called immediately after the controller 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
            Object handler, ModelAndView modelAndView) throws Exception {
        if( handler instanceof HandlerMethod ) {
            logger.info("postHandle here ..!");
        }
    }
    
    // afterCompletion(…) – called just before sending response to view 
    */
}