
package vn.printgo.pagination;

import java.util.List;

/*
{
    "embedded": {
        [
            ...data...
        ]
    },
    "page": {
        "size": 20,
        "totalElements": 100,
        "totalPages": 5,
        "currentPage": 0
    }
}
*/
public class Ipage <T> {
    
    private PageCozi page;
    private List<T> embedded;
    
    private int size;
    private int totalElements;
    private int currentPage;
    private int totalPage;
    
    public Ipage(int limit, int totalElements, int curentPage, List<T> embeddeds) {
        
        this.size = limit;
        this.totalElements = totalElements;
        this.currentPage = curentPage;
        this.embedded = embeddeds;
        if (totalElements % size == 0) {
            this.totalPage = totalElements/size;
        } else {
            this.totalPage = (totalElements/size) + 1;
        }
        
        this.setPage();
    }
    
    public void setPage() {
        PageCozi page = new PageCozi(this.size, this.totalElements, totalPage, this.currentPage);
        this.page = page;
    }
    
    public PageCozi getPage() {
        return page;
    }
    
    public List<T> getEmbedded() {
        return embedded;
    }
}
