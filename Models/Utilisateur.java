package roll_the_ball.models;
import java.security.*;

import roll_the_ball.views.Fen_Partie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

public class Utilisateur implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6701369995001858770L;
	private String id_utilisateur ;
	private String mot_de_passe;
	private int age;
	private HashMap<Integer, Niveau> niveaux;
	private int[] score;


	public Utilisateur(String id) {
		id_utilisateur=id;
	}

	public Utilisateur(String id_utilisateur, String mot_de_passe, int age, HashMap<Integer, Niveau> niveaux) throws Exception
	{
		this.id_utilisateur = id_utilisateur;
		this.mot_de_passe = hashStr(mot_de_passe);
		this.age = age;
		this.niveaux = niveaux;
		score= new int[10];  // 5 niveaux
	}


	public String hashStr(String str0) throws Exception
	{

		byte[] bytesOfMessage = str0.getBytes("UTF-8");

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		String str = new String(thedigest, "UTF-8");


		return str;
	}


	public void rinitialiserMDP(String mot) throws Exception
	{
		mot=hashStr(mot);
		this.mot_de_passe=mot;

	}
	public boolean verifierMotDePasse(String md2) throws Exception {
		md2=hashStr(md2);
		return md2.equals(this.mot_de_passe);
	}

	public Utilisateur(String id_utilisateur, String mot_de_passe, int age)
	{
		this.id_utilisateur = id_utilisateur;
		this.mot_de_passe = mot_de_passe;
		this.age = age;
	}


	public String getId_utilisateur() {
		return id_utilisateur;
	}


	public String getMot_de_passe() {
		return mot_de_passe;
	}


	public int getAge() {
		return age;
	}



	public HashMap<Integer, Niveau>getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(HashMap<Integer, Niveau> niveaux) {		this.niveaux = niveaux;
	}
}
