package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier {

    @Autowired
    private IDao idao;


//    public MetierImpl(IDao idao) {
//        this.idao = idao;
//    }

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
