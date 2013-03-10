/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Saliou
 */
@Entity
@NamedQueries({
    @NamedQuery(name="", query=""),
    @NamedQuery(name="", query="")
})

public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long idProductType;
    private String title;
    private String description;
    private int price;
    private int score;
    private byte[] picture;
    private Product type;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(Long id) {
        this.idProductType = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Lob
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    @OneToOne (cascade= CascadeType.ALL, mappedBy="productType")
    public Product getType() {
        return type;
    }

    public void setType(Product type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductType != null ? idProductType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductType)) {
            return false;
        }
        ProductType other = (ProductType) object;
        if ((this.idProductType == null && other.idProductType != null) || (this.idProductType != null && !this.idProductType.equals(other.idProductType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Product[ id=" + idProductType + " ]";
    }
    
}
