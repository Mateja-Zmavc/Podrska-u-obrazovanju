package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.MailingLista;
import THE.Group.Podrska.u.obrazovanju.repositories.MailingListaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MailingListaServiceImpl implements MailingListaService {
    private MailingListaRespository mailingListaRespository;
    @Autowired
    private MailingListaService mailingListaService;
    @Autowired
    public MailingListaServiceImpl(MailingListaRespository mailingListaRespository) {
        this.mailingListaRespository = mailingListaRespository;
    }

    public Optional<MailingLista> findById(Long mailingListeID){
        return this.mailingListaRespository.findById(mailingListeID);
    }

    @Override
    public MailingLista getById(Long mailingListeID) {
        return mailingListaRespository.findById(mailingListeID).orElse(null);
    }

    @Override
    public void deleteById(Long mailingListeID) {
        mailingListaRespository.deleteById(mailingListeID);
    }

    @Override
    public List<MailingLista> listAll() {
        List<MailingLista> mailingLista = new ArrayList<>();
        mailingListaRespository.findAll().forEach(mailingLista::add);
        return mailingLista;
    }

    @Override
    public MailingLista save(MailingLista mailingLista) {
        mailingListaRespository.save(mailingLista);
        return mailingLista;
    }

}
