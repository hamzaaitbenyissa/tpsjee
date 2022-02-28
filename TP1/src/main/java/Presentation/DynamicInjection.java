package Presentation;
import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicInjection {

    public static void main(String[] args) throws Exception {

         Scanner scanner = new Scanner(new File("src/main/config.txt"));

         String daoClassname = scanner.nextLine();
         Class cDao = Class.forName(daoClassname);
         IDao dao = (IDao) cDao.newInstance();

         String metierClassname = scanner.nextLine();
         Class cMetier = Class.forName(metierClassname);
         IMetier metier = (IMetier) cMetier.newInstance();

         Method method = cMetier.getMethod("setIdao",IDao.class);
         method.invoke(metier,dao);
         metier.calcul();

    }
}
