/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Saliou
 */
@Entity
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idComment;

    public Long getId() {
        return idComment;
    }

    public void setId(Long id) {
        this.idComment = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bid",
    joinColumns = {
        @JoinColumn(name = "idUser")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "idArticle")
    })
//    private Set<Book> bids;

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Article[ id=" + idComment + " ]";
    }
}
