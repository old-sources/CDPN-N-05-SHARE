package fr.imie.videodb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "video" database table.
 * 
 */
@Entity
@Table(name="video")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="datesortie")
	private Date datesortie;

	@Column(name="duree")
	private Integer duree;

	@Column(name="libelle")
	private String libelle;

	//bi-directional many-to-one association to Exemplaire
	@OneToMany(mappedBy="video")
	private List<Exemplaire> exemplaires;

	//bi-directional many-to-one association to Auteur
	@ManyToOne
	@JoinColumn(name = "auteur_id", referencedColumnName = "id")
	private Auteur auteur;

	public Video() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getDatesortie() {
		return this.datesortie;
	}

	public void setDatesortie(Date datesortie) {
		this.datesortie = datesortie;
	}

	public Integer getDuree() {
		return this.duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Exemplaire> getExemplaires() {
		return this.exemplaires;
	}

	public void setExemplaires(List<Exemplaire> exemplaires) {
		this.exemplaires = exemplaires;
	}

	public Exemplaire addExemplaire(Exemplaire exemplaire) {
		getExemplaires().add(exemplaire);
		exemplaire.setVideo(this);

		return exemplaire;
	}

	public Exemplaire removeExemplaire(Exemplaire exemplaire) {
		getExemplaires().remove(exemplaire);
		exemplaire.setVideo(null);

		return exemplaire;
	}

	public Auteur getAuteur() {
		return this.auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

}