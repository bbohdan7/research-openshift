package ua.gov.bank.services.rest;

import ua.gov.bank.services.filters.CorsFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    private final Set<Class<?>> classes;

    public ApplicationConfig() {
        HashSet<Class<?>> conf = new HashSet<>();

        conf.add(UsersAPI.class);
        conf.add(AccountsAPI.class);
        conf.add(PaymentsAPI.class);
        conf.add(CorsFilter.class);

        classes = Collections.unmodifiableSet(conf);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}