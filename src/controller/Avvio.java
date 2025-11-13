package controller;

import exceptions.ExistingCfException;
import model.Persona;
import model.PersonaCrud;
import view.View;

public class Avvio {


	static Persona p;
	static String cf;
	static int scelta=0;
	static boolean flag=true;
	static View v=new View();
	static PersonaCrud crud=new PersonaCrud();



	public static void main(String[] args) {



		do {
			scelta=v.menu(); //stampo il menu di avvio 

			switch (scelta) {
			case 1: 
				//Inserimento
				p=new Persona();//creiamo la persona,l'oggetto da gestire
				v.formInit(p);
				try {
					crud.checkCf(p); 
					crud.insertPersona(p);
				} catch (ExistingCfException e) {
					//e.printStackTrace();
					v.msg(e.getMessage());
					break;
				}

				break;
			case 2: 
				//Stampa tutto
				v.stampaPersone(crud.getPersona());//recupera le reference e la assegna a stampaPersone
				break;
			case 3: 
				//ricerca sia per cf sia per key
				p=ricerca();
				if(p!=null)
					v.stampaPersona(p);
				else
					v.msg("persona non presente");

				break;
			case 4: 
				//elimina
				p=ricerca();
				if(p!=null)
				{
					if(v.conferma("Sei sicuro di eliminare la seguente persona: " +p+" si/no"))
						crud.eliminaPersona(p);
					else
						v.msg("Eliminazione annullata");
				}else
					v.msg("persona non presente");
				break;
			case 5: 
				//modifica
				p=ricerca();
				if(p!=null)
				{
					//preparo la modifica
					Persona pnew=v.formModifica(p);

					if(!p.equals(pnew))
					{
						try {
							crud.checkCf(p,pnew);
							if(crud.modifica(p,pnew))
								v.msg("Modifica eseguita!");
							else
								v.msg("Modifica annullata,qualcosa è andato storto!");
						}catch(ExistingCfException e) {
							System.out.println(e.getMessage());
						}
					}else
						v.msg("Modifica annullata, dati uguali");

				}else
					v.msg("CF non trovato!");
				break;


			case 0: 
				//ESCI
				flag=false;
				v.msg("Fine!");
				break;

			default:
				v.msg("Scelta non valida!");

			}

		} while (flag);


	}


	public static Persona ricerca()
	{
		Persona p=null; //creo una variabile p di tipo persona
		//al momento non punta ad alcun oggetto in memoria
		boolean flag;//indica se è stata trovata una persona
		int scelta=0;

		do {
			scelta=v.leggiValore("Ricerca Persona\n 1)per CF\n 2)Key ");
			flag=false;
			switch(scelta) {
			case 1:
				//ricerca cf
				cf=v.leggiStringa("Inserisci il cf della persona da cercare");
				p=crud.ricercaCf(cf);
				break;

			case 2:
				//ricerca Key
				p=crud.ricercaKey(v.leggiValore("Inserisci la key da ricercare: "));
				break;

			default:
				v.msg("scelta non valida!");
				flag=true;
				break;
			}

		} while (flag);

		return p;

	}

}