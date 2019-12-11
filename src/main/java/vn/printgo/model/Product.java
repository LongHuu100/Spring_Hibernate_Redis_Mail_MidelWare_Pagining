package vn.printgo.model;
// Generated Oct 19, 2019 1:13:00 PM by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import vn.printgo.components.AmazonUtil;

@Entity
@Table(name = "product", catalog = "printgo")
public class Product implements java.io.Serializable {
	
	private static final long serialVersionUID = -451308823437966857L;
	private Integer id;
	private Integer typeId;
	private String providerCode;
	private String giftcode;
	private String image;
	private String name;
	private String title;
	private String desc;
	private String keyword;
	private String shortName;
	private Integer createdAt;
	private Integer updatedAt;
	private Integer publishedAt;
	private BigDecimal weight;
	private String content;
	private String social;
	private String search;
	private Integer priceRef;
	private Integer priceDesign;
	private Integer status;
	private Integer statusImage;
	private Integer statusImageUpdatedAt;
	private Integer statusContent;
	private Integer statusContentUpdatedAt;
	private String unit;
	private Integer countSold;
	private String tags;
	private String pagesId;
	private String faqId;
	private String filePrint;
	private String sizeExtend;
	private Set<Category> categories = new HashSet<Category>(0);
	
	public Product() {
	}

	public Product(Integer typeId, String providerCode, String giftcode, String image, String name, String title,
			String desc, String keyword, String shortName, Integer createdAt, Integer updatedAt, Integer publishedAt,
			BigDecimal weight, String content, String social, String search, Integer priceRef, Integer priceDesign,
			Integer status, Integer statusImage, Integer statusImageUpdatedAt, Integer statusContent,
			Integer statusContentUpdatedAt, String unit, Integer countSold, String tags, String pagesId, String faqId,
			String filePrint, String attri, String sizeExtend, Set<Category> categories) {
		this.typeId = typeId;
		this.providerCode = providerCode;
		this.giftcode = giftcode;
		this.image = image;
		this.name = name;
		this.title = title;
		this.desc = desc;
		this.keyword = keyword;
		this.shortName = shortName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.publishedAt = publishedAt;
		this.weight = weight;
		this.content = content;
		this.social = social;
		this.search = search;
		this.priceRef = priceRef;
		this.priceDesign = priceDesign;
		this.status = status;
		this.statusImage = statusImage;
		this.statusImageUpdatedAt = statusImageUpdatedAt;
		this.statusContent = statusContent;
		this.statusContentUpdatedAt = statusContentUpdatedAt;
		this.unit = unit;
		this.countSold = countSold;
		this.tags = tags;
		this.pagesId = pagesId;
		this.faqId = faqId;
		this.filePrint = filePrint;
		this.sizeExtend = sizeExtend;
		this.categories = categories;
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

	@Column(name = "type_id")
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "provider_code", length = 45)
	public String getProviderCode() {
		return this.providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	@Column(name = "giftcode", length = 50)
	public String getGiftcode() {
		return this.giftcode;
	}

	public void setGiftcode(String giftcode) {
		this.giftcode = giftcode;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@NotEmpty(message = "Tên sản phẩm không được để trống")
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "desc")
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "keyword")
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "short_name")
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	@Column(name = "published_at")
	public Integer getPublishedAt() {
		return this.publishedAt;
	}

	public void setPublishedAt(Integer publishedAt) {
		this.publishedAt = publishedAt;
	}

	@Column(name = "weight", precision = 5, scale = 3)
	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "social", length = 200)
	public String getSocial() {
		return this.social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	@Column(name = "search", length = 200)
	public String getSearch() {
		return this.search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Column(name = "price_ref")
	public Integer getPriceRef() {
		return this.priceRef;
	}

	public void setPriceRef(Integer priceRef) {
		this.priceRef = priceRef;
	}

	@Column(name = "price_design")
	public Integer getPriceDesign() {
		return this.priceDesign;
	}

	public void setPriceDesign(Integer priceDesign) {
		this.priceDesign = priceDesign;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "status_image")
	public Integer getStatusImage() {
		return this.statusImage;
	}

	public void setStatusImage(Integer statusImage) {
		this.statusImage = statusImage;
	}

	@Column(name = "status_image_updated_at")
	public Integer getStatusImageUpdatedAt() {
		return this.statusImageUpdatedAt;
	}

	public void setStatusImageUpdatedAt(Integer statusImageUpdatedAt) {
		this.statusImageUpdatedAt = statusImageUpdatedAt;
	}

	@Column(name = "status_content")
	public Integer getStatusContent() {
		return this.statusContent;
	}

	public void setStatusContent(Integer statusContent) {
		this.statusContent = statusContent;
	}

	@Column(name = "status_content_updated_at")
	public Integer getStatusContentUpdatedAt() {
		return this.statusContentUpdatedAt;
	}

	public void setStatusContentUpdatedAt(Integer statusContentUpdatedAt) {
		this.statusContentUpdatedAt = statusContentUpdatedAt;
	}

	@Column(name = "unit", length = 50)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "count_sold")
	public Integer getCountSold() {
		return this.countSold;
	}

	public void setCountSold(Integer countSold) {
		this.countSold = countSold;
	}

	@Column(name = "tags", length = 65535)
	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "pages_id")
	public String getPagesId() {
		return this.pagesId;
	}

	public void setPagesId(String pagesId) {
		this.pagesId = pagesId;
	}

	@Column(name = "faq_id")
	public String getFaqId() {
		return this.faqId;
	}

	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}

	@Column(name = "file_print")
	public String getFilePrint() {
		return this.filePrint;
	}

	public void setFilePrint(String filePrint) {
		this.filePrint = filePrint;
	}

	@Column(name = "size_extend")
	public String getSizeExtend() {
		return this.sizeExtend;
	}

	public void setSizeExtend(String sizeExtend) {
		this.sizeExtend = sizeExtend;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_category", catalog = "printgo", joinColumns = {
			@JoinColumn(name = "product_id", nullable = false, updatable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "category_id", nullable = false, updatable = false) })
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public static String setFileName(String name) throws NoSuchAlgorithmException {
		
		String nameUniqiue = name + AmazonUtil.dateToInt();
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(nameUniqiue.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
		return sb.toString();
	}
}
