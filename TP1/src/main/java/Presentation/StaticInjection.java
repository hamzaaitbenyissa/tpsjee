package Presentation;

import dao.DaoImp;
import metier.MetierImpl;

public class StaticInjection {

    public static void main(String[] args) throws Exception {
        DaoImp dao = new DaoImp();
        MetierImpl metier = new MetierImpl();
        metier.setIdao(dao);
        System.out.println(metier.calcul());
    }
}
