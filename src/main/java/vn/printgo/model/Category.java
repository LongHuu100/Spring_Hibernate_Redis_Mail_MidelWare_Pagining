package vn.printgo.model;
// Generated Oct 19, 2019 1:13:00 PM by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({
    @NamedNativeQuery(
        name = "getProductById",
        query = "select * from product \n" + 
    		"inner join product_category on product_category.product_id=product.id \n" + 
    		"where product_category.category_id=:cateId limit :pLimit OFFSET :pOffset",
    	resultClass = Product.class
    ),
    @NamedNativeQuery(
        name = "countProduct",
        query = "select count(product_id) from product_category where category_id = :cateId"
    )
})

@Entity
@Table(name = "category", catalog = "printgo")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = -1970625853486904923L;
	private Integer id;
	private String name;
	private Long amazon;
	private Integer status;
	private Integer percent;
	private String icon;
	private Integer isDisplayIcon;
	private Integer createdAt;
	private Integer updatedAt;
	private Integer parentId;
	private Integer pageId;
	private String faqsId;
	private String seoTitle;
	private String seoDescription;
	private String seoKeyword;
	private Integer countItems;
	private String seoContent;
	private Set<Product> products = new HashSet<Product>(0);

	public Category() {
	}

	public Category(String name, Long amazon, Integer status, Integer percent, String icon, Integer isDisplayIcon,
			Integer createdAt, Integer updatedAt, Integer parentId, Integer pageId, String faqsId, String seoTitle,
			String seoDescription, String seoKeyword, Integer countItems, String seoContent, Set<Product> products) {
		this.name = name;
		this.amazon = amazon;
		this.status = status;
		this.percent = percent;
		this.icon = icon;
		this.isDisplayIcon = isDisplayIcon;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parentId = parentId;
		this.pageId = pageId;
		this.faqsId = faqsId;
		this.seoTitle = seoTitle;
		this.seoDescription = seoDescription;
		this.seoKeyword = seoKeyword;
		this.countItems = countItems;
		this.seoContent = seoContent;
		this.products = products;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "amazon")
	public Long getAmazon() {
		return this.amazon;
	}

	public void setAmazon(Long amazon) {
		this.amazon = amazon;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "percent")
	public Integer getPercent() {
		return this.percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	@Column(name = "icon")
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "is_display_icon")
	public Integer getIsDisplayIcon() {
		return this.isDisplayIcon;
	}

	public void setIsDisplayIcon(Integer isDisplayIcon) {
		this.isDisplayIcon = isDisplayIcon;
	}

	@Column(name = "created_at")
	public Integer getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at")
	public Integer getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Integer updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "page_id")
	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	@Column(name = "faqs_id")
	public String getFaqsId() {
		return this.faqsId;
	}

	public void setFaqsId(String faqsId) {
		this.faqsId = faqsId;
	}

	@Column(name = "seo_title")
	public String getSeoTitle() {
		return this.seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	@Column(name = "seo_description")
	public String getSeoDescription() {
		return this.seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	@Column(name = "seo_keyword")
	public String getSeoKeyword() {
		return this.seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	@Column(name = "count_items")
	public Integer getCountItems() {
		return this.countItems;
	}

	public void setCountItems(Integer countItems) {
		this.countItems = countItems;
	}

	@Column(name = "seo_content", length = 65535)
	public String getSeoContent() {
		return this.seoContent;
	}

	public void setSeoContent(String seoContent) {
		this.seoContent = seoContent;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_category", catalog = "printgo", joinColumns = {
			@JoinColumn(name = "category_id", nullable = false, updatable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "product_id", nullable = false, updatable = false) })
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
