package dao;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImp implements IDao {

    @Override
    public double getData() {

        System.out.println("This is the version 1 of our app");
        return 1;
    }

}
