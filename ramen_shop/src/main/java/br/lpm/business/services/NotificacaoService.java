package br.lpm.business.services;

import br.lpm.business.utils.Observer;
import br.lpm.business.utils.Subject;

public class NotificacaoService {
  public void notificarCliente(Subject subject, Observer cliente) {
    subject.registerObserver(cliente);
    subject.notifyObservers();
  }
}
