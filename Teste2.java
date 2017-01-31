import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Teste2 {

	public class ItemDeRota{
		private int origem;
		private int destino;
		private String palavra;
		private boolean peek;
		private boolean push;
		private boolean pop;
		private boolean destinoEhEstadoFinal;
		
		public ItemDeRota(int origem, int destino, String palavra, boolean peek, boolean push, boolean pop, boolean destinoEhEstadoFinal){
			this.origem=origem;
			this.destino=destino;
			this.palavra=palavra;
			this.peek=peek;
			this.push=push;
			this.pop=pop;
			this.destinoEhEstadoFinal = destinoEhEstadoFinal;
		}
		
		public boolean isDestinoEhEstadoFinal() {
			return destinoEhEstadoFinal;
		}

		public void setDestinoEhEstadoFinal(boolean destinoEhEstadoFinal) {
			this.destinoEhEstadoFinal = destinoEhEstadoFinal;
		}

		public ItemDeRota(){
			
		}

		public int getOrigem() {
			return origem;
		}

		public void setOrigem(int origem) {
			this.origem = origem;
		}

		public int getDestino() {
			return destino;
		}

		public void setDestino(int destino) {
			this.destino = destino;
		}

		public String getPalavra() {
			return palavra;
		}

		public void setPalavra(String palavra) {
			this.palavra = palavra;
		}

		public boolean isPeek() {
			return peek;
		}

		public void setPeek(boolean peek) {
			this.peek = peek;
		}

		public boolean isPush() {
			return push;
		}

		public void setPush(boolean push) {
			this.push = push;
		}

		public boolean isPop() {
			return pop;
		}

		public void setPop(boolean pop) {
			this.pop = pop;
		}
			
		
	}
	
	//representa um item de rota mais um array de valores possíveis para serem sugeridos ao usuário. Essa array de valores será utilizado como sugestões
	public class ItemDeRotaComValores{
		private ItemDeRota itemDeRota;
		private ArrayList<String> valores;
		public ItemDeRotaComValores(ItemDeRota itemDeRota, ArrayList<String> valores){
			this.itemDeRota=itemDeRota;
			this.valores=valores;
		}
		public ItemDeRotaComValores(){
			
		}
		public ItemDeRota getItemDeRota() {
			return itemDeRota;
		}
		public void setItemDeRota(ItemDeRota itemDeRota) {
			this.itemDeRota = itemDeRota;
		}
		public ArrayList<String> getValores() {
			return valores;
		}
		public void setValores(ArrayList<String> valores) {
			this.valores = valores;
		}
		
	}
	
	public class ItemPergunta{
		private String tipoItem;//#QUAIS, #CONCEITO, #QUE, #RESTRICAO, #OPERADOR, #VALOR, #OPERADOR_LOGICO
		private int id;
		private String valor;
	}
	
	//Os tipos possíveis são #QUAIS, #CONCEITO, #QUE, #RESTRICAO, #OPERADOR, #VALOR, #OPERADOR_LOGICO
	//É preciso trocar esse código para ir no banco e melhorar o processamento das variações
	public boolean valorEstaDeAcordoComTipo(String tipoItem, String valor){
		
		boolean estaDeAcordo=false;
	
		if((valor!=null)&&(tipoItem!=null)){
			if(tipoItem.equals("#QUAIS")){
				estaDeAcordo = ((valor.toUpperCase().equals("QUAIS SÃO"))||
								(valor.toUpperCase().equals("QUAIS SAO"))||
								(valor.toUpperCase().equals("QUAIS"))||
								(valor.toUpperCase().equals("QUAL"))||
								(valor.toUpperCase().equals("QUAL É"))||
								(valor.toUpperCase().equals("QUAL E")));
			}
			else if(tipoItem.equals("#CONCEITO")){
				estaDeAcordo = ((valor.toUpperCase().equals("POÇO"))||
						(valor.toUpperCase().equals("POCO"))||
						(valor.toUpperCase().equals("POÇOS"))||
						(valor.toUpperCase().equals("POCOS"))||
						(valor.toUpperCase().equals("BLOCO"))||
						(valor.toUpperCase().equals("BLOCOS")));
			}
			else if(tipoItem.equals("#QUE")){
				estaDeAcordo = ((valor.toUpperCase().equals("QUE"))||
						(valor.toUpperCase().equals("QUE TEM"))||
						(valor.toUpperCase().equals("TEM")));
			}
			else if(tipoItem.equals("#RESTRICAO")){
				estaDeAcordo = ((valor.toUpperCase().equals("NOME"))||
						(valor.toUpperCase().equals("UO"))||
						(valor.toUpperCase().equals("SIGLA"))||
						(valor.toUpperCase().equals("DATA_INI_PERF"))||
						(valor.toUpperCase().equals("AREA_BLOCO")));
			}
			else if(tipoItem.equals("#OPERADOR")){
				estaDeAcordo = ((valor.toUpperCase().equals("IGUAL"))||
						(valor.toUpperCase().equals("DIFERENTE"))||
						(valor.toUpperCase().equals("MAIOR"))||
						(valor.toUpperCase().equals("MENOR")));
			}
			else if(tipoItem.equals("#VALOR")){
				estaDeAcordo=true;
			}
			else if(tipoItem.equals("#OPERADOR_LOGICO")){
				estaDeAcordo = ((valor.toUpperCase().equals("E")));
			}
		}
				
		return estaDeAcordo;
	}
	
	public class Pergunta{
		private ArrayList<String> texto;
		private boolean validada;
		public ArrayList<String> getTexto() {
			return texto;
		}
		public void setTexto(ArrayList<String> texto) {
			this.texto = texto;
		}
		public boolean isValidada() {
			return validada;
		}
		public void setValidada(boolean validada) {
			this.validada = validada;
		}
		public Pergunta(ArrayList<String> texto, boolean validada){
			this.texto=texto;
			this.validada=validada;
		}
		public Pergunta(){
			
		}
	}
	
	//não estou considerando por enquanto buscas com o filtrinho
	public ArrayList<ArrayList<ItemDeRota>> criaMatrizDeRotas(){
	
		//ItemDeRota i = new ItemDeRota(origem, destino, palavra, peek, push, pop, destinoEhEstadoFinal);
		
		int tam = 7; 
		ArrayList<ArrayList<ItemDeRota>> m = new ArrayList<ArrayList<ItemDeRota>>(tam);
		
		ArrayList<ItemDeRota> linha0 = new ArrayList<ItemDeRota>(8);for(int i=0; i<tam;i++){linha0.add(new ItemDeRota());}
		linha0.set(1, new ItemDeRota(0,1,"#QUAIS",false,false,false,false));
		
		//destino eh estado final
		//enquanto estiver trabalhando nesse conceito, devo deixar esse conceito na base da pilha e consultar seu valor pelo PEEK
		ArrayList<ItemDeRota> linha1 = new ArrayList<ItemDeRota>(8);for(int i=0; i<tam;i++){linha1.add(new ItemDeRota());}
		linha1.set(2, new ItemDeRota(1,2,"#CONCEITO",false,true,false,true));
		
		ArrayList<ItemDeRota> linha2 = new ArrayList<ItemDeRota>(8);for(int i=0; i<tam;i++){linha2.add(new ItemDeRota());}
		linha2.add(3, new ItemDeRota(2,3,"#QUE",true,false,false,false));
		
		ArrayList<ItemDeRota> linha3 = new ArrayList<ItemDeRota>(8);for(int i=0; i<tam;i++){linha3.add(new ItemDeRota());}
		linha3.add(4, new ItemDeRota(3,4,"#RESTRICAO",false,true,true,false));
		
		ArrayList<ItemDeRota> linha4 = new ArrayList<ItemDeRota>(8);for(int i=0; i<tam;i++){linha4.add(new ItemDeRota());}
		linha4.add(5, new ItemDeRota(4,5,"#OPERADOR",false,true,true,false));
		
		//destino eh estado final
		ArrayList<ItemDeRota> linha5 = new ArrayList<ItemDeRota>(8);for(int i=0; i<tam;i++){linha5.add(new ItemDeRota());}
		linha5.add(6, new ItemDeRota(5,6,"#VALOR",false,false,false,true));
		
		ArrayList<ItemDeRota> linha6 = new ArrayList<ItemDeRota>(8);for(int i=0; i<tam;i++){linha6.add(new ItemDeRota());}
		linha6.add(3, new ItemDeRota(6,3,"#OPERADOR_LOGICO",true,false,false,false));
		
		m.add(0, linha0);
		m.add(1, linha1);
		m.add(2, linha2);
		m.add(3, linha3);
		m.add(4, linha4);
		m.add(5, linha5);
		m.add(6, linha6);
				
		return m;
	}
	
	public void desenhaMatrizDeRotas(ArrayList<ArrayList<ItemDeRota>> matrizDeRotas){
		
		for(int i=0; i<matrizDeRotas.size();i++){
			ArrayList<ItemDeRota> linha = matrizDeRotas.get(i);
			for(int j=0;j<linha.size();j++){
				ItemDeRota itemDeRota = linha.get(j);
				System.out.print("Pos"+i+","+j+":"+ itemDeRota.getPalavra()+" ; ");
			}
			System.out.println();
		}
			
	}
	
	//retorna uma lista vazia se nao tiver sugestoes
	public ArrayList<ItemDeRota> getSugestoesDeRota(int estadoAtual, ArrayList<ArrayList<ItemDeRota>> matriz){
		
		ArrayList<ItemDeRota> sugestoesDeRota = new ArrayList<ItemDeRota>();
		
		ArrayList<ItemDeRota> linha = matriz.get(estadoAtual);
		for (int i = 0; i < linha.size(); i++) {
			ItemDeRota item = linha.get(i);
			if(item.getPalavra()!=null){
				sugestoesDeRota.add(item);
			}
		}
				
		return sugestoesDeRota;
	}
	
	public void imprimeSugestoesDeRota(ArrayList<ItemDeRota> sugestoesDeRota){
		System.out.print("Sugestoes: ");
		for (int i = 0; i < sugestoesDeRota.size(); i++) {
			ItemDeRota item = sugestoesDeRota.get(i);
			System.out.print(item.getPalavra() + ", ");
		}
		System.out.println();
	}
	
	public void imprimeSugestoesDeRotaComValores(ArrayList<ItemDeRotaComValores> sugestoesDeRotaComValores){
		System.out.print("Sugestoes com valores: ");
		for (int i = 0; i < sugestoesDeRotaComValores.size(); i++) {
			ItemDeRotaComValores item = sugestoesDeRotaComValores.get(i);
			System.out.print(item.getValores());
		}
		System.out.println();
	}
	
	//a premissa é que dado um conjunto de parâmetros sempre encontraremos apenas 1 item de rota adequado
	public ItemDeRota descobreDestino(int origem, String termoDigitado, ArrayList<ArrayList<ItemDeRota>> matrizDeRotas){
		ItemDeRota retorno = new ItemDeRota();
		retorno.setDestino(-1);
				
		if(termoDigitado.equals("#START")){
			retorno.setDestino(0);
		}
		else{
			if(termoDigitado!=null){
				ArrayList<ItemDeRota> linha = matrizDeRotas.get(origem);
				for (int i = 0; i < linha.size(); i++) {
					ItemDeRota item = linha.get(i);
					//if(termoDigitado.equals(item.getPalavra()))
					//Os tipos possíveis são #QUAIS, #CONCEITO, #QUE, #RESTRICAO, #OPERADOR, #VALOR, #OPERADOR_LOGICO
					if(valorEstaDeAcordoComTipo(item.getPalavra(), termoDigitado))
					{
						retorno=item;
						break;
					}
				}
			}
		}
						
		return retorno;
	}
	
	//tenho dúvidas quanto a utilidade desse método
	private ArrayList<String> getValorPorOperador(String infoDaPilha) {
		ArrayList<String> valoresPorOperadores=new ArrayList<String>();
		valoresPorOperadores.add("Qualquer valor!");
		
		return valoresPorOperadores;
	}

	//metodo que deverá ir na base do VGE
	private ArrayList<String> getOperadorPorRestricao(String infoDaPilha) {
		ArrayList<String> operadoresPorRestricao=new ArrayList<String>();
		
		if(infoDaPilha!=null){
			if(infoDaPilha.toUpperCase().equals("NOME")||infoDaPilha.toUpperCase().equals("SIGLA")||infoDaPilha.toUpperCase().equals("UO")){
				operadoresPorRestricao.add("IGUAL");
				operadoresPorRestricao.add("DIFERENTE");
			}
			else if(infoDaPilha.toUpperCase().equals("DATA_INI_PERF")||infoDaPilha.toUpperCase().equals("AREA_BLOCO")){
				operadoresPorRestricao.add("MAIOR");
				operadoresPorRestricao.add("MENOR");
			}
		}
		
		return operadoresPorRestricao;
	}

	//metodo que deverá ir na base do VGE
	private ArrayList<String> getRestricoesPorConceito(String infoDaPilha) {
		ArrayList<String> restricoesPorConceito=new ArrayList<String>();
		
		if(infoDaPilha!=null){
			//trocar esse codigo por busca no banco
			if((infoDaPilha.toUpperCase().equals("POCO"))||
					(infoDaPilha.toUpperCase().equals("POCOS"))||
					(infoDaPilha.toUpperCase().equals("POÇO"))||
					(infoDaPilha.toUpperCase().equals("POÇOS"))){
				restricoesPorConceito.add("NOME");
				restricoesPorConceito.add("SIGLA");
				restricoesPorConceito.add("DATA_INI_PERF");
			}
			else if(infoDaPilha.toUpperCase().equals("BLOCO")||infoDaPilha.toUpperCase().equals("BLOCOS")){
				restricoesPorConceito.add("NOME");
				restricoesPorConceito.add("UO");
				restricoesPorConceito.add("AREA_BLOCO");
			}
		}
		
		return restricoesPorConceito;
	}
	
	/*
	 * Tem que mudar esse método para buscar na base do VGE
	 * O parametro nao esta sendo usado atualmente
	 */
	private ArrayList<String> getConceitos(String infoDaPilha){
		ArrayList<String> listaDeConceitos = new ArrayList<String>();
		
		listaDeConceitos.add("POCO");
		listaDeConceitos.add("BLOCO");
		
		return listaDeConceitos;
	}
	
	/*
	 * O parametro nao esta sendo usado atualmente
	 */
	private ArrayList<String> getConectorQue(String infoDaPilha){
		ArrayList<String> listaDeConectores = new ArrayList<String>();
		
		listaDeConectores.add("que");
		
		return listaDeConectores;
	}
	
	private ArrayList<String> getOperadorLogico(String infoDaPilha){
		ArrayList<String> listaDeOperadoresLogicos = new ArrayList<String>();
		
		listaDeOperadoresLogicos.add("E");
		
		return listaDeOperadoresLogicos;
	}
	
	private ArrayList<String> getQuais(String infoDaPilha){
		ArrayList<String> listaDeQuais = new ArrayList<String>();
		
		listaDeQuais.add("Quais");
				
		return listaDeQuais;
	}
	
	public ArrayList<String> getSugestoesDeValores(String token, String infoDaPilha){
		ArrayList<String> sugestoesDeValores = new ArrayList<String>();
		
		if(token.equals("#RESTRICAO")){
			sugestoesDeValores=getRestricoesPorConceito(infoDaPilha);
		}
		else if (token.equals("#OPERADOR")){
			sugestoesDeValores=getOperadorPorRestricao(infoDaPilha);
		}
		else if (token.equals("#VALOR")){
			sugestoesDeValores=getValorPorOperador(infoDaPilha);
		}
		else if (token.equals("#CONCEITO")){
			sugestoesDeValores=getConceitos(infoDaPilha);
		}
		else if (token.equals("#QUE")){
			sugestoesDeValores=getConectorQue(infoDaPilha);
		}
		else if (token.equals("#OPERADOR_LOGICO")){
			sugestoesDeValores=getOperadorLogico(infoDaPilha);
		}
		else if (token.equals("#QUAIS")){
			sugestoesDeValores=getQuais(infoDaPilha);
		}
		
		
		return sugestoesDeValores;
	}
		
	public ArrayList<ItemDeRotaComValores> getSugestoesDeRotaComValores(ArrayList<ItemDeRota> sugestoesDeRota, String infoDaPilha){
		
		ArrayList<ItemDeRotaComValores> sugestoesDeRotaComValores = new ArrayList<ItemDeRotaComValores>();
		
		if(sugestoesDeRota!=null){
			for (int i = 0; i < sugestoesDeRota.size(); i++) {
				ItemDeRota itemDeRota = sugestoesDeRota.get(i);
				if(itemDeRota.getPalavra()!=null){
					//descubro sugestoes de valores com base no token e na informação que veio da pilha
					ArrayList<String> valores = getSugestoesDeValores(itemDeRota.getPalavra(), infoDaPilha);
					
					ItemDeRotaComValores itemDeRotaComValores = new ItemDeRotaComValores(itemDeRota, valores);
					sugestoesDeRotaComValores.add(itemDeRotaComValores);
				}
			}
		}
		
		return sugestoesDeRotaComValores;
	}
	
	/*
	 * PENDENTE: colocar a pilha no roteamento para considerar a memória e só sugerir as restrições do conceito selecionado
	 */
	public static void main(String[] args) {
				
		Teste2 t2 = new Teste2();
		Scanner reader = new Scanner(System.in);
		
		ArrayList<ArrayList<ItemDeRota>> matrizDeRotas = t2.criaMatrizDeRotas();
		
		Pergunta pergunta = t2.new Pergunta(new ArrayList<String>(), false);
		String termoDigitado = "#START";
		Stack pilha=new Stack<>();//utilizada para memorizar algumas informações durante a execução do fluxo
		int origem=0;
		//String infoDaPilha=null;//representa o valor recuperado do topo da pilha
		//ItemDeRota itemDeRotaAnterior = null;//armazena o último roteamento feito para apoiar a recuperação da pilha caso o usuário não digite uma sugestão. Sem isso não conseguiremos dar uma sugestão pelo segunda vez caso o usuário digite um termo errado já que já teremos possivelmente dado um POP na pilha
		//boolean novoErro=true;//para impedir o empilhamento repetido quando o usuario erra varias vezes a sugestao
		ArrayList<ItemDeRota> sugestoesDeRota = null;
		ArrayList<ItemDeRotaComValores> sugestoesDeRotaComValores = null;
		
		do{
			ItemDeRota itemDeRotaEncontrado = t2.descobreDestino(origem, termoDigitado, matrizDeRotas);
			int destino = itemDeRotaEncontrado.getDestino();
			
			if(destino==-1){
				//nao foi possivel encontrar rota com o termo digitado
				System.out.println("Não entendi :(");
				
				//preciso fazer uma compensação para voltar a pilha para o estado anterior, caso contrário, poderei ter problema com as sugestoes
				//infoDaPilha ainda está com o valor do último roteamento
				//if((itemDeRotaAnterior!=null)&&(novoErro)){
				//a compensação é invertida em relação ao processo normal
				//NORMAL: push e depois pop
				//COMPENSAÇÃO: pop e depois push
				/*
				if(itemDeRotaAnterior!=null){
					if(itemDeRotaAnterior.isPop()){
						//vou devolver o valor da pilha
						pilha.push(infoDaPilha);
						//novoErro=false;
					}
					if(itemDeRotaAnterior.isPeek()){
						//não faz nada
					}
					if(itemDeRotaAnterior.isPush()){
						//não faz nada
						pilha.pop();
					}
				}
				*/
				
			}
			else{
				//atualiza novo erro para permitir a recuperação da pilha caso o usuário erre a sugestão
				//novoErro=true;
				
				//memoriza a rota para permitir a recuperação do estado da pilha caso o usuário não digite uma sugestão
				//itemDeRotaAnterior=itemDeRotaEncontrado;
				
				//atualiza origem
				origem=destino;
				
				//atualiza pergunta
				pergunta.getTexto().add(termoDigitado);
				
				//verifica se nova origem eh um estado final
				if(itemDeRotaEncontrado.isDestinoEhEstadoFinal()){
					pergunta.setValidada(true);
				}
				else{
					pergunta.setValidada(false);
				}
			
				//SO ATUALIZO A PILHA E PEGO NOVAS SUGESTOES QUANDO O USUARIO DIGITA SUGESTAO CORRETA
				
				//atualizar a pilha e pegar mais informações para as sugestões
				//eh importante que seja respeitada a ordem: push, peek e pop
				String infoDaPilha=null;
				if(itemDeRotaEncontrado.isPush()){
					pilha.push(termoDigitado);
				}
				if(itemDeRotaEncontrado.isPeek()){
					infoDaPilha=(String)pilha.peek();
				}
				if(itemDeRotaEncontrado.isPop()){
					infoDaPilha=(String)pilha.pop();
				}
																
				//pega sugestoes
				sugestoesDeRota = t2.getSugestoesDeRota(origem, matrizDeRotas);
				sugestoesDeRotaComValores = t2.getSugestoesDeRotaComValores(sugestoesDeRota, infoDaPilha);
			
			}
			
			
			
			//inicia a preparacao para um novo ciclo
			System.out.println("Estamos na posição: " + origem);
			
			//apresenta sugestoes ao usuario
			t2.imprimeSugestoesDeRota(sugestoesDeRota);
			t2.imprimeSugestoesDeRotaComValores(sugestoesDeRotaComValores);
			
			System.out.println("Pilha: " + pilha);
			
			System.out.println("Pergunta bem formada? " + pergunta.isValidada() + " - " + pergunta.getTexto());
			System.out.println("Escolha uma sugestão ou sair: ");
			termoDigitado = reader.nextLine();
			System.out.println();
		}
		while(!"SAIR".equals(termoDigitado.toUpperCase()));
		
		reader.close();
		pergunta.getTexto().remove(0);//vou remover o marcador inicial #START
		System.out.println("------ SAIU ------");
		
		if(pergunta.isValidada()){
			System.out.println("Você saiu! Sua pergunta está bem formada: " + pergunta.getTexto());
		}
		else{
			System.out.println("Você saiu! Sua pergunta está mal formada: " + pergunta.getTexto());
		}
		
		
	}
	
}
