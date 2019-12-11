
package vn.printgo.pagination;

import java.io.Serializable;

public class PageCozi implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5302862214964526552L;
	public int size;
    public int totalElements;
    public int totalPages;
    public int currentPage;
    
    public PageCozi(int size, int totalElements, int totalPages, int currentPage) {
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public void setTotalElements( int ele) {
        this.totalElements = ele;
    }
    
    public void setTotalPages(int ttp) {
        this.totalPages = ttp;
    }
    
    public void setPage(int page) {
        this.currentPage = page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPage() {
        return currentPage;
    }
}
