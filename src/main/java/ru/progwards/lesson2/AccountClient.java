package ru.progwards.lesson2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.progwards.lesson2.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.exceptions.UnknownAccountException;
import ru.progwards.lesson2.service.AccountService;
import ru.progwards.lesson2.service.AccountServiceImpl;
import ru.progwards.lesson2.store.StoreImpl;

public class AccountClient {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("account-context.xml");
    private static final StoreImpl<?> store = context.getBean("store", StoreImpl.class);
    private static final AccountService service = context.getBean("service", AccountServiceImpl.class);

    public static void main(String[] args) {
//        try {
//            service.withdraw(5, 25);
//        } catch (UnknownAccountException | NotEnoughMoneyException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            service.deposit(5, 35);
//        } catch (NotEnoughMoneyException | UnknownAccountException e) {
//            System.out.println(e.getMessage());
//        }

        try {
            service.transfer(1,2,520);
        } catch (UnknownAccountException | NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(service.balance(1));
            System.out.println(service.balance(2));
            System.out.println(service.balance(5));
        } catch (UnknownAccountException e) {
            System.out.println(e.getMessage());
        }
    }

}
