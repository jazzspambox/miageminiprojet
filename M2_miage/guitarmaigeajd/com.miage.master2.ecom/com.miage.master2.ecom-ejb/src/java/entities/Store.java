/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Saliou
 */
@Entity
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idStore;
    private String name;
    private String adresse;
    private int latitude;
    private int longititude;
    private List<Product> products;
    private Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getIdStore() {
        return idStore;
    }

    public void setIdStore(Long id) {
        this.idStore = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongititude() {
        return longititude;
    }

    public void setLongititude(int longititude) {
        this.longititude = longititude;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "store")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        } else {
            //Exception
        }

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStore != null ? idStore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.idStore == null && other.idStore != null) || (this.idStore != null && !this.idStore.equals(other.idStore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Store[ id=" + idStore + " ]";
    }
}
