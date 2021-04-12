package by.training.jwd.finalproject.entity;

import java.io.Serializable;

/**
 * The {@code Image} class represents Image entity.
 * 
 * @author Sergei Klimovich
 * @version 1.0
 */
public class Image implements Serializable {

	private static final long serialVersionUID = -8577059716883244621L;
	private Integer imageId;
	private String name;

	/**
	 * Instantiates a new Image.
	 */
	public Image() {

	}

	/**
	 * Instantiates a new Image.
	 *
	 * @param imageId a image index
	 * @param name    a name
	 * 
	 */
	public Image(Integer imageId, String name) {
		super();
		this.imageId = imageId;
		this.name = name;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Image other = (Image) obj;
		if (imageId == null) {
			if (other.imageId != null)
				return false;
		} else if (!imageId.equals(other.imageId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[imageId=" + imageId + ", name=" + name + "]";
	}

}
