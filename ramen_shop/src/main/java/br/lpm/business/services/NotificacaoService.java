package br.lpm.business.services;

import br.lpm.business.observer.Observer;
import br.lpm.business.observer.Subject;

public class NotificacaoService {
  public void notificarCliente(Subject subject, Observer cliente) {
    subject.registrarObservador(cliente);
    subject.notificarObservadores();
  }
}
