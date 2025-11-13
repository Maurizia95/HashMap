package model;

import java.util.HashMap;

import exceptions.ExistingCfException;

public class PersonaCrud {

	//HashMap è una coppia chiave-valore
	private HashMap<Integer, Persona> map= new HashMap<Integer, Persona>();
	
	
	public void insertPersona(Persona p)
	{
		p.setId();
		map.put(p.getId(), p);
	}//inserisce la persona con la sua chiave id
	
	
	public HashMap<Integer, Persona> getPersona()
	{
		return map;
		
	}
	
	/*public HashMap<Integer, Persona> getPersona(){
		return map;
	}
	*/
	
	public Persona ricercaCf(String cf)
	{
		for(Persona p: map.values()) //itera su tutti gli elementi, restituisce una collezione di tutti i valori contenuti nella mappa
			if(p.getCf().equals(cf))
				return p;
	
	
	return null;
	}
	
	public Persona ricercaKey(int key) { 
		return map.get(key);
	}
	
	
	public void eliminaPersona(Persona p)
	{
			map.remove(p.getId());
			
	}
	
	
	
	//verificare se il cf esiste già!
	public void checkCf(Persona p) throws ExistingCfException
	{
		for (Persona persona : map.values()) 
			if(p.getCf().equals(persona.getCf()))
					throw new ExistingCfException("Errore: Persona con CF gi� presente!");	
		
		
	}

	public void checkCf(Persona p, Persona pnew) throws ExistingCfException
	{
		for (Persona persona : map.values()) 
			                                                
			if(persona.getCf().equals(pnew.getCf())&& !pnew.getCf().equals(p.getCf()) )
					throw new ExistingCfException("Errore: Persona con CF gi� presente!");	
	}
	
	
	public boolean modifica(Persona p, Persona pnew)
	{
		
		return map.replace(p.getId(), p, pnew);
		
	}

}
