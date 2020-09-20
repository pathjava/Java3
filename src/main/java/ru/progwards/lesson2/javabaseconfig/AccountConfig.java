package ru.progwards.lesson2.javabaseconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.progwards.lesson2.javabaseconfig.account.IAccount;
import ru.progwards.lesson2.javabaseconfig.service.AccountServiceImpl;
import ru.progwards.lesson2.javabaseconfig.store.StoreImpl;

@Configuration
@ComponentScan(basePackages = "ru.progwards.lesson2.javabaseconfig")
public class AccountConfig {

    @Bean
    public StoreImpl<IAccount> getStoreImpl() {
        return new StoreImpl<>();
    }

    @Bean
    public AccountServiceImpl getAccountServiceImpl() {
        return new AccountServiceImpl();
    }

    @Bean
    public AccountClient getAccountClient() {
        return new AccountClient();
    }
}
