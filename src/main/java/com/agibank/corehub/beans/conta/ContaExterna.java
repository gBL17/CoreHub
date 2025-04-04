package com.agibank.corehub.beans.conta;

public class ContaExterna {

    private int idContaExterna;
    private String dadosContaExterna;
    public ContaExterna(int idContaExterna, String dadosContaExterna) {
        this.idContaExterna = idContaExterna;
        this.dadosContaExterna = dadosContaExterna;
    }

    public ContaExterna(){}

    private int agencia;
    private int numeroContaExternac ContaExterna(int agencia, int numer
		      -----------------------------------------------|  -----------------------------------------------|          this.agencia = agencia;               
		        -----------------------------------------------|  -----------------------------------------------|          this.numeroContaExterna = numeroContaE
			  -----------------------------------------------|  -----------------------------------------------|          this.codigoBanco = codigoBanco;       
			    -----------------------------------------------|  -----------------------------------------------|      }                                         
			      -----------------------------------------------|  -----------------------------------------------|                                                
			        -----------------------------------------------|  -----------------------------------------------|      public ContaExterna(int idContaExterna, in
					  -----------------------------------------------|  -----------------------------------------------|          this.idContaExterna = idContaExterna; 
					    -----------------------------------------------|  -----------------------------------------------|          this.agencia = agencia;               
					      -----------------------------------------------|  -----------------------------------------------|          this.numeroContaExterna = numeroContaE
					        -----------------------------------------------|  -----------------------------------------------|          this.codigoBanco = codigoBanco;       
						  -----------------------------------------------|  -----------------------------------------------|      }    ;
    private int codigoBanco;

    public ContaExterna() {
    }

    public ContaExterna(int agencia, int numeroContaExterna, int codigoBanco) {
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
        this.codigoBanco = codigoBanco;
    }

    public ContaExterna(int idContaExterna, int agencia, int numeroContaExterna, int codigoBanco) {
        this.idContaExterna = idContaExterna;
        this.agencia = agencia;
        this.numeroContaExterna = numeroContaExterna;
        this.codigoBanco = codigoBanco;
    }
>>>>>>> dev

    public int getIdContaExterna() {
        return idContaExterna;
    }

    public void setIdContaExterna(int idContaExterna) {
        this.idContaExterna = idContaExterna;
    }

<<<<<<< HEAD
    public String getDadosContaExterna() {
        return dadosContaExterna;
    }

    public void setDadosContaExterna(String dadosContaExterna) {
        this.dadosContaExterna = dadosContaExterna;
    }
}
=======
    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumeroContaExterna() {
        return numeroContaExterna;
    }

    public void setNumeroContaExterna(int numeroContaExterna) {
        this.numeroContaExterna = numeroContaExterna;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(int codigoBanco) {
        this.codigoBanco = codigoBanco;
    }
}
>>>>>>> dev
