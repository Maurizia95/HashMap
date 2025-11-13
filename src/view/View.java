package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import exceptions.MaggioreEtaException;
import model.Persona;

public class View {

	

	private Scanner input=new Scanner(System.in);


		
	
	public String leggiStringa(String s)
	{
		System.out.println(s);
		return input.nextLine();

	}



	public int leggiValore(String s)
	{
		int num1=0;
		boolean flag;

		do {
			flag=false;
			try {
				System.out.println(s);
				num1=Integer.parseInt(input.nextLine()); 

			} catch (NumberFormatException e) {
				flag=true;
				//e.printStackTrace();
				System.out.println("non hai scelto un numero!");
			}
		} while (flag);


		return num1;
	}



	public void formInit(Persona p)
	{
		boolean flag;
//		p.setId();
		p.setNome(leggiStringa("Inserisci il nome:"));
		p.setCognome(leggiStringa("Inserisci il cognome:"));
		p.setCf(leggiStringa("Inserisci il CF:"));	
		do {
			flag=false;
			LocalDate data=checkDataDiNascita("Inserisci la data di nascita nel seguente formato dd/MM/yyyy: ");
			try {
				if(checkMaggioreEta(data))
					p.setDataDiNascita(data);
			} catch (MaggioreEtaException e) {
				flag=true;
				System.out.println(e.getMessage());
			}
		}while(flag);



	}


	public LocalDate checkDataDiNascita(String s)
	{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String stringaData;

		while (true) {
			try {
				stringaData=leggiStringa(s);//chiedo di inserire una data da input
				return LocalDate.parse(stringaData, formatter);//verifichiamo se è una data valida e rispetta il formatter

			} catch (DateTimeParseException e) {//altrimenti lancia questa eccezione
				System.out.println(e.getMessage()+" Data di nascita non valida!");
			}

		}

	}


	public LocalDate leggiDataDiNascita(String s)
	{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String stringaData;
		LocalDate data=null;
		boolean flag;

		do {
			flag=false;
			try {
				stringaData=leggiStringa(s);//chiedo di inserire una data da input
				if(!stringaData.isEmpty())//se premo invio 
				{
					data=LocalDate.parse(stringaData, formatter);
					checkMaggioreEta(data);
				}
			} catch (DateTimeParseException e) {
				System.out.println(e.getMessage()+" Data di nascita non valida!");
				flag=true;
			}catch (MaggioreEtaException e) {
				System.out.println(e.getMessage());
				flag=true;
			}

		} while(flag);

		return data;	


	}



	public boolean checkMaggioreEta(LocalDate dataNascita) throws MaggioreEtaException //11/11/2009
	{
		LocalDate oggi=LocalDate.now(); //data odierna

		LocalDate dataLimite=oggi.minusYears(18); //11/11/2007

		//controllo se la dataNascita fornita viene prima della dataLimite

		//oggi.minusYears(dataNascita.getYear())

		if(dataNascita.isBefore(dataLimite)||dataNascita.isEqual(dataLimite))
			return true;
		else
			throw new MaggioreEtaException("Non hai 18 anni!");

	}



	public void stampaPersona(Persona p)
	{
		//System.out.println(p.getNome()+" "+p.getCognome()+" "+p.getEta());
		System.out.println(p);
	}


	public void msg(String s)
	{
		System.out.println(s);
	}


	public void stampaPersone(HashMap<Integer, Persona> map)
	{

		if(!map.isEmpty())
			for (Map.Entry<Integer, Persona> entry : map.entrySet()) 
				//map entry rappresenta una coppia ciave valore di una mapp
				//entrySet restituisce un insieme di coppie contenute nella mappa
				System.out.println("key:"+entry.getKey()+" "+entry.getValue());
		else
			System.out.println("Nessun elemento presente!");
		
		
	}


	public Persona formModifica(Persona p)
	{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String nome=leggiStringa("nome["+p.getNome()+"]:");
		nome=nome.isEmpty() ? p.getNome(): nome;

		String cognome=leggiStringa("cognome["+p.getCognome()+"]:");
		cognome=cognome.isEmpty() ? p.getCognome(): cognome;

		String cf=leggiStringa("cf["+p.getCf()+"]:");
		cf=cf.isEmpty() ? p.getCf(): cf;
		
			LocalDate data=leggiDataDiNascita("data di nascita["+p.getDataDiNascita().format(formatter)+"]:"); //posso avere o null o una nuova data
			data=data==null ? p.getDataDiNascita(): data;
			
			if(conferma("vuoi confermare le eventuali modifiche si/no: "))
				return new Persona(p.getId(),nome, cognome, data, cf);//recuperiamo il vecchio id eprchè non deve variare, deve essere associato sempre alla stessa chiave
			else
				return p;
			
	
	}



	public int menu()
	{

		System.out.println("MENU");
		System.out.println("1) Inserisci");
		System.out.println("2) stampaTutto");
		System.out.println("3) ricerca cf o key"); 
		System.out.println("4) elimina");
		System.out.println("5) modifica");
		System.out.println("0) ESCI");

		return leggiValore("Fai una scelta:");

	}



	public boolean conferma(String s)
	{
		if(leggiStringa(s).equalsIgnoreCase("si"))
			return true;
		else
			return false;

	}
	
	
}
