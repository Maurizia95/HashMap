package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Persona {

	private Integer id;
	private String nome;
	private String cognome;
	private LocalDate dataDiNascita;
	private String cf;
	
	static int cont=0;//comune a tutte le istanze
	
	//set e get
	public Integer getId() {
		return id;
	}
	public void setId() {
		cont++;
		this.id = cont;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public int getEta(LocalDate dataDiNascita)
	{
		
		Period diff=Period.between(dataDiNascita,LocalDate.now());
		return diff.getYears();
		
	}
	
	public Persona()
	{
		
	}
	
	
	public Persona(Integer id,String nome,String cognome,LocalDate dataDiNascita, String cf) {
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
		this.dataDiNascita=dataDiNascita;
		this.cf=cf;
	}
	
	//tre metodi fondamentali ereditati da Object:
	@Override
	public String toString() { //rappresenta l'oggetto come una stringa
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy"); //creo un formatter per mostrare la data nel formato gg/MM/aaaa
		
		return "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita.format(formatter)+ 
		           ", eta=" + getEta(dataDiNascita) + ",eta="+getEta(dataDiNascita)+", cf=" + cf;
		//restituisce una stringa con tutte le informazioni principali della persona
	}
	
	
	@Override
	public int hashCode() { 
		return Objects.hash( nome, cognome, dataDiNascita, cf);
		//usa Object.hash() per calcolare un numero che rappresenta l'oggetto, in base ai suoi campi
		//restiuisce tale valore numerico
	}
	
	@Override
	public boolean equals(Object obj) { 
	    // Confronta l'oggetto corrente con quello passato come parametro 
	    // e verifica se sono uguali in base ai loro valori

	    if (this == obj) 
	        return true; // Se i due riferimenti puntano allo stesso oggetto in memoria

	    if (obj == null)
	        return false; // Non sono uguali perché l'altro oggetto è null

	    if (getClass() != obj.getClass())
	        return false; // Non appartengono alla stessa classe

	    Persona other = (Persona) obj; // Effettua il cast

	    // Confronta i campi uno per uno usando Objects.equals (con la S!)
	    return Objects.equals(id, other.id)
	        && Objects.equals(nome, other.nome)
	        && Objects.equals(cognome, other.cognome)
	        && Objects.equals(dataDiNascita, other.dataDiNascita)
	        && Objects.equals(cf, other.cf);
	}
	
	
	
}
