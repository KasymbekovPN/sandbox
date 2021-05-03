package org.kpn.ch16.util;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest){
        String encoding = httpServletRequest.getCharacterEncoding();
        if (encoding == null){
            encoding = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        pathSegment = UriUtils.encodePathSegment(pathSegment, encoding);

        return pathSegment;
    }
}
