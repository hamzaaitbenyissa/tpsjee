# Compte rendu de TP1 : "Inversion de contrôle et Injection des dépendances"

## Sommaire

- [Créer l'interface IDao](#Créer-linterface-IDao)
- [Créer une implémentation de cette interface](#créer-une-implémentation-de-cette-interface)
- [Créer l'interface IMetier](#Créer-linterface-IMetier)
- [Créer une implémentation de cette interface en utilisant le couplage faible](#Créer-une-implémentation-de-cette-interface-en-utilisant-le-couplage-faible)
- [Faire l'injection des dépendances](#faire-linjection-des-dépendances) 
  - [Par instanciation statique](#Par-instanciation-statique)
  - [Par instanciation dynamique](#Par-instanciation-dynamique)
  - [En utilisant le Framework Spring](#En-utilisant-le-Framework-Spring)
    - [Version XML](#Version-XML)
    - [Version annotations](#Version-annotations)

## Créer l'interface IDao

<!-- ![Screenshot](1.jpg) -->

```java
package dao;
public interface IDao {
    double getData();
}
```

## Créer une implémentation de cette interface

```java
package dao;

public class DaoImp implements IDao {

    @Override
    public double getData() {
        System.out.println("This is the version 1 of our app");
        return 1;
    }

}
```

## Créer l'interface IMetier

```java
package metier;

public interface IMetier {
    double calcul();
}
```

## Créer une implémentation de cette interface en utilisant le couplage faible

```java
package metier;
import dao.IDao;

public class MetierImpl implements IMetier {

    private IDao idao;

    @Override
    public double calcul() {
        System.out.println("Metier calcul  execution :  ");
        idao.getData();
        return 10;
    }

    public void setIdao(IDao idao) {
        this.idao = idao;
    }
}
```

## Faire l'injection des dépendances
L'injection des dépendances nous permet de séparer le code metier et le code technique , pour le faire on peut travailler avec les méthodes suivants :  
### Par instanciation statique
```java
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
```
### Par instanciation dynamique

```java
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
```

voici le contenu de config.txt : 

```txt
dao.DaoImp
metier.MetierImpl
```

### En utilisant le Framework Spring

D'abord pour travailler avec le framework spring nous avons installé les dépendances correspondantes en utilisant le package manager maven qui lié à le fichier pom.xml suivant :

```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>ma.enset</groupId>
    <artifactId>maven1</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <!--        la version de java utilise ; exemple 8-->
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.16</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>5.3.16</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.16</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-aop -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>5.3.16</version>
        </dependency>
    </dependencies>

</project>
```
#### Version annotations
```java
package Presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnotation {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier");
        IMetier metier = context.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}

```
#### Version XML

```java
package Presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXML {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        IMetier metier =(IMetier) context.getBean("metier");
        System.out.println(metier.calcul());
    }
}
```

Le contenu de ApplicationContext.xml

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="dao" class="ext.DaoImp2"></bean>
    <bean id="metier" class="metier.MetierImpl">
        <property name="idao" ref="dao"></property>
    </bean>
</beans>
```



## 🔗 Retrouvez moi sur :
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/hamzaaitbenyissa/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/h_aitbenyissa)


