package fr.imie.videodb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "exemplaire" database table.
 * 
 */
@Entity
@Table(name="exemplaire")
@NamedQuery(name="Exemplaire.findAll", query="SELECT e FROM Exemplaire e")
public class Exemplaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="lib")
	private String lib;

	//bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name = "video_id", referencedColumnName = "id")
	private Video video;

	public Exemplaire() {
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


	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}