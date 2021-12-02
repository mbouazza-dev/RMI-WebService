package fr.ifshare;

import fr.sharedclasses.Product;

public class BasketItem {
	private final int idProduct;
	private final int idAnnounce;
	private final Product product;
	
	public BasketItem(int idAnnounce, Product product) {
		this.idProduct = product.getId();
		this.idAnnounce = idAnnounce;
		this.product = product;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public int getIdAnnounce() {
		return idAnnounce;
	}

	public Product getProduct() {
		return product;
	}
	
	
}
