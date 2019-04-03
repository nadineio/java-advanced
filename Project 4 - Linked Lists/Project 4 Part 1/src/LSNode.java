public class LSNode {
    // Implements String nodes for a Linked Stack
    
    private String info;
    private LSNode link;

    public LSNode(String info) {
        this.info = info;
        this.link = null;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }

    public void setLink(LSNode link) {
        this.link = link;
    }

    public LSNode getLink() {
        return link;
    }
    
}