package entityBean;

import javax.persistence.Embeddable;


@Embeddable
public class LigneCommandePK {
	private int idCommande;
	private int idArticle;
	public LigneCommandePK() {
		super();
	}
	public LigneCommandePK(int idCommande, int idArticle) {
		super();
		this.idCommande = idCommande;
		this.idArticle = idArticle;
	}
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArticle;
		result = prime * result + idCommande;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LigneCommandePK other = (LigneCommandePK) obj;
		if (idArticle != other.idArticle)
			return false;
		if (idCommande != other.idCommande)
			return false;
		return true;
	}
	
	

}
