package by.training.jwd.finalproject.entity;

import java.io.Serializable;

/**
 * The {@code Product} class represents Product entity.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 2322256823860452603L;
	private Integer productId;
	private String title;
	private double price;
	private String facture;
	private String applicationArea;
	private Image image;

	/**
	 * Instantiates a new Product.
	 */
	public Product() {
	}

	/**
	 * Instantiates a new Product.
	 *
	 * @param productId       a product index
	 * @param title           a title
	 * @param surname         a surname
	 * @param price           a price
	 * @param facture         a facture
	 * @param applicationArea a applicationArea
	 * @param image           a image
	 * 
	 */
	public Product(Integer productId, String title, double price, String facture, String applicationArea, Image image) {
		super();
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.facture = facture;
		this.applicationArea = applicationArea;
		this.image = image;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFacture() {
		return facture;
	}

	public void setFacture(String facture) {
		this.facture = facture;
	}

	public String getApplicationArea() {
		return applicationArea;
	}

	public void setApplicationArea(String applicationArea) {
		this.applicationArea = applicationArea;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationArea == null) ? 0 : applicationArea.hashCode());
		result = prime * result + ((facture == null) ? 0 : facture.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Product other = (Product) obj;
		if (applicationArea == null) {
			if (other.applicationArea != null)
				return false;
		} else if (!applicationArea.equals(other.applicationArea))
			return false;
		if (facture == null) {
			if (other.facture != null)
				return false;
		} else if (!facture.equals(other.facture))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[productId=" + productId + ", title=" + title + ", price=" + price + ", facture="
				+ facture + ", applicationArea=" + applicationArea + ", image=" + image + "]";
	}

}