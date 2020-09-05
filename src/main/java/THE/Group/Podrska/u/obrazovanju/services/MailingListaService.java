package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.MailingLista;

import java.util.List;

public interface MailingListaService {
    MailingLista getById(Long mailingListeID);
    void deleteById(Long mailingListeID);
    List<MailingLista> listAll();
    MailingLista save(MailingLista mailingLista);
}
