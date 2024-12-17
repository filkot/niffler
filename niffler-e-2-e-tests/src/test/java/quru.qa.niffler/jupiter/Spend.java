package quru.qa.niffler.jupiter;

import org.junit.jupiter.api.extension.ExtendWith;
import quru.qa.niffler.model.CurrencyValues;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith(SpendExtension.class)
public @interface Spend {
    String category();

    String description();

    String username();

    CurrencyValues currency();

    double amount();

}
