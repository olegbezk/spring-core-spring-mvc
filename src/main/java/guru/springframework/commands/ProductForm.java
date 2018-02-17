package guru.springframework.commands;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductForm {

    private Long id;

    private Long version;

    @NotEmpty
    @Size(min = 5, max = 200)
    private String description;

    @NotNull
    @Min(0)
    @Max(5000)
    private BigDecimal price;

    @NotEmpty
    @URL
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

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
