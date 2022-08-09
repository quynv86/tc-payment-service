package vn.nodo.tcpaymentpaymentservice.i18n;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class PreferKhmerAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver {

    public PreferKhmerAcceptHeaderLocaleResolver() {
        setDefaultLocale(Locale.forLanguageTag("kh"));
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("Accept-Language");
        if (acceptLanguage == null || !acceptLanguage.contains("kh")) {
            return super.resolveLocale(request);
        } else {
            return Locale.forLanguageTag("kh");
        }
    }
}
