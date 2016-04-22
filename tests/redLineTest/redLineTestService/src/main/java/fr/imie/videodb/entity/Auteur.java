package fr.imie.videodb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "auteur" database table.
 * 
 */
@Entity
@Table(name="auteur")
@NamedQuery(name="Auteur.findAll", query="SELECT a FROM Auteur a")
public class Auteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="lib")
	private String lib;

	//bi-directional many-to-one association to Video
	@OneToMany(mappedBy="auteur")
	private List<Video> videos;

	public Auteur() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLib() {
		return this.lib;
	}

	public void setLib(String lib) {
		this.lib = lib;
	}

	public List<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setAuteur(this);

		return video;
	}

	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setAuteur(null);

		return video;
	}

}