package br.lpm.business.model.enums;

public enum Status {
  PENDENTE("Pendente"),
  EM_PREPARO("Em preparo"),
  PRONTO("Pronto"),
  RETIRADO("Retirado");

  private final String status;

  Status(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }


}
