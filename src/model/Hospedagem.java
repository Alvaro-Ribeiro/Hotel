package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Acomodacao.EEstadoOcupacao;
import model.Pagamento.ETipoPagamento;
import util.Util;

public class Hospedagem implements Serializable{
    
    private static final long serialVersionUID = -20678667L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private static int inicioCheckin = 13;
    private static int limiteCheckout = 12;
    private final String id = UUID.randomUUID().toString();
    private final LocalDateTime checkin = LocalDateTime.now();
    private LocalDateTime checkout = null;

    private final IAcomodacao acomodacao;

    private final IHospede hospede;
    private final ArrayList<IHospede> acompanhantes = new ArrayList<>();

    private final ArrayList<Pagamento> pagamentos = new ArrayList<>();

    private final IConta conta = new Conta();

    public Hospedagem(IAcomodacao acomodacao, IHospede hospede, ArrayList<IHospede> acompanhantes){
        if(acomodacao.getEstadoOcupacao() != EEstadoOcupacao.DISPONIVEL){
            throw exception.Excecoes.acomodOcupada;
        }
        this.acomodacao = acomodacao;
        this.acomodacao.setEstadoOcupacao(EEstadoOcupacao.OCUPADO);
        this.hospede = hospede;
        addAcompanhantes(acompanhantes);
    }

    public static int getInicioCheckin(){
        return inicioCheckin;
    }

    public static int getLimiteCheckout(){
        return limiteCheckout;
    }

    public String getId() {
        return this.id;
    }

    public LocalDateTime getCheckin() {
        return this.checkin;
    }

    public LocalDateTime getCheckout() {
        return this.checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        if(this.checkout == null){
            this.checkout = checkout;
        }
    }

    public IAcomodacao getAcomodacao() {
        return this.acomodacao;
    }

    public IHospede getHospede() {
        return this.hospede;
    }

    public ArrayList<IHospede> getAcompanhantes() {
        return this.acompanhantes;
    }

    public ArrayList<Pagamento> getPagamentos() {
        return this.pagamentos;
    }

    public IConta getConta() {
        return this.conta;
    }
    
    public void addPagamento(ETipoPagamento tipo, double valor) throws IllegalArgumentException {
    	pagamentos.add(new Pagamento(tipo, valor));
    }

    public void addAcompanhantes(List<IHospede> l){
        acompanhantes.addAll(l);
    }

    public int getQtdAcompanhantes(){
        return this.getAcompanhantes().size();
    }

    public long getDias(){
        LocalDateTime temp1 = LocalDateTime.of(this.getCheckin().getYear(), this.getCheckin().getMonthValue(), this.getCheckin().getDayOfMonth(), 0, 0, 0);
        LocalDateTime temp2 = LocalDateTime.of(this.getCheckout().getYear(), this.getCheckout().getMonthValue(), this.getCheckout().getDayOfMonth(), 0, 0, 0);
        long dias = temp1.until(temp2, ChronoUnit.DAYS) + 1;
        if(this.getCheckin().getHour() < inicioCheckin){
            dias++;
        }
        if(this.getCheckout().getHour() < limiteCheckout || (this.getCheckout().getHour() == limiteCheckout && this.getCheckout().getMinute() == 0)){
            dias--;
        }

        return dias;
    }

    public double getTotalAcompanhantes(){
        return this.getQtdAcompanhantes() * acomodacao.getAdicionalAcompanhante() * this.getDias();
    }

    public double getTotalHospede(){
        return acomodacao.getTarifaDiaria() * this.getDias();
    }

    public double getTotalDiarias(){
        return this.getTotalAcompanhantes() + this.getTotalHospede();
    }

    public double getTotalConta(){
        return conta.getTotal();
    }

    public double getTotalHospedagem(){
        return this.getTotalDiarias() + this.getTotalConta();      
    }

    public StringBuilder listar(){

        StringBuilder listar = new StringBuilder();
        listar.append("HOSPEDAGEM:\n");
        listar.append(this.acomodacao.getTipo() + " | Diária: R$ " + Util.valueFormat(this.acomodacao.getTarifaDiaria()) + " | " + this.getDias() + " dia(s)" + " | Parcial: R$ " + Util.valueFormat(this.getTotalHospede()) + "\n");
        listar.append("Acompanhantes: " + this.getQtdAcompanhantes() + " | Taxa Acompanhante: R$ " + Util.valueFormat(this.acomodacao.getAdicionalAcompanhante()) + " | Parcial: R$ " + Util.valueFormat(this.getTotalAcompanhantes()) + "\n");
        listar.append("CheckIn: " + Util.dataFormat(this.checkin) + "\n");
        listar.append("CheckOut: " + Util.dataFormat(this.checkout) + "\n");

        listar.append(conta.listar() + "\n");

        listar.append("TOTAL: " + Util.valueFormat(getTotalHospedagem()));

        return listar;
    }

    public double getTotalPagamentos(){
        double total = 0.;
        for (Pagamento p : pagamentos) {
            total += p.getValor();
        }
        return total;
    }

    public void realizarCheckout(){ // TO DO - Estornar valor excedente
    	this.setCheckout(LocalDateTime.now());
        if (this.getTotalPagamentos() < this.getTotalHospedagem()){
            throw exception.Excecoes.pgmtPendente;
        }
        this.listar();
        acomodacao.setEstadoOcupacao(EEstadoOcupacao.MANUTENCAO);
    }

    public double pgmtFaltante(){
        return this.getTotalHospedagem() - this.getTotalPagamentos();
    }

}
