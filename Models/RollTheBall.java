package roll_the_ball.models;


import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


//Y aura l'instance de cette classe dans le main et là on fait appelle à ses méthodes !

public class RollTheBall implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = -5773966283051569999L;
	/**
	 *
	 */
	private HashMap<String,Utilisateur> utilisateurs=new HashMap<String, Utilisateur>();
	private LinkedHashMap<Integer,Niveau> niveaux;
	private static final String filepath="sauvegarde.ser";



	public void sauver_objets() {
		try{
			FileOutputStream l_fos = new FileOutputStream(filepath);
			ObjectOutputStream l_oos = new ObjectOutputStream (l_fos);
			l_oos.write(this.utilisateurs.size());//une variable globale qui compte le nombre d'objets présents dans le tableau d'utilisateur
			for(Map.Entry<String, Utilisateur> entry:utilisateurs.entrySet()) {
				l_oos.write(entry.getKey().length());
				l_oos.writeChars(entry.getKey());
				l_oos.write(entry.getValue().getMot_de_passe().length());
				l_oos.writeChars(entry.getValue().getMot_de_passe());
				l_oos.write(entry.getValue().getAge());
				l_oos.write(entry.getValue().getNiveaux().size());
			}
			l_oos.flush();
			l_oos.close();
		}catch(java.io.IOException er){er.printStackTrace();}
	}




	public HashMap<String,Utilisateur> charger_objets() throws ClassNotFoundException {
		HashMap<String,Utilisateur> u=new HashMap<String,Utilisateur>();
		try{
			FileInputStream l_fis = new FileInputStream(filepath);
			ObjectInputStream l_ois = new ObjectInputStream(l_fis);
			int nb_objets = (int) l_ois.read();
			System.out.println(nb_objets);
			int indice;
			String id = "";
			String motdepass="";
			int size;
			int age;
			for(indice=0;indice<nb_objets;indice++) {
				id="";
				motdepass="";
				int i=(int)l_ois.read();
				int k;
				for(k=0;k<i;k++) {
					id+=l_ois.readChar();
				}
				i=(int)l_ois.read();
				for(k=0;k<i;k++) {
					motdepass+=l_ois.readChar();
				}
				age=(int)l_ois.read();
				size=(int)l_ois.read();
				HashMap<Integer,Niveau> niveaux=new HashMap<Integer, Niveau>();
				for(k=0;k<size;k++) {
					niveaux.put(k,new Niveau(4,4,k));
				}
				Utilisateur ut=new Utilisateur(id,motdepass,age);
				ut.setNiveaux(niveaux);
				u.put(id,ut);
				System.out.println(id+" ,"+motdepass+" "+age);


			}

		}
		catch(java.io.IOException err)		{
			System.out.println("\n     erreur chargement\n\n");
		}
		return u;
	}


	public HashMap<String, Utilisateur> getUtilisateurs()
	{
		return utilisateurs;
	}

	public void setUtilisateurs(HashMap<String, Utilisateur> utilisateurs)
	{
		this.utilisateurs = utilisateurs;
	}


	public void initNiveaux()
	{
		Niveau niveau0,niveau1,niveau2,niveau3,niveau4;
		Plateau plateau;

		niveaux=new LinkedHashMap<>();

		// Initialisation du Niveau 0
		plateau=new Plateau(4,4);
		plateau.initPlateau(0);
		niveau0=new Niveau(plateau);

		// Initialisation du Niveau 1 
		plateau=new Plateau(4,4);
		plateau.initPlateau(1);
		niveau1=new Niveau(plateau);

		// Initialisation du Niveau 2
		plateau=new Plateau(4,4);
		plateau.initPlateau(2);
		niveau2=new Niveau(plateau);

		// Initialisation du Niveau 3
		plateau=new Plateau(4,4);
		plateau.initPlateau(03);
		niveau3=new Niveau(plateau);

		// Initialisation du Niveau 4
		plateau=new Plateau(4,4);
		plateau.initPlateau(4);
		niveau4=new Niveau(plateau);

		this.niveaux.put(0,niveau0);
		this.niveaux.put(1,niveau1);
		this.niveaux.put(2,niveau2);
		this.niveaux.put(3,niveau3);
		this.niveaux.put(4,niveau4);



	}


	public RollTheBall()
	{
		utilisateurs =new HashMap<String,Utilisateur>();
		initNiveaux();

	}
	public Boolean verifier(String nom_utilisateur) {
		return utilisateurs.containsKey(nom_utilisateur);
	}

	public Utilisateur retourneUtilisateur(String id)
	{
		if(this.utilisateurs.containsKey(id)){
			return this.utilisateurs.get(id);
		}
		return null;
	}

	public void ajouterUtilisateur(Utilisateur u) {
		utilisateurs.put(u.getId_utilisateur(), u);
	}

	public void removeutilisateur(Utilisateur u) {
		utilisateurs.remove(u.getId_utilisateur());
	}





}
