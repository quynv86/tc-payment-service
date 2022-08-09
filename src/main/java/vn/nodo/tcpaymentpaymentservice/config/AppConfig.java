package vn.nodo.tcpaymentpaymentservice.config;


import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.web.servlet.LocaleResolver;
import vn.nodo.tcpaymentpaymentservice.i18n.PreferKhmerAcceptHeaderLocaleResolver;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Locale;

@Configuration
@EnableJpaAuditing
public class AppConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasename("messages");
        String hi = messageSource.getMessage("hello", null, Locale.forLanguageTag("kh"));
        System.out.println("Getting message for test: " + hi);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeContextResolver() {
        PreferKhmerAcceptHeaderLocaleResolver resolver = new PreferKhmerAcceptHeaderLocaleResolver();
        Locale vi = Locale.forLanguageTag("vi");
        resolver.setDefaultLocale(vi);
        resolver.setSupportedLocales(Collections.singletonList(vi));
        return resolver;
    }

    @Bean
    public ValidatorFactory validatorFactory() {
        MessageSourceResourceBundleLocator resourceBundleLocator
                = new MessageSourceResourceBundleLocator(messageSource());
        ResourceBundleMessageInterpolator interpolator = new ResourceBundleMessageInterpolator(resourceBundleLocator);
        return Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(interpolator)
                .buildValidatorFactory();
    }
}
