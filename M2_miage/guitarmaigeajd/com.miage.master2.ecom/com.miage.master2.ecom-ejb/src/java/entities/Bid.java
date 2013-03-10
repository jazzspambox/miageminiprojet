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
    @NamedQuery(name="findBidByProductId",query="select b from bid b where b.id_product=:id_product"),
    @NamedQuery(name="findBidByUserId", query="select b from bid b where b.id_user=:id_user ")
        
})
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idBid;
    private User user;
    private ProductType product;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getIdBid() {
        return idBid;
    }

    public void setIdBid(Long id) {
        this.idBid = id;
    }
 @ManyToOne
    @JoinColumn(name = "id_user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "id_product")
    public ProductType getProduct() {
        return product;
    }

    public void setProduct(ProductType product) {
        this.product = product;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBid != null ? idBid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.idBid == null && other.idBid != null) || (this.idBid != null && !this.idBid.equals(other.idBid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Bid[ id=" + idBid + " ]";
    }
    
}
