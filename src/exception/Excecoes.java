package exception;

public class Excecoes {

    public final static IllegalArgumentException pgmtPendente =  new IllegalArgumentException("Erro: Pagamento pendente!\n");
    public final static IllegalArgumentException ocupMaxExtrapolada =  new IllegalArgumentException("Erro: Ocupação máxima extrapolada!\n");
    public final static IllegalArgumentException acomodOcupada = new IllegalArgumentException("Erro: Acomodacao ocupada!\n");
    public final static IllegalArgumentException ocupMaxInvalida = new IllegalArgumentException("Erro: Ocupação máxima inválida! (Precisa ser maior que 0)\n");
    public final static IllegalArgumentException tarifaDiaria = new IllegalArgumentException("Erro: Tarifa inválida! (Precisa ser maior que 0.00)\n");
    public final static IllegalArgumentException adcAcomp = new IllegalArgumentException("Erro: Tarifa inválida! (Precisa ser maior ou igual a 0.00)\n");
    public final static IllegalArgumentException valorInv = new IllegalArgumentException("Erro: Valor inválido! (Precisa ser maior que 0.00)\n");
    public final static IllegalArgumentException qtdeInv = new IllegalArgumentException("Erro: quantidade inválida\n");
    public final static IllegalArgumentException itemJaCadCat = new IllegalArgumentException("Erro: Item já cadastrado nessa categoria.\n");
    public final static IllegalArgumentException itemNaoCad = new IllegalArgumentException("Erro: Item inexistente.\n");
    public final static IllegalArgumentException cpfInv = new IllegalArgumentException("Erro: CPF inválido.\n");
    public final static IllegalArgumentException nomeInv = new IllegalArgumentException("Erro: Nome inválido.\n");
    public final static IllegalArgumentException telInv = new IllegalArgumentException("Erro: Telefone inválido.\n");
    public final static IllegalArgumentException cpfJaCad = new IllegalArgumentException("CPF já cadastrado no sistema.\n");
    public final static IllegalArgumentException emailInv = new IllegalArgumentException("Erro: Email inválido.\n");
	public final static IllegalArgumentException cpfNaoCad = new IllegalArgumentException("CPF não cadastrado. \nFavor cadastrar antes de realizar a Hospedagem.\n");
	public final static IllegalArgumentException catJaExiste = new IllegalArgumentException("Categoria já existente!\n");
	public final static IllegalArgumentException itemJaCad = new IllegalArgumentException("Erro: Item já cadastrado no sistema.\n");
	public final static IllegalArgumentException numJaExiste = new IllegalArgumentException("Erro: Já existe acomodação com esse número.\n");
	public final static IllegalArgumentException tipoAcomodJaExiste = new IllegalArgumentException("Já existe tipo de acomodacao com esse nome.\n");
    public final static IllegalArgumentException tipoAcomodInv = new IllegalArgumentException("Tipo de Acomodação inválido.\n");
	public final static IllegalArgumentException numInv = new IllegalArgumentException("Número inválido!.\n");
	public final static IllegalArgumentException acompJaListado = new IllegalArgumentException("Acompanhante já adicionado");
	public final static IllegalArgumentException hospNaoAcomp = new IllegalArgumentException("Hóspede não pode ser acompanhante");

}
