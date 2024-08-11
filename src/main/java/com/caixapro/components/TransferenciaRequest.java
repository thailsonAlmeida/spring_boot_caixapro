package com.caixapro.components;

public class TransferenciaRequest {
	
	private Long contaDestinoId;
    private double valor;
	
    public TransferenciaRequest(Long contaDestinoId, double valor) {
		super();
		this.contaDestinoId = contaDestinoId;
		this.valor = valor;
	}
    
    public TransferenciaRequest() {}

	public Long getContaDestinoId() {
		return contaDestinoId;
	}

	public void setContaDestinoId(Long contaDestinoId) {
		this.contaDestinoId = contaDestinoId;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
    
}
