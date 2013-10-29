package entityBean;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class LigneFournisseurPK implements Serializable {
	private int idProduit;
	private int idFournisseur;
	
	public LigneFournisseurPK() {
		super();
	}
	public LigneFournisseurPK(int idProduit, int idFournisseur) {
		super();
		this.idProduit = idProduit;
		this.idFournisseur = idFournisseur;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public int getIdFournisseur() {
		return idFournisseur;
	}
	public void setIdFournisseur(int idFournisseur) {
		this.idFournisseur = idFournisseur;
	}
	
}
