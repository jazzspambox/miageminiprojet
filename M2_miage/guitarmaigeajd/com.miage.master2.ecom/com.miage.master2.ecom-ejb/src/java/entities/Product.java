/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Saliou
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findProductById", query = "select p from product p where p.id=:id"),
    @NamedQuery(name = "findProductByProductType", query = "select p from produit p where p.id_category=:id_type")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idProduct;
    private String title;
    private String description;
    private ProductType type;
    private Store store;
    private List<Comment> comments;
    private List<Bid> bids;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long id) {
        this.idProduct = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProductType[ id=" + idProduct + " ]";
    }
}
