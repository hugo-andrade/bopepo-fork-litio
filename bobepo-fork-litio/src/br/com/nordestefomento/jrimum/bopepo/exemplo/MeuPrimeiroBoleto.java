package br.com.nordestefomento.jrimum.bopepo.exemplo;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import br.com.nordestefomento.jrimum.bopepo.BancoSuportado;
import br.com.nordestefomento.jrimum.bopepo.Boleto;
import br.com.nordestefomento.jrimum.bopepo.view.BoletoViewer;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.CEP;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.Endereco;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.Banco;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Agencia;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Carteira;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Cedente;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Modalidade;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Sacado;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Titulo;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Titulo.EnumAceite;

/**
 * 
 * <p>
 * Exemplo de cÃ³digo para geraÃ§Ã£o de um boleto simples.
 * </p>
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author RÃ´mulo Augusto
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class MeuPrimeiroBoleto {

	public static void main(String[] args) {

		/*
		 * INFORMANDO DADOS SOBRE O CEDENTE.
		 */
		Cedente cedente = new Cedente("PROJETO JRimum", "00.000.208/0001-00");

		// Informando dados sobre a conta bancÃ¡ria do cendente.
		Banco banco = BancoSuportado.NOSSA_CAIXA.create();
		ContaBancaria contaBancariaCed = new ContaBancaria(banco);
		contaBancariaCed.setBanco(banco);
		contaBancariaCed.setNumeroDaConta(new NumeroDaConta(123456, "0"));
		contaBancariaCed.setCarteira(new Carteira(123,
				TipoDeCobranca.SEM_REGISTRO));
		contaBancariaCed.setModalidade(new Modalidade(4));
		contaBancariaCed.setAgencia(new Agencia(1234, "1"));
		cedente.addContaBancaria(contaBancariaCed);

		/*
		 * INFORMANDO DADOS SOBRE O SACADO.
		 */
		Sacado sacado = new Sacado("JavaDeveloper Pronto Para FÃ©rias",
				"222.222.222-22");

		// Informando o endereÃ§o do sacado.
		Endereco enderecoSac = new Endereco();
		enderecoSac.setUF(UnidadeFederativa.RN);
		enderecoSac.setLocalidade("Natal");
		enderecoSac.setCep(new CEP("59064-120"));
		enderecoSac.setBairro("Grande Centro");
		enderecoSac.setLogradouro("Rua poeta dos programas");
		enderecoSac.setNumero("1");
		sacado.addEndereco(enderecoSac);

		/*
		 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
		 */
		SacadorAvalista sacadorAvalista = new SacadorAvalista(
				"Nordeste Fomento Mercantil", "00.000.000/0001-91");

		// Informando o endereÃ§o do sacador avalista.
		Endereco enderecoSacAval = new Endereco();
		enderecoSacAval.setUF(UnidadeFederativa.DF);
		enderecoSacAval.setLocalidade("BrasÃ­lia");
		enderecoSacAval.setCep(new CEP("00000-000"));
		enderecoSacAval.setBairro("Grande Centro");
		enderecoSacAval.setLogradouro("Rua Eternamente Principal");
		enderecoSacAval.setNumero("001");
		sacadorAvalista.addEndereco(enderecoSacAval);

		/*
		 * INFORMANDO OS DADOS SOBRE O TÃ�TULO.
		 */
		Titulo titulo = new Titulo(contaBancariaCed, sacado, cedente,
				sacadorAvalista);
		titulo.setNumeroDoDocumento("123456");
		titulo.setNossoNumero("993456789");
		titulo.setDigitoDoNossoNumero("5");
		titulo.setValor(BigDecimal.valueOf(0.23));
		titulo.setDataDoDocumento(new Date());
		titulo.setDataDoVencimento(new Date());
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		titulo.setAceite(EnumAceite.A);
		titulo.setDesconto(new BigDecimal(0.05));

		/*
		 * INFORMANDO MAIS DADOS BANCÃ�RIOS, QUANDO NECESSÃ�RIO. Dependendo do
		 * banco, talvez seja necessÃ¡rio informar mais dados alÃ©m de:
		 * 
		 * > Valor do tÃ­tulo; > Vencimento; > Nosso nÃºmero; > CÃ³digo do banco >
		 * Data de vencimento; > AgÃªncia/CÃ³digo do cedente; > CÃ³digo da
		 * carteira; > CÃ³digo da moeda;
		 * 
		 * Definidos como padrÃ£o pela FEBRABAN. Verifique na documentaÃ§Ã£o.
		 */
		titulo.setParametrosBancarios(new ParametrosBancariosMap("dadoNecessario",
				"Por exemplo, uma constante string").adicione("outroDadoNecessario:Constante1", new Integer(1)));
		
		/*
		 * Para recuperar um dado
		 */
		String dado = titulo.getParametrosBancarios().getValor("dadoNecessario");
		System.out.println("ParÃ¢metros BancÃ¡rios: um dado necessÃ¡rio. "+dado);

		/*
		 * INFORMANDO OS DADOS SOBRE O BOLETO.
		 */
		Boleto boleto = new Boleto(titulo);
		boleto.setLocalPagamento("PagÃ¡vel preferencialmente na Rede X ou em "
				+ "qualquer Banco atÃ© o Vencimento.");
		boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
				+ "cobrado nÃ£o Ã© o esperado, aproveite o DESCONTÃƒO!");
		boleto.setInstrucao1("PARA PAGAMENTO 1 atÃ© Hoje nÃ£o cobrar nada!");
		boleto.setInstrucao2("PARA PAGAMENTO 2 atÃ© AmanhÃ£ NÃ£o cobre!");
		boleto
				.setInstrucao3("PARA PAGAMENTO 3 atÃ© Depois de amanhÃ£, OK, nÃ£o cobre.");
		boleto.setInstrucao4("PARA PAGAMENTO 4 atÃ© 04/xx/xxxx de 4 dias atrÃ¡s "
				+ "COBRAR O VALOR DE: R$ 01,00");
		boleto.setInstrucao5("PARA PAGAMENTO 5 atÃ© 05/xx/xxxx COBRAR O VALOR "
				+ "DE: R$ 02,00");
		boleto.setInstrucao6("PARA PAGAMENTO 6 atÃ© 06/xx/xxxx COBRAR O VALOR "
				+ "DE: R$ 03,00");
		boleto.setInstrucao7("PARA PAGAMENTO 7 atÃ© xx/xx/xxxx COBRAR O VALOR "
				+ "QUE VOCÃŠ QUISER!");
		boleto.setInstrucao8("APÃ“S o Vencimento, PagÃ¡vel Somente na Rede X.");

		/*
		 * GERANDO O BOLETO BANCÃ�RIO.
		 */
		// Instanciando um objeto "BoletoViewer", classe responsÃ¡vel pela
		// geraÃ§Ã£o
		// do boleto bancÃ¡rio.
		BoletoViewer boletoViewer = new BoletoViewer(boleto);

		// Gerando o arquivo. No caso o arquivo mencionado serÃ¡ salvo na mesma
		// pasta do projeto. Outros exemplos:
		// WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
		// LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
		File arquivoPdf = boletoViewer.getPdfAsFile("c:/tmp/MeuPrimeiroBoleto.pdf");

		// Mostrando o boleto gerado na tela.
		mostreBoletoNaTela(arquivoPdf);
	}

	private static void mostreBoletoNaTela(File arquivoBoleto) {

		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		
		try {
			desktop.open(arquivoBoleto);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
