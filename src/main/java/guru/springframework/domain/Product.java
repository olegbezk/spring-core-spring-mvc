package guru.springframework.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product extends AbstractDomainClass {

    private String description;

    private BigDecimal price;

    private String imageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
