package fr.imie.videodb.dto;

public class ExemplaireDTO {

	private Integer id;
	private String numSerie;
	private FilmDTO filmDTO;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the numSerie
	 */
	public String getNumSerie() {
		return numSerie;
	}
	/**
	 * @param numSerie the numSerie to set
	 */
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	/**
	 * @return the filmDTO
	 */
	public FilmDTO getFilmDTO() {
		return filmDTO;
	}
	/**
	 * @param filmDTO the filmDTO to set
	 */
	public void setFilmDTO(FilmDTO filmDTO) {
		this.filmDTO = filmDTO;
	}
	
	
}

