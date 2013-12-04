package com.senac.jogos.labirinto;

public class Sala {

	Conexao[] conexoes = new Conexao[6];
	
	private static int getDirecaoIndex(String direcao) throws Exception
	{
		Direcao dir = Direcao.converte(direcao);
		return dir.getIndex();
	}
	
	public void setArmadilha(String direcao) throws Exception
	{
		conexoes[getDirecaoIndex(direcao)].setArmadilha();
	}

	public void addConexao(String direcao, int sala) throws Exception
	{
		int ndx = getDirecaoIndex(direcao);
		if (conexoes[ndx] != null)
			throw new Exception("Conexao j‡ existente.");
		
		conexoes[ndx] = new Conexao(sala);
	}
	
	public String toString()
	{	
		try {
			String res =  "A sala tem saida nas direcoes:\n";
			for (int i = 0; i < 6; i++)
				if (conexoes[i] != null)
					res += "\t" + Direcao.converte(i) + " " + conexoes[i] + "\n";
			return res;
		} catch (Exception e) {
			return "SALA COM ESTADO INVALIDO!";
		}
		
	}
    public Conexao getConectar(Direcao direcao) {
        return conexoes[direcao.getIndex()];
}  
	
}
