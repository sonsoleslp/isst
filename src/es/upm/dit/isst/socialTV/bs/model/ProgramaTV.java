package es.upm.dit.isst.socialTV.bs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * DefiniciÛn de la Tabla ProgramaTV
 * 
 * @author Sonsoles
 *
 */
@Entity
public class ProgramaTV implements Serializable {

	private static final long serialVersionUID = 01L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	private String titulo;
	private String episodeCode;
	private String fechaInicio;
	private String fechaFin;
	private String hashtag;
	private int count;
	private long lastId;
	private String lastTweet;
	
    private int Alava = 0;
	private int Alicante = 0;
	private int Almeria = 0;
	private int Asturias = 0;
	private int Albacete = 0;
	private int Avila = 0;
	private int Badajoz = 0;
	private int Barcelona = 0;
	private int Burgos = 0;
	private int Caceres = 0;
	private int Cadiz = 0;
	private int Cantabria = 0;
	private int Castellon = 0;
	private int CiudadReal = 0;
	private int Cordoba = 0;
	private int Coruna = 0;
	private int Cuenca = 0;
	private int Girona = 0;
	private int Granada = 0;
	private int Guadalajara = 0;
	private int Guipuzcoa = 0;
	private int Huelva = 0;
	private int Huesca = 0;
	private int Baleares = 0;
	private int Jaen = 0;
	private int Leon = 0;
	private int Lleida = 0;
	private int Lugo = 0;
	private int Madrid = 0;
	private int Malaga = 0;
	private int Murcia = 0;
	private int Navarra = 0;
	private int Palencia = 0;
	private int LasPalmas = 0;
	private int Pontevedra = 0;
	private int Ourense = 0;
	private int LaRioja = 0;
	private int Salamanca = 0;
	private int Segovia = 0;
	private int Sevilla = 0;
	private int Soria = 0;
	private int Tarragona = 0;
	private int Tenerife = 0;
	private int Teruel = 0;
	private int Toledo = 0;
	private int Valencia = 0;
	private int Valladolid = 0;
	private int Vizcaya = 0;
	private int Zamora = 0;
	private int Zaragoza = 0;

	

	
	/**
	 * Constructor
	 * @param titulo
	 * @param episodeCode
	 * @param fechainicio
	 * @param horaInicio
	 * @param duracion
	 * @param hashtag
	 */
	public ProgramaTV(String titulo, String episodeCode, String fechaInicio, String fechaFin, String hashtag){
		this.titulo = titulo;
		this.episodeCode = episodeCode;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.hashtag = hashtag;
		this.count = 0;
		this.lastId = -1;
		this.lastTweet = "";
	}

	@Override
	public String toString() {
		return "ProgramaTV [primaryKey=" + primaryKey + ", titulo=" + titulo + ", episodeCode=" + episodeCode
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", hashtag=" + hashtag + ", count="
				+ count + ", lastId=" + lastId + ", lastTweet=" + lastTweet + ", Alava=" + Alava + ", Alicante="
				+ Alicante + ", Almeria=" + Almeria + ", Asturias=" + Asturias + ", Albacete=" + Albacete + ", Avila="
				+ Avila + ", Badajoz=" + Badajoz + ", Barcelona=" + Barcelona + ", Burgos=" + Burgos + ", Caceres="
				+ Caceres + ", Cadiz=" + Cadiz + ", Cantabria=" + Cantabria + ", Castellon=" + Castellon
				+ ", CiudadReal=" + CiudadReal + ", Cordoba=" + Cordoba + ", Coruna=" + Coruna + ", Cuenca=" + Cuenca
				+ ", Girona=" + Girona + ", Granada=" + Granada + ", Guadalajara=" + Guadalajara + ", Guipuzcoa="
				+ Guipuzcoa + ", Huelva=" + Huelva + ", Huesca=" + Huesca + ", Baleares=" + Baleares + ", Jaen=" + Jaen
				+ ", Leon=" + Leon + ", Lleida=" + Lleida + ", Lugo=" + Lugo + ", Madrid=" + Madrid + ", Malaga="
				+ Malaga + ", Murcia=" + Murcia + ", Navarra=" + Navarra + ", Palencia=" + Palencia + ", LasPalmas="
				+ LasPalmas + ", Pontevedra=" + Pontevedra + ", Ourense=" + Ourense + ", LaRioja=" + LaRioja
				+ ", Salamanca=" + Salamanca + ", Segovia=" + Segovia + ", Sevilla=" + Sevilla + ", Soria=" + Soria
				+ ", Tarragona=" + Tarragona + ", Tenerife=" + Tenerife + ", Teruel=" + Teruel + ", Toledo=" + Toledo
				+ ", Valencia=" + Valencia + ", Valladolid=" + Valladolid + ", Vizcaya=" + Vizcaya + ", Zamora="
				+ Zamora + ", Zaragoza=" + Zaragoza + "]";
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getLastTweet() {
		return lastTweet;
	}

	public void setLastTweet(String lastTweet) {
		this.lastTweet = lastTweet;
	}

	public long getLastId() {
		return lastId;
	}

	public void setLastId(long lastId) {
		this.lastId = lastId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getHashtag(){
		return this.hashtag;
	}
	
	public void setHashtag(String hashtag){
		this.hashtag = hashtag;
	}
	
	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public int getProvince(String province){
		switch(province){
		case "Alava":
			return this.Alava;
		case "Alicante":
		    return this.Alicante;
		case "Almeria":
			return this.Almeria;
		case "Asturias":
			return this.Asturias;
		case "Albacete":
			return this.Albacete;
		case "Avila":
			return this.Avila;
		case "Badajoz":
			return this.Badajoz;
		case "Barcelona":
			return this.Barcelona;
		case "Burgos":
			return this.Burgos;
		case "Caceres":
			return this.Caceres;
		case "Cadiz":
			return this.Cadiz;
		case "Cantabria":
		case "Santander":
			return this.Cantabria;
		case "Castellon":
		case "CastellÛ":
		case "CastellÛn":
			return this.Castellon;
		case "Ciudad Real":
			return this.CiudadReal;
		case "Cordoba":
			return this.Cordoba;
		case "Coruna":
		case "A Coruna":
		case "CoruÒa":
		case "A CoruÒa":
			return this.Coruna;
		case "Cuenca":
			return this.Cuenca;
		case "Girona":
		case "Gerona":
			return this.Girona;
		case "Granada":
			return this.Granada;
		case "Guadalajara":
			return this.Guadalajara;
		case "Guipuzcoa":
		case "Gipuzkoa":
			return this.Guipuzcoa;
		case "Huelva":
			return this.Huelva;
		case "Huesca":
			return this.Huesca;
		case "Baleares":
		case "Illes Balears":
			return this.Baleares;
		case "Jaen":
			return this.Jaen;
		case "Leon":
			return this.Leon;
		case "Lleida":
		case "Lerida":
			return this.Lleida;
		case "Lugo":
			return this.Lugo;
		case "Madrid":
			return this.Madrid;
		case "Malaga":
			return this.Malaga;
		case "Murcia":
			return this.Murcia;
		case "Navarra":
			return this.Navarra;
		case "Palencia":
			return this.Palencia;
		case "Las Palmas":
		case "Las Palmas de Gran Canaria":
		case "Gran Canaria":
		case "Las Palmas De Gran Canaria":
			return this.LasPalmas;
		case "Pontevedra":
			return this.Pontevedra;
		case "Ourense":
		case "Orense":
			return this.Ourense;
		case "La Rioja":
		case "LogroÒo":
			return this.LaRioja;
		case "Salamanca":
			return this.Salamanca;
		case "Segovia":
			return this.Segovia;
		case "Sevilla":
			return this.Sevilla;
		case "Soria":
			return this.Soria;
		case "Tarragona":
			return this.Tarragona;
		case "Santa Cruz De Tenerife":
		case "Santa Cruz de Tenerife":
		case "Tenerife":
			return this.Tenerife;
		case "Teruel":
			return this.Teruel;
		case "Toledo":
			return this.Toledo;
		case "Valencia":
			return this.Valencia;
		case "Valladolid":
			return this.Valladolid;
		case "Vizcaya":
		case "Bizkaia":
			return this.Vizcaya;
		case "Zamora":
			return this.Zamora;
		case "Zaragoza":
			return this.Zaragoza;
		default:
			return 0;
		}
	
	}
	public void setProvince(String province){
		// Quitar acentos
		String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«Ò—";
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCnN";
	    for (int i=0; i<original.length(); i++) {
	        province = province.replace(original.charAt(i), ascii.charAt(i));
	    }
	    
		switch(province){
		case "Alava":
			this.Alava++; break;
		case "Alicante":
		    this.Alicante++; break;
		case "Almeria":
			this.Almeria++; break;
		case "Asturias":
			this.Asturias++; break;
		case "Albacete":
			this.Albacete++; break;
		case "Avila":
			this.Avila++; break;
		case "Badajoz":
			this.Badajoz++; break;
		case "Barcelona":
			this.Barcelona++; break;
		case "Burgos":
			this.Burgos++; break;
		case "Caceres":
			this.Caceres++; break;
		case "Cadiz":
			this.Cadiz++; break;
		case "Cantabria":
		case "Santander":
			this.Cantabria++; break;
		case "Castellon":
		case "CastellÛn":
		case "CastellÛ":
			this.Castellon++; break;
		case "Ciudad Real":
			this.CiudadReal++; break;
		case "Cordoba":
			this.Cordoba++; break;
		case "Coruna":
		case "A Coruna":
		case "CoruÒa":
		case "A CoruÒa":
			this.Coruna++; break;
		case "Cuenca":
			this.Cuenca++; break;
		case "Girona":
		case "Gerona":
			this.Girona++; break;
		case "Granada":
			this.Granada++; break;
		case "Guadalajara":
			this.Guadalajara++; break;
		case "Guipuzcoa":
		case "Gipuzkoa":
			this.Guipuzcoa++; break;
		case "Huelva":
			this.Huelva++; break;
		case "Huesca":
			this.Huesca++; break;
		case "Baleares":
		case "Islas Baleares":
		case "Illes Balears":
			this.Baleares++; break;
		case "Jaen":
			this.Jaen++; break;
		case "Leon":
			this.Leon++; break;
		case "Lleida":
		case "Lerida":
			this.Lleida++; break;
		case "Lugo":
			this.Lugo++; break;
		case "Madrid":
			this.Madrid++; break;
		case "Malaga":
			this.Malaga++; break;
		case "Murcia":
			this.Murcia++; break;
		case "Navarra":
			this.Navarra++; break;
		case "Palencia":
			this.Palencia++; break;
		case "Las Palmas":
		case "Las Palmas de Gran Canaria":
		case "Gran Canaria":
		case "Las Palmas De Gran Canaria":
			this.LasPalmas++; break;
		case "Pontevedra":
			this.Pontevedra++; break;
		case "Ourense":
		case "Orense":
			this.Ourense++; break;
		case "La Rioja":
		case "LogroÒo":
			this.LaRioja++; break;
		case "Salamanca":
			this.Salamanca++; break;
		case "Segovia":
			this.Segovia++; break;
		case "Sevilla":
			this.Sevilla++; break;
		case "Soria":
			this.Soria++; break;
		case "Tarragona":
			this.Tarragona++; break;
		case "Santa Cruz De Tenerife":
		case "Santa Cruz de Tenerife":
		case "Tenerife":
			this.Tenerife++; break;
		case "Teruel":
			this.Teruel++; break;
		case "Toledo":
			this.Toledo++; break;
		case "Valencia":
			this.Valencia++; break;
		case "Valladolid":
			this.Valladolid++; break;
		case "Vizcaya":
		case "Bizkaia":
			this.Vizcaya++; break;
		case "Zamora":
			this.Zamora++; break;
		case "Zaragoza":
			this.Zaragoza++; break;
		default:
			
		}
	
	}
}