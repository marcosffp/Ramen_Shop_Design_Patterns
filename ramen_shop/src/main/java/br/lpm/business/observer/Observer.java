package br.lpm.business.observer;

public abstract class Observer {
  protected Subject subject;

  public Observer(Subject subject) {
    this.subject = subject;
    this.subject.registrarObservador(this);
  }

  public abstract void atualizar();
}
