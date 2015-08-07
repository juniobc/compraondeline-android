package br.com.entidade;


public class Produto {
	
	private String nome;
	private float preco;
	private int quantidade;
	private String cdBarra;
	private String nrLat;
	private String nrLong;
	private String tpUnidade;
	private float qtUnidade;
	private boolean tpCad;
	private int dt_hr;	
	private int cdProd;

	public Produto(){}
	
	public Produto(int cdProd, String nome, String tpUnidade, float qtUnidade, float preco, int quantidade, 
			String nr_lat, String nr_long, boolean tpCad, int dt_hr) {
		
		super();
		
		this.cdProd = cdProd;
		this.nome = nome;
		this.tpUnidade = tpUnidade;
		this.qtUnidade = qtUnidade;
		this.preco = preco;		
		this.quantidade = quantidade;
		this.nrLat = nr_lat;
		this.nrLong = nr_long;
		this.tpCad = tpCad;
		this.dt_hr = dt_hr;
		
	}
	
	public Produto(String nome, String tpUnidade, float qtUnidade, float preco, int quantidade, 
			String nr_lat, String nr_long, boolean tpCad) {
		
		super();
		
		this.nome = nome;
		this.tpUnidade = tpUnidade;
		this.qtUnidade = qtUnidade;
		this.preco = preco;		
		this.quantidade = quantidade;
		this.nrLat = nr_lat;
		this.nrLong = nr_long;
		this.tpCad = tpCad;
		this.dt_hr = dt_hr;
		
	}
	
	public int getCdProd() {
		return cdProd;
	}

	public void setCdProd(int cdProd) {
		this.cdProd = cdProd;
	}
	
	public int getDt_hr() {
		return dt_hr;
	}

	public void setDt_hr(int dt_hr) {
		this.dt_hr = dt_hr;
	}
	
	public boolean getTpCad() {
		return tpCad;
	}

	public void setTpCad(boolean tpCad) {
		this.tpCad = tpCad;
	}
	
	public String getTpUnidade() {
		return tpUnidade;
	}
	
	public float getQtUnidade() {
		return qtUnidade;
	}
	
	public String getNrLat(){
		
		return this.nrLat;
		
	}
	
	public String getNrLong(){

		return this.nrLong;

	}
	
	public float getPreco(){
		
		return preco;
		
	}
	
	public String getNome(){
		
		return nome;
		
	}
	
	public int getQuantidade(){
		
		return quantidade;
		
	}
	
	public String getCdBarra(){
		
		return cdBarra;
		
	}
	
	public float setPreco(float preco){
		
		this.preco = preco;
		
		return preco;
		
	}
	
	public String setNome(String nome){
		
		this.nome = nome;
		
		return nome;
		
	}
	
	public int setQuantidade(int quantidade){
		
		this.quantidade = quantidade;
		
		return quantidade;
		
	}
	
	public String setCdBarra(String cdBarra){
		
		this.cdBarra = cdBarra;
		
		return cdBarra;
		
	}
	
	public void setNrLat(String lat){
		
		this.nrLat = lat;
		
	}
	
	public void setNrLong(String nrLong){

		this.nrLong = nrLong;

	}
	
	public void setTpUnidade(String tpUnidade) {
		this.tpUnidade = tpUnidade;
	}	

	public void setQtUnidade(float qtUnidade) {
		this.qtUnidade = qtUnidade;
	}
	
	
}
