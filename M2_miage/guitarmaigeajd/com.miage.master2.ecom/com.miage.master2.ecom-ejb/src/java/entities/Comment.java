package entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author Saliou 
 * Cette classe represente un commentaire d'un utilisateur sur un
 * produit.
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllCommentsByUserId", query = "select c from comment c where c.user.id=:userId"),
    @NamedQuery(name = "findCommentById", query = "select c from comment c where c.idComment=:idComment"),
    @NamedQuery(name = "findAllCommentByUserAndArticleId", query = "select c from comment c where c.user.idUser=:idUser"
    + "and c.article.idArticle=:idArticle"),
    @NamedQuery(name = "findAllCommentByidArticle", query = "select c from comment c where c.article.idArticle=:idArticle")
})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idComment;
    private User user;
    private ProductType product;
    private String description;
    private Date creatDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long id) {
        this.idComment = id;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setProduct(ProductType article) {
        this.product = article;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Comment[ id=" + idComment + " ]";
    }
}
